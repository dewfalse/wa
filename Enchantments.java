package wa;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class Enchantments {

	public static final Enchantment purgation = new EnchantmentPurgation(Config.echantmentBaseID, 10, EnumEnchantmentType.all);//mobのスポーンを阻害する

	public static void init() {
	}
}
