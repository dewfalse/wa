package wa;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderUmeWood implements ISimpleBlockRenderingHandler {

	private final float C = 1.0F;// ceil
	private final float M = 0.5F;// middle
	private final float F = 0.0F;// floor
	private final float W = 0.3F;// width

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBoundsForItemRender();
		renderer.setRenderBoundsFromBlock(block);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D,
				renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		RenderEngine engine = FMLClientHandler.instance().getClient().renderEngine;
		renderBlock(world, x, y, z, block, modelId, renderer);
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		engine.bindTexture("/terrain.png");
		return true;
	}

	boolean renderBlock(IBlockAccess world, int x, int y, int z, Block block,
			int modelId, RenderBlocks renderer) {
		int blockID = world.getBlockId(x, y, z);
		boolean[] nbr = { world.getBlockId(x, y + 1, z) == blockID,
				world.getBlockId(x, y - 1, z) == blockID,
				world.getBlockId(x + 1, y, z) == blockID,
				world.getBlockId(x, y, z + 1) == blockID,
				world.getBlockId(x - 1, y, z) == blockID,
				world.getBlockId(x, y, z - 1) == blockID, };
		int srnd = 0;
		for (int i = 2; i < 6; ++i) {
			if (nbr[i]) {
				srnd++;
			}
		}

		renderer.setRenderBounds(M - W, M - W, M - W, M + W, M + W, M + W);
		renderer.renderStandardBlock(block, x, y, z);
		if (nbr[0]) {
			renderer.setRenderBounds(M - W, M + W, M - W, M + W, C, M + W);
			renderer.renderStandardBlock(block, x, y, z);
		}
		if (nbr[1] || srnd == 0) {
			renderer.setRenderBounds(M - W, F, M - W, M + W, M - W, M + W);
			renderer.renderStandardBlock(block, x, y, z);
		}
		if (nbr[2]) {
			renderer.setRenderBounds(M + W, M - W, M - W, C, M + W, M + W);
			renderer.renderStandardBlock(block, x, y, z);
		}
		if (nbr[3]) {
			renderer.setRenderBounds(M - W, M - W, M + W, M + W, M + W, C);
			renderer.renderStandardBlock(block, x, y, z);
		}
		if (nbr[4]) {
			renderer.setRenderBounds(F, M - W, M - W, M - W, M + W, M + W);
			renderer.renderStandardBlock(block, x, y, z);
		}
		if (nbr[5]) {
			renderer.setRenderBounds(M - W, M - W, F, M + W, M + W, M - W);
			renderer.renderStandardBlock(block, x, y, z);
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockUmeWood.renderID;
	}

}
