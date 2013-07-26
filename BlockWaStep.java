package wa;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWaStep extends BlockHalfSlab {

	public static final String[] blockStepTypes = new String[] { "tatami"/*"zabuton"*/ };
	private Icon[] icons;

	public BlockWaStep(int par1, boolean par2) {
		super(par1, par2, Material.grass);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[par2 & 7];
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Blocks.BlockWaHalfBlock.blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 2, par1 & 7);
	}

	@Override
	public String getFullSlabName(int par1) {
		if (par1 < 0 || par1 >= blockStepTypes.length) {
			par1 = 0;
		}

		return super.getUnlocalizedName() + "." + blockStepTypes[par1];
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		if (this.isDoubleSlab) {
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
				return !isBlockSingleSlab(par1IBlockAccess.getBlockId(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) == 0;
			}
			else {
				if(par5 == 1) {
					return true;
				}
				else if(par5 == 0 && super.shouldSideBeRendered(par1IBlockAccess,par2, par3, par4, par5)) {
					return true;

				}
				return !isBlockSingleSlab(par1IBlockAccess.getBlockId(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0;
			}
		}
	}

	private static boolean isBlockSingleSlab(int par0) {
		return par0 == Blocks.BlockWaHalfBlock.blockID;
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return Blocks.BlockWaHalfBlock.blockID;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		if (isBlockSingleSlab(par1)) {
			for (int var4 = 0; var4 < blockStepTypes.length; var4++) {
				par3List.add(new ItemStack(par1, 1, var4));
			}
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.icons = new Icon[blockStepTypes.length];
		for (int i = 0; i < blockStepTypes.length; ++i) {
			this.icons[i] = par1IconRegister.registerIcon("wa:" + blockStepTypes[i]);
		}
	}
}
