package wa.block;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.awt.*;

public class BlockUmeSapling extends BlockSapling {

	protected BlockUmeSapling() {
		super();
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wa:umeSapling");
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
