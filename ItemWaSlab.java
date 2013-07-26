package wa;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;

public class ItemWaSlab extends ItemSlab {

	public ItemWaSlab(int par1, BlockHalfSlab par2BlockHalfSlab,
			BlockHalfSlab par3BlockHalfSlab, boolean par4) {
		super(par1, par2BlockHalfSlab, par3BlockHalfSlab, par4);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return new CreativeTabs[] {Wa.creativeTab};
	}
}
