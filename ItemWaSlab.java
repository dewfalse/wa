package wa;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;
import wa.block.BlockWaStep;

public class ItemWaSlab extends ItemSlab {

	public ItemWaSlab(Block par1, BlockWaStep par2BlockHalfSlab,
                      BlockWaStep par3BlockHalfSlab, Boolean par4) {
		super(par1, par2BlockHalfSlab, par3BlockHalfSlab, par4.booleanValue());
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return new CreativeTabs[] {Wa.creativeTab};
	}
}
