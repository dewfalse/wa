package wa;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenSpring extends BiomeGenBase {

	public BiomeGenSpring(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.theBiomeDecorator.reedsPerChunk = 2;
		this.theBiomeDecorator.clayPerChunk = 1;
		//worldGeneratorTrees = new WorldGeneratorSakuraTrees(false);
	}

	private int takenokoPerChunk = 10;

	@Override
	public void decorate(World par1World, Random par2Random, int chunk_X,
			int chunk_Z) {
		int i, j, k, l, i1;
		i = theBiomeDecorator.treesPerChunk;
		if (par2Random.nextInt(10) > 3) {
			for (j = 0; j < 1 + par2Random.nextInt(9); ++j) {
				k = chunk_X + par2Random.nextInt(16) + 8;
				l = chunk_Z + par2Random.nextInt(16) + 8;
				WorldGenerator worldgenerator = new WorldGeneratorSakuraTrees(false);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(par1World, par2Random, k,
						par1World.getHeightValue(k, l), l);
			}
		}
		else if (par2Random.nextInt(10) > 3) {
			for (j = 0; j < 1 + par2Random.nextInt(9); ++j) {
				k = chunk_X + par2Random.nextInt(16) + 8;
				l = chunk_Z + par2Random.nextInt(16) + 8;
				WorldGenerator worldgenerator = new WorldGeneratorUmeTrees(false);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(par1World, par2Random, k,
						par1World.getHeightValue(k, l), l);
			}
		}

		i = this.takenokoPerChunk;
		if (par2Random.nextInt(4) != 0) {
			return;
		}

		i = this.takenokoPerChunk;
		for (j = 0; j < i; ++j) {
			k = chunk_X + par2Random.nextInt(16) + 8;
			l = chunk_Z + par2Random.nextInt(16) + 8;
			WorldGenerator worldgenerator = new WorldGenTakenoko();
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(par1World, par2Random, k,
					par1World.getHeightValue(k, l), l);
		}
	}

}
