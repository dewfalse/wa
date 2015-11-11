package wa.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wa.block.Blocks;

public class ItemIne extends Item {

	public ItemIne() {
		super();
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(Blocks.wara);
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

}
