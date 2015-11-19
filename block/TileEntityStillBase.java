package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import wa.api.IWaDistillingRecipe;
import wa.api.RecipeManagerWa;
// 蒸留器
// マルチブロックで加熱用のブロックと冷却用のブロックの２ブロックからなる
// 動作するには加熱用のブロックの下に炎が必要
// 動作するには冷却用のブロックの下に氷が必要

/**
 * Created by dew on 2015/11/11.
 */
public abstract class TileEntityStillBase extends TileEntity implements ISidedInventory {

    public FluidTankEx productTank = new FluidTankEx(1000);

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

	/* メイン処理部分 */

    @Override
    public void updateEntity() {
        int metadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        if(metadata == 1) {
            // 蒸留元なので蒸留処理する

            if (!this.worldObj.isRemote) {
                boolean update = false;
                if (this.isConfortablePlace()){
                    // レシピ処理中である
                    if (this.recipeID > 0){
                        // 完了条件
                        if (completeDistill()){
                            // インベントリ内のアイテム消費、FluidStack発生処理
                            if (onDistill()){
                                update = true;
                                this.setAged(true);
                                this.setAgingTime(0);
                            }
                        } else {
                            // 液体素材タンクに必要量が残っているかチェック＆材料スロットのアイテムを液体素材タンクに注ぐ
                            if (this.onProgress()){
                                this.age++;
                            } else {
                                // 揃わない場合はレシピIDや経過時間をリセット
                                this.resetBarrel();
                            }
                        }
                    } else {
                        if(startDistill()) {
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
    }

    // 対になる蒸留元・受けフラスコのある方向
    public ForgeDirection getPairDir() {
        int metadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);

        // 0は接続されてない
        if(metadata == 1) {
            // メタデータが1なら蒸留元
            for (ForgeDirection d : new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}) {
                ForgeDirection o = d.getOpposite();
                Block block = this.worldObj.getBlock(this.xCoord + o.offsetX, this.yCoord + o.offsetY, this.zCoord + o.offsetZ);
                int meta = this.worldObj.getBlockMetadata(this.xCoord + o.offsetX, this.yCoord + o.offsetY, this.zCoord + o.offsetZ);
                if (block != Blocks.still) continue;
                if (meta == 2 && d == ForgeDirection.NORTH) return o;
                if (meta == 3 && d == ForgeDirection.EAST) return o;
                if (meta == 4 && d == ForgeDirection.SOUTH) return o;
                if (meta == 5 && d == ForgeDirection.WEST) return o;
            }
        }
        if(metadata == 2) return ForgeDirection.NORTH;
        if(metadata == 3) return ForgeDirection.EAST;
        if(metadata == 4) return ForgeDirection.SOUTH;
        if(metadata == 5) return ForgeDirection.WEST;
        return ForgeDirection.UNKNOWN;
    }

    // 蒸留開始。レシピを確定する
    private boolean startDistill() {
        int id = this.getCurrentRecipeID();
        if (id > 0){
            this.recipeID = id;

            // 最初はグレード1000で固定
            this.setGrade(1000);

            return true;
        }
        return false;
    }

    private boolean findWater() {
        for(ForgeDirection d : new ForgeDirection[]{ForgeDirection.UP, ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}) {
            Block block = this.worldObj.getBlock(this.xCoord + d.offsetX, this.yCoord + d.offsetY, this.zCoord + d.offsetZ);
            if(block == Blocks.water) {
                return true;
            }
            if(block == Blocks.flowing_water) {
                return true;
            }
        }
        return false;
    }

    private boolean findIce() {
        for(ForgeDirection d : new ForgeDirection[]{ForgeDirection.UP, ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}) {
            Block block = this.worldObj.getBlock(this.xCoord + d.offsetX, this.yCoord + d.offsetY, this.zCoord + d.offsetZ);
            if(block == Blocks.ice) {
                return true;
            }
            if(block == Blocks.packed_ice) {
                return true;
            }
        }
        return false;
    }

    private boolean findSpiritLamp() {
        ForgeDirection d = ForgeDirection.DOWN;
        Block block = this.worldObj.getBlock(this.xCoord + d.offsetX, this.yCoord + d.offsetY, this.zCoord + d.offsetZ);
        if(block == Blocks.spiritLamp) {
            TileEntitySpiritLamp tile = (TileEntitySpiritLamp)this.worldObj.getTileEntity(this.xCoord + ForgeDirection.DOWN.offsetX, this.yCoord + ForgeDirection.DOWN.offsetY, this.zCoord + ForgeDirection.DOWN.offsetZ);
            if(tile != null && tile.getIgnited()) {
                return true;
            }
        }
        return false;
    }

    private boolean findFire() {
        for(ForgeDirection d : new ForgeDirection[]{ForgeDirection.UP, ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}) {
            Block block = this.worldObj.getBlock(this.xCoord + d.offsetX, this.yCoord + d.offsetY, this.zCoord + d.offsetZ);
            if(block == Blocks.fire) {
                return true;
            }
            if(block == Blocks.lava) {
                return true;
            }
            if(block == Blocks.flowing_lava) {
                return true;
            }
        }
        return false;
    }

    // 蒸留可能な場所に置かれているか
    protected boolean isConfortablePlace(){
        if(findIce() || findWater()) {
            // 氷か水があれば動作しない
            return false;
        }
        else if(findFire()) {
            // 周囲に溶岩・炎があれば動作する
            return true;
        }
        else if(findSpiritLamp()) {
            // 真下が着火済みアルコールランプなら動作する
            return true;
        }
        return false;
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
        // 材料スロットのアイテムが液体素材と同じ液体のコンテナだったら液体素材に追加する

        IWaDistillingRecipe recipe = RecipeManagerWa.distillingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return false;

        for(int i : new int[]{0,1}) {
            ItemStack input = this.getStackInSlot(i);

            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(input);

            // レシピに適合する液体コンテナなアイテムなら液体素材に追加
            if(recipe.getInput().isFluidEqual(input)) {
                if(productTank.getCapacity() - productTank.getFluidAmount() >= fluid.amount) {
                    this.fill(ForgeDirection.UNKNOWN, fluid, true);
                    input.stackSize -= 1;
                    if(input.stackSize == 0) {
                        this.setInventorySlotContents(i, null);
                    }
                    else {
                        this.setInventorySlotContents(i, input);
                    }

                    // 材料のグレード＝蒸留液のグレード
                    // グレードの低い材料を追加すると蒸留液のグレードも下がる
                    int grade = 0;
                    if(input.hasTagCompound()) {
                        NBTTagCompound nbt = input.getTagCompound();
                        if(nbt != null) {
                            grade = nbt.getShort("Grade");
                            this.setGrade(Math.min(this.getGrade(), grade));
                        }
                    }
                }
            }
        }

        // 液体素材がレシピの必要量より多ければ継続OK
        return productTank.getFluidAmount() >= recipe.getInputRequire();
    }

    /*
     * 蒸留が完了できるかの判定。
     * ageが規定数に達したか
     */
    protected boolean completeDistill(){
        IWaDistillingRecipe recipe = RecipeManagerWa.distillingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return false;

        return this.age > recipe.getDistillTime();
    }

    /*
     * 完成時の処理。
     * 材料の消費、液体の生成、Gradeの判定を行う。
     * 変化成功時にtrueを返す
     */
    protected boolean onDistill(){
        // 蒸留完了。液体素材をレシピ分量だけ消費して蒸留液タンクに注ぎ込む
        IWaDistillingRecipe recipe = RecipeManagerWa.distillingRegistry.getRecipeFromID(recipeID);
        if(recipe == null) return false;

        // 液体素材消費
        this.drain(ForgeDirection.UNKNOWN, recipe.getInputRequire(), true);

        ForgeDirection pairDir = getPairDir();
        // 完成したら受けフラスコのタンクに液体をねじ込む
        if(pairDir != ForgeDirection.UNKNOWN) {
            // 受けフラスコ
            TileEntityStill tile = (TileEntityStill)this.worldObj.getTileEntity(this.xCoord + pairDir.offsetX, this.yCoord + pairDir.offsetY, this.zCoord + pairDir.offsetZ);
            FluidStack output = recipe.getOutput();
            if(findFire()) {
                // 周囲に炎・溶岩がある場合は熱すぎて効率ダウン
                output.amount = output.amount / 2;
            }
            tile.fill(pairDir.getOpposite(), output, true);
        }

        this.setAgingTime(0);

        return true;
    }

    /*
     * 材料アイテムからレシピIDを見る。
     * 一致したレシピがあったら、レシピIDを取得して醸造開始。
     */
    protected int getCurrentRecipeID(){
        for(int i : new int[]{0,1}) {
            ItemStack input = this.getStackInSlot(i);
            if (input == null) continue;

            // 液体コンテナなアイテム以外はNG
            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(input);
            if(fluid == null) continue;
            return RecipeManagerWa.distillingRegistry.getRecipeID(fluid, null);
        }

        return -1;
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
        // 受けフラスコの方向
        ForgeDirection pairDir = getPairDir();
        if(pairDir == ForgeDirection.UNKNOWN) {
            return false;
        }

        ItemStack in = this.getStackInSlot(2);
        ItemStack current = this.getStackInSlot(3);

        // 隣接した蒸留器ブロック（受けフラスコ扱い）に蒸留液は保存されている
        TileEntityStill tile = (TileEntityStill)this.worldObj.getTileEntity(this.xCoord + pairDir.offsetX, this.yCoord + pairDir.offsetY, this.zCoord + pairDir.offsetZ);
        FluidStack fluid = tile.productTank.getFluid();
        if (in == null || fluid == null){
            return false;
        }

        // 空容器かの判定
        if (FluidContainerRegistry.isEmptyContainer(in)) {
            // 充填
            ItemStack ret = FluidContainerRegistry.fillFluidContainer(fluid, in);

            boolean flag1 = ret != null;
            boolean flag2 = false;
            if (current == null) flag2 = true;
            else if (this.isItemStackable(ret, current)) flag2 = true;

            if (flag1 && flag2) {
                // 充填アイテムにグレードを付与
                NBTTagCompound tag = ret.getTagCompound();
                if (tag == null){
                    tag = new NBTTagCompound();
                }
                tag.setShort("Grade", (short) this.grade);
                ret.setTagCompound(tag);

                // 受けフラスコの液体タンク、インベントリの増減処理
                tile.drain(pairDir.getOpposite(), FluidContainerRegistry.getContainerCapacity(ret), true);
                this.incrStackInSlot(3, ret);
                if (this.decrStackSize(2, 1) == null) {
                    this.markDirty();
                }
                tile.markDirty();
                worldObj.markBlockForUpdate(this.xCoord + pairDir.offsetX, this.yCoord + pairDir.offsetY, this.zCoord + pairDir.offsetZ);
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

        this.age = par1NBTTagCompound.getInteger("Age");
        this.grade = par1NBTTagCompound.getInteger("Grade");
        this.recipeID = par1NBTTagCompound.getInteger("ID");
        this.isAged = par1NBTTagCompound.getBoolean("IsAged");

        this.productTank = new FluidTankEx(1000);
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

        NBTTagCompound sink = new NBTTagCompound();
        this.productTank.writeToNBT(sink);
        par1NBTTagCompound.setTag("productTank", sink);

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
        IWaDistillingRecipe recipe = RecipeManagerWa.distillingRegistry.getRecipeFromID(recipeID);
        if (recipe == null) return 0;
        int ret = this.age * i / recipe.getDistillTime();
        return ret;
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
        if (id == 4)// ID
        {
            ForgeDirection d = this.getPairDir();
            if(d != ForgeDirection.UNKNOWN) {
                TileEntityStill tileConnected = (TileEntityStill) worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);
                if (tileConnected.productTank.getFluid() == null) {
                    tileConnected.productTank.setFluidById(val);
                } else {
                    int amo = tileConnected.productTank.getFluidAmount();
                    tileConnected.productTank.setFluidById(val);
                }
            }
        } else if (id == 5)// amount
        {
            ForgeDirection d = this.getPairDir();
            if(d != ForgeDirection.UNKNOWN) {
                TileEntityStill tileConnected = (TileEntityStill) worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);
                if (tileConnected.productTank.getFluid() == null) {
                    tileConnected.productTank.setFluid((FluidStack) null);
                } else {
                    tileConnected.productTank.getFluid().amount = val;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getFluidAmountScaled(int par1) {
        return this.productTank.getFluidAmount() * par1 / 1000;
    }

	/* Fluidの取扱い */

	/*
	 * 以下のメソッドはIFluidHandlerのメソッドとほぼ同様。
	 * パイプを使って液体を抜いたり、無理矢理流し込んだりさせないため、意図的にIFluidHandleの実装は見送り。
	 */

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null) {
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

        // 外部から注入された場合は効率チェック
        int i = 0;
        if(from != ForgeDirection.UNKNOWN) {
            if(findFire() || findSpiritLamp()) {
                // 溶岩・炎・アルコールランプがあれば効率-100%
                return 0;
            }
            else if(findIce()) {
                // 氷があれば効率100%
            }
            else if(findWater()) {
                // 水があれば効率70%
                resourceCopy.amount = (int) (resourceCopy.amount * 0.7);
            }
            else {
                // 何もなければ効率20%
                resourceCopy.amount = (int) (resourceCopy.amount * 0.7);
            }
        }

        int used = this.productTank.fill(resourceCopy, doFill);
        resourceCopy.amount -= used;
        i += used;
        return i;
    }

    // 空でないと受け入れない
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid != null && this.productTank.isEmpty();
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

	/* ISidedInventory */

    /*
     * 0,1 : 材料
     * 2,3 : タンクから液体を取り出すためのスロット
     */
    protected int[] slotsTop(){
        return new int[] {0,1,2};
    }

    protected int[] slotsBottom(){
        return new int[] {3};
    }

    protected int[] slotsSides(){
        return new int[] {0,1,2,3};
    }

    public ItemStack[] itemstacks = new ItemStack[getSizeInventory()];

    @Override
    public int getSizeInventory() {
        return 4;
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

    // 材料スロットは液体のみOK。取り出しスロットは空容器のみOK。
    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        if (i == 0 || i == 1) {
            return FluidContainerRegistry.isFilledContainer(stack);
        } else {
            return i == 2 ? FluidContainerRegistry.isEmptyContainer(stack) : false;
        }
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

    public boolean connected() {
        int metadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        return metadata == 1;
    }
}
