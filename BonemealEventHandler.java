package wa;

import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BonemealEventHandler {

	@ForgeSubscribe
	public void onBonemealEvent(BonemealEvent event) {
		if(event.world.getBlockId(event.X, event.Y, event.Z) == Blocks.sakuraSapling.blockID) {
			if ((double)event.world.rand.nextFloat() < 0.45D) {
				WorldGenerator g = new WorldGeneratorSakuraTrees(true);
				event.world.setBlock(event.X, event.Y, event.Z, 0, 0, 4);
				g.generate(event.world, event.world.rand, event.X, event.Y, event.Z);
				event.setResult(Result.ALLOW);
			}
		}

	}

}
