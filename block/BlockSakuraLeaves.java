package wa.block;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class BlockSakuraLeaves extends BlockLeaves {

	IIcon theIcon;
    public static final String[] name = new String[] {"sakura"};

	protected BlockSakuraLeaves() {
		super();
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
	public IIcon getIcon(int par1, int par2) {
		return theIcon;
	}

    @Override
    public String[] func_150125_e() {
        return name;
    }

    @Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		theIcon = par1IconRegister.registerIcon("wa:sakuraLeaves");
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
			int par4, int par5, float par6, int par7) {
		if (!par1World.isRemote) {
			if (par1World.rand.nextInt(20) == 0) {
				dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(
                        Blocks.sakuraSapling));
			}
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.sakuraSapling);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}
}
