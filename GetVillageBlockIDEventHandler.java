package wa;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.BiomeEvent;
import wa.block.Blocks;

public class GetVillageBlockIDEventHandler {

	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event) {

		if (event == null) {
			return;
		}
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

		if (event.original == Blocks.planks) {
			event.replacement = Blocks.colorWood[1];
		} else if (event.original == Blocks.cobblestone) {
			event.replacement = Blocks.sakuraWood;
		} else if (event.original == Blocks.planks) {
			event.replacement = Blocks.shikui;
		} else if (event.original == Blocks.oak_stairs) {
			event.replacement = Blocks.wara;
		} else if (event.original == Blocks.stone_stairs) {
			return;
		} else if (event.original == Blocks.gravel) {
			event.replacement = Blocks.sand;
		} else if (event.original == Blocks.dirt) {
			event.replacement = Blocks.sand;
		} else if (event.original == Blocks.furnace) {
			event.replacement = Blocks.taiko;
		} else if (event.original == Blocks.carrots) {
			event.replacement = Blocks.ine;
		} else if (event.original == Blocks.potatoes) {
			event.replacement = Blocks.ine;
		} else if (event.original == Blocks.wheat) {
			event.replacement = Blocks.ine;
		} else if (event.original == Blocks.glass_pane) {
			return;
		} else if (event.original == Blocks.double_stone_slab) {
			event.replacement = Blocks.BlockWaDoubleBlock;
		} else if (event.original == Blocks.stone_slab) {
			event.replacement = Blocks.sakuraPlank;
		} else if (event.original == Blocks.fence) {
			return;
		} else if (event.original == Blocks.bookshelf) {
			return;
		} else if (event.original == Blocks.flowing_water) {
			return;
		} else {
			return;
		}

		event.setResult(Event.Result.DENY);
	}

	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockMeta event) {

		if (event == null) {
			return;
		}
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

		if (event.original == Blocks.oak_stairs) {
			event.replacement = 0;
		} else if (event.original == Blocks.furnace) {
			event.replacement = 4;
		} else {
			return;
		}

		event.setResult(Event.Result.DENY);
	}
}
