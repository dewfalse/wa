package wa.recipe;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import wa.api.IDistillingRegistry;
import wa.api.IWaDistillingRecipe;
import wa.api.RecipeManagerWa;

import java.util.ArrayList;
import java.util.HashMap;
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
    public String getVersion() {
        return "1.0";
    }

    @Override
    public ArrayList<? extends IWaDistillingRecipe> getRecipeList() {
        return this.recipes;
    }

    @Override
    public IWaDistillingRecipe getRecipe(FluidStack input) {
        for (WaDistillingRecipe recipe : recipes){
            if (recipe.matches(input)){
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void addRecipe(Fluid input, int inputRequire, FluidStack output, int distillingTime) {
        FluidStack fluid = new FluidStack(input, inputRequire);
        addRecipe(fluid, inputRequire, output, distillingTime);
    }

    @Override
    public void addRecipe(FluidStack input, int inputRequire, FluidStack output, int distillingTime) {
        if (input != null && output != null && inputRequire > 0){
            WaDistillingRecipe add = new WaDistillingRecipe(output, input, inputRequire, distillingTime);
            int id = recipeMap.isEmpty() ? 1 : recipeMap.size() + 1;
            recipes.add(add);
            recipeMap.put(id, add);
        }
    }

    @Override
    public void addRecipe(FluidStack input, FluidStack output, int distillingTime) {
        addRecipe(input, input.amount, output, distillingTime);
    }

    @Override
    public int getRecipeID(FluidStack input) {
        for (Map.Entry<Integer, WaDistillingRecipe> entry : recipeMap.entrySet()){
            if (entry.getValue().matches(input)){
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
        private final int require1;
        private final int day;
        private final FluidStack output;

        public WaDistillingRecipe(FluidStack out, FluidStack in, int r1, int d){
            input = in;
            require1 = r1;
            day = d;
            output = out;
        }

        @Override
        public FluidStack getInput() {
            return input;
        }

        @Override
        public FluidStack getOutput() {
            return output.copy();
        }

        @Override
        public int getInputRequire() {
            return require1;
        }


        /*
         * 注: matchingでは個数は問わない。
         * 少量でもアウトプットの酒は完成する仕組み。
         * ただし、グレードが大きく下がる仕組み。
         */
        @Override
        public boolean matches(FluidStack fluidStack) {
            if (fluidStack == null) return false;
            return fluidStack.isFluidEqual(this.getInput());
        }

        @Override
        public int getDistillingTime() {
            return day;
        }

        /*
         * とりあえず、蒸留元の液体と同じグレードで生成
         */
        @Override
        public int getOutputGrade(TileEntity tile, FluidStack input, int inputGrade) {
            return inputGrade;
        }
    }

}
