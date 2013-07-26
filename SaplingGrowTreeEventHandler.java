package wa;

import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;

public class SaplingGrowTreeEventHandler {

	@ForgeSubscribe
	public void onSaplingGrowTreeEvent(SaplingGrowTreeEvent event) {
		if(event.world.getBlockId(event.x, event.y, event.z) == Blocks.sakuraSapling.blockID) {
			(new WorldGeneratorSakuraTrees(true)).generate(event.world, event.rand, event.x, event.y, event.z);
			event.setResult(Result.DENY);
		}

	}
}
