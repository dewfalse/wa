package wa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMirror extends Item {

	public ItemMirror(int par1) {
		super(par1);
		setMaxDamage(30);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
			Entity entity) {
		if(entity instanceof EntityLiving || entity instanceof EntityMinecart) {
			if(entity instanceof IBossDisplayData == false) {
				if(entity.dimension != Config.dimensionID) {
					TeleporterEx.transferEntityToWorld(entity, Config.dimensionID);
					stack.damageItem(1, player);
					return true;
				}
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

}
