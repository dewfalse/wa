package wa;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ItemCoin extends Item {
	private IIcon icons[];

	public ItemCoin() {
		super();
		setHasSubtypes(true);
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, EnumCoin.values().length-1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[EnumCoin.values().length];
		for (int var4 = 0; var4 < EnumCoin.values().length; ++var4) {
			icons[var4] = par1IconRegister.registerIcon("wa:"+EnumCoin.values()[var4].toString().toLowerCase());
		}
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < EnumCoin.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack)
				+ "." + EnumCoin.values()[par1ItemStack.getItemDamage()].toString().toLowerCase();
	}

	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
        	EntityCoin e = new EntityCoin(par2World, par3EntityPlayer);
        	e.setCoinType(par1ItemStack.getItemDamage());
            par2World.spawnEntityInWorld(e);
        }

        return par1ItemStack;
    }
}
