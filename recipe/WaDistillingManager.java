package wa.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import wa.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dew on 2015/11/14.
 */
public class WaDistillingManager  implements IDistillingRegistry {

    private static ArrayList<WaDistillingRecipe> recipes;
    private static Map<Integer, WaDistillingRecipe> recipeMap;

    public WaDistillingManager() {
        this.recipes = new ArrayList<WaDistillingRecipe>();
        this.recipeMap = new HashMap<Integer, WaDistillingRecipe>();
    }

    public IDistillingRegistry instance() {
        return RecipeManagerWa.distillingRegistry;
    }

    @Override
    public ArrayList<? extends IWaDistillingRecipe> getRecipeList() {
        return this.recipes;
    }

    @Override
    public IWaDistillingRecipe getRecipe(FluidStack input, FluidStack second) {
        for (WaDistillingRecipe recipe : recipes){
            FluidStack[] checks = {input, second};
            if (recipe.matches(checks)){
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void addRecipe(Fluid input, int inputRequire, Fluid secondary,
                          int secondRequire, FluidStack output, int distillingTime) {
        FluidStack fluid1 = new FluidStack(input, inputRequire);
        FluidStack fluid2 = (secondary != null) ? new FluidStack(secondary, secondRequire) : null;
        addRecipe(fluid1, inputRequire, fluid2, secondRequire, output, distillingTime);
    }

    @Override
    public void addRecipe(FluidStack input, int inputRequire, FluidStack secondary,
                          int secondRequire, FluidStack output, int distillingTime) {
        if (input != null && output != null && inputRequire > 0){
            WaDistillingRecipe add = new WaDistillingRecipe(output, input, secondary,
                    inputRequire, secondRequire, distillingTime);
            int id = recipeMap.isEmpty() ? 1 : recipeMap.size() + 1;
            recipes.add(add);
            recipeMap.put(id, add);
        }
    }

    @Override
    public void addRecipe(FluidStack input, FluidStack secondary, FluidStack output, int distillingTime) {
        addRecipe(input, input.amount, secondary, secondary.amount, output, distillingTime);
    }

    @Override
    public int getRecipeID(FluidStack input, FluidStack second) {
        for (Map.Entry<Integer, WaDistillingRecipe> entry : recipeMap.entrySet()){
            FluidStack[] checks = {input, second};
            if (entry.getValue().matches(checks)){
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public IWaDistillingRecipe getRecipeFromID(int id) {
        if (recipeMap.isEmpty() || id < 1 || id > recipeMap.size()){
            return null;
        }
        if (recipeMap.containsKey(id)){
            return recipeMap.get(id);
        }
        return null;
    }

    public class WaDistillingRecipe implements IWaDistillingRecipe {

        private final FluidStack input;
        private final FluidStack second;
        private final int require1;
        private final int require2;
        private final int day;
        private final FluidStack output;

        public WaDistillingRecipe(FluidStack out, FluidStack in, FluidStack sec, int r1, int r2, int d){
            input = in;
            second = sec;
            require1 = r1;
            require2 = r2;
            day = d;
            output = out;
        }

        @Override
        public FluidStack getInput() {
            return input;
        }

        @Override
        public FluidStack getSecondInput() {
            return second;
        }

        @Override
        public FluidStack getOutput() {
            return output.copy();
        }

        @Override
        public int getInputRequire() {
            return require1;
        }

        @Override
        public int getSecondRequire() {
            return require2;
        }

        /*
         * 注: matchingでは個数は問わない。
         * 少量でもアウトプットの酒は完成する仕組み。
         * ただし、グレードが大きく下がる仕組み。
         */
        @Override
        public boolean matches(FluidStack[] items) {
            if (items.length != 2) return false;
            boolean fst = false;
            boolean sec = false;
            for (FluidStack check : items){
                // second
                if (check == null){
                    if (second == null) sec = true;
                } else {
                    // input
                    if (check.isFluidEqual(this.getInput())){
                        fst = true;
                    }
                    // second
                    if (check.isFluidEqual(this.getSecondInput())){
                        sec = true;
                    }
                }
            }
            return fst && sec;
        }

        @Override
        public int getDistillingTime() {
            return day;
        }

        /*
         * とりあえず、inputとoutputの数から生成。
         * 条件を足しても良いと思う。
         */
        @Override
        public int getOutputGrade(TileEntity tile, int inputNum, int secondNum) {
            int grade = 100;
            int i1 = 100 - Math.abs((require1 - inputNum));
            float f1 = i1 / 80.0F;
            int i2 = 100 + secondNum - require2;
            float f2 = i2 / 100.0F;
            if (tile.getWorldObj().rand.nextInt(10) == 0){
                grade += 50;
            }
            int ret = MathHelper.ceiling_float_int(grade * f1 * f2);
            return ret;
        }

    }

}
