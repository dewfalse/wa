package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import wa.TileEntityKoto;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockKoto extends BlockContainer {
	private IIcon[] icons;

	public BlockKoto(Material par2Material) {
		super(par2Material);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if(par1World.isRemote) {
			if(par5EntityPlayer.getCurrentEquippedItem() == null) {
				
				/**
				 * 音色を変えられるようにしてみた
				 * @author defeatedcrow 
				 */
				float f = par7 * par9;
				
				par1World.playSound(par2, par3, par4, "wa:koto", 1.0F, f, false);
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? (par2 == 4 ? icons[2] : icons[0]) : icons[1];
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[3];
		icons[0] = par1IconRegister.registerIcon("wa:koto_head");
		icons[1] = par1IconRegister.registerIcon("wa:koto_side");
		icons[2] = par1IconRegister.registerIcon("wa:koto_head_1");
	}
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * 向き変更用メソッド、レンダー用メソッドを追加
     * @author defeatedcrow
     */
    
    @Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int playerFacing = MathHelper.floor_double((double)((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int m = par1World.getBlockMetadata(par2, par3, par4);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, playerFacing, 3);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityKoto();
	}
	
	public void setBlockBoundsForItemRender()
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		int m = par1World.getBlockMetadata(par2, par3, par4);
        this.updateBounds(m);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		int m = par1World.getBlockMetadata(par2, par3, par4);
        this.updateBounds(m);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
    {
		float f = 0.5F - 0.125F;
		float f1 = 0.5F + 0.125F;
//		this.setBlockBounds(f, 0.125F, f, f1, 0.150F, f1);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
	
	protected void updateBounds(int meta)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		return false;
	}

}
