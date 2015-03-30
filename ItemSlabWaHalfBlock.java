package wa;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;

public class ItemSlabWaHalfBlock extends ItemSlab {

	public ItemSlabWaHalfBlock(Block par1, BlockSlab par2BlockHalfSlab,
			BlockSlab par3BlockHalfSlab, boolean par4) {
		super(par1, par2BlockHalfSlab, par3BlockHalfSlab, par4);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return Wa.creativeTab;
	}

}
