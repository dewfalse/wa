package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import wa.api.IWaSqueezingRecipe;
import wa.api.RecipeManagerWa;

import java.util.Arrays;

/**
 * Created by dew on 2015/11/11.
 */
public abstract class TileEntitySqueezerBase extends TileEntity implements ISidedInventory {

    public FluidTankEx productTank = new FluidTankEx(10000);

    // 材料投下後の日数。
    private int age = 0;
    // お酒が完成したかどうか。完成後も熟成継続なので、フラグやレシピIDを保持しておく。
    private boolean isAged = false;
    // 出来たお酒の評価。完成したアイテムにNBTとして書き込む。
    private int grade = 0;
    // レシピID。材料消費後も保持しておく。
    private int recipeID = 0;

    // Blockの更新チェック用
    private int lastDay = 0;
    private int lastAmount = 0;

    public int furnaceBurnTime = 0;
    public int currentItemBurnTime = 0;

	/* メイン処理部分 */

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            boolean update = false;

            // レシピ処理中である
            if (this.recipeID > 0){
                // 完了条件
                if (completeSqueeze()){
                    // インベントリ内のアイテム消費、FluidStack発生処理
                    if (onSqueeze()){
                        // 一旦リセット
                        this.resetBarrel();
                        update = true;
                    }
                } else {
                    // 材料スロットに材料が残っているかチェック＆燃料スロットに燃料があるかチェック
                    if (this.onProgress()){
                        update = true;
                    } else {
                        // 揃わない場合はレシピIDや経過時間をリセット
                        this.resetBarrel();
                        update = true;
                    }
                }
            } else {
                if(startSqueeze()) {
                    update = true;
                }
                else {
                    // 何もなくても燃焼カウントを進める
                    if(this.furnaceBurnTime > 0) {
                        --this.furnaceBurnTime;
                        update = true;
                    }
                }
            }

            // 次に液体取り出し用スロットの処理
            if (this.fillFluidContainerItem()){
                update = true;
            }

            if (lastAmount != this.productTank.getFluidAmount()){
                lastAmount = this.productTank.getFluidAmount();
                update = true;
            }

