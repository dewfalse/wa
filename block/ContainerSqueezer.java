package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Created by dew on 2015/11/13.
 */
public class ContainerSqueezer extends Container {

    private TileEntitySqueezer tile;

    private TileEntitySqueezer inventory;

    private int lowerProgress;
    private int upperProgress;
    private int lastFluidAmount;
    private int lastFluidID;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerSqueezer(EntityPlayer player, TileEntitySqueezer par2TileEntity) {
        this.tile = par2TileEntity;
        this.inventory = par2TileEntity;

        // 材料
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.addSlotToContainer(new Slot(this.inventory, i*3+j, 15+j*18, 19+i*18));
            }
        }

        // 燃料
        this.addSlotToContainer(new Slot(this.inventory, 9, 79, 59));

        // 液体用
        this.addSlotToContainer(new Slot(this.inventory, 10, 136, 16));
        this.addSlotToContainer(new SlotFurnace(player, this.inventory, 11, 136, 56));

        int i;

        // 1 ～ 3段目のインベントリ
        for (i = 0; i < 3; ++i) {
            for (int h = 0; h < 9; ++h) {
                this.addSlotToContainer(new Slot(player.inventory, h + i * 9 + 9, 8 + h * 18, 84 + i * 18));
            }
        }

        // 4段目のインベントリ
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, tile.getUnder());
        par1ICrafting.sendProgressBarUpdate(this, 1, tile.getUpper());

        if (this.tile.productTank.getFluid() != null) {
            par1ICrafting.sendProgressBarUpdate(this, 2, this.tile.productTank.getFluid().getFluid().getID());
            par1ICrafting.sendProgressBarUpdate(this, 3, this.tile.productTank.getFluidAmount());
        } else {
            par1ICrafting.sendProgressBarUpdate(this, 2, 0);
            par1ICrafting.sendProgressBarUpdate(this, 3, 0);
        }
        par1ICrafting.sendProgressBarUpdate(this, 4, tile.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 5, tile.currentItemBurnTime);
        //par1ICrafting.sendProgressBarUpdate(this, 6, tile.getAgingTime());

    }

    // 更新を送る
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            //icrafting.sendProgressBarUpdate(this, 6, tile.getAgingTime());
            if (tile.getAgingTime() > 0) {
                if (this.lowerProgress != tile.getUnder()) {
                    icrafting.sendProgressBarUpdate(this, 0, tile.getUnder());
                }
                if (this.upperProgress != tile.getUpper()) {
                    icrafting.sendProgressBarUpdate(this, 1, tile.getUpper());
                }
                this.lowerProgress = tile.getUnder();
                this.upperProgress = tile.getUpper();
            } else {
                if (this.lowerProgress != 0) {
                    icrafting.sendProgressBarUpdate(this, 0, 0);
                }
                if (this.upperProgress != 0) {
                    icrafting.sendProgressBarUpdate(this, 1, 0);
                }
                this.lowerProgress = 0;
                this.upperProgress = 0;
            }

            if (this.tile.productTank.getFluid() != null) {
                if (this.lastFluidID != this.tile.productTank.getFluid().getFluid().getID()) {
                    icrafting.sendProgressBarUpdate(this, 2, this.tile.productTank.getFluid().getFluid().getID());
                }
                if (this.lastFluidAmount != this.tile.productTank.getFluidAmount()) {
                    icrafting.sendProgressBarUpdate(this, 3, this.tile.productTank.getFluidAmount());
                }

                this.lastFluidAmount = this.tile.productTank.getFluidAmount();
                this.lastFluidID = this.tile.productTank.getFluid().getFluid().getID();
            } else {
                if (this.lastFluidID != 0) {
                    icrafting.sendProgressBarUpdate(this, 2, 0);
                }
                if (this.lastFluidAmount != 0) {
                    icrafting.sendProgressBarUpdate(this, 3, 0);
                }

                this.lastFluidAmount = 0;
                this.lastFluidID = 0;
            }

            if (this.lastBurnTime != this.tile.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 4, this.tile.furnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.tile.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 5, this.tile.currentItemBurnTime);
            }
        }

        this.lastBurnTime = this.tile.furnaceBurnTime;
        this.lastItemBurnTime = this.tile.currentItemBurnTime;
    }

    // 更新する
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            tile.setUnder(par2);
        } else if (par1 == 1) {
            tile.setUpper(par2);
        } else if (par1 == 2 || par1 == 3) {
            this.tile.getGuiFluidUpdate(par1, par2);
        } else if (par1 == 4) {
            tile.furnaceBurnTime = par2;
        } else if (par1 == 5) {
            tile.currentItemBurnTime = par2;
        } else if (par1 == 6) {
            tile.setAgingTime(par2);
        }
    }

    // InventorySample内のisUseableByPlayerメソッドを参照
    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.inventory.isUseableByPlayer(par1EntityPlayer);
    }

    // Shiftクリック
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // カーソルを排出スロットにあわせているとき
            if (par2 == 11) {
                // アイテムの移動(プレイヤーのインベントリへ)
                if (!this.mergeItemStack(itemstack1, 12, 48, true))
                    return null;

                slot.onSlotChange(itemstack1, itemstack);
            }
            // カーソルをプレイヤーのインベントリにあわせている
            else if (par2 > 11) {
                // 液体容器である
                if (FluidContainerRegistry.isEmptyContainer(itemstack1)) {
                    // アイテムの移動(スロット2へ)
                    if (!this.mergeItemStack(itemstack1, 10, 11, false))
                        return null;
                }
                else if(TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 9, 10, false))
                        return null;
                } else// それ以外のアイテムはすべて材料欄に飛ばす
                {
                    // アイテムの移動(材料スロットへ)
                    if (!this.mergeItemStack(itemstack1, 0, 9, false))
                        return null;
                }
            }
            // アイテムの移動(プレイヤーのインベントリへ)
            else if (!this.mergeItemStack(itemstack1, 12, 48, false))
                return null;

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
                return null;

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
