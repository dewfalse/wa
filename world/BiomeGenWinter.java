package wa.world;

import net.minecraft.world.biome.BiomeGenTaiga;
import wa.block.Blocks;

public class BiomeGenWinter extends BiomeGenTaiga {

	public BiomeGenWinter(int par1, int par2) {
		super(par1, par2);
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 4;
		this.topBlock = Blocks.snow;
	}

}