            if (update){
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    // 材料を消費する
    private boolean consumeIngredient() {
        // レシピIDを取得
        int id = this.getCurrentRecipeID();
        if (id <= 0) return false;

        // レシピ自体を取得
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return false;

        // 0-8が材料スロット
        for(int i = 0; i < 9; i++) {
            ItemStack input = this.getStackInSlot(i);
            boolean a = recipe.matches(input);
            if (a) {
                // 材料があれば消費する
                input.stackSize -= 1;
                if (input.stackSize == 0) {
                    this.setInventorySlotContents(i, null);
                } else {
                    this.setInventorySlotContents(i, input);
                }
                return true;
            }
        }
        return false;
    }

    // 着火済みで有効なレシピがあるなら開始する
    private boolean startSqueeze() {
        // レシピIDを取得
        int id = this.getCurrentRecipeID();
        if (id <= 0) return false;

        // レシピ自体を取得
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(id);
        if (recipe == null) return false;

        // 燃料がなく燃焼中でもないならNG
        if(this.furnaceBurnTime == 0 && TileEntityFurnace.getItemBurnTime(this.itemstacks[getFuelSlotIndex()]) <= 0) return false;

        // 材料スロットに材料があるか
        boolean resource = false;

        // 0-8が材料スロット
        for(int i : getInputSlotIndex()) {
            ItemStack input = this.getStackInSlot(i);
            boolean a = recipe.matches(input);
            if (a) {
                resource = true;
                break;
            }
        }

        // 材料がない
        if(!resource) return false;

        // 液体タンクに別の液体が充填されている
        if(!this.productTank.isEmpty() && this.productTank.getFluidAmount() > 0 && !recipe.getOutput().isFluidEqual(this.productTank.getFluid())) return false;

        if(this.productTank.isFull()) return false;

        // 未着火だが燃料がある
        if(this.furnaceBurnTime == 0 && TileEntityFurnace.getItemBurnTime(this.itemstacks[getFuelSlotIndex()]) > 0) {
            this.burnFuelItem();
        }

        this.recipeID = id;
        consumeIngredient();

        // グレード設定
        this.setGrade(recipe.getOutputGrade(this, 1));
        return true;
    }

    // 燃料があれば燃やす
    private void burnFuelItem() {
        // 未着火
        if(this.furnaceBurnTime == 0) {
            // 燃焼時間を更新
            this.currentItemBurnTime = this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(this.itemstacks[getFuelSlotIndex()]);

            // 燃料を減らす
            if (this.itemstacks[getFuelSlotIndex()] != null) {
                --this.itemstacks[getFuelSlotIndex()].stackSize;
                if (this.itemstacks[getFuelSlotIndex()].stackSize == 0) {
                    this.itemstacks[getFuelSlotIndex()] = itemstacks[getFuelSlotIndex()].getItem().getContainerItem(itemstacks[getFuelSlotIndex()]);
                }
            }
        }
    }

    // ゲーム内日数。int上限に達すると0にもどる
    public int getDay() {
        long day = (worldObj.getWorldInfo().getWorldTime() / 24000L) + 1;
        if (day > Integer.MAX_VALUE)
            day -= Integer.MAX_VALUE;
        return (int) day;
    }

    /*
     * 材料がレシピに適合しているか、処理中はTick毎に監視している。
     * 取り出してしまったなど、適合しなくなったときにパラメータをリセットする。
     */
    protected boolean onProgress(){
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return false;

        // 燃焼カウントを進める
        if(this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        else {
            this.burnFuelItem();
        }
        if(this.isBurning()) {
            this.age++;
        }

        return true;
    }

    /*
     * 圧搾が完了できるかの判定。
     * ageが規定数に達したかを見る。
     */
    protected boolean completeSqueeze(){
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return false;

        return this.age > recipe.getSqueezingTime();
    }

    /*
     * 完成時の処理。
     * 液体の生成を行う。
     * 変化成功時にtrueを返す
     */
    protected boolean onSqueeze(){
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return false;

        // 液体タンクに注入
        FluidStack output = recipe.getOutput();
        this.fill(ForgeDirection.UNKNOWN, output, true);

        this.setAgingTime(0);
        return true;
    }

    /*
     * 材料アイテムからレシピIDを見る。
     * 一致したレシピがあったら、レシピIDを取得して醸造開始。
     */
    protected int getCurrentRecipeID(){
        int id = -1;
        for(int i : getInputSlotIndex()) {
            ItemStack input = this.getStackInSlot(i);
            if (input == null) continue;

            id = RecipeManagerWa.squeezingRegistry.getRecipeID(input);
            if(id != -1) break;
        }
        return id;
    }

    // 樽を空にして、ブロック更新をする
    protected void resetBarrel(){
        this.setAged(false);
        this.setAgingTime(0);
        this.setGrade(0);
        this.setRecipeID(0);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    // タンク用のスロットの処理
    protected boolean fillFluidContainerItem(){
        ItemStack emptyContainer = this.getStackInSlot(getEmptyContainerSlotIndex());
        ItemStack output = this.getStackInSlot(getOutputSlotIndex());
        FluidStack fluid = this.productTank.getFluid();
        if (emptyContainer == null || fluid == null){
            return false;
        }

        // 空容器かの判定
        if (FluidContainerRegistry.isEmptyContainer(emptyContainer)) {
            // 充填
            ItemStack ret = FluidContainerRegistry.fillFluidContainer(fluid, emptyContainer);

            boolean flag1 = ret != null;
            boolean flag2 = false;
            if (output == null) flag2 = true;
            else if (this.isItemStackable(ret, output)) flag2 = true;

            if (flag1 && flag2) {
                // 充填アイテムにグレードを付与
                NBTTagCompound tag = ret.getTagCompound();
                if (tag == null){
                    tag = new NBTTagCompound();
                }
                tag.setShort("Grade", (short) this.grade);
                ret.setTagCompound(tag);

                // 液体タンク、インベントリの増減処理
                this.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.getContainerCapacity(ret), true);
                this.incrStackInSlot(getOutputSlotIndex(), ret);
                if (this.decrStackSize(getEmptyContainerSlotIndex(), 1) == null) {
                    this.markDirty();
                }
                return true;
            }
        }
        return false;
    }

    // 判定用
    private static boolean isItemStackable(ItemStack target, ItemStack current) {
        if (target == null || current == null) return false;

        if (target.getItem() == current.getItem() && target.getItemDamage() == current.getItemDamage()) {
            return (current.stackSize + target.stackSize) <= current.getMaxStackSize();
        }

        return false;
    }

    // IInventoryにはこのメソッドがないので...
    private void incrStackInSlot(int i, ItemStack input) {
        if (i < this.getSizeInventory() && input != null &&  this.itemstacks[i] != null) {
            if (this.itemstacks[i].getItem() == input.getItem()
                    && this.itemstacks[i].getItemDamage() == input.getItemDamage()) {
                this.itemstacks[i].stackSize += input.stackSize;
                if (this.itemstacks[i].stackSize > this.getInventoryStackLimit()) {
                    this.itemstacks[i].stackSize = this.getInventoryStackLimit();
                }
            }
        } else {
            this.setInventorySlotContents(i, input);
        }
    }

	/* パケッヨ */

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.itemstacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.itemstacks.length) {
                this.itemstacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.itemstacks[1]);

        this.age = par1NBTTagCompound.getInteger("Age");
        this.grade = par1NBTTagCompound.getInteger("Grade");
        this.recipeID = par1NBTTagCompound.getInteger("ID");
        this.isAged = par1NBTTagCompound.getBoolean("IsAged");

        this.productTank = new FluidTankEx(10000);
        if (par1NBTTagCompound.hasKey("productTank")) {
            this.productTank.readFromNBT(par1NBTTagCompound.getCompoundTag("productTank"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Age", this.age);
        par1NBTTagCompound.setInteger("Grade", this.grade);
        par1NBTTagCompound.setInteger("ID", this.recipeID);
        par1NBTTagCompound.setBoolean("IsAged", this.isAged);

        NBTTagCompound tank = new NBTTagCompound();
        this.productTank.writeToNBT(tank);
        par1NBTTagCompound.setTag("productTank", tank);

        par1NBTTagCompound.setShort("BurnTime", (short) this.furnaceBurnTime);

        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.itemstacks.length; ++i) {
            if (this.itemstacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.itemstacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }

	/* getter, setter */

    public int getAgingTime() {
        return this.age;
    }

    public void setAgingTime(int par1) {
        this.age = par1;
    }

    public boolean getAged() {
        return this.isAged;
    }

    public void setAged(boolean par1) {
        this.isAged = par1;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int par1) {
        this.grade = par1;
    }

    public int getRecipeID() {
        return this.recipeID;
    }

    public void setRecipeID(int par1) {
        this.recipeID = par1;
    }

    public int getAgingProgress(int i) {
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return 0;
        int ret = this.age * i / recipe.getSqueezingTime();
        return ret;
    }

    public int getSqueezingTime() {
        IWaSqueezingRecipe recipe = RecipeManagerWa.squeezingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return 0;
        return recipe.getSqueezingTime();
    }

    public int getMetadata() {
        return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    // Container更新用
    public int getUpper() {
        int i = this.age;
        int get = i >>> 4;
        return get;
    }

    public int getUnder() {
        int i = this.age;
        int get = i & 15;
        return get;
    }

    public void setUpper(int i) {
        int current = this.age & 15;
        int get = i << 4;
        get += current;
        this.age = get;
    }

    public void setUnder(int i) {
        int currentUpper = this.age >>> 4;
        int cur = currentUpper << 4;
        int get = i & 15;
        get += cur;
        this.age = get;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * p_145955_1_ / this.currentItemBurnTime;
    }

    public void getGuiFluidUpdate(int id, int val) {
        if (id == 2)// ID
        {
            if (productTank.getFluid() == null) {
                productTank.setFluidById(val);
            } else {
                int amo = productTank.getFluidAmount();
                productTank.setFluidById(val);
            }
        } else if (id == 3)// amount
        {
            if (productTank.getFluid() == null) {
                productTank.setFluid((FluidStack) null);
            } else {
                productTank.getFluid().amount = val;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getFluidAmountScaled(int par1) {
        return this.productTank.getFluidAmount() * par1 / 10000;
    }

	/* Fluidの取扱い */

	/*
	 * 以下のメソッドはIFluidHandlerのメソッドとほぼ同様。
	 * パイプを使って液体を抜いたり、無理矢理流し込んだりさせないため、意図的にIFluidHandleの実装は見送り。
	 */

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !this.isAged) {
            return null;
        }
        if (productTank.getFluidType() == resource.getFluid()) {
            FluidStack ret = productTank.drain(resource.amount, doDrain);
            return ret;
        }
        return null;
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return this.productTank.drain(maxDrain, doDrain);
    }

    // 外部からの液体の受け入れ
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource == null || resource.getFluid() == null) {
            return 0;
        }

        FluidStack current = this.productTank.getFluid();
        FluidStack resourceCopy = resource.copy();
        if (current != null && current.amount > 0 && !current.isFluidEqual(resourceCopy)) {
            return 0;
        }

        int i = 0;
        int used = this.productTank.fill(resourceCopy, doFill);
        resourceCopy.amount -= used;
        i += used;

        return i;
    }

    // 空でないと&醸造未完了でないと受け入れない
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return !this.isAged && fluid != null && this.productTank.isEmpty();
    }

    // 醸造が完了するまで引き抜けないようにする
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return this.isAged;
    }

    int[] getInputSlotIndex() {
        return new int[] {0,1,2,3,4,5,6,7,8};
    }
    int getFuelSlotIndex() {
        return 9;
    }

    int getEmptyContainerSlotIndex() {
        return 10;
    }

    int getOutputSlotIndex() {
        return 11;
    }

	/* ISidedInventory */

    /*
     * 0-8 : 材料
     * 9   : 燃料
     * 10  : 空容器
     * 11  : 完成品
     */
    protected int[] slotsTop(){
        return new int[] {0,1,2,3,4,5,6,7,8,10};
    }

    protected int[] slotsBottom(){
        return new int[] {11};
    }

    protected int[] slotsSides(){
        return new int[] {9};
    }

    public ItemStack[] itemstacks = new ItemStack[getSizeInventory()];

    @Override
    public int getSizeInventory() {
        return 12;
    }

    // インベントリ内の任意のスロットにあるアイテムを取得
    @Override
    public ItemStack getStackInSlot(int par1) {
        return par1 < this.getSizeInventory() ? this.itemstacks[par1] : null;
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.itemstacks[par1] != null) {
            ItemStack itemstack;

            if (this.itemstacks[par1].stackSize <= par2) {
                itemstack = this.itemstacks[par1];
                this.itemstacks[par1] = null;
                return itemstack;
            } else {
                itemstack = this.itemstacks[par1].splitStack(par2);

                if (this.itemstacks[par1].stackSize == 0) {
                    this.itemstacks[par1] = null;
                }

                return itemstack;
            }
        } else
            return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.itemstacks[par1] != null) {
            ItemStack itemstack = this.itemstacks[par1];
            this.itemstacks[par1] = null;
            return itemstack;
        } else
            return null;
    }

    // インベントリ内のスロットにアイテムを入れる
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

        if (par1 > this.getSizeInventory()) {
            par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。
        }

        this.itemstacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    // インベントリの名前
    @Override
    public abstract String getInventoryName();

    // 多言語対応かどうか
    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    // インベントリ内のスタック限界値
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    // par1EntityPlayerがTileEntityを使えるかどうか
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer
                .getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    // 材料スロットは何でもOK。液体タンク用のスロットは制限を掛ける。
    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        if(Arrays.asList(getInputSlotIndex()).contains(i)) {
            // 材料スロットは何でもOK
            return true;
        } else if (i == getFuelSlotIndex()) {
            // 燃料スロットは燃料のみ
            return TileEntityFurnace.isItemFuel(stack);
        } else if (i == getEmptyContainerSlotIndex()) {
            // 空容器スロットは空容器のみ
            return FluidContainerRegistry.isEmptyContainer(stack);
        }

        return false;
    }

    // ホッパーにアイテムの受け渡しをする際の優先度
    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return par1 == 0 ? slotsBottom() : (par1 == 1 ? slotsTop() : slotsSides());
    }

    // ホッパーからアイテムを入れられるかどうか
    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    // 隣接するホッパーにアイテムを送れるかどうか
    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }
}
