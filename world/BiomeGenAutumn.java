package wa.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenAutumn extends BiomeGenBase {

	private int susukiPerChunk = 10;

	public BiomeGenAutumn(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 7;
		this.theBiomeDecorator.flowersPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.flowersPerChunk = 4;
		this.theBiomeDecorator.deadBushPerChunk = 4;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.bigMushroomsPerChunk = 1;
	}

	@Override
	public void decorate(World par1World, Random par2Random, int chunk_X,
			int chunk_Z) {
		int i, j, k, l, i1;

		if (par2Random.nextInt(4) != 0) {
			return;
		}

		i = this.susukiPerChunk;
		for (j = 0; j < i; ++j) {
			k = chunk_X + par2Random.nextInt(16) + 8;
			l = chunk_Z + par2Random.nextInt(16) + 8;
			WorldGenerator worldgenerator = new WorldGenSusuki();
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(par1World, par2Random, k,
					par1World.getHeightValue(k, l), l);
		}
	}

}
