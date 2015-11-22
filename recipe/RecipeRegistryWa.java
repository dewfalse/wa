package wa.recipe;

import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import wa.Config;
import wa.api.RecipeManagerWa;
import wa.block.Blocks;
import wa.item.Items;

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
        float adjust = 1.0F;
        switch(Config.酒造レシピ難易度) {
            case 0: adjust =  10F; break;
            case 1: adjust =   4F; break;
            case 2: adjust =   2F; break;
            case 3: adjust =   1F; break;
            case 4: adjust = 0.5F; break;
            case 5: adjust = 0.1F; break;
        }
        // 圧搾機レシピ。原材料1個あたり200mB相場とする（FfMのリンゴ→ジュースの相場）
        // りんごジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.apple), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.apple_juice"), 200), 800);
        // 金のりんごジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.golden_apple), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_apple_juice"), 200), 800);
        // じゃがいもエキスレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.potato), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.potato_essence"), 200), 800);
        // にんじんジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.carrot), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.carrot_juice"), 200), 800);
        // 金のにんじんジュースレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.golden_carrot), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_carrot_juice"), 200), 800);
        // 糖蜜レシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.reeds), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.syrup"), 200), 800);
        // 麦汁レシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Items.wheat), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.wort"), 200), 800);
        // サボテンエキスレシピ
        RecipeManagerWa.squeezingRegistry.addRecipe(new ItemStack(Blocks.cactus), 1,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.cactus_essence"), 200), 800);


        // 醸造樽レシピ
        // シードルレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.りんごジュース), 24, new ItemStack(Items.りんごジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.cider"), 1000), (int) (24000*adjust));
        // 金のシードルレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.金のりんごジュース), 24, new ItemStack(Items.金のりんごジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_cider"), 1000), (int) (24000*adjust));
        // じゃがいも発酵エキスレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.じゃがいもエキス), 24, new ItemStack(Items.じゃがいもエキス), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.potato_ferment_essence"), 1000), (int) (24000*adjust));
        // 高麗人参酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.にんじんジュース), 24, new ItemStack(Items.にんじんジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.ginseng_liquor"), 1000), (int) (24000*adjust));
        // 金の高麗人参酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.金のにんじんジュース), 24, new ItemStack(Items.金のにんじんジュース), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_ginseng_liquor"), 1000), (int) (24000*adjust));
        // 糖蜜発酵エキスレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.糖蜜), 24, new ItemStack(Items.糖蜜), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.syrup_ferment_essence"), 1000), (int) (24000*adjust));
        // ウォッシュレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.麦汁), 24, new ItemStack(Items.麦汁), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.wash"), 1000), (int) (24000*adjust));
        // プルケレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.サボテンエキス), 24, new ItemStack(Items.サボテンエキス), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.pulque"), 1000), (int) (24000*adjust));

        // 馬乳酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.milk_bucket), 24, new ItemStack(Items.milk_bucket), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.kumis"), 1000), (int) (24000*adjust));


        // 蒸留器レシピ。10倍濃縮を相場とする。蒸留器は液体→液体のレシピ
        // カルヴァドスレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.cider"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.calvados"), 10), (int) (24000*adjust));
        // 金のカルヴァドスレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.golden_cider"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.golden_calvados"), 10), (int) (24000*adjust));
        // スピリタスレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.potato_ferment_essence"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.spirytus"), 10), (int) (24000*adjust));
        // 若いラムレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.syrup_ferment_essence"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.young_rum"), 10), (int) (24000*adjust));
        // 若いウィスキーレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.wash"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.young_whisky"), 10), (int) (24000*adjust));
        // 若いテキーラレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.pulque"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.young_tequila"), 10), (int) (24000*adjust));
        // アルヒレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.kumis"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.arkhi"), 10), (int) (24000*adjust));
        // 日本酒レシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.unrefined_sake"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.sake"), 10), (int) (24000*adjust));

        // アルコールレシピ
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.spirytus"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.alcohol"), 90), (int) (2000*adjust));
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.rum"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.alcohol"), 30), (int) (8000*adjust));
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.whisky"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.alcohol"), 30), (int) (8000*adjust));
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.tequila"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.alcohol"), 30), (int) (8000*adjust));
        RecipeManagerWa.distillingRegistry.addRecipe(FluidRegistry.getFluid("wa.fluid.tequila"), 100,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.sake"), 30), (int) (8000*adjust));

        // 醸造樽レシピ（蒸留酒を再醸造）
        // ラムレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.若いラム), 24, new ItemStack(Items.若いラム), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.rum"), 1000), (int) (24000*adjust));
        // ウィスキーレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.若いウィスキー), 24, new ItemStack(Items.若いウィスキー), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.whisky"), 1000), (int) (24000*adjust));
        // テキーラレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.若いテキーラ), 24, new ItemStack(Items.若いテキーラ), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.tequila"), 1000), (int) (24000*adjust));

        // 混合醸造酒レシピ
        // 梅酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.梅の実), 24, new ItemStack(Items.sugar), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.umesyu"), 1000), (int) (24000*adjust));
        // ひれ酒レシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.日本酒), 24, new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.ordinal()), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.fin_sake"), 1000), (int) (24000*adjust));
        // スライムゼリーソーダレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.slime_ball), 24, new ItemStack(Items.シードル), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.slime_jelly_soda"), 1000), (int) (24000*adjust));
        // かぼちゃエールレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Blocks.pumpkin), 24, new ItemStack(Items.sugar), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.pumpkin_ale"), 1000), (int) (24000*adjust));
        // ヘルリカーレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.ghast_tear), 24, new ItemStack(Items.nether_wart), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.infernal_liquor"), 1000), (int) (24000*adjust));
        // マグマクリームサワーレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.magma_cream), 24, new ItemStack(Items.fermented_spider_eye), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.magma_cream_sour"), 1000), (int) (24000*adjust));
        // どぶろくレシピ
        RecipeManagerWa.brewingRegistry.addRecipe(new ItemStack(Items.米), 24, new ItemStack(Items.米), 24,
                new FluidStack(FluidRegistry.getFluid("wa.fluid.unrefined_sake"), 1000), (int) (24000*adjust));
    }

}
