package wa.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * BlockSlubの動作が怪しいため継承先を変更
 * 
 */
public class BlockTatami extends BlockSlabBase {

	public BlockTatami(boolean par2, Material par3Material) {
		super(par2, par3Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.BlockWaHalfBlock);
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this, 2, par1 & 3);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}

//    @Override
//	public String func_150002_b(int i) {
//		return super.getUnlocalizedName() + "." + "tatami";
//	}

//	@Override
//	public Item getItem(World par1World, int par2, int par3, int par4) {
//		//return Blocks.畳.blockID;
//		return null;
//	}
	
	/* 追加メソッド */
	
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
	protected String[] topIconString() {
		return new String[] {"tatami"};
	}
	
	@Override
	protected String[] topIconString2() {
		return new String[] {"tatami_v"};
	}

	@Override
	protected String[] sideIconString() {
		return new String[] {"tatami"};
	}

	@Override
	protected float slabHeight() {
		return 0.5F;
	}

}
