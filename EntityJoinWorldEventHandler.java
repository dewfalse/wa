package wa;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityJoinWorldEventHandler {

	@ForgeSubscribe
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityPlayer) {
			if(event.entity.dimension == Config.dimensionID) {
				((EntityPlayer)event.entity).addStat(Achievements.travelToDimension, 1);
			}
		}
		if(event.entity instanceof EntityZombie) {
			EntityZombie entity = (EntityZombie)event.entity;
			ItemStack itemStack = entity.getCurrentItemOrArmor(0);
			if(itemStack == null) {
				if(event.world.rand.nextDouble() < (event.world.difficultySetting == 3 ? 0.05F : 0.01F)) {
					entity.setCurrentItemOrArmor(0, new ItemStack(Items.刀));
				}
			}
		}
		if(event.entity instanceof EntityVillager && event.entity.dimension == Config.dimensionID) {
			switch(event.entity.worldObj.rand.nextInt(5)) {
			case 0:
				((EntityVillager)event.entity).setProfession(Config.刀鍛冶ID);
				break;
			case 1:
				((EntityVillager)event.entity).setProfession(Config.茶人ID);
				break;
			default:
				((EntityVillager)event.entity).setProfession(Config.町人ID);
			}
		}
	}
}
