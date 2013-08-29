package wa;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBrewingBarrel extends BlockContainer {

	Icon[] icons;

	public BlockBrewingBarrel(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		if(par1 == 0 || par1 == 1) {
			return icons[0];
		}
		return icons[1];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("wa:brewingBarrel_top");
		icons[1] = par1IconRegister.registerIcon("wa:brewingBarrel_side");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBrewingBarrel();
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		if(tile instanceof TileEntityBrewingBarrel) {
			TileEntityBrewingBarrel brewingBarrel = (TileEntityBrewingBarrel)tile;
			for(int i = 0; i < brewingBarrel.getSizeInventory(); ++i) {
				ItemStack itemStack = brewingBarrel.getStackInSlot(i);
				if(itemStack != null) {
					double x = par2 + par1World.rand.nextFloat() * 0.8F + 0.1F;
					double y = par3 + par1World.rand.nextFloat() * 0.8F + 0.1F;
					double z = par4 + par1World.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityItem = new EntityItem(par1World, x, y, z, itemStack);
					par1World.spawnEntityInWorld(entityItem);
				}
			}
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		if (par1World.isRemote) {
			return true;
		}

		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);

		if (tile != null) {
			par5EntityPlayer.openGui(Wa.instance, Config.醸造樽GUIID, par1World, par2, par3, par4);
		}

		return true;
	}

}
