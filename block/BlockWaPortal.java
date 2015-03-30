package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wa.Config;
import wa.TeleporterEx;

import java.util.Random;

public class BlockWaPortal extends Block {
	ForgeDirection[] dirs = {ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.EAST,ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.SOUTH};

	public BlockWaPortal(Material par2Material) {
		super(par2Material);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
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
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, Block par5) {
		boolean found = false;
		for(ForgeDirection dir : dirs) {
			Block block = par1World.getBlock(par2 + dir.offsetX, par3 + dir.offsetY, par4 + dir.offsetZ);
			if(block == Blocks.colorWood[1]) {
				return;
			}
		}
		par1World.setBlockToAir(par2, par3, par4);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		return true;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {
		long worldTime = par1World.provider.getWorldTime() % 24000;
		if(12000 <= worldTime && worldTime <= 13500) {
			TeleporterEx.transferEntityToWorld(par5Entity, par1World.provider.dimensionId == Config.dimensionID ? 0 : Config.dimensionID);
		}
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		long worldTime = par1World.provider.getWorldTime() % 24000;
		if(12000 <= worldTime && worldTime <= 13500) {
			if (par5Random.nextInt(100) == 0) {
				par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D,
						(double) par4 + 0.5D, "portal.portal", 0.5F,
						par5Random.nextFloat() * 0.4F + 0.8F, false);
			}

			for (int l = 0; l < 4; ++l) {
				double d0 = (double) ((float) par2 + par5Random.nextFloat());
				double d1 = (double) ((float) par3 + par5Random.nextFloat());
				double d2 = (double) ((float) par4 + par5Random.nextFloat());
				double d3 = 0.0D;
				double d4 = 0.0D;
				double d5 = 0.0D;
				int i1 = par5Random.nextInt(2) * 2 - 1;
				d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
				d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
				d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;

				if (par1World.getBlock(par2 - 1, par3, par4) != this
						&& par1World.getBlock(par2 + 1, par3, par4) != this) {
					d0 = (double) par2 + 0.5D + 0.25D * (double) i1;
					d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
				} else {
					d2 = (double) par4 + 0.5D + 0.25D * (double) i1;
					d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
				}

				par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
			}
		}
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wa:portal");
	}
}
