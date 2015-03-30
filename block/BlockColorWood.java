package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;
import java.util.Random;

public class BlockColorWood extends BlockLog {

	private IIcon[] icons;
	int color;


	protected BlockColorWood(int par2) {
		super();
		color = par2;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public int getRenderColor(int par1) {
		return ItemDye.field_150922_c[color];
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4) {
		return ItemDye.field_150922_c[color];
	}

    /**
     * Args: side, metadata
     */
	@Override
	public IIcon getIcon(int par1, int par2) {
		int k = par2 & 12;
		int l = par2 & 3;
		switch(k) {
		case 0:
			if(par1 == 1 || par1 == 0) {
				return icons[0];
			}
			break;
		case 4:
			if(par1 == 5 || par1 == 4) {
				return icons[0];
			}
			break;
		case 8:
			if(par1 == 2 || par1 == 3) {
				return icons[0];
			}
			break;
		}
		return icons[1];
}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("wa:paintedTreeTop");
		icons[1] = par1IconRegister.registerIcon("wa:paintedTreeSide");
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(this);
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this, 1, par1);
	}

}
