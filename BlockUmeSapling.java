package wa;

import java.awt.Color;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockUmeSapling extends BlockSapling {

	protected BlockUmeSapling(int par1) {
		super(par1);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
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
