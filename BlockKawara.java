package wa;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockKawara extends Block {

	public static int renderID;

	public BlockKawara(int par1, Material par2Material) {
		super(par1, par2Material);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        setLightOpacity(3);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		boolean flag = (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 4) != 0;

		if (flag) {
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		int l = MathHelper
				.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int i1 = par1World.getBlockMetadata(par2, par3, par4) & 4;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | i1, 2);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | i1, 2);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | i1, 2);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | i1, 2);
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4,
			int par5, float par6, float par7, float par8, int par9) {
		return par5 != 0 && (par5 == 1 || (double) par7 <= 0.5D) ? par9
				: par9 | 4;
	}
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3,
			int par4, AxisAlignedBB par5AxisAlignedBB, List par6List,
			Entity par7Entity) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		super.addCollisionBoxesToList(par1World, par2, par3, par4,
				par5AxisAlignedBB, par6List, par7Entity);
	}
}
