package wa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
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
		for(int j = y; j < 255; ++j) {
			//Material m = par1Entity.worldObj.getBlockMaterial(x, j - 2, z);
			//if(m == null) continue;
			//if(m.isSolid() == false) continue;
			if(world.getBlockId(x, j - 1, z) != 0) continue;
			if(world.getBlockId(x, j, z) != 0) continue;
			if(world.getBlockId(x, j + 1, z) != 0) continue;
			par1Entity.setLocationAndAngles(x, j + 3, z, 0, 0);
			break;
		}
	}

	public static void transferEntityToWorld(Entity par1Entity, int dim) {

		if (!par1Entity.worldObj.isRemote && !par1Entity.isDead) {
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
				for (int j = y; j < 255; ++j) {
					if (worldserver1.getBlockId(x, j - 1, z) != 0) continue;
					if (worldserver1.getBlockId(x, j, z) != 0) continue;
					if (worldserver1.getBlockId(x, j + 1, z) != 0) continue;
					par1Entity.setLocationAndAngles(x, j + 2, z, 0, 0);
					break;
				}
				worldserver1.spawnEntityInWorld(entity);
			}

			par1Entity.isDead = true;
			worldserver.resetUpdateEntityTick();
			worldserver1.resetUpdateEntityTick();
		}
	}
}
