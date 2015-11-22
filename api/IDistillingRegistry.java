package wa.api;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

/**
 * Created by dew on 2015/11/12.
 */
public interface IDistillingRegistry {
    String getVersion();

    // 蒸留レシピは液体→液体のみサポート
    // なのでFluidStackのみでいい、はず、たぶん

    ArrayList<? extends IWaDistillingRecipe> getRecipeList();

    /**
     * 蒸留レシピの登録
     *
     * @param input
     *            主材料のFluidStack
     * @param inputRequire
     *            主材料の必要量 この値より少ないほどグレードが下がる
     * @param output
     *            得られるお酒の液体
     * @param distillingTime
     *            最低限の必要Tick。これを超えると酒が完成する。以後はグレードが変動する（初垂れ/本垂れ/末垂れ）。
     *
     */
    void addRecipe(Fluid input, int inputRequire, FluidStack output, int distillingTime);
    void addRecipe(FluidStack input, int inputRequire, FluidStack output, int distillingTime);
    void addRecipe(FluidStack input, FluidStack output, int distillingTime);

    IWaDistillingRecipe getRecipe(FluidStack input);

	/*
	 * 以下は暫定仕様
	 *
	 * 材料消費後もレシピの情報をグレード判定時に確認する必要があるので、
	 * IDで照会できるようにしてみる
	 */

    int getRecipeID(FluidStack input);

    IWaDistillingRecipe getRecipeFromID(int id);
}
