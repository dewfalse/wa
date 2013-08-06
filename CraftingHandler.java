package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		if(craftMatrix.getClass().getName().contains("TileSmelter")) {
			if(item.itemID == Item.ingotIron.itemID) {
				if(Config.レシピ難易度 <= RecipeDifficulty.HARD) {
					item.itemID = Items.ズク破片.itemID;
				}
				else if(Config.レシピ難易度 == RecipeDifficulty.NORMAL) {
					item.itemID = Items.ズク.itemID;
				}
			}
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
