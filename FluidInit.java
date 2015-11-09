package wa;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {
	
	private FluidInit(){}
	public static FluidInit instance = new FluidInit();
	
	public static Fluid umesyuFluid;
	public static Fluid sakeFluid; // 未実装
	
	public static Block fluidDummyBlock;
	
	public static void preInit(){
		// fluiのIcon用ダミーアイテム
		fluidDummyBlock = (new FluidDummyBlock()).setBlockName("wa.fluidDummyBlock");
		GameRegistry.registerBlock(fluidDummyBlock, FluidDummyItem.class, "wa.fluidDummyBlock");
		
		// fluid登録
		umesyuFluid = new Fluid("wa.fluid.umesyu").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(umesyuFluid);
		
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wa.fluid.umesyu",
				200), new ItemStack(Items.梅酒, 1, 0), new ItemStack(
				Items.glass_bottle));
		
		Wa.proxy.registerFluidTex();
	}

}
