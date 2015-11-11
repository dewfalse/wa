package wa.block;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTatara extends TileEntity {
    public int burnTime = 0;
    public short cx, cy, cz;

    public TileEntityTatara() {
    }

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		this.burnTime = par1nbtTagCompound.getShort("BurnTime");
		this.cx = par1nbtTagCompound.getShort("cx");
		this.cy = par1nbtTagCompound.getShort("cy");
		this.cz = par1nbtTagCompound.getShort("cz");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setShort("BurnTime", (short) this.burnTime);
		par1nbtTagCompound.setShort("cx", (short) this.cx);
		par1nbtTagCompound.setShort("cy", (short) this.cy);
		par1nbtTagCompound.setShort("cz", (short) this.cz);

	}

	public boolean isBurning() {
		return this.burnTime > 0;
	}

	@Override
	public void updateEntity() {
        if (this.burnTime > 0)
        {
            --this.burnTime;
        }
        if (this.burnTime <= 0)
        {
        	if(this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 2) {
        		this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 3);
        	}
        }
		Block block = this.worldObj.getBlock(xCoord, yCoord, zCoord);
		boolean isTatara = true;
		for(int i = -1; i <= 1; ++i) {
			for(int j = -1; j <= 1; ++j) {
				for(int k = -1; k <= 1; ++k) {
					if(isTatara == false) {
						break;
					}
					if(this.worldObj.getBlock(xCoord + i, yCoord + j, zCoord + k) != block) {
						isTatara = false;
						break;
					}
					if(this.worldObj.getBlockMetadata(xCoord + i, yCoord + j, zCoord + k) != 0) {
						isTatara = false;
						break;
					}
				}
			}
		}
		if(isTatara) {
			for(int i = -1; i <= 1; ++i) {
				for(int j = -1; j <= 1; ++j) {
					for(int k = -1; k <= 1; ++k) {
						TileEntityTatara tile = (TileEntityTatara) worldObj.getTileEntity(xCoord, yCoord, zCoord);
						if(tile == null) {
							continue;
						}
						tile.cx = (short) i;
						tile.cy = (short) j;
						tile.cz = (short) k;
						worldObj.setBlockMetadataWithNotify(xCoord + i, yCoord + j, zCoord + k, 1, 3);
						worldObj.markBlockForUpdate(xCoord + i, yCoord + j, zCoord + k);
					}
				}
			}
		}

		if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1) {
			ForgeDirection[] dirs = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.SOUTH, ForgeDirection.DOWN, ForgeDirection.UP};
			for(ForgeDirection dir : dirs) {
				Block theBlcok = worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
				int meta = worldObj.getBlockMetadata(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
				if(theBlcok == Blocks.tataraBlock && meta == 2) {
					burnTime = 3600;
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 3);
					break;
				}
			}
		}

	}

	@Override
	public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);
	}

}
