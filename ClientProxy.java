package wa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
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
	}
	@Override
	public void preInit() {
		Minecraft mc = FMLClientHandler.instance().getClient();
		File f = mc.func_110438_M().func_110612_e();
		System.out.println(f.getPath());
		/*
		File soundDir = new File(mc.getMinecraftDir(), "resources/mod/sound/wa");
		if (!soundDir.exists() && !soundDir.mkdirs()) {
			return;
		}
		boolean needReload = false;
		File outFile = new File(mc.getMinecraftDir() + "/resources/mod/sound/wa/", "koto.ogg");
		if (!outFile.exists()) {
			copyResource(Wa.class, "/mods/wa/sound/koto.ogg", outFile);
			needReload = true;
		}
		outFile = new File(mc.getMinecraftDir() + "/resources/mod/sound/wa/", "taiko.ogg");
		if (!outFile.exists()) {
			copyResource(Wa.class, "/mods/wa/sound/taiko.ogg", outFile);
			needReload = true;
		}
		*/
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

			while ((size = reader.read()) != -1) {
				writer.write(size);
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
