package wa.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wa.Config;
import wa.TeleporterEx;

public class ItemMagatama extends Item {

	public ItemMagatama() {
		super();
		setMaxDamage(14);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if(par3EntityPlayer.dimension != Config.dimensionID) {
			if(TeleporterEx.transferEntityToWorld(par3EntityPlayer, Config.dimensionID)) {
				par1ItemStack.damageItem(1, par3EntityPlayer);
				return par1ItemStack;
			}
		}
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
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
