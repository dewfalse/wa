package wa.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.entity.player.BonemealEvent;
import wa.block.Blocks;
import wa.world.WorldGeneratorSakuraTrees;
import wa.world.WorldGeneratorUmeTrees;

public class BonemealEventHandler {

	@SubscribeEvent
	public void onBonemealEvent(BonemealEvent event) {
		if(event.world.isRemote) {
			return;
		}
		if(event.world.getBlock(event.x, event.y, event.z) == Blocks.sakuraSapling) {
			if ((double)event.world.rand.nextFloat() < 0.45D) {
				WorldGenerator g = new WorldGeneratorSakuraTrees(true);
				event.world.setBlockToAir(event.x, event.y, event.z);
				g.generate(event.world, event.world.rand, event.x, event.y, event.z);
				event.setResult(Event.Result.ALLOW);
			}
		}
		else if(event.world.getBlock(event.x, event.y, event.z) == Blocks.umeSapling) {
			if ((double)event.world.rand.nextFloat() < 0.45D) {
				WorldGenerator g = new WorldGeneratorUmeTrees(true);
				event.world.setBlockToAir(event.x, event.y, event.z);
				g.generate(event.world, event.world.rand, event.x, event.y, event.z);
				event.setResult(Event.Result.ALLOW);
			}
		}
		else if(event.world.getBlock(event.x, event.y, event.z) == Blocks.takenoko) {
			if(event.world.isRemote == false) {
				if ((double)event.world.rand.nextFloat() < 0.45D) {
					event.world.setBlock(event.x, event.y, event.z, Blocks.take);
					event.setResult(Event.Result.ALLOW);
				}
			}
		}
	}
}
