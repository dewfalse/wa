package wa.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import wa.FluidInit;
import wa.Items;
import wa.api.RecipeManagerWa;

public class RecipeRegisterWa {
	
	private RecipeRegisterWa(){}
	
	/*
	 * APIにインスタンスを登録するメソッド。
	 */
	public static void registerInstance(){
		RecipeManagerWa.brewingRegister = new WaBrewingManager();
	}
	
	public static void registerRecipe(){
		RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.梅の実), 24, new ItemStack(Items.sugar), 24, 
				new FluidStack(FluidInit.umesyuFluid, 1000), 200);
	}

}
