package wa.recipe;

import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import wa.FluidInit;
import wa.item.Items;
import wa.api.RecipeManagerWa;
import wa.block.Blocks;

public class RecipeRegisterWa {
	
	private RecipeRegisterWa(){}
	
	/*
	 * APIにインスタンスを登録するメソッド。
	 */
	public static void registerInstance(){
		RecipeManagerWa.brewingRegister = new WaBrewingManager();
	}
	
	public static void registerRecipe(){
        // 梅酒レシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.梅の実), 24, new ItemStack(Items.sugar), 24,
                new FluidStack(FluidInit.umesyuFluid, 1000), 200);
        // シードルレシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.apple), 24, null, 24,
                new FluidStack(FluidInit.ciderFluid, 1000), 200);
        // 金のシードルレシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.golden_apple), 24, null, 24,
                new FluidStack(FluidInit.goldenCiderFluid, 1000), 200);
        // ひれ酒レシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.日本酒), 24, new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.ordinal()), 24,
                new FluidStack(FluidInit.finSakeFluid, 1000), 200);
        // スライムゼリーソーダレシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.slime_ball), 24, new ItemStack(Items.シードル), 24,
                new FluidStack(FluidInit.slimeJellySodaFluid, 1000), 200);
        // 高麗人蔘酒レシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.carrot), 24, null, 24,
                new FluidStack(FluidInit.ginsengLiquorFluid, 1000), 200);
        // 馬乳酒レシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.milk_bucket), 24, null, 24,
                new FluidStack(FluidInit.kumisFluid, 1000), 200);
        // かぼちゃエールレシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Blocks.pumpkin), 24, new ItemStack(Items.sugar), 24,
                new FluidStack(FluidInit.pumpkinAleFluid, 1000), 200);
        // ステアウェイトゥヘルレシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.ghast_tear), 24, new ItemStack(Items.nether_wart), 24,
                new FluidStack(FluidInit.infernalLiquorFluid, 1000), 200);
        // マグマクリームサワーレシピ
        RecipeManagerWa.brewingRegister.addRecipe(new ItemStack(Items.magma_cream), 24, new ItemStack(Items.fermented_spider_eye), 24,
                new FluidStack(FluidInit.magmaCreamSourFluid, 1000), 200);
    }

}
