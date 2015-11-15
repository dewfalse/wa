package wa.recipe;

import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import wa.item.Items;
import wa.api.RecipeManagerWa;
import wa.block.Blocks;

public class RecipeRegistryWa {
	
	private RecipeRegistryWa(){}
	
	/*
	 * APIにインスタンスを登録するメソッド。
	 */
	public static void registerInstance(){
        RecipeManagerWa.brewingRegistry = new WaBrewingManager();
        RecipeManagerWa.squeezingRegistry = new WaSqueezingManager();
        RecipeManagerWa.distillingRegistry = new WaDistillingManager();
	}
	
	public static void registerRecipe(){
        // 圧搾機レシピ。原材料1個あたり200mB相場とする（FfMのリンゴ→ジュースの相場）
        // りんごジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.apple), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.apple_juice"), 200), 200);
        // 金のりんごジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.golden_apple), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_apple_juice"), 200), 200);
        // じゃがいもエキスレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.potato), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.potato_essence"), 200), 200);
        // にんじんジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.carrot), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.carrot_juice"), 200), 200);
        // 金のにんじんジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.golden_carrot), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_carrot_juice"), 200), 200);
        // 糖蜜レシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.reeds), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.syrup"), 200), 200);
        // 麦汁レシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.wheat), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.wort"), 200), 200);
        // サボテンエキスレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Blocks.cactus), 1, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.cactus_essence"), 200), 200);


        // 醸造樽レシピ
        // シードルレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.りんごジュース), 24, new ItemStack(Items.りんごジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.cider"), 1000), 200);
        // 金のシードルレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.金のりんごジュース), 24, new ItemStack(Items.金のりんごジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_cider"), 1000), 200);
        // じゃがいも発酵エキスレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.じゃがいもエキス), 24, new ItemStack(Items.じゃがいもエキス), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.potato_ferment_essence"), 1000), 200);
        // 高麗人参酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.にんじんジュース), 24, new ItemStack(Items.にんじんジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.ginseng_liquor"), 1000), 200);
        // 金の高麗人参酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.金のにんじんジュース), 24, new ItemStack(Items.金のにんじんジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_ginseng_liquor"), 1000), 200);
        // 糖蜜発酵エキスレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.糖蜜), 24, new ItemStack(Items.糖蜜), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.syrup_ferment_essence"), 1000), 200);
        // ウォッシュレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.麦汁), 24, new ItemStack(Items.麦汁), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.wash"), 1000), 200);
        // プルケレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.サボテンエキス), 24, new ItemStack(Items.サボテンエキス), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.pulque"), 1000), 200);

        // 馬乳酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.milk_bucket), 24, new ItemStack(Items.milk_bucket), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.kumis"), 1000), 200);


        // 蒸留器レシピ。10倍濃縮を相場とする。蒸留器は液体→液体のレシピ
        // カルヴァドスレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.cider"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.calvados"), 10), 200);
        // 金のカルヴァドスレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.golden_cider"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_calvados"), 10), 200);
        // スピリタスレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.potato_ferment_essence"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.spirytus"), 10), 200);
        // ラム酒レシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.syrup_ferment_essence"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.rum"), 10), 200);
        // ウィスキーレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.wash"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.whisky"), 10), 200);
        // テキーラレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.pulque"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.tequila"), 10), 200);

        // アルコールレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.spirytus"), 100, null, 0,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.alcohol"), 90), 200);


        // 混合醸造酒レシピ
        // 梅酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.梅の実), 24, new ItemStack(Items.sugar), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.umesyu"), 1000), 200);
        // ひれ酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.日本酒), 24, new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.ordinal()), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.fin_sake"), 1000), 200);
        // スライムゼリーソーダレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.slime_ball), 24, new ItemStack(Items.シードル), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.slime_jelly_soda"), 1000), 200);
        // かぼちゃエールレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Blocks.pumpkin), 24, new ItemStack(Items.sugar), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.pumpkin_ale"), 1000), 200);
        // ヘルリカーレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.ghast_tear), 24, new ItemStack(Items.nether_wart), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.infernal_liquor"), 1000), 200);
        // マグマクリームサワーレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.magma_cream), 24, new ItemStack(Items.fermented_spider_eye), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.magma_cream_sour"), 1000), 200);
    }

}
