package wa;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTake extends WorldGenerator {

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		for (int l = 0; l < 20; ++l) {
			int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i1, j1, k1) && par1World.getBlockId(i1, j1 - 1, k1) == Block.grass.blockID && Blocks.take.canPlaceBlockAt(par1World, i1, j1, k1)) {
				for(int n = 0; n < par2Random.nextInt(15); ++n) {
					if(par1World.getBlockId(i1, j1 + n, k1) == 0) {
						par1World.setBlock(i1, j1 + n, k1, Blocks.take.blockID, 0, 2);
					}
					else {
						break;
					}
				}
			}
		}

		return true;
	}

}
