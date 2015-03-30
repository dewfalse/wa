package wa;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LivingDeathEventHandler {

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if(event.entityLiving.worldObj.isRemote) {
			return;
		}
		if(event.entityLiving.dimension == Config.dimensionID) {
			if(event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton) {
				if(event.entityLiving.worldObj.rand.nextInt(10) == 0) {
					event.entityLiving.dropItem(Items.貨幣, 1 + event.entityLiving.worldObj.rand.nextInt(16));
				}
			}
		}
        if(event.entityLiving instanceof EntityCreeper) {
            if(((EntityCreeper)event.entityLiving).getPowered()) {
                for(int i = 0; i < event.entityLiving.worldObj.rand.nextInt(17); ++i) {
                    event.entityLiving.dropItem(Items.磁鉄鉱インゴット, 1);
                }
            }
        }
	}
}
