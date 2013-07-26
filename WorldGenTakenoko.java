package wa;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenTakenoko extends WorldGenerator implements IWorldGenerator {

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
		int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

		for(int j1 = 255; j1 > 0; --j1) {
			if (par1World.isAirBlock(i1, j1, k1) && par1World.getBlockId(i1, j1 - 1, k1) == Block.grass.blockID && Blocks.takenoko.canPlaceBlockAt(par1World, i1, j1, k1)) {
				par1World.setBlock(i1, j1, k1, Blocks.takenoko.blockID, 0, 2);
				break;
			}
		}

		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == 0) {
			if(random.nextInt(800) == 0) {
				generate(world, random, chunkX, 64, chunkZ);
			}
		}

	}
}
