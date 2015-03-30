package wa;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import wa.block.Blocks;

import java.util.List;

public class ItemBlockSapling extends ItemBlock {

	IIcon[] icons;

	public ItemBlockSapling(Block par1) {
		super(par1);
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
		if(field_150939_a == Blocks.sakuraSapling) {
			return this.icons[1];
		}
		return this.icons[0];
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("wa:takenoko");
		icons[1] = par1IconRegister.registerIcon("wa:sakuraSapling");
	}

}
