package wa;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemWaFood extends ItemFood {
	public ItemWaFood(int par2, float par3, boolean par4) {
		super(par2, par3, par4);
		setHasSubtypes(true);
	}

	private IIcon icons[];

	@Override
	public IIcon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, EnumFood.values().length-1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[EnumFood.values().length];
		for (int var4 = 0; var4 < EnumFood.values().length; ++var4) {
			icons[var4] = par1IconRegister.registerIcon("wa:"+EnumFood.values()[var4].toString());
		}
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < EnumFood.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack)
				+ "." + EnumFood.values()[par1ItemStack.getItemDamage()].toString();
	}
}
