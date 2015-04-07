package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import wa.block.BlockCharm;

public class RenderCharmBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		//TextureManager engine = FMLClientHandler.instance().getClient().renderEngine;
		//FMLClientHandler.instance().getClient().func_110434_K().func_110577_a(par1ResourceLocation)
		//engine.bindTexture("wa:noren");
		drawBlock(world, x, y, z, block, modelId, renderer);
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		//engine.bindTexture("/terrain.png");
		return false;
	}

	private void drawBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		renderer.renderStandardBlock(block, x, y, z);

	}

	@Override
	public boolean shouldRender3DInInventory(int par1) {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockCharm.renderID;
	}

}
