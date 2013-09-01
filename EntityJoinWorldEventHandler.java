package wa;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityJoinWorldEventHandler {

	public static Map<Integer, Set<PathPoint>> charmsInDim = new LinkedHashMap();

	@ForgeSubscribe
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if(event.entity instanceof IMob) {
			if(event.entity instanceof IBossDisplayData == false) {
				Set<PathPoint> list = charmsInDim.get(event.entity.dimension);
				if(list != null) {
					for(PathPoint point : list) {
						if(Math.abs(point.xCoord - event.entity.posX) < 8.0D && Math.abs(point.yCoord - event.entity.posY) < 8.0D && Math.abs(point.zCoord - event.entity.posZ) < 8.0D) {
							TileEntity tile = event.world.getBlockTileEntity(point.xCoord, point.yCoord, point.zCoord);
							if(tile != null && tile instanceof TileEntityCharm) {
								if(((TileEntityCharm)tile).charmLevel < 5) {
									((TileEntityCharm)tile).damage += ((EntityLiving)event.entity).func_110138_aP() / ((TileEntityCharm)tile).charmLevel;
								}
							}
							event.setCanceled(true);
							return;
						}
					}
				}
			}

		}
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
			switch(event.entity.worldObj.rand.nextInt(6)) {
			case 0:
				((EntityVillager)event.entity).setProfession(Config.刀鍛冶ID);
				break;
			case 1:
				((EntityVillager)event.entity).setProfession(Config.茶人ID);
				break;
			case 2:
				((EntityVillager)event.entity).setProfession(Config.神官ID);
				break;
			default:
				((EntityVillager)event.entity).setProfession(Config.町人ID);
			}
		}
	}
}
