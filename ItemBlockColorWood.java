package wa;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockColorWood extends ItemBlock {

	public ItemBlockColorWood(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".")
				.append(dyeColorNames[itemstack.getItemDamage()]).toString();
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return Blocks.colorWood[0].getIcon(2, i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	public static final String dyeColorNames[] = {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
}
