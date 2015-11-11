package wa.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import wa.block.Blocks;
import wa.world.WorldGeneratorSakuraTrees;
import wa.world.WorldGeneratorUmeTrees;

public class SaplingGrowTreeEventHandler {

	@SubscribeEvent
	public void onSaplingGrowTreeEvent(SaplingGrowTreeEvent event) {
		if(event.world.getBlock(event.x, event.y, event.z) == Blocks.sakuraSapling) {
			(new WorldGeneratorSakuraTrees(true)).generate(event.world, event.rand, event.x, event.y, event.z);
			event.setResult(Event.Result.DENY);
		}
		else if(event.world.getBlock(event.x, event.y, event.z) == Blocks.umeSapling) {
			(new WorldGeneratorUmeTrees(true)).generate(event.world, event.rand, event.x, event.y, event.z);
			event.setResult(Event.Result.DENY);
		}

	}
}
