package wa.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import wa.api.IBrewingRegister;
import wa.api.IWaBrewingRecipe;
import wa.api.RecipeManagerWa;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class WaBrewingManager implements IBrewingRegister {

	private static ArrayList<WaBrewingRecipe> recipes;
	private static Map<Integer, WaBrewingRecipe> recipeMap;

	public WaBrewingManager() {
		this.recipes = new ArrayList<WaBrewingRecipe>();
		this.recipeMap = new HashMap<Integer, WaBrewingRecipe>();
	}

	public IBrewingRegister instance() {
		return RecipeManagerWa.brewingRegister;
	}

	@Override
	public ArrayList<? extends IWaBrewingRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public IWaBrewingRecipe getRecipe(ItemStack input, ItemStack second) {
		for (WaBrewingRecipe recipe : recipes){
			ItemStack[] checks = {input, second};
			if (recipe.matches(checks)){
				return recipe;
			}
		}
		return null;
	}
	
	@Override
	public void addRecipe(Object input, int inputRequire, Object secondary,
			int secondRequire, FluidStack output, int brewingTime) {
		if (input != null && output != null && inputRequire > 0){
			WaBrewingRecipe add = new WaBrewingRecipe(output, input, secondary, 
					inputRequire, secondRequire, brewingTime);
			int id = recipeMap.isEmpty() ? 1 : recipeMap.size() + 1;
			recipes.add(add);
			recipeMap.put(id, add);
		}
	}

	@Override
	public int getRecipeID(ItemStack input, ItemStack second) {
		for (Entry<Integer, WaBrewingRecipe> entry : recipeMap.entrySet()){
			ItemStack[] checks = {input, second};
			if (entry.getValue().matches(checks)){
				return entry.getKey();
			}
		}
		return -1;
	}

	@Override
	public IWaBrewingRecipe getRecipeFromID(int id) {
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

	public class WaBrewingRecipe implements IWaBrewingRecipe {
		
		private final Object input;
		private final Object second;
		private final int require1;
		private final int require2;
		private final int day;
		private final FluidStack output;
		
		public WaBrewingRecipe(FluidStack out, Object in, Object sec, int r1, int r2, int d){
			input = in;
			second = sec;
			require1 = r1;
			require2 = r2;
			day = d;
			output = out;
		}

		@Override
		public Object getInput() {
			return input;
		}

		@Override
		public Object getSecondInput() {
			return second;
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
		public List<ItemStack> getSecondProcessedInput() {
			ArrayList<ItemStack> processedSecond = new ArrayList<ItemStack>();
			if (second instanceof String) {
				processedSecond.addAll(OreDictionary.getOres((String) second));
			} else if (second instanceof ItemStack) {
				processedSecond.add(((ItemStack) second).copy());
			} else if (second instanceof Item) {
				processedSecond.add(new ItemStack((Item) second, 1, 0));
			} else if (second instanceof Block) {
				processedSecond.add(new ItemStack((Block) second, 1, 0));
			}
			return processedSecond;
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
		public boolean matches(ItemStack[] items) {
			if (items.length != 2) return false;
			boolean fst = false;
			boolean sec = false;
			for (ItemStack check : items){
				// second
				if (check == null){
					if (second == null) sec = true;
				} else {
					// input
					for (ItemStack target : this.getProcessedInput()){
						if (isItemEqual(check, target)){
							fst = true;
						}
					}
					// second
					for (ItemStack target : this.getSecondProcessedInput()){
						if (isItemEqual(check, target)){
							sec = true;
						}
					}
				}
			}
			return fst && sec;
		}

		@Override
		public int getBrewingTime() {
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
