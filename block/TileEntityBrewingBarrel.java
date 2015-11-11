package wa.block;

import net.minecraft.block.Block;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import wa.item.Items;

public class TileEntityBrewingBarrel extends TileEntity implements IInventory {

	public ItemStack[] contents = new ItemStack[7];
	public int processTime;
	public int progressTime;
	public int waterLevel;
	public static final String invName = "inv.brewingBarrel";

	public TileEntityBrewingBarrel() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int getSizeInventory() {
		return contents.length - 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return contents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.contents[i] != null) {
			ItemStack var3;

			if (this.contents[i].stackSize <= j) {
				var3 = this.contents[i];
				this.contents[i] = null;
				return var3;
			} else {
				var3 = this.contents[i].splitStack(j);

				if (this.contents[i].stackSize == 0) {
					this.contents[i] = null;
				}

				return var3;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.contents[i] != null) {
			ItemStack var2 = this.contents[i];
			this.contents[i] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		this.contents[i] = itemstack;

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return invName;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(0 <= i && i <= 2) {
			if(itemstack.getItem() == Items.water_bucket) {
				return true;
			}
			if(itemstack.getItem() == Items.sugar) {
				return true;
			}
			if(itemstack.getItem() == Items.梅の実) {
				return true;
			}
		}
		return false;
	}

	@Override
	public World getWorldObj() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getWorldObj();
	}

	@Override
	public void setWorldObj(World par1World) {
		// TODO 自動生成されたメソッド・スタブ
		super.setWorldObj(par1World);
	}

	@Override
	public boolean hasWorldObj() {
		// TODO 自動生成されたメソッド・スタブ
		return super.hasWorldObj();
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		NBTTagList var2 = par1nbtTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		this.contents = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.getCompoundTagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.contents.length) {
				this.contents[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.contents.length; ++var3) {
			if (this.contents[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.contents[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1nbtTagCompound.setTag("Items", var2);
	}

	@Override
	public void updateEntity() {
		updateWaterLevel();
		if(this.processTime == 0 && this.canBrew()) {
			for(int i = 0; i < 3; ++i) {
				if(contents[i] != null) {
					--contents[i].stackSize;
					ItemStack is = contents[i].getItem().getContainerItem(contents[i]);
					if(is != null) {
						addItemStackToResultSlot(is);
					}
					if(contents[i].stackSize <= 0) {
						contents[i] = null;
					}
				}
			}

			this.processTime = 200;
			this.progressTime = 0;
			this.waterLevel -= 1;
			contents[6] = getBrewResult();
			this.markDirty();
		}
		else if(this.processTime > 0) {
			if(this.progressTime >= processTime) {
				this.progressTime = 0;
				this.processTime = 0;
				addItemStackToResultSlot(contents[6]);
				contents[6] = null;
			}
			else {
				++this.progressTime;
			}
			this.markDirty();
		}
	}

	private void updateWaterLevel() {
		for(int i = 0; i < 3; ++i) {
			if(contents[i] != null && contents[i].getItem() == Items.water_bucket) {
				--contents[i].stackSize;
				ItemStack is = contents[i].getItem().getContainerItem(contents[i]);
				if(is != null) {
					addItemStackToResultSlot(is);
				}
				if(contents[i].stackSize <= 0) {
					contents[i] = null;
				}
				this.waterLevel = Math.min(256, this.waterLevel + 16);
			}
		}
	}

	private boolean canBrew() {
		return getBrewResult() != null;
	}

	private ItemStack getBrewResult() {
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		for(int i = 0; i < 3; ++i) {
			if(contents[i] == null) {
				break;
			}
			if(contents[i].getItem() == Items.sugar) {
				b1 = true;
			}
			if(contents[i].getItem() == Items.梅の実) {
				b2 = true;
			}
			if(contents[i].getItem() == Items.glass_bottle) {
				b3 = true;
			}

		}
		if(b1 && b2 && b3 && waterLevel > 0) {
			return new ItemStack(Items.梅酒, 1, 0);
		}
		return null;
	}

	private void addItemStackToResultSlot(ItemStack is) {
		if(is == null) {
			return;
		}

		for(int j = 3; j < 6; ++j) {
			if(contents[j] != null && is.isItemEqual(contents[j]) && contents[j].stackSize < contents[j].getMaxStackSize()) {
				++contents[j].stackSize;
				is = null;
				break;
			}
		}
		if(is != null) {
			for(int j = 3; j < 6; ++j) {
				if(contents[j] == null) {
					contents[j] = is.copy();
					contents[j].stackSize = 1;
					break;
				}
			}
		}
	}

	@Override
	public int getBlockMetadata() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getBlockMetadata();
	}

	@Override
	public void markDirty() {
		// TODO 自動生成されたメソッド・スタブ
		super.markDirty();
	}

	@Override
	public double getDistanceFrom(double par1, double par3, double par5) {
		// TODO 自動生成されたメソッド・スタブ
		return super.getDistanceFrom(par1, par3, par5);
	}

	@Override
	public double getMaxRenderDistanceSquared() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getMaxRenderDistanceSquared();
	}

	@Override
	public Block getBlockType() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getBlockType();
	}

	@Override
	public Packet getDescriptionPacket() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getDescriptionPacket();
	}

	@Override
	public boolean isInvalid() {
		// TODO 自動生成されたメソッド・スタブ
		return super.isInvalid();
	}

	@Override
	public void invalidate() {
		// TODO 自動生成されたメソッド・スタブ
		super.invalidate();
	}

	@Override
	public void validate() {
		// TODO 自動生成されたメソッド・スタブ
		super.validate();
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		// TODO 自動生成されたメソッド・スタブ
		return super.receiveClientEvent(par1, par2);
	}

	@Override
	public void updateContainingBlockInfo() {
		// TODO 自動生成されたメソッド・スタブ
		super.updateContainingBlockInfo();
	}

	@Override
	public void func_145828_a(CrashReportCategory par1CrashReportCategory) {
		// TODO 自動生成されたメソッド・スタブ
		super.func_145828_a(par1CrashReportCategory);
	}

	@Override
	public boolean canUpdate() {
		// TODO 自動生成されたメソッド・スタブ
		return super.canUpdate();
	}

	@Override
	public void onChunkUnload() {
		// TODO 自動生成されたメソッド・スタブ
		super.onChunkUnload();
	}

	@Override
	public boolean shouldRefresh(Block oldID, Block newID, int oldMeta,
			int newMeta, World world, int x, int y, int z) {
		// TODO 自動生成されたメソッド・スタブ
		return super.shouldRefresh(oldID, newID, oldMeta, newMeta, world, x, y, z);
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		// TODO 自動生成されたメソッド・スタブ
		return super.shouldRenderInPass(pass);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getRenderBoundingBox();
	}

	public boolean isBrewing() {
		return this.processTime > 0;
	}

	public int getProcessTimeRemainingSc(int i) {
		if (this.processTime == 0) {
			this.processTime = 200;
		}

		return this.processTime * i / this.processTime;
	}

	public int getProgressSc(int i) {
		return this.progressTime * i / 200;
	}

}
