package wa.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by dew on 2015/11/11.
 */
public class TileEntitySpiritLamp extends TileEntity {

    public FluidTankEx fuelTank = new FluidTankEx(1000);

    private boolean ignited = false;
    private int lastAmount = 0;

    private int tickCount = 0;

    @Override
    public void updateEntity() {

        boolean update = false;
        if(ignited) {
            // 着火してれば燃料の残量チェック
            if(fuelTank.isEmpty()) {
                // 残量なしなら消火
                setIgnited(false);
                update = true;
            }
            else {
                // 20tickごとに1消費
                tickCount = (tickCount + 1) % 20;
                if(tickCount == 0) {
                    // 残量ありなら残量を1減らす
                    this.drain(ForgeDirection.UNKNOWN, new FluidStack(fuelTank.getFluidType(), 1), true);
                    update = true;
                }
            }

            if (lastAmount != this.fuelTank.getFluidAmount()){
                lastAmount = this.fuelTank.getFluidAmount();
            }
        }

        if(update) {
            // よくわかんないので全部呼んじゃう
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            this.markDirty();
        }
        super.updateEntity();
    }

	/* パケッヨ */

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.ignited = par1NBTTagCompound.getBoolean("ignited");

        this.fuelTank = new FluidTankEx(1000);
        if (par1NBTTagCompound.hasKey("fuelTank")) {
            this.fuelTank.readFromNBT(par1NBTTagCompound.getCompoundTag("fuelTank"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("ignited", this.ignited);

        NBTTagCompound tank = new NBTTagCompound();
        this.fuelTank.writeToNBT(tank);
        par1NBTTagCompound.setTag("fuelTank", tank);
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

    public boolean getIgnited() {
        return this.ignited;
    }

    public void setIgnited(boolean par1) {
        if(this.ignited != par1) {
            this.ignited = par1;
        }
    }

    public int getAmount() {
        return this.fuelTank.getFluidAmount();
    }

    public void setAmount(int amount) {
        this.fuelTank.setAmount(amount);
    }

    public int getCapacity() {
        return this.fuelTank.getCapacity();
    }

    public int getMetadata() {
        return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }


	/* Fluidの取扱い */

	/*
	 * 以下のメソッドはIFluidHandlerのメソッドとほぼ同様。
	 * パイプを使って液体を抜いたり、無理矢理流し込んだりさせないため、意図的にIFluidHandleの実装は見送り。
	 */

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        // 引き抜けるのは自分自身のみ
        if (resource == null || from != ForgeDirection.UNKNOWN) {
            return null;
        }
        if (fuelTank.getFluidType() == resource.getFluid()) {
            FluidStack ret = fuelTank.drain(resource.amount, doDrain);
            return ret;
        }
        return null;
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return this.fuelTank.drain(maxDrain, doDrain);
    }

    // 外部からの液体の受け入れ
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        // 入力が液体でなければ受け入れない
        if (resource == null || resource.getFluid() == null) {
            return 0;
        }

        // タンクに液体があるなら同一の液体しか受け入れない
        FluidStack current = this.fuelTank.getFluid();
        FluidStack resourceCopy = resource.copy();
        if (current != null && current.amount > 0 && !current.isFluidEqual(resourceCopy)) {
            return 0;
        }

        // タンクに液体がないなら高濃度アルコールだけ受け入れる
        if(resourceCopy.getFluid() != FluidRegistry.getFluid("wa.fluid.alcohol")) {
            return 0;
        }

       return this.fuelTank.fill(resourceCopy, doFill);
    }

    // 満杯なら受け入れない
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return !this.fuelTank.isFull();
    }

    // 引き抜けるのは自分自身のみ
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from == ForgeDirection.UNKNOWN;
    }
}
