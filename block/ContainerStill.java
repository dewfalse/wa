package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Created by dew on 2015/11/14.
 */
public class ContainerStill extends Container {

    private TileEntityStill tile;

    private TileEntityStill inventory;

    private int lowerProgress;
    private int upperProgress;
    private int lastSourceFluidAmount;
    private int lastSourceFluidID;
    private int lastProductFluidAmount;
    private int lastProductFluidID;

    public ContainerStill(EntityPlayer player, TileEntityStill par2TileEntity) {
        this.tile = par2TileEntity;
        this.inventory = par2TileEntity;

        // 材料1
        this.addSlotToContainer(new Slot(this.inventory, 0, 32, 26));
        // 材料2
        this.addSlotToContainer(new Slot(this.inventory, 1, 32, 47));

        // 液体用
        this.addSlotToContainer(new Slot(this.inventory, 2, 126, 16));
        this.addSlotToContainer(new SlotFurnace(player, this.inventory, 3, 126, 56));

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
        ForgeDirection d = this.tile.getPairDir();
        if(d != ForgeDirection.UNKNOWN) {
            TileEntityStill tileConnected = (TileEntityStill) tile.getWorldObj().getTileEntity(tile.xCoord + d.offsetX, tile.yCoord + d.offsetY, tile.zCoord + d.offsetZ);
            if (tileConnected.productTank.getFluid() != null) {
                par1ICrafting.sendProgressBarUpdate(this, 4,tileConnected.productTank.getFluid().getFluid().getID());
                par1ICrafting.sendProgressBarUpdate(this, 5, tileConnected.productTank.getFluidAmount());
            } else {
                par1ICrafting.sendProgressBarUpdate(this, 4, 0);
                par1ICrafting.sendProgressBarUpdate(this, 5, 0);
            }
        }
    }

    // 更新を送る
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

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
                if (this.lastSourceFluidID != this.tile.productTank.getFluid().getFluid().getID()) {
                    icrafting.sendProgressBarUpdate(this, 2, this.tile.productTank.getFluid().getFluid().getID());
                }
                if (this.lastSourceFluidAmount != this.tile.productTank.getFluidAmount()) {
                    icrafting.sendProgressBarUpdate(this, 3, this.tile.productTank.getFluidAmount());
                }

                this.lastSourceFluidAmount = this.tile.productTank.getFluidAmount();
                this.lastSourceFluidID = this.tile.productTank.getFluid().getFluid().getID();
            } else {
                if (this.lastSourceFluidID != 0) {
                    icrafting.sendProgressBarUpdate(this, 2, 0);
                }
                if (this.lastSourceFluidAmount != 0) {
                    icrafting.sendProgressBarUpdate(this, 3, 0);
                }

                this.lastSourceFluidAmount = 0;
                this.lastSourceFluidID = 0;
            }

            ForgeDirection d = this.tile.getPairDir();
            if (d != ForgeDirection.UNKNOWN) {
                TileEntityStill tileConnected = (TileEntityStill) tile.getWorldObj().getTileEntity(tile.xCoord + d.offsetX, tile.yCoord + d.offsetY, tile.zCoord + d.offsetZ);
                if (tileConnected.productTank.getFluid() != null) {
                    if (this.lastProductFluidID != tileConnected.productTank.getFluid().getFluid().getID()) {
                        icrafting.sendProgressBarUpdate(this, 4, tileConnected.productTank.getFluid().getFluid().getID());
                    }
                    if (this.lastProductFluidAmount != tileConnected.productTank.getFluidAmount()) {
                        icrafting.sendProgressBarUpdate(this, 5, tileConnected.productTank.getFluidAmount());
                    }

                    this.lastProductFluidAmount = tileConnected.productTank.getFluidAmount();
                    this.lastProductFluidID = tileConnected.productTank.getFluid().getFluid().getID();
                } else {
                    if (this.lastProductFluidID != 0) {
                        icrafting.sendProgressBarUpdate(this, 4, 0);
                    }
                    if (this.lastProductFluidAmount != 0) {
                        icrafting.sendProgressBarUpdate(this, 5, 0);
                    }

                    this.lastProductFluidAmount = 0;
                    this.lastProductFluidID = 0;
                }
            }
        }
    }

    // 更新する
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            tile.setUnder(par2);
        } else if (par1 == 1) {
            tile.setUpper(par2);
        } else {
            this.tile.getGuiFluidUpdate(par1, par2);
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
            if (par2 == 3) {
                // アイテムの移動(スロット6～42へ)
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                    return null;

                slot.onSlotChange(itemstack1, itemstack);
            }
            // カーソルをプレイヤーのインベントリにあわせている
            else if (par2 > 5) {
                // 液体容器である
                if (FluidContainerRegistry.isEmptyContainer(itemstack1)) {
                    // アイテムの移動(スロット2へ)
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                        return null;
                } else// それ以外のアイテムはすべて材料欄に飛ばす
                {
                    // アイテムの移動(スロット2へ)
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                        return null;
                }
            }
            // アイテムの移動(スロット6～42へ)
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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
