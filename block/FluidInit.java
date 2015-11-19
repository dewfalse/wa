package wa;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import wa.block.FluidDummyBlock;
import wa.block.FluidDummyItem;
import wa.block.WaFluid;
import wa.item.Items;

import java.util.LinkedHashSet;
import java.util.Set;

public class FluidInit {
/*
    // シードル
    public static Fluid ciderFluid;
    // 金のシードル
    public static Fluid goldenCiderFluid;
    // ひれ酒
    public static Fluid finSakeFluid;
    // スライムゼリーソーダ
    public static Fluid slimeJellySodaFluid;
    // 高麗人参酒
    public static Fluid ginsengLiquorFluid;
    // 馬乳酒
    public static Fluid kumisFluid;
    // かぼちゃエール
    public static Fluid pumpkinAleFluid;
    // ステアウェイトゥヘル
    public static Fluid infernalLiquorFluid;
    // マグマクリームサワー
    public static Fluid magmaCreamSourFluid;
    // 梅酒
    public static Fluid umesyuFluid;
    // 日本酒
    public static Fluid sakeFluid;

    // アルコール
    public static Fluid alcohol;

    public static Fluid appleJuiceFluid;
    public static Fluid goldenAppleJuiceFluid;
    public static Fluid potatoEssenceFluid;
    public static Fluid carrotJuiceFluid;
    public static Fluid goldenCarrotJuiceFluid;
    public static Fluid syrupFluid;
    public static Fluid wortFluid;
    public static Fluid cactusEssenceFluid;
    public static Fluid calvadosFluid;
    public static Fluid goldenCalvadosFluid;
    public static Fluid spirytusFluid;
    public static Fluid rumFluid;
    public static Fluid whiskyFluid;
    public static Fluid tequilaFluid;
    public static Fluid goldenGinsengLiquorFluid;
    */
    public static Set<Fluid> fluids = new LinkedHashSet<Fluid>();

    private FluidInit() {
    }

    public static FluidInit instance = new FluidInit();

    public static Block fluidDummyBlock;

    public static void preInit() {
        // fluidのIcon用ダミーアイテム
        fluidDummyBlock = (new FluidDummyBlock()).setBlockName("wa.fluidDummyBlock");
        GameRegistry.registerBlock(fluidDummyBlock, FluidDummyItem.class, "wa.fluidDummyBlock");

        // fluid登録

        // エキス（圧搾機）
        fluids.add(new WaFluid("wa.fluid.apple_juice").registerFluid().registerFluidContainer(200, new ItemStack(Items.りんごジュース, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.golden_apple_juice").setLuminosity(10).setRarity(EnumRarity.uncommon).registerFluid().registerFluidContainer(200, new ItemStack(Items.金のりんごジュース, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.potato_essence").registerFluid().registerFluidContainer(200, new ItemStack(Items.じゃがいもエキス, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.carrot_juice").registerFluid().registerFluidContainer(200, new ItemStack(Items.にんじんジュース, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.golden_carrot_juice").setLuminosity(10).setRarity(EnumRarity.uncommon).registerFluid().registerFluidContainer(200, new ItemStack(Items.金のにんじんジュース, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.syrup").registerFluid().registerFluidContainer(200, new ItemStack(Items.糖蜜, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.wort").registerFluid().registerFluidContainer(200, new ItemStack(Items.麦汁, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.cactus_essence").registerFluid().registerFluidContainer(200, new ItemStack(Items.サボテンエキス, 1, 0), new ItemStack(Items.glass_bottle)));

        // 醸造酒（エキス＠醸造樽）
        fluids.add(new WaFluid("wa.fluid.cider").registerFluid().registerFluidContainer(200, new ItemStack(Items.シードル, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.golden_cider").setLuminosity(10).setRarity(EnumRarity.uncommon).registerFluid().registerFluidContainer(200, new ItemStack(Items.金のシードル, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.potato_ferment_essence").registerFluid().registerFluidContainer(200, new ItemStack(Items.じゃがいも発酵エキス, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.ginseng_liquor").registerFluid().registerFluidContainer(200, new ItemStack(Items.高麗人参酒, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.golden_ginseng_liquor").setLuminosity(10).setRarity(EnumRarity.uncommon).registerFluid().registerFluidContainer(200, new ItemStack(Items.金の高麗人参酒, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.syrup_ferment_essence").registerFluid().registerFluidContainer(200, new ItemStack(Items.糖蜜発酵エキス, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.wash").registerFluid().registerFluidContainer(200, new ItemStack(Items.ウォッシュ, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.pulque").registerFluid().registerFluidContainer(200, new ItemStack(Items.プルケ, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.kumis").registerFluid().registerFluidContainer(200, new ItemStack(Items.馬乳酒, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.unrefined_sake").registerFluid().registerFluidContainer(200, new ItemStack(Items.どぶろく, 1, 0), new ItemStack(Items.glass_bottle)));

        // 蒸留酒（醸造酒＠蒸留器）
        fluids.add(new WaFluid("wa.fluid.calvados").registerFluid().registerFluidContainer(200, new ItemStack(Items.カルヴァドス, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.golden_calvados").setLuminosity(10).setRarity(EnumRarity.uncommon).registerFluid().registerFluidContainer(200, new ItemStack(Items.金のカルヴァドス, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.spirytus").registerFluid().registerFluidContainer(200, new ItemStack(Items.スピリタス, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.young_rum").registerFluid().registerFluidContainer(200, new ItemStack(Items.若いラム, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.young_whisky").registerFluid().registerFluidContainer(200, new ItemStack(Items.若いウィスキー, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.young_tequila").registerFluid().registerFluidContainer(200, new ItemStack(Items.若いテキーラ, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.arkhi").registerFluid().registerFluidContainer(200, new ItemStack(Items.アルヒ, 1, 0), new ItemStack(Items.glass_bottle)));

        // 蒸留酒（蒸留酒＠蒸留器）
        fluids.add(new WaFluid("wa.fluid.alcohol").registerFluid().registerFluidContainer(200, new ItemStack(Items.アルコール, 1, 0), new ItemStack(Items.glass_bottle)));

        // 蒸留酒（蒸留酒＠醸造酒）
        fluids.add(new WaFluid("wa.fluid.rum").registerFluid().registerFluidContainer(200, new ItemStack(Items.ラム, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.whisky").registerFluid().registerFluidContainer(200, new ItemStack(Items.ウィスキー, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.tequila").registerFluid().registerFluidContainer(200, new ItemStack(Items.テキーラ, 1, 0), new ItemStack(Items.glass_bottle)));

        // 醸造酒
        fluids.add(new WaFluid("wa.fluid.fin_sake").registerFluid().registerFluidContainer(200, new ItemStack(Items.ひれ酒, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.slime_jelly_soda").registerFluid().registerFluidContainer(200, new ItemStack(Items.スライムゼリーソーダ, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.pumpkin_ale").registerFluid().registerFluidContainer(200, new ItemStack(Items.かぼちゃエール, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.infernal_liquor").registerFluid().registerFluidContainer(200, new ItemStack(Items.ヘルリカー, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.magma_cream_sour").registerFluid().registerFluidContainer(200, new ItemStack(Items.マグマクリームサワー, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.umesyu").registerFluid().registerFluidContainer(200, new ItemStack(Items.梅酒, 1, 0), new ItemStack(Items.glass_bottle)));
        fluids.add(new WaFluid("wa.fluid.sake").registerFluid().registerFluidContainer(200, new ItemStack(Items.日本酒, 1, 0), new ItemStack(Items.glass_bottle)));


        Wa.proxy.registerFluidTex();
    }
}
