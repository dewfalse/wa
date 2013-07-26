package wa;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenTaiga;

public class BiomeGenWinter extends BiomeGenTaiga {

	public BiomeGenWinter(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 4;
		this.topBlock = (byte) Block.blockSnow.blockID;
	}

}
