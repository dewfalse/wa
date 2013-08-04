package wa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterEx extends Teleporter {

	public TeleporterEx(WorldServer par1WorldServer) {
		super(par1WorldServer);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4,
			double par6, float par8) {
		int x = (int)par1Entity.posX;
		int y = (int)par1Entity.posY;
		int z = (int)par1Entity.posZ;
		WorldServer world = MinecraftServer.getServer().worldServerForDimension(par1Entity.dimension);
		int j = y;
		for (; j < 255; ++j) {
			if (world.getBlockId(x, j, z) != 0) continue;
			if (world.getBlockId(x, j + 1, z) != 0) continue;
			if (world.getBlockId(x, j + 2, z) != 0) continue;
			break;
		}
		for (; j > 0; --j) {
			if (world.getBlockId(x, j, z) == 0) continue;
			break;
		}
		par1Entity.setLocationAndAngles(x, j + 1, z, 0, 0);
	}

	// dim: 転送先ディメンション
	public static boolean transferEntityToWorld(Entity par1Entity, int dim) {

		if (!par1Entity.worldObj.isRemote && !par1Entity.isDead) {
			if(par1Entity.ridingEntity != null) {
				return false;
			}
			if(par1Entity.riddenByEntity != null) {
				return false;
			}
			if(par1Entity.dimension == dim) {
				return false;
			}
			if(par1Entity instanceof IBossDisplayData) {
				return false;
			}
			if(par1Entity instanceof EntityPlayerMP) {
				if(transferPlayerToDimension(par1Entity, dim)) {
					return true;
				}
				return false;
			}
			if(par1Entity instanceof EntityLiving == false && par1Entity instanceof EntityMinecart == false) {
				return false;
			}
			MinecraftServer minecraftserver = MinecraftServer.getServer();
			int dimOld = par1Entity.dimension;
			WorldServer worldserver = minecraftserver.worldServerForDimension(dimOld);
			WorldServer worldserver1 = minecraftserver.worldServerForDimension(dim);
			par1Entity.dimension = dim;

			par1Entity.worldObj.removeEntity(par1Entity);
			par1Entity.isDead = false;
			minecraftserver.getConfigurationManager().transferEntityToWorld(par1Entity, dimOld, worldserver, worldserver1, new TeleporterEx(worldserver1));
			Entity entity = EntityList.createEntityByName(EntityList.getEntityString(par1Entity), worldserver1);

			if (entity != null) {
				entity.copyDataFrom(par1Entity, true);
				int x = (int) par1Entity.posX;
				int y = (int) par1Entity.posY;
				int z = (int) par1Entity.posZ;
				int j = y;
				for (; j < 255; ++j) {
					if (worldserver1.getBlockId(x, j, z) != 0) continue;
					if (worldserver1.getBlockId(x, j + 1, z) != 0) continue;
					if (worldserver1.getBlockId(x, j + 2, z) != 0) continue;
					break;
				}
				for (; j > 0; --j) {
					if (worldserver1.getBlockId(x, j, z) == 0) continue;
					break;
				}
				par1Entity.setLocationAndAngles(x, j + 1, z, 0, 0);
				worldserver1.spawnEntityInWorld(entity);
			}

			par1Entity.isDead = true;
			worldserver.resetUpdateEntityTick();
			worldserver1.resetUpdateEntityTick();
			return true;
		}
		return true;
	}

	private static boolean transferPlayerToDimension(Entity par1Entity, int dim) {
		EntityPlayerMP thePlayer = (EntityPlayerMP) par1Entity;
		if(thePlayer.timeUntilPortal > 0) {
			return false;
		}
		thePlayer.timeUntilPortal = 10;

		if (dim == 0) {
			thePlayer.mcServer.getConfigurationManager()
			.transferPlayerToDimension(thePlayer, 0, new TeleporterEx(thePlayer.mcServer.worldServerForDimension(0)));
			return true;
		} else if(dim == Config.dimensionID){
			thePlayer.mcServer.getConfigurationManager()
			.transferPlayerToDimension(thePlayer, Config.dimensionID, new TeleporterEx(thePlayer.mcServer.worldServerForDimension(Config.dimensionID)));
			return true;
		}
		return false;

	}
}
