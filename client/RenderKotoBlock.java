package wa.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import wa.*;
import wa.block.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKotoBlock extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation thisTex = new ResourceLocation("wa:textures/entity/koto_model.png");
    public static RenderKotoBlock thisRenderer;
    private ModelKoto thisModel = new ModelKoto();
    
    public void renderTileEntityThisAt(TileEntityKoto par1Tile, double par2, double par4, double par6, float par8)
    {
        this.setRotation(par1Tile, (float)par2, (float)par4, (float)par6);
    }
    
    public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer)
    {
        super.func_147497_a(par1TileEntityRenderer);
        thisRenderer = this;
    }
    
    public void setRotation(TileEntityKoto par0Tile, float par1, float par2, float par3)
    {
    	byte l = (byte)par0Tile.getBlockMetadata();
    	
    	float f = 0.0F;
    	switch(l)
    	{
    	case 0:
    		f = 180.0F;
    		break;
    	case 1:
    		f = -90.0F;
    		break;
    	case 2:
    		f = 0.0F;
    		break;
    	case 3:
    		f = 90.0F;
    		break;
    		default:
    			break;
    	}
    	
    	this.bindTexture(this.thisTex);
        
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        thisModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix(); 
        
        
        Tessellator tessellator = Tessellator.instance;
        
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
        GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 0.5F, (float)par3 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        
        IIcon iicon = Blocks.koto.getIcon(1, 4);
        float f14 = iicon.getMinU();
        float f15 = iicon.getMaxU();
        float f4 = iicon.getMinV();
        float f5 = iicon.getMaxV();
        this.bindTexture(TextureMap.locationBlocksTexture);
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV(-0.7D, 0.175D, -0.3D, (double)f15, (double)f4);
        tessellator.addVertexWithUV(0.75D, 0.225D, -0.3D, (double)f14, (double)f4);
        tessellator.addVertexWithUV(0.75D, 0.225D, 0.3D, (double)f14, (double)f5);
        tessellator.addVertexWithUV(-0.7D, 0.175D, 0.3D, (double)f15, (double)f5);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV(0.7D, 0.225D, -0.3D, (double)f15, (double)f4);
        tessellator.addVertexWithUV(1.05D, 0.3D, -0.3D, (double)f14, (double)f4);
        tessellator.addVertexWithUV(1.05D, 0.3D, 0.3D, (double)f14, (double)f5);
        tessellator.addVertexWithUV(0.7D, 0.225D, 0.3D, (double)f15, (double)f5);
        tessellator.draw();
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
    
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityThisAt((TileEntityKoto)par1TileEntity, par2, par4, par6, par8);
    }

}
