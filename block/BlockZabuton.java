package wa.block;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import wa.*;

/**
 * 内容を色々と変更しました
 * 使用メタは0-7と8-15、一応2色用意
 * 一段目のみBlockのレンダーを使い、二段目以降にモデルを使用
 * 
 * @author defeatedcrow
 * */
public class BlockZabuton extends BlockContainer {

	IIcon[] icons;

	public BlockZabuton(Material par3Material) {
		super(par3Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return isAltColor(par2) ? icons[1] : icons[0];
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(this);
	}

//	@Override
//	protected ItemStack createStackedBlock(int par1) {
//		return new ItemStack(this, 2, par1 & 8);
//	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 8));
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("wa:zabuton");
		icons[1] = par1IconRegister.registerIcon("wa:zabuton_alt");
	}
	
	private boolean isAltColor(int meta)
	{
		return (meta & 8) != 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityZabuton();
	}
	
	/* 以下、追加メソッド */
	
	// レンダーと当たり判定
	
	@Override
	public void setBlockBoundsForItemRender()
    {
		float f = 0.125F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
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
		float f = 0.125F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }
	
	protected void updateBounds(int meta)
    {
		int m = meta & 7;
		float f = 0.125F * m;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F + f, 1.0F);
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
	
	@Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

	@Override
    public int damageDropped(int meta)
    {
        return meta & 8;
    }
	
	// Entityの生成
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (!world.isRemote)
		{
			EntityZabuton entity = new EntityZabuton(world, x + 0.5D, y, z + 0.5D);
			world.spawnEntityInWorld(entity);
		}
		
	}
	
	// 破壊時の動作
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
		if (!world.isRemote)
        {
			int m = par6;
			int color = m & 8;
			int size = m & 7;
			
			float f = 0.7F;
            double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.2D + 0.6D;
            double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            ItemStack drop = new ItemStack(this, size + 1, color);
            EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, drop);
            entityitem.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(entityitem);
        }
    }
}
