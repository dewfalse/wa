package wa;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockSapling extends ItemBlock {

	private int _blockID;
	Icon[] icons;

	public ItemBlockSapling(int par1) {
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
		if(_blockID == Blocks.sakuraSapling.blockID) {
			return this.icons[1];
		}
		return this.icons[0];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("wa:takenoko");
		icons[1] = par1IconRegister.registerIcon("wa:sakuraSapling");
	}

}
