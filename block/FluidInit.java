package wa;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import wa.block.FluidDummyBlock;
import wa.block.FluidDummyItem;
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
        addFluid("wa.fluid.apple_juice", new ItemStack(Items.りんごジュース, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.golden_apple_juice", new ItemStack(Items.金のりんごジュース, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.potato_essence", new ItemStack(Items.じゃがいもエキス, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.carrot_juice", new ItemStack(Items.にんじんジュース, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.golden_carrot_juice", new ItemStack(Items.金のにんじんジュース, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.syrup", new ItemStack(Items.糖蜜, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.wort", new ItemStack(Items.麦汁, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.cactus_essence", new ItemStack(Items.サボテンエキス, 1, 0), new ItemStack(Items.glass_bottle));

        // 醸造酒（エキス＠醸造樽）
        addFluid("wa.fluid.cider", new ItemStack(Items.シードル, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.golden_cider", new ItemStack(Items.金のシードル, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.potato_ferment_essence", new ItemStack(Items.じゃがいも発酵エキス, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.ginseng_liquor", new ItemStack(Items.高麗人参酒, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.golden_ginseng_liquor", new ItemStack(Items.金の高麗人参酒, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.syrup_ferment_essence", new ItemStack(Items.糖蜜発酵エキス, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.wash", new ItemStack(Items.ウォッシュ, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.pulque", new ItemStack(Items.プルケ, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.kumis", new ItemStack(Items.馬乳酒, 1, 0), new ItemStack(Items.glass_bottle));

        // 蒸留酒（醸造酒＠蒸留器）
        addFluid("wa.fluid.calvados", new ItemStack(Items.カルヴァドス, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.golden_calvados", new ItemStack(Items.金のカルヴァドス, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.spirytus", new ItemStack(Items.スピリタス, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.rum", new ItemStack(Items.ラム酒, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.whisky", new ItemStack(Items.ウィスキー, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.tequila", new ItemStack(Items.テキーラ, 1, 0), new ItemStack(Items.glass_bottle));

        // 蒸留酒（蒸留酒＠蒸留器）
        addFluid("wa.fluid.alcohol", new ItemStack(Items.アルコール, 1, 0), new ItemStack(Items.glass_bottle));

        // 醸造酒
        addFluid("wa.fluid.fin_sake", new ItemStack(Items.ひれ酒, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.slime_jelly_soda", new ItemStack(Items.スライムゼリーソーダ, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid( "wa.fluid.pumpkin_ale", new ItemStack(Items.かぼちゃエール, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.infernal_liquor", new ItemStack(Items.ヘルリカー, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.magma_cream_sour", new ItemStack(Items.マグマクリームサワー, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.umesyu", new ItemStack(Items.梅酒, 1, 0), new ItemStack(Items.glass_bottle));
        addFluid("wa.fluid.sake", new ItemStack(Items.日本酒, 1, 0), new ItemStack(Items.glass_bottle));


        Wa.proxy.registerFluidTex();
    }

    public static void addFluid(String fluidName, ItemStack filledContainer, ItemStack emptyContainer) {
        Fluid fluid = new Fluid(fluidName).setDensity(1000).setViscosity(1000);
        fluids.add(fluid);
        FluidRegistry.registerFluid(fluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidName, 200),
                filledContainer,
                emptyContainer);
    }
}
