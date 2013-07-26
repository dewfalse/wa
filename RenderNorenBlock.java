package wa;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderNorenBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		RenderEngine engine = FMLClientHandler.instance().getClient().renderEngine;
		//engine.bindTexture("wa:noren");
		drawBlock(world, x, y, z, block, modelId, renderer);
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		engine.bindTexture("/terrain.png");
		return false;
	}

	private void drawBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		renderer.renderStandardBlock(block, x, y, z);

	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockNoren.renderID;
	}

}
