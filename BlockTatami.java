package wa;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTatami extends BlockHalfSlab {

	Icon[] icons;

	public BlockTatami(int par1, boolean par2, Material par3Material) {
		super(par1, par2, par3Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[0];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[1];
		icons[0] = par1IconRegister.registerIcon("wa:tatami");
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 2, par1 & 7);
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		return true;
	}

	@Override
	public String getFullSlabName(int i) {
		return super.getUnlocalizedName() + "." + "tatami";
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		//return Blocks.畳.blockID;
		return 0;
	}

}
