package wa.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

/**
 * Created by dew on 2015/11/12.
 */
public interface ISqueezingRegistry {
    // 使い道があるかもしれないので醸造レシピと同じインターフェースにしておく

    ArrayList<? extends IWaSqueezingRecipe> getRecipeList();

    /**
     * 圧搾器レシピの登録。鉱石辞書対応。
     *
     * @param input
     *            主材料のItemStack または String(OreDicName)
     * @param inputRequire
     *            主材料の必要量
     * @param secondary
     *            副材料のItemStack または String(OreDicName) こちらはnullでもOKなように
     * @param secondRequire
     *            副材料の必要量
     * @param output
     *            得られるエキスの液体
     * @param squeezingTime
     *            最低限の必要Tick。これを超えるとエキスが完成する
     *
     */
    void addRecipe(Object input, int inputRequire, Object secondary, int secondRequire,
                   FluidStack output, int squeezingTime);

    IWaSqueezingRecipe getRecipe(ItemStack input, ItemStack second);

	/*
	 * 以下は暫定仕様
	 *
	 * 材料消費後もレシピの情報をグレード判定時に確認する必要があるので、
	 * IDで照会できるようにしてみる
	 */

    int getRecipeID(ItemStack input, ItemStack second);

    IWaSqueezingRecipe getRecipeFromID(int id);
}
