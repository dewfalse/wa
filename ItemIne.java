package wa;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIne extends Item {

	public ItemIne(int par1) {
		super(par1);
	}

	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack) {
		return new ItemStack(Blocks.wara);
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

}
