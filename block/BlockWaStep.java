package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockWaStep extends BlockSlab {

	public static final String[] blockStepTypes = new String[] { "tatami"/*"zabuton"*/ };
	private IIcon[] icons;

	public BlockWaStep(boolean par2) {
		super(par2, Material.grass);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return icons[par2 & 7];
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.BlockWaHalfBlock);
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this, 2, par1 & 7);
	}

	@Override
	public String func_150002_b(int par1) {
		if (par1 < 0 || par1 >= blockStepTypes.length) {
			par1 = 0;
		}

		return super.getUnlocalizedName() + "." + blockStepTypes[par1];
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		if (this.field_150004_a) {
			return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
		} else if (par5 != 1 && par5 != 0 && !super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5)) {
			return false;
		} else {
			int i1 = par2 + Facing.offsetsXForSide[Facing.oppositeSide[par5]];
			int j1 = par3 + Facing.offsetsYForSide[Facing.oppositeSide[par5]];
			int k1 = par4 + Facing.offsetsZForSide[Facing.oppositeSide[par5]];
			boolean flag = (par1IBlockAccess.getBlockMetadata(i1, j1, k1) & 8) != 0;
			if((par1IBlockAccess.getBlockMetadata(i1, j1, k1) & 8) != 0) {
				if(par5 == 0) {
					return true;
				}
				else if(par5 == 1 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5)){
					return true;
				}
				return !isBlockSingleSlab(par1IBlockAccess.getBlock(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) == 0;
			}
			else {
				if(par5 == 1) {
					return true;
				}
				else if(par5 == 0 && super.shouldSideBeRendered(par1IBlockAccess,par2, par3, par4, par5)) {
					return true;

				}
				return !isBlockSingleSlab(par1IBlockAccess.getBlock(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0;
			}
		}
	}

	private static boolean isBlockSingleSlab(Block par0) {
		return par0 == Blocks.BlockWaHalfBlock;
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4) {
		return Item.getItemFromBlock(Blocks.BlockWaHalfBlock);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		if (isBlockSingleSlab(Block.getBlockFromItem(par1))) {
			for (int var4 = 0; var4 < blockStepTypes.length; var4++) {
				par3List.add(new ItemStack(par1, 1, var4));
			}
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.icons = new IIcon[blockStepTypes.length];
		for (int i = 0; i < blockStepTypes.length; ++i) {
			this.icons[i] = par1IconRegister.registerIcon("wa:" + blockStepTypes[i]);
		}
	}
}
