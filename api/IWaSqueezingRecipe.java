package wa.api;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Created by dew on 2015/11/12.
 */
public interface IWaSqueezingRecipe {

    /*
     * 主材料。必ず入れるもの
     */
    Object getInput();

    /*
     * できたエキスの液体
     */
    FluidStack getOutput();

    /*
     * Object型からItemStack型に変換したもの
     */
    List<ItemStack> getProcessedInput();

    /*
     * 必要個数
     */
    int getInputRequire();


    /*
     * 圧搾可能判定
     */
    boolean matches(ItemStack itemStack);

    /*
     * 必要Tick
     */
    int getSqueezingTime();

    /*
     * グレードの判定メソッド
     */
    int getOutputGrade(TileEntity tile, int inputNum);
}
