package wa;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import wa.block.Blocks;

import java.util.Random;

public class WorldGenSusuki extends WorldGenerator {

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		for (int l = 0; l < 40; ++l) {
			int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i1, j1, k1) && par1World.getBlock(i1, j1 - 1, k1) == Blocks.grass && Blocks.susuki.canPlaceBlockAt(par1World, i1, j1, k1)) {
				par1World.setBlock(i1, j1, k1, Blocks.susuki, 0, 2);
				par1World.setBlock(i1, j1+1, k1, Blocks.susuki, 0, 2);
			}
		}

		return true;
	}
}
