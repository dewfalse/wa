package wa;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class WaCreativeTabs extends CreativeTabs {

	public WaCreativeTabs() {
		super("和");
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Items.食べ物);
	}

	@Override
	public String getTranslatedTabLabel() {
		return "和";
	}

}
