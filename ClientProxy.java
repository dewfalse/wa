package wa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		RenderingRegistry.registerEntityRenderingHandler(EntityObon.class, new RenderObon());

		BlockNoren.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderNorenBlock());

		RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
		RenderingRegistry.registerEntityRenderingHandler(EntityKakejiku.class, new RenderKakejiku());
		RenderingRegistry.registerEntityRenderingHandler(EntityCoin.class, new RenderCoin());

		EntityUmeLeavesFX.rendererId = ModLoader.getMinecraftInstance().renderEngine.getTexture("/mods/wa/textures/ume.png");

		BlockUmeWood.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderUmeWood());

		BlockKawara.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderKawaraBlock());

		MinecraftForge.EVENT_BUS.register(new Particles());
	}
	@Override
	public void preInit() {
		Minecraft mc = FMLClientHandler.instance().getClient();
		File soundDir = new File(mc.getMinecraftDir(), "resources/mod/sound/wa");
		if (!soundDir.exists() && !soundDir.mkdirs()) {
			return;
		}
		boolean needReload = false;
		File outFile = new File(mc.getMinecraftDir() + "/resources/mod/sound/wa/", "koto.ogg");
		if (!outFile.exists() || outFile.length() < 10) {
			copyResource(Wa.class, "/mods/wa/sound/koto.ogg", outFile);
			needReload = true;
		}
		outFile = new File(mc.getMinecraftDir() + "/resources/mod/sound/wa/", "taiko.ogg");
		if (!outFile.exists() || outFile.length() < 10) {
			copyResource(Wa.class, "/mods/wa/sound/taiko.ogg", outFile);
			needReload = true;
		}
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
		try {
			BufferedInputStream reader = new BufferedInputStream(c.getResourceAsStream(res));
			BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(outputFile));
			int size = 0;

			byte[] buf = new byte[1024];
			while ((size = reader.read(buf)) != -1) {
				writer.write(buf, 0, size);
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
