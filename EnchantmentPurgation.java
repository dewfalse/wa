package wa;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import wa.block.Blocks;

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
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack.getItem() == Item.getItemFromBlock(Blocks.charm) || stack.getItem() == Blocks.itemBlockCharm;
	}

	@Override
	public boolean canApplyTogether(Enchantment par1Enchantment) {
		return false;
	}

	@Override
	public boolean canApply(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == Item.getItemFromBlock(Blocks.charm) || par1ItemStack.getItem() == Blocks.itemBlockCharm;
	}
}
