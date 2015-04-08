package wa.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * バニラのhalfslabに向き変更機能を追加したもの
 * @author defeatedcrow
 * */
public abstract class BlockSlabBase extends Block{
	
	protected final boolean isDouble;
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] topIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon[] topIcon2;
	@SideOnly(Side.CLIENT)
	protected IIcon[] sideIcon;
	
	public BlockSlabBase (boolean b, Material material)
	{
		super(material);
		this.setHardness(0.2F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeCloth);
		isDouble = b;
	}
	
	//レンダーと当たり判定の処理
	
	@Override
	public void setBlockBoundsForItemRender()
    {
        if (isDouble) {
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {
        	float f = slabHeight();
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
        }
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
    {
        this.updateBounds(world.getBlockMetadata(par2, par3, par4));
    }
	
	protected void updateBounds(int par1)
    {
		if (isDouble) {
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else {
        	float f = slabHeight();
        	boolean rev = (par1 & 8) != 0;
        	if (!rev) {
        		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
        	}
        	else {
        		this.setBlockBounds(0.0F, 1.0F - f, 0.0F, 1.0F, 1.0F, 1.0F);
        	}
        }
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	//アイコン
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int par1, int par2)
	{
		int meta = par2 & 3;
		int i = Math.min(meta, topIconString().length - 1);
		int j = Math.min(meta, topIconString2().length - 1);
		int k = Math.min(meta, sideIconString().length - 1);
		if (par1 == 0 || par1 == 1) {
			return (par2 & 4) != 0 ? topIcon2[j] : topIcon[i];
		}
		else
		{
			return sideIcon[k];
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0 ; i < topIconString().length ; i++)
		{
			topIcon = new IIcon[topIconString().length];
			topIcon[i] = par1IconRegister.registerIcon("wa:" + topIconString()[i]);
		}
		
		for (int j = 0 ; j < topIconString2().length ; j++)
		{
			topIcon2 = new IIcon[topIconString2().length];
			topIcon2[j] = par1IconRegister.registerIcon("wa:" + topIconString2()[j]);
		}
		
		for (int k = 0 ; k < sideIconString().length ; k++)
		{
			sideIcon = new IIcon[sideIconString().length];
			sideIcon[k] = par1IconRegister.registerIcon("wa:" + sideIconString()[k]);
		}
	}
	
	protected abstract String[] topIconString();
	
	protected abstract String[] topIconString2();
	
	protected abstract String[] sideIconString();
	
	protected abstract float slabHeight();
	
	//半ブロックの動作
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float fx, float fy, float fz, int meta)
    {
        return this.isDouble ? meta : (side != 0 && (side == 1 || (double)fy <= 0.5D) ? meta : meta | 8);
    }
	
	//設置時の向き
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int playerFacing = MathHelper.floor_double((double)((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int m = par1World.getBlockMetadata(par2, par3, par4);
		
		if (playerFacing == 0 || playerFacing == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, m | 4, 3);
		}
		else {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, m, 3);
		}
	}

	@Override
    public int quantityDropped(Random rand)
    {
        return this.isDouble ? 2 : 1;
    }

	@Override
    public int damageDropped(int meta)
    {
        return meta & 3;
    }

}
