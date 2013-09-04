package wa;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUmeWood extends BlockLog {

	public static int renderID;
	private Icon[] icons;

	protected BlockUmeWood(int par1) {
		super(par1);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Blocks.umeLog.blockID;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
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

				int k1 = this.idDropped(par5, par1World.rand, par7);
				this.dropBlockAsItem_do(par1World, par2, par3, par4,
						new ItemStack(k1, 1, this.damageDropped(par5)));

			j1 = 6;

			if (par7 > 0) {
				j1 -= par7;

				if (j1 < 1) {
					j1 = 1;
				}
			}

			if ((this.blockID == Blocks.umeWood.blockID) && par1World.rand.nextInt(j1) == 0) {
				this.dropBlockAsItem_do(par1World, par2, par3, par4,
						new ItemStack(Items.梅の実, 1+par1World.rand.nextInt(4), 0));
			}
		}
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[1];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("log_oak_top");
		icons[1] = par1IconRegister.registerIcon("log_oak");
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
				entityFX.func_110125_a(Particles.getInstance().getIcon("wa:ume"));
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
			}
	}

}
