package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import wa.client.Face;
import wa.block.BlockKawara;
import wa.block.Blocks;

public class RenderKawaraBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		//RenderEngine engine = FMLClientHandler.instance().getClient().renderEngine;
		drawBlock(world, x, y, z, block, modelId, renderer);
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		//engine.bindTexture("/terrain.png");
		return false;
	}

	private void drawBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		IIcon icon = Blocks.iron_block.getBlockTextureFromSide(3);
		int metadata = world.getBlockMetadata(x, y, z);
		int angle = 270;
		if((metadata & 0x3) == 0) angle = 270;
		if((metadata & 0x3) == 1) angle = 90;
		if((metadata & 0x3) == 2) angle = 0;
		if((metadata & 0x3) == 3) angle = 180;
		double[] upside = {0.0F, (metadata & 0x4) > 0 ? 0.5F : 0.0F, 0.0F};
		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = icon.getMaxU();
		double maxV = icon.getMaxV();

		Tessellator tess = Tessellator.instance;
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.setBrightness(0);
		Block theblock = world.getBlock(x, y, z);
		{
			double[][] f1 = {{0.0F, 0.1F, -0.1F}, {0.3F, 0.1F, -0.1F}, {0.3F, 0.0F, -0.1F}, {0.0F, 0.0F, -0.1F}};
			double[][] f2 = {{0.0F, 0.5F, 1.0F}, {0.3F, 0.5F, 1.0F}, {0.3F, 0.4F, 1.0F}, {0.0F, 0.4F, 1.0F}};
			Face face1 = new Face(f1).rotate(angle);
			Face face2 = new Face(f2).rotate(angle);
			renderBox(face1.add(upside), face2.add(upside), x, y, z, minU, maxU, minV, maxV);
		}
		{
			double[][] f1 = {{0.3F, 0.1F, -0.1F}, {0.4F, 0.3F, -0.1F}, {0.4F, 0.2F, -0.1F}, {0.3F, 0.0F, -0.1F}};
			double[][] f2 = {{0.3F, 0.5F, 1.0F}, {0.4F, 0.7F, 1.0F}, {0.4F, 0.6F, 1.0F}, {0.3F, 0.4F, 1.0F}};
			Face face1 = new Face(f1).rotate(angle);
			Face face2 = new Face(f2).rotate(angle);
			renderBox(face1.add(upside), face2.add(upside), x, y, z, minU, maxU, minV, maxV);
		}
		{
			double[][] f1 = {{0.4F, 0.3F, -0.1F}, {0.6F, 0.3F, -0.1F}, {0.6F, 0.2F, -0.1F}, {0.4F, 0.2F, -0.1F}};
			double[][] f2 = {{0.4F, 0.7F, 1.0F}, {0.6F, 0.7F, 1.0F}, {0.6F, 0.6F, 1.0F}, {0.4F, 0.6F, 1.0F}};
			Face face1 = new Face(f1).rotate(angle);
			Face face2 = new Face(f2).rotate(angle);
			renderBox(face1.add(upside), face2.add(upside), x, y, z, minU, maxU, minV, maxV);
		}
		{
			double[][] f1 = {{0.6F, 0.3F, -0.1F}, {0.7F, 0.1F, -0.1F}, {0.7F, 0.0F, -0.1F}, {0.6F, 0.2F, -0.1F}};
			double[][] f2 = {{0.6F, 0.7F, 1.0F}, {0.7F, 0.5F, 1.0F}, {0.7F, 0.4F, 1.0F}, {0.6F, 0.6F, 1.0F}};
			Face face1 = new Face(f1).rotate(angle);
			Face face2 = new Face(f2).rotate(angle);
			renderBox(face1.add(upside), face2.add(upside), x, y, z, minU, maxU, minV, maxV);
		}
		{
			double[][] f1 = {{0.7F, 0.1F, -0.1F}, {1.0F, 0.1F, -0.1F}, {1.0F, 0.0F, -0.1F}, {0.7F, 0.0F, -0.1F}};
			double[][] f2 = {{0.7F, 0.5F, 1.0F}, {1.0F, 0.5F, 1.0F}, {1.0F, 0.4F, 1.0F}, {0.7F, 0.4F, 1.0F}};
			Face face1 = new Face(f1).rotate(angle);
			Face face2 = new Face(f2).rotate(angle);
			renderBox(face1.add(upside), face2.add(upside), x, y, z, minU, maxU, minV, maxV);
		}

	}

	@Override
	public boolean shouldRender3DInInventory(int par1) {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockKawara.renderID;
	}

	class Coord {
		double x;
		double y;
		double z;
		Coord(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		Coord(double[] v) {
			this.x = v[0];
			this.y = v[1];
			this.z = v[2];
		}
		void set(double[] v) {
			this.x = v[0];
			this.y = v[1];
			this.z = v[2];
		}
		Coord set(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
			return this;
		}
		Coord add(double x, double y, double z) {
			this.x += x;
			this.y += y;
			this.z += z;
			return this;
		}
	}
	private void renderBox(double[][] s1, double[][] s2, double x, double y, double z, double minU,
			double maxU, double minV, double maxV) {
		Coord[] vlist = new Coord[8];
		vlist[0] = new Coord(s1[0]).add(x, y, z);
		vlist[1] = new Coord(s1[1]).add(x, y, z);
		vlist[2] = new Coord(s1[2]).add(x, y, z);
		vlist[3] = new Coord(s1[3]).add(x, y, z);
		vlist[4] = new Coord(s2[0]).add(x, y, z);
		vlist[5] = new Coord(s2[1]).add(x, y, z);
		vlist[6] = new Coord(s2[2]).add(x, y, z);
		vlist[7] = new Coord(s2[3]).add(x, y, z);
		renderBox(vlist, minU, maxU, minV, maxV);
	}
	private void renderBox(Face f1, Face f2, double x, double y, double z, double minU,
			double maxU, double minV, double maxV) {
		Coord[] vlist = new Coord[8];
		vlist[0] = new Coord(f1.v[0]).add(x, y, z);
		vlist[1] = new Coord(f1.v[1]).add(x, y, z);
		vlist[2] = new Coord(f1.v[2]).add(x, y, z);
		vlist[3] = new Coord(f1.v[3]).add(x, y, z);
		vlist[4] = new Coord(f2.v[0]).add(x, y, z);
		vlist[5] = new Coord(f2.v[1]).add(x, y, z);
		vlist[6] = new Coord(f2.v[2]).add(x, y, z);
		vlist[7] = new Coord(f2.v[3]).add(x, y, z);
		renderBox(vlist, minU, maxU, minV, maxV);
	}

	private void renderBox(Coord[] vlist, double minU,
			double maxU, double minV, double maxV) {
		Tessellator tess = Tessellator.instance;
		tess.addVertexWithUV(vlist[0].x, vlist[0].y, vlist[0].z, maxU, minV);
		tess.addVertexWithUV(vlist[1].x, vlist[1].y, vlist[1].z, maxU, maxV);
		tess.addVertexWithUV(vlist[2].x, vlist[2].y, vlist[2].z, minU, maxV);
		tess.addVertexWithUV(vlist[3].x, vlist[3].y, vlist[3].z, minU, minV);

		tess.addVertexWithUV(vlist[7].x, vlist[7].y, vlist[7].z, minU, minV);
		tess.addVertexWithUV(vlist[6].x, vlist[6].y, vlist[6].z, minU, maxV);
		tess.addVertexWithUV(vlist[5].x, vlist[5].y, vlist[5].z, maxU, maxV);
		tess.addVertexWithUV(vlist[4].x, vlist[4].y, vlist[4].z, maxU, minV);

		tess.addVertexWithUV(vlist[2].x, vlist[2].y, vlist[2].z, maxU, minV);
		tess.addVertexWithUV(vlist[6].x, vlist[6].y, vlist[6].z, maxU, maxV);
		tess.addVertexWithUV(vlist[7].x, vlist[7].y, vlist[7].z, minU, maxV);
		tess.addVertexWithUV(vlist[3].x, vlist[3].y, vlist[3].z, minU, minV);

		tess.addVertexWithUV(vlist[0].x, vlist[0].y, vlist[0].z, minU, minV);
		tess.addVertexWithUV(vlist[4].x, vlist[4].y, vlist[4].z, minU, maxV);
		tess.addVertexWithUV(vlist[5].x, vlist[5].y, vlist[5].z, maxU, maxV);
		tess.addVertexWithUV(vlist[1].x, vlist[1].y, vlist[1].z, maxU, minV);

		tess.addVertexWithUV(vlist[3].x, vlist[3].y, vlist[3].z, minU, minV);
		tess.addVertexWithUV(vlist[7].x, vlist[7].y, vlist[7].z, minU, maxV);
		tess.addVertexWithUV(vlist[4].x, vlist[4].y, vlist[4].z, maxU, maxV);
		tess.addVertexWithUV(vlist[0].x, vlist[0].y, vlist[0].z, maxU, minV);

		tess.addVertexWithUV(vlist[1].x, vlist[1].y, vlist[1].z, maxU, minV);
		tess.addVertexWithUV(vlist[5].x, vlist[5].y, vlist[5].z, maxU, maxV);
		tess.addVertexWithUV(vlist[6].x, vlist[6].y, vlist[6].z, minU, maxV);
		tess.addVertexWithUV(vlist[2].x, vlist[2].y, vlist[2].z, minU, minV);
	}

}
