package wa.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import wa.TileEntityZabuton;

@SideOnly(Side.CLIENT)
public class RenderZabutonBlock extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation thisTex = new ResourceLocation("wa:textures/entity/zabuton_model.png");
	private static final ResourceLocation altTex = new ResourceLocation("wa:textures/entity/zabuton_model_alt.png");
    public static RenderZabutonBlock thisRenderer;
    private ModelZabuton thisModel = new ModelZabuton();
    
    public void renderTileEntityThisAt(TileEntityZabuton par1Tile, double par2, double par4, double par6, float par8)
    {
        this.setRotation(par1Tile, (float)par2, (float)par4, (float)par6);
    }
    
    public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer)
    {
        super.func_147497_a(par1TileEntityRenderer);
        thisRenderer = this;
    }
    
    public void setRotation(TileEntityZabuton par0Tile, float par1, float par2, float par3)
    {
    	byte l = (byte)par0Tile.getBlockMetadata();
    	boolean alt = (l & 8) != 0;
    	byte size = (byte) (l & 7);
    	
    	this.bindTexture(alt ? this.altTex : this.thisTex);
        
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        thisModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, size);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix(); 
    }
    
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityThisAt((TileEntityZabuton)par1TileEntity, par2, par4, par6, par8);
    }

}
