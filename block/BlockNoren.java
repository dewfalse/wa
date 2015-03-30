package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;


public class BlockNoren extends BlockBreakable {

	public static int renderID;

	public BlockNoren(Material par2Material) {
		super("wa:noren", par2Material, false);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return renderID;
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
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float minX = 1.0F;
		float minY = 1.0F;
		float minZ = 1.0F;
		float maxX = 0.0F;
		float maxY = 0.0F;
		float maxZ = 0.0F;

		if ((metadata & 1) != 0) {
			minZ = 0.4375F;
			maxZ = 0.5625F;
			minX = 0.0F;
			maxX = 1.0F;
			minY = 0.0F;
			maxY = 1.0F;
		}
		else {
			minX = 0.4375F;
			maxX = 0.5625F;
			minY = 0.0F;
			maxY = 1.0F;
			minZ = 0.0F;
			maxZ = 1.0F;
		}

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	return true;
    }

    @Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
    	int metadata = par1World.getBlockMetadata(par2, par3, par4);
    	if(metadata > 0) {
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
    	}
    	else {
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
    	}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return canStay(par1World, par2, par3, par4);
	}

	private boolean canStay(World par1World, int par2, int par3, int par4) {
		Block block = par1World.getBlock(par2, par3 + 1, par4);
		if(block != null && block.isNormalCube(par1World, par2, par3 + 1, par4)) {
			return true;
		}
		ForgeDirection n = ForgeDirection.NORTH;
		ForgeDirection s = ForgeDirection.SOUTH;
		ForgeDirection e = ForgeDirection.EAST;
		ForgeDirection w = ForgeDirection.WEST;
		Block blockS = par1World.getBlock(par2 + s.offsetX, par3 + s.offsetY, par4 + s.offsetZ);
		Block blockW = par1World.getBlock(par2 + w.offsetX, par3 + w.offsetY, par4 + w.offsetZ);
		Block blockE = par1World.getBlock(par2 + e.offsetX, par3 + e.offsetY, par4 + e.offsetZ);
		Block blockN = par1World.getBlock(par2 + n.offsetX, par3 + n.offsetY, par4 + n.offsetZ);
		if(blockS != null && blockN != null
				&& blockS.isNormalCube(par1World,par2 + s.offsetX, par3 + s.offsetY, par4 + s.offsetZ)
				&& blockN.isNormalCube(par1World,par2 + n.offsetX, par3 + n.offsetY, par4 + n.offsetZ)) {
			return true;
		}
		if(blockE != null && blockW != null
				&& blockE.isNormalCube(par1World,par2 + e.offsetX, par3 + e.offsetY, par4 + e.offsetZ)
				&& blockW.isNormalCube(par1World,par2 + w.offsetX, par3 + w.offsetY, par4 + w.offsetZ)) {
			return true;
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, Block par5) {
		if (!par1World.isRemote
				&& !this.canStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4,
					par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4,
			int par5, float par6, float par7, float par8, int par9) {
		byte b0 = 0;

		switch (par5) {
		case 2:
			b0 = 1;
			break;
		case 3:
			b0 = 4;
			break;
		case 4:
			b0 = 8;
			break;
		case 5:
			b0 = 2;
		}

		return b0 != 0 ? b0 : par9;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.noren);
	}

}
