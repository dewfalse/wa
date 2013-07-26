package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class ItemIne extends Item implements ICraftingHandler {

	public ItemIne(int par1) {
		super(par1);
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		if (item.itemID == Items.米.itemID) {
			player.dropItem(Blocks.wara.blockID, item.stackSize);
			player.addStat(Achievements.kome, 1);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
	}

}
