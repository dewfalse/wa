package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSusuki extends Block {

	public BlockSusuki(Material par2Material) {
		super(par2Material);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		Block block = par1World.getBlock(par2, par3 - 1, par4);
		if(block == this || block == Blocks.dirt || block == Blocks.grass) {
			return true;
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, Block par5) {
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
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.susuki);
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
	public Item getItem(World par1World, int par2, int par3, int par4) {
		return Item.getItemFromBlock(Blocks.susuki);
	}
}
