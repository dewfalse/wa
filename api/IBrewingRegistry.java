package wa.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

public interface IBrewingRegistry {
	
	ArrayList<? extends IWaBrewingRecipe> getRecipeList();
	
	/**
	 * 醸造レシピの登録。鉱石辞書対応。
	 * 
	 * @param input
	 *            主材料のItemStack または String(OreDicName)
	 * @param inputRequire
	 *            主材料の必要量 この値より少ないほどグレードが下がる
	 * @param secondary
	 *            副材料のItemStack または String(OreDicName) こちらはnullでもOKなように
	 * @param secondRequire
	 *            副材料の必要量 この値から離れるほどグレードが下がる
	 * @param output
	 *            得られるお酒の液体
	 * @param brewingTime
	 *            最低限の必要Tick。これを超えると酒が完成する。以後は徐々にグレードが上がる。
	 *            
	 */
	void addRecipe(Object input, int inputRequire, Object secondary, int secondRequire, 
			FluidStack output, int brewingTime);
	
	IWaBrewingRecipe getRecipe(ItemStack input, ItemStack second);
	
	/*
	 * 以下は暫定仕様
	 * 
	 * 材料消費後もレシピの情報をグレード判定時に確認する必要があるので、
	 * IDで照会できるようにしてみる
	 */
	
	int getRecipeID(ItemStack input, ItemStack second);
	
	IWaBrewingRecipe getRecipeFromID(int id);

}
