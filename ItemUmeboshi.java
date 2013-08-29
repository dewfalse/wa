package wa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemUmeboshi extends Item {

	Potion[] curableEffects = {Potion.moveSlowdown, Potion.digSlowdown, Potion.blindness, Potion.weakness, Potion.wither};

	public ItemUmeboshi(int par1) {
		super(par1);
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote) {
			curePotionEffects(par1ItemStack, par3EntityPlayer);
		}

		return par1ItemStack.stackSize <= 0 ? null : par1ItemStack;
	}

	private void curePotionEffects(ItemStack par1ItemStack,
			EntityPlayer par3EntityPlayer) {
		Collection c = par3EntityPlayer.getActivePotionEffects();
		List<Integer> potionIDs = new ArrayList();
		for(Object obj : c) {
			if(obj instanceof PotionEffect) {
				String effectName = ((PotionEffect)obj).getEffectName();
				for(Potion potion : curableEffects) {
					if(effectName.equalsIgnoreCase(potion.getName())) {
						potionIDs.add(((PotionEffect)obj).getPotionID());
					}
				}
			}
		}
		for(int id : potionIDs) {
			par3EntityPlayer.removePotionEffect(id);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
}
