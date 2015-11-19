package wa.api;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Created by dew on 2015/11/12.
 */
public interface IWaDistillingRecipe {

    /*
     * 主材料。必ず入れるもの
     */
    FluidStack getInput();

    /*
     * 副材料。これも何かしらは入れる
     */
    FluidStack getSecondInput();

    /*
     * できた酒の液体
     */
    FluidStack getOutput();

    /*
     * 必要個数
     */
    int getInputRequire();

    int getSecondRequire();

    /*
     * 蒸留可能判定
     */
    boolean matches(FluidStack[] items);

    /*
     * 必要Tick
     */
    int getDistillTime();

    /*
     * グレードの判定メソッド
     */
    int getOutputGrade(TileEntity tile, int inputNum, int secondNum);
}
