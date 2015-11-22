package wa.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import wa.FluidInit;
import wa.block.TileEntitySpiritLamp;

@SideOnly(Side.CLIENT)
public class RenderSpiritLampTile extends TileEntitySpecialRenderer {

	public static RenderSpiritLampTile innerRenderer;

	public void renderTileEntityBottleAt(TileEntitySpiritLamp par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		innerRenderer = this;
	}

	public void setRotation(TileEntitySpiritLamp tile, float par1, float par2, float par3) {

		Tessellator tessellator = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.25F, 1.25F, 2.0F, 0.9F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 0.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);

		IIcon iicon = FluidInit.fluidDummyBlock.getIcon(0, 21);
		float fu = iicon.getMinU();
		float fU = iicon.getMaxU();
		float fv = iicon.getMinV();
		float fV = iicon.getMaxV();
		this.bindTexture(TextureMap.locationBlocksTexture);
		
		double d = 0.0625D;
		double j = 0.4375D;
		double h = j - (tile.getAmount() / (tile.getCapacity() * 1.0F)) * 0.25D;
		
		if (tile.getAmount() > 0){
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(j, h, j, (double) fu, (double) fv);
			tessellator.addVertexWithUV(-j, h, j, (double) fU, (double) fv);
			tessellator.addVertexWithUV(-j, h, -j, (double) fU, (double) fV);
			tessellator.addVertexWithUV(j, h, -j, (double) fu, (double) fV);
			tessellator.draw();
			
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(j, j, -j, (double) fu, (double) fv);
			tessellator.addVertexWithUV(-j, j, -j, (double) fU, (double) fv);
			tessellator.addVertexWithUV(-j, j, j, (double) fU, (double) fV);
			tessellator.addVertexWithUV(j, j, j, (double) fu, (double) fV);
			tessellator.draw();
			
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(-j, j, -j, (double) fu, (double) fv);
			tessellator.addVertexWithUV(j, j, -j, (double) fU, (double) fv);
			tessellator.addVertexWithUV(j, h, -j, (double) fU, (double) fV);
			tessellator.addVertexWithUV(-j, h, -j, (double) fu, (double) fV);
			tessellator.draw();
			
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(j, j, j, (double) fu, (double) fv);
			tessellator.addVertexWithUV(-j, j, j, (double) fU, (double) fv);
			tessellator.addVertexWithUV(-j, h, j, (double) fU, (double) fV);
			tessellator.addVertexWithUV(j, h, j, (double) fu, (double) fV);
			tessellator.draw();
			
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(j, j, -j, (double) fu, (double) fv);
			tessellator.addVertexWithUV(j, j, j, (double) fU, (double) fv);
			tessellator.addVertexWithUV(j, h, j, (double) fU, (double) fV);
			tessellator.addVertexWithUV(j, h, -j, (double) fu, (double) fV);
			tessellator.draw();
			
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(-j, j, j, (double) fu, (double) fv);
			tessellator.addVertexWithUV(-j, j, -j, (double) fU, (double) fv);
			tessellator.addVertexWithUV(-j, h, -j, (double) fU, (double) fV);
			tessellator.addVertexWithUV(-j, h, j, (double) fu, (double) fV);
			tessellator.draw();
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBottleAt((TileEntitySpiritLamp) par1TileEntity, par2, par4, par6, par8);
	}
}
