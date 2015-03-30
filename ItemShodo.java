package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemShodo extends Item {

	private static IIcon[] icons;

	public ItemShodo() {
		super();
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String title = "";
		if(par1ItemStack.hasTagCompound()) {
			NBTTagCompound tag = par1ItemStack.getTagCompound();
			if(tag.hasKey("Motive")) {
				title = tag.getString("Motive");
                String name = StatCollector.translateToLocal("wa:Motive." + title + ".name");
				par3List.add(name);
			}
		}
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World,
			int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 == 0) {
			return false;
		} else if (par7 == 1) {
			return false;
		} else {
			int i1 = Direction.facingToDirection[par7];

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
				return false;
			} else {
				String title = "";
				if(par1ItemStack.hasTagCompound()) {
					NBTTagCompound tag = par1ItemStack.getTagCompound();
					if(tag.hasKey("Motive")) {
						title = tag.getString("Motive");
					}
				}
				EntityKakejiku entityKakejiku = title.isEmpty() ? new EntityKakejiku(par3World, par4, par5, par6, i1) : new EntityKakejiku(par3World, par4, par5, par6, i1, title);
				if (entityKakejiku != null && entityKakejiku.onValidSurface()) {
					if (!par3World.isRemote) {
						par3World.spawnEntityInWorld(entityKakejiku);
					}

					--par1ItemStack.stackSize;
				}

				return true;
			}
		}
	}

}
