package wa;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBrewingBarrel extends Container {

	TileEntityBrewingBarrel inventory;

	private int lastProgressTime = 0;
	private int lastProcessTime = 0;
	private int lastWaterLevel = 0;

	static final int sourceSize = 3;
	static final int productSize = 3;
	static final int inventorySize = 27;
	static final int itemSlotSize = 9;

	static final int sourceIndex = 0;
	static final int productIndex = sourceIndex + sourceSize;
	static final int inventoryIndex = productIndex + productSize;
	static final int itemSlotIndex = inventoryIndex + inventorySize;

	public ContainerBrewingBarrel(InventoryPlayer inventory, TileEntityBrewingBarrel tile) {
		this.inventory = tile;
        int var3;

        for (var3 = 0; var3 < sourceSize; ++var3)
        {
            this.addSlotToContainer(new Slot(tile, sourceIndex + var3, 53, 17 + var3 * 18));
        }
        for (var3 = 0; var3 < productSize; ++var3)
        {
            this.addSlotToContainer(new Slot(tile, productIndex + var3, 107, 17 + (var3) * 18));
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 142));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	protected Slot addSlotToContainer(Slot par1Slot) {
		// TODO 自動生成されたメソッド・スタブ
		return super.addSlotToContainer(par1Slot);
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1iCrafting) {
        super.addCraftingToCrafters(par1iCrafting);
        par1iCrafting.sendProgressBarUpdate(this, 0, this.inventory.processTime);
        par1iCrafting.sendProgressBarUpdate(this, 1, this.inventory.progressTime);
        par1iCrafting.sendProgressBarUpdate(this, 2, this.inventory.waterLevel);
	}

	@Override
	public List getInventory() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getInventory();
	}

	@Override
	public void removeCraftingFromCrafters(ICrafting par1iCrafting) {
		// TODO 自動生成されたメソッド・スタブ
		super.removeCraftingFromCrafters(par1iCrafting);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
			ICrafting var2 = (ICrafting) this.crafters.get(var1);

			if (this.lastProcessTime != this.inventory.processTime) {
				var2.sendProgressBarUpdate(this, 0, this.inventory.processTime);
			}

			if (this.lastProgressTime != this.inventory.progressTime) {
				var2.sendProgressBarUpdate(this, 1, this.inventory.progressTime);
			}

			if (this.lastWaterLevel != this.inventory.waterLevel) {
				var2.sendProgressBarUpdate(this, 2, this.inventory.waterLevel);
			}
		}

		this.lastProcessTime = this.inventory.processTime;
		this.lastProgressTime = this.inventory.progressTime;
		this.lastWaterLevel = this.inventory.waterLevel;
	}

	@Override
	public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2) {
		// TODO 自動生成されたメソッド・スタブ
		return super.enchantItem(par1EntityPlayer, par2);
	}

	@Override
	public Slot getSlotFromInventory(IInventory par1iInventory, int par2) {
		// TODO 自動生成されたメソッド・スタブ
		return super.getSlotFromInventory(par1iInventory, par2);
	}

	@Override
	public Slot getSlot(int par1) {
		// TODO 自動生成されたメソッド・スタブ
		return super.getSlot(par1);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		// TODO 自動生成されたメソッド・スタブ
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}

	@Override
	public ItemStack slotClick(int par1, int par2, int par3,
			EntityPlayer par4EntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}

	@Override
	public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
		// TODO 自動生成されたメソッド・スタブ
		return super.func_94530_a(par1ItemStack, par2Slot);
	}

	@Override
	protected void retrySlotClick(int par1, int par2, boolean par3,
			EntityPlayer par4EntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		//super.retrySlotClick(par1, par2, par3, par4EntityPlayer);
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		super.onContainerClosed(par1EntityPlayer);
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1iInventory) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCraftMatrixChanged(par1iInventory);
	}

	@Override
	public void putStackInSlot(int par1, ItemStack par2ItemStack) {
		// TODO 自動生成されたメソッド・スタブ
		super.putStackInSlot(par1, par2ItemStack);
	}

	@Override
	public void putStacksInSlots(ItemStack[] par1ArrayOfItemStack) {
		// TODO 自動生成されたメソッド・スタブ
		super.putStacksInSlots(par1ArrayOfItemStack);
	}

	@Override
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.inventory.processTime = par2;
		}

		if (par1 == 1) {
			this.inventory.progressTime = par2;
		}

		if (par1 == 1) {
			this.inventory.waterLevel = par2;
		}
	}

	@Override
	public short getNextTransactionID(InventoryPlayer par1InventoryPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		return super.getNextTransactionID(par1InventoryPlayer);
	}

	@Override
	public boolean isPlayerNotUsingContainer(EntityPlayer par1EntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		return super.isPlayerNotUsingContainer(par1EntityPlayer);
	}

	@Override
	public void setPlayerIsPresent(EntityPlayer par1EntityPlayer, boolean par2) {
		// TODO 自動生成されたメソッド・スタブ
		super.setPlayerIsPresent(par1EntityPlayer, par2);
	}

	@Override
	protected boolean mergeItemStack(ItemStack par1ItemStack, int par2,
			int par3, boolean par4) {
		// TODO 自動生成されたメソッド・スタブ
		return super.mergeItemStack(par1ItemStack, par2, par3, par4);
	}

	@Override
	protected void func_94533_d() {
		// TODO 自動生成されたメソッド・スタブ
		super.func_94533_d();
	}

	@Override
	public boolean canDragIntoSlot(Slot par1Slot) {
		// TODO 自動生成されたメソッド・スタブ
		return super.canDragIntoSlot(par1Slot);
	}

}
