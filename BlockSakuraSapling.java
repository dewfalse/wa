package wa;

import java.awt.Color;

import net.minecraft.block.BlockSapling;
import net.minecraft.world.IBlockAccess;

public class BlockSakuraSapling extends BlockSapling {

	protected BlockSakuraSapling(int par1) {
		super(par1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int getBlockColor() {
		return Color.PINK.getRGB();
	}

	@Override
	public int getRenderColor(int par1) {
		return Color.PINK.getRGB();
	}

	@Override
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4) {
		return Color.PINK.getRGB();
	}

}
