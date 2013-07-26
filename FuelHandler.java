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
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
