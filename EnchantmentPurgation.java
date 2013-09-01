package wa;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class EnchantmentPurgation extends Enchantment {

	public EnchantmentPurgation(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) {
		super(par1, par2, par3EnumEnchantmentType);
		setName("purgation");
	}

	@Override
	public String getTranslatedName(int par1) {
		return "Purgation" + " " + StatCollector.translateToLocal("enchantment.level." + par1);
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 1 + (par1 - 1) * 10;
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return 10 + (par1 - 1) * 10;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public boolean canApplyTogether(Enchantment par1Enchantment) {
		return false;
	}

	@Override
	public boolean canApply(ItemStack par1ItemStack) {
		return par1ItemStack.itemID == Blocks.charm.blockID || par1ItemStack.itemID == Blocks.itemBlockCharm.itemID;
	}
}
