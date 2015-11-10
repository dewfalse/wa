package wa;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {

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

    private FluidInit() {
    }

    public static FluidInit instance = new FluidInit();

    public static Block fluidDummyBlock;

    public static void preInit() {
        // fluidのIcon用ダミーアイテム
        fluidDummyBlock = (new FluidDummyBlock()).setBlockName("wa.fluidDummyBlock");
        GameRegistry.registerBlock(fluidDummyBlock, FluidDummyItem.class, "wa.fluidDummyBlock");

        // fluid登録
        ciderFluid = new Fluid("wa.fluid.cider").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(ciderFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.cider", 200),
                new ItemStack(Items.シードル, 1, 0),
                new ItemStack(Items.glass_bottle));

        goldenCiderFluid = new Fluid("wa.fluid.golden_cider").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(goldenCiderFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.golden_cider", 200),
                new ItemStack(Items.金のシードル, 1, 0),
                new ItemStack(Items.glass_bottle));

        finSakeFluid = new Fluid("wa.fluid.fin_sake").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(finSakeFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.fin_sake", 200),
                new ItemStack(Items.ひれ酒, 1, 0),
                new ItemStack(Items.glass_bottle));

        slimeJellySodaFluid = new Fluid("wa.fluid.slime_jelly_soda").setDensity(1500).setViscosity(500);
        FluidRegistry.registerFluid(slimeJellySodaFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.slime_jelly_soda", 200),
                new ItemStack(Items.スライムゼリーソーダ, 1, 0),
                new ItemStack(Items.glass_bottle));

        ginsengLiquorFluid = new Fluid("wa.fluid.ginseng_liquor").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(ginsengLiquorFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.ginseng_liquor", 200),
                new ItemStack(Items.高麗人参酒, 1, 0),
                new ItemStack(Items.glass_bottle));

        kumisFluid = new Fluid("wa.fluid.kumis").setDensity(1100).setViscosity(950);
        FluidRegistry.registerFluid(kumisFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.kumis", 200),
                new ItemStack(Items.馬乳酒, 1, 0),
                new ItemStack(Items.glass_bottle));

        pumpkinAleFluid = new Fluid("wa.fluid.pumpkin_ale").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(pumpkinAleFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.pumpkin_ale", 200),
                new ItemStack(Items.かぼちゃエール, 1, 0),
                new ItemStack(Items.glass_bottle));

        infernalLiquorFluid = new Fluid("wa.fluid.infernal_liquor").setDensity(1200).setViscosity(900);
        FluidRegistry.registerFluid(infernalLiquorFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.infernal_liquor", 200),
                new ItemStack(Items.ヘルリカー, 1, 0),
                new ItemStack(Items.glass_bottle));

        magmaCreamSourFluid = new Fluid("wa.fluid.magma_cream_sour").setDensity(1200).setViscosity(900);
        FluidRegistry.registerFluid(magmaCreamSourFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.magma_cream_sour", 200),
                new ItemStack(Items.マグマクリームサワー, 1, 0),
                new ItemStack(Items.glass_bottle));

        umesyuFluid = new Fluid("wa.fluid.umesyu").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(umesyuFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.umesyu", 200),
                new ItemStack(Items.梅酒, 1, 0),
                new ItemStack(Items.glass_bottle));

        sakeFluid = new Fluid("wa.fluid.sake").setDensity(1000).setViscosity(1000);
        FluidRegistry.registerFluid(sakeFluid);
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.sake", 200),
                new ItemStack(Items.日本酒, 1, 0),
                new ItemStack(Items.glass_bottle));

        Wa.proxy.registerFluidTex();
    }

}
