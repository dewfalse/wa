package wa.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wa.Config;
import wa.TeleporterEx;

public class ItemMirror extends Item {

	public ItemMirror() {
		super();
		setMaxDamage(14);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
			Entity entity) {
		if(entity.dimension != Config.dimensionID) {
			if(TeleporterEx.transferEntityToWorld(entity, Config.dimensionID)) {
				stack.damageItem(1, player);
				return true;
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

}
