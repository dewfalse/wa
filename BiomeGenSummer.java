package wa;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSummer extends BiomeGenBase {

	public BiomeGenSummer(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 5;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.clayPerChunk = 1;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
	}

	private int takePerChunk = 30;

	@Override
	public void decorate(World par1World, Random par2Random, int chunk_X,
			int chunk_Z) {
		int i, j, k, l, i1;

		if (par2Random.nextInt(20) != 0) {
			return;
		}

		i = this.takePerChunk;
		for (j = 0; j < i; ++j) {
			k = chunk_X + par2Random.nextInt(16) + 8;
			l = chunk_Z + par2Random.nextInt(16) + 8;
			WorldGenerator worldgenerator = new WorldGenTake();
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(par1World, par2Random, k,
					par1World.getHeightValue(k, l), l);
		}
	}

}
