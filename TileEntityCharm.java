package wa;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.LinkedHashSet;
import java.util.Set;

public class TileEntityCharm extends TileEntity {

	public int charmType;
	public int charmLevel;
	public int damage;

	public TileEntityCharm() {
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		charmType = par1nbtTagCompound.getInteger("CharmType");
		charmLevel = par1nbtTagCompound.getInteger("CharmLevel");
		damage = par1nbtTagCompound.getInteger("Damage");
	}

	@Override
	public void setWorldObj(World par1World) {
		super.setWorldObj(par1World);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("CharmType", charmType);
		par1nbtTagCompound.setInteger("CharmLevel", charmLevel);
		par1nbtTagCompound.setInteger("Damage", damage);
	}

	@Override
	public void updateEntity() {
		if(damage >= 1600) {
			Set<PathPoint> list = EntityJoinWorldEventHandler.charmsInDim.get(this.worldObj.provider.dimensionId);
			if(list != null) {
				list.remove(new PathPoint(this.xCoord, this.yCoord, this.zCoord));
				EntityJoinWorldEventHandler.charmsInDim.put(this.worldObj.provider.dimensionId, list);
			}
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
			this.updateContainingBlockInfo();
		}
		super.updateEntity();
	}

	@Override
	public Packet getDescriptionPacket() {
		if(this.charmType == 3) {
			Set<PathPoint> list = EntityJoinWorldEventHandler.charmsInDim.get(this.worldObj.provider.dimensionId);
			if(list == null) {
				list = new LinkedHashSet<PathPoint>();
			}
			list.add(new PathPoint(this.xCoord, this.yCoord, this.zCoord));
			EntityJoinWorldEventHandler.charmsInDim.put(this.worldObj.provider.dimensionId, list);
		}
		return super.getDescriptionPacket();
	}

}
