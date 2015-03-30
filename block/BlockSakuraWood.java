package wa.block;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;
import java.util.Random;

public class BlockSakuraWood extends BlockLog {

	private IIcon[] icons;

	protected BlockSakuraWood() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.sakuraWood);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public int getRenderColor(int par1) {
		return 0x44444444;
	}

	@Override
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4) {
		return 0x44444444;
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		int k = par2 & 12;
		int l = par2 & 3;
		return k == 0 && (par1 == 1 || par1 == 0) ? icons[0] : (k == 4
				&& (par1 == 5 || par1 == 4) ? icons[0]
				: (k == 8 && (par1 == 2 || par1 == 3) ? icons[0]
						: icons[1]));
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("wa:sakura_top");
		icons[1] = par1IconRegister.registerIcon("wa:sakura_side");
	}

}
