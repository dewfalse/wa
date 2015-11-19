package wa.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import wa.api.ISqueezingRegistry;
import wa.api.IWaSqueezingRecipe;
import wa.api.RecipeManagerWa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dew on 2015/11/14.
 */
public class WaSqueezingManager  implements ISqueezingRegistry {

    private static ArrayList<WaSqueezingRecipe> recipes;
    private static Map<Integer, WaSqueezingRecipe> recipeMap;

    public WaSqueezingManager() {
        this.recipes = new ArrayList<WaSqueezingRecipe>();
        this.recipeMap = new HashMap<Integer, WaSqueezingRecipe>();
    }

    public ISqueezingRegistry instance() {
        return RecipeManagerWa.squeezingRegistry;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public ArrayList<? extends IWaSqueezingRecipe> getRecipeList() {
        return this.recipes;
    }

    @Override
    public IWaSqueezingRecipe getRecipe(ItemStack input) {
        for (WaSqueezingRecipe recipe : recipes){
            if (recipe.matches(input)){
                return recipe;
            }
        }
        return null;
    }

    @Override
    public void addRecipe(Object input, int inputRequire, FluidStack output, int squeezingTime) {
        if (input != null && output != null && inputRequire > 0){
            WaSqueezingRecipe add = new WaSqueezingRecipe(output, input, inputRequire, squeezingTime);
            int id = recipeMap.isEmpty() ? 1 : recipeMap.size() + 1;
            recipes.add(add);
            recipeMap.put(id, add);
        }
    }

    @Override
    public int getRecipeID(ItemStack input) {
        for (Map.Entry<Integer, WaSqueezingRecipe> entry : recipeMap.entrySet()){
            if (entry.getValue().matches(input)){
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public IWaSqueezingRecipe getRecipeFromID(int id) {
        if (recipeMap.isEmpty() || id < 1 || id > recipeMap.size()){
            return null;
        }
        if (recipeMap.containsKey(id)){
            return recipeMap.get(id);
        }
        return null;
    }

    private boolean isItemEqual(ItemStack a, ItemStack b) {
        if (a == null || a.getItem() == null || b == null || b.getItem() == null){
            // 両方nullなら同じと判断する
            return a == null && b == null;
        }

        boolean flag = false;
        if (a.getItem() == b.getItem()) {
            if (a.getItemDamage() == b.getItemDamage()) {
                flag = true;
            } else if (b.getItemDamage() == Short.MAX_VALUE) {
                flag = true;
            }
        }
        return flag;
    }

    public class WaSqueezingRecipe implements IWaSqueezingRecipe {

        private final Object input;
        private final int require1;
        private final int day;
        private final FluidStack output;

        public WaSqueezingRecipe(FluidStack out, Object in, int r1, int d){
            input = in;
            require1 = r1;
            day = d;
            output = out;
        }

        @Override
        public Object getInput() {
            return input;
        }


        @Override
        public FluidStack getOutput() {
            return output.copy();
        }

        @Override
        public List<ItemStack> getProcessedInput() {
            ArrayList<ItemStack> processedInput = new ArrayList<ItemStack>();
            if (input instanceof String) {
                processedInput.addAll(OreDictionary.getOres((String) input));
            } else if (input instanceof ItemStack) {
                processedInput.add(((ItemStack) input).copy());
            } else if (input instanceof Item) {
                processedInput.add(new ItemStack((Item) input, 1, 0));
            } else if (input instanceof Block) {
                processedInput.add(new ItemStack((Block) input, 1, 0));
            }
            return processedInput;
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
        public boolean matches(ItemStack itemStack) {
            if (itemStack == null) return false;
            // input
            for (ItemStack target : this.getProcessedInput()){
                if (isItemEqual(itemStack, target)){
                    return true;
                }
            }
            return false;
        }

        @Override
        public int getSqueezingTime() {
            return day;
        }

        /*
         * とりあえず、100固定
         */
        @Override
        public int getOutputGrade(TileEntity tile, int inputNum) {
            return 100;
        }

    }

}
