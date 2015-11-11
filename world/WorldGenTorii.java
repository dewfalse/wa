package wa.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.ForgeDirection;
import wa.Config;
import wa.block.Blocks;

import java.util.Random;

public class WorldGenTorii implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0 || world.provider.dimensionId == Config.dimensionID) {
			if(random.nextDouble() * 100.0D > Config.鳥居出現率) {
				return;
			}

			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);
			int y = 255;
			for(; y > 0; --y) {
				if(world.getBlock(x, y, z).getMaterial() == Material.air) {
					continue;
				}
				break;
			}
			Block blockID = world.getBlock(x, y, z);
            Block underBlockID = world.getBlock(x, y - 1, z);
			int offsetY = 0;
			if(blockID == Blocks.grass) {
				offsetY = 1;
			}
			else if(blockID == Blocks.flowing_water && underBlockID == Blocks.dirt) {

			}
			else if(blockID == Blocks.water && underBlockID == Blocks.dirt) {

			}
			else if(blockID == Blocks.snow && underBlockID == Blocks.grass) {

			}
			else if(blockID == Blocks.snow && underBlockID == Blocks.dirt) {

			}
			else if(blockID == Blocks.snow && underBlockID == Blocks.snow_layer) {

			}
			else {
				return;
			}
			ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
			ForgeDirection dir = dirs[random.nextInt(dirs.length)];
			int metadata =  (dir == ForgeDirection.EAST || dir == ForgeDirection.WEST) ? 4 : 8;
			world.setBlock(x, y + offsetY, z, Blocks.colorWood[1]);
			world.setBlock(x, y + 1 + offsetY, z, Blocks.colorWood[1]);
			world.setBlock(x, y + 2 + offsetY, z, Blocks.colorWood[1]);
			world.setBlock(x, y + 4 + offsetY, z, Blocks.colorWood[1]);

			world.setBlock(x + dir.offsetX * 3, y + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1]);
			world.setBlock(x + dir.offsetX * 3, y + 1 + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1]);
			world.setBlock(x + dir.offsetX * 3, y + 2 + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1]);
			world.setBlock(x + dir.offsetX * 3, y + 4 + offsetY, z + dir.offsetZ * 3, Blocks.colorWood[1]);

			for(int i = 1; i <= 2; ++i) {
				for(int j = 0; j < 3; ++j) {
					if(world.getBlock(x + dir.offsetX * i, y + j + offsetY, z + dir.offsetZ * i).getMaterial() == Material.water) {
						continue;
					}
					world.setBlock(x + dir.offsetX * i, y + j + offsetY, z + dir.offsetZ * i, Blocks.portal, 0, 0);
				}
			}

			for(int i = 1; i <= 2; ++i) {
				if(world.getBlock(x + dir.offsetX * i, y + 4 + offsetY, z + dir.offsetZ * i).getMaterial() == Material.water) {
					continue;
				}
				world.setBlockToAir(x + dir.offsetX * i, y + 4 + offsetY, z + dir.offsetZ * i);
			}

			for(int i = -1; i <= 4; ++i) {
				world.setBlock(x + dir.offsetX * i, y + 3 + offsetY, z + dir.offsetZ * i, Blocks.colorWood[1], metadata, 3);
				world.setBlock(x + dir.offsetX * i, y + 5 + offsetY, z + dir.offsetZ * i, Blocks.colorWood[0], metadata, 3);
			}
		}
	}

}
