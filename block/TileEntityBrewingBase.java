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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import wa.api.IWaBrewingRecipe;
import wa.api.RecipeManagerWa;

public abstract class TileEntityBrewingBase extends TileEntity implements ISidedInventory{
	
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
		if (!this.worldObj.isRemote) {
			boolean update = false;
			// 完成後の処理
			if (this.isAged){
				if (productTank.isEmpty()){
					// リセット処理
					this.resetBarrel();
				} else {
					// まだ中身がある間は、Gradeが1日1ずつ増える
					if (lastDay != this.getDay()){
						if (this.isConfortablePlace() && this.recipeID > 0)
							this.grade++;
						lastDay = this.getDay();
					}
				}
			} else if (this.isConfortablePlace()){
				// レシピ処理中である
				if (this.recipeID > 0){
					// 完了条件
					if (completeBrewing()){
						// インベントリ内のアイテム消費、FluidStack発生処理
						if (onBrewing()){
							update = true;
							this.setAged(true);
							this.setAgingTime(0);
						}
					} else {
						// インベントリの中身が揃っているかどうか
						if (this.onProgress()){
							this.age++;
						} else {
							// 揃わない場合はレシピIDや経過時間をリセット
							this.resetBarrel();
						}
					}
				} else {
					int id = this.getCurrentRecipeID();
					if (id > 0){
						this.recipeID = id;
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
	
	// 醸造可能な場所に置かれているか
	protected boolean isConfortablePlace(){
		// 暫定的に、日光のみ見る
		return !worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
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
		IWaBrewingRecipe recipe = RecipeManagerWa.brewingRegistry.getRecipeFromID(recipeID);
		if (recipe == null) return false;
		
		ItemStack input = this.getStackInSlot(0);
		ItemStack second = this.getStackInSlot(1);
		ItemStack[] checks = {input, second};
		
		return recipe.matches(checks);
	}
	
	/*
	 * 醸造か完了できるかの判定。
	 * ageが規定数に達したかを見ているが、オーバーライドして条件を追加しても良いと思う。
	 */
	protected boolean completeBrewing(){
		IWaBrewingRecipe recipe = RecipeManagerWa.brewingRegistry.getRecipeFromID(recipeID);
		if (recipe == null) return false;
		
		ItemStack input = this.getStackInSlot(0);
		ItemStack second = this.getStackInSlot(1);
		ItemStack[] checks = {input, second};
		boolean a = recipe.matches(checks);
		boolean b = this.age > recipe.getBrewingTime();
		
		return a && b;
	}
	
	/*
	 * 完成時の処理。
	 * 材料の消費、液体の生成、Gradeの判定を行う。
	 * 変化成功時にtrueを返す
	 */
	protected boolean onBrewing(){
		IWaBrewingRecipe recipe = RecipeManagerWa.brewingRegistry.getRecipeFromID(recipeID);
		if (recipe == null || !this.productTank.isEmpty()) return false;
		
		FluidStack output = recipe.getOutput();
		this.fill(ForgeDirection.UNKNOWN, output, true);
		
		ItemStack slot1 = this.getStackInSlot(0);
		ItemStack slot2 = this.getStackInSlot(1);
		int i1 = 0;
		int i2 = 0;
		if (slot1 != null) i1 += slot1.stackSize;
		if (slot2 != null) i2 += slot2.stackSize;
		int grade = recipe.getOutputGrade(this, i1, i2);
		this.setGrade(grade);
		
		this.setInventorySlotContents(0, null);
		this.setInventorySlotContents(1, null);
		return true;
	}
	
	/*
	 * 材料アイテムからレシピIDを見る。
	 * 一致したレシピがあったら、レシピIDを取得して醸造開始。
	 */
	protected int getCurrentRecipeID(){
		ItemStack input = this.getStackInSlot(0);
		ItemStack second = this.getStackInSlot(1);
		if (input == null) return -1;
		
		int id = RecipeManagerWa.brewingRegistry.getRecipeID(input, second);
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
		ItemStack in = this.getStackInSlot(2);
		ItemStack current = this.getStackInSlot(3);
		FluidStack fluid = this.productTank.getFluid();
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
				
				// 液体タンク、インベントリの増減処理
				this.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.getContainerCapacity(ret), true);
				this.incrStackInSlot(3, ret);
				if (this.decrStackSize(2, 1) == null) {
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

		NBTTagCompound tank = new NBTTagCompound();
		this.productTank.writeToNBT(tank);
		par1NBTTagCompound.setTag("productTank", tank);

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
		IWaBrewingRecipe recipe = RecipeManagerWa.brewingRegistry.getRecipeFromID(recipeID);
		if (recipe == null) return 0;
		int ret = this.age * i / recipe.getBrewingTime();
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

	// 材料スロットは何でもOK。液体タンク用のスロットは制限を掛ける。
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0 || i == 1){
			return true;
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

}
