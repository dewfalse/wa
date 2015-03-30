package wa;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import wa.block.BlockCharm;
import wa.block.BlockKawara;
import wa.block.BlockNoren;
import wa.block.BlockUmeWood;

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
		RenderingRegistry.registerBlockHandler(new RenderUmeWood());

		BlockKawara.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderKawaraBlock());

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
}
