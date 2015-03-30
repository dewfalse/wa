package wa.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockTatami extends BlockSlab {

	IIcon[] icons;

	public BlockTatami(boolean par2, Material par3Material) {
		super(par2, par3Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return icons[0];
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[1];
		icons[0] = par1IconRegister.registerIcon("wa:tatami");
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(this);
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this, 2, par1 & 7);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		return true;
	}

    @Override
	public String func_150002_b(int i) {
		return super.getUnlocalizedName() + "." + "tatami";
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4) {
		//return Blocks.畳.blockID;
		return null;
	}

}
