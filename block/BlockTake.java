package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockTake extends Block {

	public BlockTake(Material par2Material) {
		super(par2Material);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		if (par1World.isAirBlock(par2, par3 + 1, par4)) {
			int l;

			for (l = 1; par1World.getBlock(par2, par3 - l, par4) == this; ++l) {
				;
			}

			if (l < 15) {
				int i1 = par1World.getBlockMetadata(par2, par3, par4);

				if (i1 == 1) {
					par1World.setBlock(par2, par3 + 1, par4, this);
					par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
				} else {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 1, 4);
				}
			}else {
				if(par5Random.nextInt(1000) == 0) {
					ForgeDirection[] dirs = {ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
					ForgeDirection dir = dirs[par5Random.nextInt(dirs.length)];
					int i1 = par2 + dir.offsetX;
					int j1 = par3 + dir.offsetY;
					int k1 = par4 + dir.offsetZ;
					if (par1World.isAirBlock(i1, j1, k1) && par1World.getBlock(i1, j1 - 1, k1) == Blocks.grass && Blocks.takenoko.canPlaceBlockAt(par1World, i1, j1, k1)) {
						par1World.setBlock(i1, j1, k1, Blocks.takenoko);
					}
				}
			}
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5) {
		// TODO 自動生成されたメソッド・スタブ
		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2,
			int par3, int par4, Explosion par5Explosion) {
		// TODO 自動生成されたメソッド・スタブ
		super.onBlockDestroyedByExplosion(par1World, par2, par3, par4, par5Explosion);
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return true;
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
		Block block = par1World.getBlock(par2, par3 - 1, par4);
		if(block == this || block == Blocks.dirt || block == Blocks.grass || block == Blocks.farmland) {
			return true;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.take);
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
		return Item.getItemFromBlock(Blocks.take);
	}
}
