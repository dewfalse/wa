package wa;

import net.minecraft.entity.Entity;
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

}
