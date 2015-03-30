package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockKoto extends Block {
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
				par1World.playSound(par2, par3, par4, "wa.koto", 0.5F, 1.0F, false);
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? icons[0] : icons[1];
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
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
