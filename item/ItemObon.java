package wa.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import wa.entity.EntityObon;

public class ItemObon extends Item {

	public ItemObon() {
		super();
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World,
			int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 > 1) {
			return false;
		} else {
			int i1 = Direction.facingToDirection[par7];

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
				return false;
			} else {
				EntityObon entityObon = new EntityObon(par3World, par4, par5, par6, i1);
				entityObon.posY += 0.52F;
				//if (entityFishPrint != null && entityFishPrint.onValidSurface()) {
					if (!par3World.isRemote) {
						par3World.spawnEntityInWorld(entityObon);
					}

					--par1ItemStack.stackSize;
				//}

				return true;
			}
		}
	}

}
