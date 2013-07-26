package wa;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockKoto extends Block {
	private Icon[] icons;

	public BlockKoto(int par1, Material par2Material) {
		super(par1, par2Material);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if(par1World.isRemote) {
			if(par5EntityPlayer.getCurrentEquippedItem() == null) {
				par1World.playSound(par2, par3, par4, "wa.koto", 0.5F, 1.0F, false);
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? icons[0] : icons[1];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("wa:koto_head");
		icons[1] = par1IconRegister.registerIcon("wa:koto_side");
	}
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }

}
