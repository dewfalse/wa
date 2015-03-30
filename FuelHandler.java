package wa;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import wa.block.Blocks;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == Item.getItemFromBlock(Blocks.takezumiBlock) || fuel.getItem() == Item.getItemFromBlock(Blocks.charcoalBlock)) {
			return 16000;
		}
		else if(fuel.getItem() == Items.竹炭) {
			return 1600;
		}
		else if(fuel.getItem() == Item.getItemFromBlock(Blocks.sakuraWood)) {
			return 300;
		}
		else if(fuel.getItem() == Item.getItemFromBlock(Blocks.umeWood)) {
			return 300;
		}
		else if(fuel.getItem() == Item.getItemFromBlock(Blocks.umeLog)) {
			return 300;
		}
		else if(fuel.getItem() == Item.getItemFromBlock(Blocks.sakuraSapling)) {
			return 300;
		}
		return 0;
	}

}
