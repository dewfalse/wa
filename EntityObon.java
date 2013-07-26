package wa;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityObon extends EntityHanging {

	private static final int ITEM_INDEX = 28;
	private static final int DIR_INDEX = 29;

	public EntityObon(World par1World) {
		super(par1World);
	}

	public EntityObon(World par1World, int par2, int par3, int par4, int par5) {
		super(par1World, par2, par3, par4, par5);
		this.setDirection(par5);
	}

	@Override
	public void setDead() {
		super.setDead();
	}

	@Override
	public boolean onValidSurface() {
		return true;
	}

	@Override
	protected void entityInit() {
		this.getDataWatcher().addObjectByDataType(ITEM_INDEX, 5);// 5:ItemStack
		this.getDataWatcher().addObject(DIR_INDEX, 0);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		if (this.getDisplayedItem() != null) {
			par1NBTTagCompound.setCompoundTag("Item", this.getDisplayedItem().writeToNBT(new NBTTagCompound()));
			par1NBTTagCompound.setInteger("ItemRotation", this.getRotation());
		}
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("Item");

		if (nbttagcompound1 != null && !nbttagcompound1.hasNoTags()) {
			this.setDisplayedItem(ItemStack.loadItemStackFromNBT(nbttagcompound1));
			this.setItemRotation(par1NBTTagCompound.getInteger("ItemRotation"));
		}
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	@Override
	public int func_82329_d() {
		return 16;
	}

	@Override
	public int func_82330_g() {
		return 16;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (this.getDisplayedItem() == null) {
			ItemStack itemstack = par1EntityPlayer.getHeldItem();

			if (itemstack != null && !this.worldObj.isRemote) {
				this.setDisplayedItem(itemstack);

				if (!par1EntityPlayer.capabilities.isCreativeMode
						&& --itemstack.stackSize <= 0) {
					par1EntityPlayer.inventory.setInventorySlotContents(
							par1EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
			}
		} else if (!this.worldObj.isRemote) {
			this.setItemRotation(this.getRotation() + 1);
		}

		return true;
	}

	@Override
	public void dropItemStack() {
		this.entityDropItem(new ItemStack(Items.お盆), 0.0F);
		ItemStack itemstack = this.getDisplayedItem();

		if (itemstack != null) {
			itemstack = itemstack.copy();
			this.entityDropItem(itemstack, 0.0F);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		setItemRotation(getRotation() + 1);
	}

	public ItemStack getDisplayedItem() {
		return this.getDataWatcher().getWatchableObjectItemStack(ITEM_INDEX);
	}

	public void setDisplayedItem(ItemStack par1ItemStack) {
		par1ItemStack = par1ItemStack.copy();
		par1ItemStack.stackSize = 1;
		this.getDataWatcher().updateObject(ITEM_INDEX, par1ItemStack);
		this.getDataWatcher().setObjectWatched(ITEM_INDEX);
	}

	public int getRotation() {
		return this.getDataWatcher().getWatchableObjectInt(DIR_INDEX);
	}

	public void setItemRotation(int par1) {
		this.getDataWatcher().updateObject(DIR_INDEX, par1 % 360);
	}

}
