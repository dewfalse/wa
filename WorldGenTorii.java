package wa;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenTorii implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0 || world.provider.dimensionId == Config.dimensionID) {
			if(random.nextInt(1000) > 0) {
				return;
			}

			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);
			int y = 255;
			for(; y > 0; --y) {
				if(world.getBlockMaterial(x, y, z) == Material.air) {
					continue;
				}
				break;
			}
			int blockID = world.getBlockId(x, y, z);
			int underBlockID = world.getBlockId(x, y - 1, z);
			int offsetY = 0;
			if(blockID == Block.grass.blockID) {
				offsetY = 1;
			}
			else if(blockID == Block.waterMoving.blockID && underBlockID == Block.dirt.blockID) {

			}
			else if(blockID == Block.waterStill.blockID && underBlockID == Block.dirt.blockID) {

			}
			else {
				return;
			}
			ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
			ForgeDirection dir = dirs[random.nextInt(dirs.length)];
			int metadata =  (dir == ForgeDirection.EAST || dir == ForgeDirection.WEST) ? 4 : 8;
			world.setBlock(x, y + offsetY, z, Blocks.colorWood[1].blockID);
			world.setBlock(x, y + 1 + offsetY, z, Blocks.colorWood[1].blockID);
			world.setBlock(x, y + 2 + offsetY, z, Blocks.colorWood[1].blockID);
			world.setBlock(x, y + 4 + offsetY, z, Blocks.colorWood[1].blockID);

			world.setBlock(x + dir.offsetX * 3, y + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1].blockID);
			world.setBlock(x + dir.offsetX * 3, y + 1 + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1].blockID);
			world.setBlock(x + dir.offsetX * 3, y + 2 + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1].blockID);
			world.setBlock(x + dir.offsetX * 3, y + 4 + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1].blockID);

			for(int i = 1; i <= 2; ++i) {
				for(int j = 0; j < 3; ++j) {
					if(world.getBlockMaterial(x + dir.offsetX * i, y + j + offsetY, z + dir.offsetZ * i) == Material.water) {
						continue;
					}
					world.setBlock(x + dir.offsetX * i, y + j + offsetY, z + dir.offsetZ * i, Blocks.portal.blockID, 0, 0);
				}
			}

			for(int i = 1; i <= 2; ++i) {
				if(world.getBlockMaterial(x + dir.offsetX * i, y + 4 + offsetY, z + dir.offsetZ * i) == Material.water) {
					continue;
				}
				world.setBlockToAir(x + dir.offsetX * i, y + 4 + offsetY, z + dir.offsetZ * i);
			}

			for(int i = -1; i <= 4; ++i) {
				world.setBlock(x + dir.offsetX * i, y + 3 + offsetY, z + dir.offsetZ * i, Blocks.colorWood[1].blockID, metadata, 3);
				world.setBlock(x + dir.offsetX * i, y + 5 + offsetY, z + dir.offsetZ * i, Blocks.colorWood[0].blockID, metadata, 3);
			}
		}
	}

}
