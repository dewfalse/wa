package wa;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

public class ItemKanazuchi extends ItemTool {
	static Block[] blocks = {Blocks.kera};

	public ItemKanazuchi(int par1, int par2) {
		super(par1, par2, Items.tamahagane, blocks);
	}

}
