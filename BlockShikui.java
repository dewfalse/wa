package wa;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

public class BlockShikui extends Block {

	Icon[] icons;

	public BlockShikui(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[16];
		for(int i = 0; i < icons.length; ++i) {
			icons[i] = par1IconRegister.registerIcon("wa:shikui" + String.valueOf(i));
		}
		this.blockIcon = icons[15];
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5) {
		return true;
	}

	@Override
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4, int par5) {
		ForgeDirection[] dirs_top = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
		ForgeDirection[] dirs_2 = {ForgeDirection.UP, ForgeDirection.WEST, ForgeDirection.DOWN, ForgeDirection.EAST};
		ForgeDirection[] dirs_3 = {ForgeDirection.UP, ForgeDirection.EAST, ForgeDirection.DOWN, ForgeDirection.WEST};
		ForgeDirection[] dirs_4 = {ForgeDirection.UP, ForgeDirection.SOUTH, ForgeDirection.DOWN, ForgeDirection.NORTH};
		ForgeDirection[] dirs_5 = {ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.DOWN, ForgeDirection.SOUTH};
		ForgeDirection[] dirs;
		switch(par5) {
		case 2:
			dirs = dirs_2;
			break;
		case 3:
			dirs = dirs_3;
			break;
		case 4:
			dirs = dirs_4;
			break;
		case 5:
			dirs = dirs_5;
			break;
		default:
			dirs = dirs_top;
			break;
		}

		int index = 0;
		for(int i = 0; i < 4; ++i) {
			int nBlockID = par1iBlockAccess.getBlockId(par2+dirs[i].offsetX, par3+dirs[i].offsetY, par4+dirs[i].offsetZ);
			if(nBlockID != blockID) {
				switch(i) {
				case 0:
					index += 1;
					break;
				case 1:
					index += 2;
					break;
				case 2:
					index += 4;
					break;
				case 3:
					index += 8;
					break;
				}
			}
		}
		return icons[index];
	}

}
