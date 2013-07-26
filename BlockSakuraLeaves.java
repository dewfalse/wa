package wa;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSakuraLeaves extends BlockLeaves {

	Icon theIcon;

	protected BlockSakuraLeaves(int par1) {
		super(par1);
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

	@Override
	public Icon getIcon(int par1, int par2) {
		return theIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		theIcon = par1IconRegister.registerIcon("wa:sakuraLeaves");
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
			int par4, int par5, float par6, int par7) {
		if (!par1World.isRemote) {
			if (par1World.rand.nextInt(20) == 0) {
				dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(
						Blocks.sakuraSapling));
			}
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Blocks.sakuraSapling.blockID;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}
}
