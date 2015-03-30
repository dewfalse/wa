package wa.block;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.awt.*;

public class BlockSakuraSapling extends BlockSapling {

	protected BlockSakuraSapling() {
		super();
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wa:sakuraSapling");
	}

	@Override
	public int getBlockColor() {
		return Color.WHITE.getRGB();
	}

	@Override
	public int getRenderColor(int par1) {
		return Color.WHITE.getRGB();
	}

	@Override
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4) {
		return Color.WHITE.getRGB();
	}

}
