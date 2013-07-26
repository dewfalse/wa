package wa;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LivingDeathEventHandler {

	@ForgeSubscribe
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if(event.entityLiving.dimension == Config.dimensionID) {
			if(event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton) {
				if(event.entityLiving.worldObj.rand.nextInt(10) == 0) {
					event.entityLiving.dropItem(Items.貨幣.itemID, 1 + event.entityLiving.worldObj.rand.nextInt(16));
				}
			}
		}
	}
}
