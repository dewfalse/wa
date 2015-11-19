package wa.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import wa.*;
import wa.FluidInit;
import wa.block.*;
import wa.entity.*;

import java.io.*;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(EntityObon.class, new RenderObon());

		BlockNoren.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderNorenBlock());

		BlockCharm.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderCharmBlock());

		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
		RenderingRegistry.registerEntityRenderingHandler(EntityKakejiku.class, new RenderKakejiku());
		RenderingRegistry.registerEntityRenderingHandler(EntityCoin.class, new RenderCoin());

		BlockUmeWood.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderUmeLog());

		BlockKawara.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderKawaraBlock());
		
		BlockSusuki.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderSusuki());

        BlockSpiritLamp.renderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderSpiritLampBlock());

        BlockStill.renderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderStillBlock());

        BlockSqueezer.renderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderSqueezerBlock());

		// defeatedcrow追加物
		RenderingRegistry.registerEntityRenderingHandler(EntityZabuton.class, new RenderZabutonEntity());
		
		ClientRegistry.registerTileEntity(TileEntityZabuton.class, "wa.tileentityZabuton", new RenderZabutonBlock());
        ClientRegistry.registerTileEntity(TileEntityKoto.class, "wa.tileentityKoto", new RenderKotoBlock());

        MinecraftForge.EVENT_BUS.register(new Particles());

		VillagerRegistry.instance().registerVillagerSkin(Config.町人ID, new ResourceLocation("wa", "textures/villager.png"));
		VillagerRegistry.instance().registerVillagerSkin(Config.刀鍛冶ID, new ResourceLocation("wa", "textures/swordsmith.png"));
		VillagerRegistry.instance().registerVillagerSkin(Config.茶人ID, new ResourceLocation("wa", "textures/teaMaster.png"));
		VillagerRegistry.instance().registerVillagerSkin(Config.神官ID, new ResourceLocation("wa", "textures/priest.png"));
	}
	@Override
	public void preInit() {
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void addStat(Achievement ach, int i) {
		FMLClientHandler.instance().getClient().thePlayer.addStat(ach, i);
	}

	public static void copyResource(Class c, String res, File outputFile) {
		BufferedInputStream reader = null;
		BufferedOutputStream writer = null;
		try {
			reader = new BufferedInputStream(c.getResourceAsStream(res));
			writer = new BufferedOutputStream(new FileOutputStream(outputFile));
			int size = 0;

			byte[] buf = new byte[1024];
			while ((size = reader.read(buf)) != -1) {
				writer.write(buf, 0, size);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* defeatedcrow追加 */
	@Override
	public void registerFluidTex() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Post event) {
        for(Fluid fluid : FluidInit.fluids) {
            fluid.setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        }
        /*
        FluidRegistry.getFluid("wa.fluid.apple_juice").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.golden_apple_juice").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.potato_essence").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.carrot_juice").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.golden_carrot_juice").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.syrup").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.wort").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.cactus_essence").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.cider").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.golden_cider").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.potato_ferment_essence").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.ginseng_liquor").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.golden_ginseng_liquor").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.syrup_ferment_essence").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.wash").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.kumis").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.calvados").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.golden_calvados").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.spirytus").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.rum").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.whisky").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.tequila").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.alcohol").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.fin_sake").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.slime_jelly_soda").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.pumpkin_ale").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.infernal_liquor").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.magma_cream_sour").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.umesyu").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        FluidRegistry.getFluid("wa.fluid.sake").setIcons(FluidInit.fluidDummyBlock.getIcon(0, 0));
        */
	}
}
