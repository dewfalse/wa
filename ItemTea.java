package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemTea extends Item {

	public ItemTea(int par1) {
		super(par1);
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote) {
			addPotionEffects(par1ItemStack, par2World, par3EntityPlayer);
		}

		return par1ItemStack.stackSize <= 0 ? null : par1ItemStack;
	}

	private void addPotionEffects(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300, 0));
		if(par2World.rand.nextInt(10) == 0) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 300, 0));
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
	public ItemStack getContainerItemStack(ItemStack itemStack) {
		return new ItemStack(Items.茶碗, itemStack.stackSize, 0);
	}
}
