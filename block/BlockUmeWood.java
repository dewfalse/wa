package wa.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wa.client.Particles;
import wa.entity.EntityUmeLeavesFX;
import wa.item.Items;

import java.util.List;
import java.util.Random;

public class BlockUmeWood extends BlockLog {

	public static int renderID;
	private IIcon[] icons;

	protected BlockUmeWood() {
		super();
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.umeLog);
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
			int par4, int par5, float par6, int par7) {
		if (!par1World.isRemote) {
			int j1 = 20;

			if ((par5 & 3) == 3) {
				j1 = 40;
			}

			if (par7 > 0) {
				j1 -= 2 << par7;

				if (j1 < 10) {
					j1 = 10;
				}
			}

				Item k1 = this.getItemDropped(par5, par1World.rand, par7);
				this.dropBlockAsItem(par1World, par2, par3, par4,
						new ItemStack(k1, 1, this.damageDropped(par5)));

			j1 = 6;

			if (par7 > 0) {
				j1 -= par7;

				if (j1 < 1) {
					j1 = 1;
				}
			}

			if ((this == Blocks.umeWood) && par1World.rand.nextInt(j1) == 0) {
				this.dropBlockAsItem(par1World, par2, par3, par4,
						new ItemStack(Items.梅の実, 1+par1World.rand.nextInt(4), 0));
			}
		}
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this, 1, BlockLog.func_150165_c(par1));
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		int i = MathHelper.clamp_int(par1, 0, 2);
		return icons[i];
	}

	//変更点:アイコンを変えた
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[3];
		icons[0] = par1IconRegister.registerIcon("log_oak_top");
		icons[1] = par1IconRegister.registerIcon("log_oak");
		icons[2] = par1IconRegister.registerIcon("Wa:umeLeaves");
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

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {

			for (int l = 0; l < 2; ++l) {
				double r = 0.8D + par5Random.nextDouble();
				double t = par5Random.nextDouble() * 2 * Math.PI;
				double d0 = par2 + 0.5D + r * Math.sin(t);
				double d1 = par3 + par5Random.nextDouble();
				double d2 = par4 + 0.5D + r * Math.cos(t);
				double d3 = 0.0D;
				double d4 = -1.5D;
				double d5 = 0.0D;

				EntityUmeLeavesFX entityFX = new EntityUmeLeavesFX(par1World, d0, d1, d2, d3, d4, d5);
				entityFX.setParticleIcon(Particles.getInstance().getIcon("wa:ume"));
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
			}
	}
	
	/* 追加点 */
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack item)
	{
		int playerFacing = MathHelper.floor_double((double)((living.rotationYaw * 4F) / 360F) + 0.5D) & 3;
 
		boolean tall = false;
		
		if (living != null && living instanceof EntityPlayer)
		{
			tall = living.isSneaking();
		}
		else
		{
			tall = false;
		}
		
		//スニークしながら設置した場合、メタデータが1になる。
		//メタ1は強制的に花レンダーになる。
		if (tall)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		}
	}

}
