package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wa.Items;

public class BlockTaiko extends Block {
	private IIcon[] icons;

	public BlockTaiko(Material par2Material) {
		super(par2Material);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote) {
			ItemStack itemStack = par5EntityPlayer.getCurrentEquippedItem();
			if (itemStack != null && itemStack.getItem() == Items.stick) {
				par1World.playSound(par2, par3, par4, "wa.taiko", 0.5F, 1.0F,
						false);
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4,
				par5EntityPlayer, par6, par7, par8, par9);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		int k = par2 & 12;
		int l = par2 & 3;
		return k == 0 && (par1 == 1 || par1 == 0) ? icons[0] : (k == 4
				&& (par1 == 5 || par1 == 4) ? icons[0] : (k == 8
				&& (par1 == 2 || par1 == 3) ? icons[0] : icons[1]));
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("wa:taiko_head");
		icons[1] = par1IconRegister.registerIcon("wa:taiko_side");
	}

	public int onBlockPlaced(World par1World, int par2, int par3, int par4,
			int par5, float par6, float par7, float par8, int par9) {
		int j1 = par9 & 3;
		byte b0 = 0;

		switch (par5) {
		case 0:
		case 1:
			b0 = 0;
			break;
		case 2:
		case 3:
			b0 = 8;
			break;
		case 4:
		case 5:
			b0 = 4;
		}

		return j1 | b0;
	}

}
