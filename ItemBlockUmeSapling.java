package wa;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockUmeSapling extends ItemBlock {

	private int _blockID;
	public ItemBlockUmeSapling(int par1) {
		super(par1);
		_blockID = par1 + 256;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return this.itemIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(Block.blocksList[_blockID].getUnlocalizedName2());
	}


}
