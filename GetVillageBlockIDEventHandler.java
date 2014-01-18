package wa;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.BiomeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GetVillageBlockIDEventHandler {

	@ForgeSubscribe
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event) {

		if (event.biome == null) {
			return;
		}
		boolean found = false;
		for(BiomeGenBase biome : WorldChunkManagerWa.waBiomeList) {
			if(event.biome.biomeID == biome.biomeID) {
				found = true;
				break;
			}
		}
		if(found == false) {
			return;
		}

		if (event.original == Block.wood.blockID) {
			event.replacement = Blocks.colorWood[1].blockID;
		} else if (event.original == Block.cobblestone.blockID) {
			event.replacement = Blocks.sakuraWood.blockID;
		} else if (event.original == Block.planks.blockID) {
			event.replacement = Blocks.shikui.blockID;
		} else if (event.original == Block.stairsWoodOak.blockID) {
			event.replacement = Blocks.wara.blockID;
		} else if (event.original == Block.stairsCobblestone.blockID) {
			return;
		} else if (event.original == Block.gravel.blockID) {
			event.replacement = Block.sand.blockID;
		} else if (event.original == Block.dirt.blockID) {
			event.replacement = Block.sand.blockID;
		} else if (event.original == Block.furnaceIdle.blockID) {
			event.replacement = Blocks.taiko.blockID;
		} else if (event.original == Block.carrot.blockID) {
			event.replacement = Blocks.ine.blockID;
		} else if (event.original == Block.potato.blockID) {
			event.replacement = Blocks.ine.blockID;
		} else if (event.original == Block.crops.blockID) {
			event.replacement = Blocks.ine.blockID;
		} else if (event.original == Block.thinGlass.blockID) {
			return;
		} else if (event.original == Block.stoneDoubleSlab.blockID) {
			event.replacement = Blocks.BlockWaDoubleBlock.blockID;
		} else if (event.original == Block.stoneSingleSlab.blockID) {
			event.replacement = Blocks.sakuraPlank.blockID;
		} else if (event.original == Block.fence.blockID) {
			return;
		} else if (event.original == Block.bookShelf.blockID) {
			return;
		} else if (event.original == Block.waterMoving.blockID) {
			return;
		} else {
			return;
		}

		event.setResult(Result.DENY);
	}

	@ForgeSubscribe
	public void getVillageBlockID(BiomeEvent.GetVillageBlockMeta event) {

		if (event.biome == null) {
			return;
		}
		boolean found = false;
		for(BiomeGenBase biome : WorldChunkManagerWa.biomeList) {
			if(event.biome.biomeID == biome.biomeID) {
				found = true;
				break;
			}
		}
		if(found == false) {
			return;
		}

		if (event.original == Block.stairsWoodOak.blockID) {
			event.replacement = 0;
		} else if (event.original == Block.furnaceIdle.blockID) {
			event.replacement = 4;
		} else {
			return;
		}

		event.setResult(Result.DENY);
	}
}
