package wa;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSusuki extends Block {

	public BlockSusuki(int par1, Material par2Material) {
		super(par1, par2Material);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		int blockID = par1World.getBlockId(par2, par3 - 1, par4);
		if(blockID == this.blockID || blockID == Block.dirt.blockID || blockID == Block.grass.blockID) {
			return true;
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		return this.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Blocks.susuki.blockID;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return Blocks.susuki.blockID;
	}
}
