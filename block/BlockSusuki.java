package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSusuki extends Block {
	
	public static int renderID;
	@SideOnly(Side.CLIENT)
	private IIcon[] iconType;

	public BlockSusuki(Material par2Material) {
		super(par2Material);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		Block block = par1World.getBlock(par2, par3 - 1, par4);
		if(block == this || block == Blocks.dirt || block.getMaterial() == Material.grass) {
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
		return renderID;
	}

	@Override
	public Item getItem(World par1World, int par2, int par3, int par4) {
		return Item.getItemFromBlock(Blocks.susuki);
	}
	
	
	/**
	 * 機能追加部分
	 * @author defeatedcrow
	 */
	
	@Override
	public IIcon getIcon(int par1, int par2) {
		if (par1 < 0 || par1 > 5) {
			par1 = 5;
		}
		return this.iconType[par1];
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		boolean connectTop = world.getBlock(x, y + 1, z) == this;
		boolean connectBottom = world.getBlock(x, y - 1, z) == this;
		
        return connectTop ? (connectBottom ? iconType[2] : iconType[1]) : iconType[0];
    }
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[6];

		for (int i = 0; i < this.iconType.length; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("wa:susuki_" + i);
		}
	}
}
