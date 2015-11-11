package wa.item;

import net.minecraft.block.Block;
import wa.block.BlockSlabBase;
import wa.block.Blocks;

public class ItemTatami extends ItemSlabBase {

	public ItemTatami(Block block) {
		super(block);
	}

	@Override
	protected BlockSlabBase getHalf() {
		return Blocks.BlockWaHalfBlock;
	}

	@Override
	protected BlockSlabBase getDouble() {
		return Blocks.BlockWaDoubleBlock;
	}

}
