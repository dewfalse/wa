package wa.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

public class ItemRequor extends ItemLiquorBase {

    List<PotionEffect> potionEffects = new LinkedList<PotionEffect>();

	public ItemRequor() {
		super();
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote) {
			addPotionEffects(par1ItemStack, par3EntityPlayer);
		}

		return par1ItemStack.stackSize <= 0 ? null : par1ItemStack;
	}

    public ItemRequor addPotionEffect(int potionID, int duration, int amplifier) {
        potionEffects.add(new PotionEffect(potionID, duration, amplifier));
        return this;
    }

    public ItemRequor addPotionEffect(PotionEffect potionEffect) {
        potionEffects.add(potionEffect);
        return this;
    }

    public void addPotionEffects(ItemStack par1ItemStack,
			EntityPlayer par3EntityPlayer) {
        for(PotionEffect potionEffect : potionEffects) {
            par3EntityPlayer.addPotionEffect(potionEffect);
        }
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(Items.glass_bottle, itemStack.stackSize, 0);
	}
}
