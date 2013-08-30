package wa;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.itemID == Blocks.takezumiBlock.blockID || fuel.itemID == Blocks.charcoalBlock.blockID) {
			return 16000;
		}
		else if(fuel.itemID == Items.竹炭.itemID) {
			return 1600;
		}
		else if(fuel.itemID == Blocks.sakuraWood.blockID) {
			return 300;
		}
		else if(fuel.itemID == Blocks.umeWood.blockID) {
			return 300;
		}
		else if(fuel.itemID == Blocks.sakuraSapling.blockID) {
			return 300;
		}
		return 0;
	}

}
