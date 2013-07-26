package wa;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWaFood extends ItemFood {
	public ItemWaFood(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3, par4);
		setHasSubtypes(true);
	}

	private Icon icons[];

	@Override
	public Icon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, EnumFood.values().length-1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[EnumFood.values().length];
		for (int var4 = 0; var4 < EnumFood.values().length; ++var4) {
			icons[var4] = par1IconRegister.registerIcon("wa:"+EnumFood.values()[var4].toString());
		}
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < EnumFood.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack)
				+ EnumFood.values()[par1ItemStack.getItemDamage()].toString();
	}
}
