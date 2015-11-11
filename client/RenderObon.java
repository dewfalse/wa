package wa.client;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import wa.entity.EntityObon;

public class RenderObon extends Render {

    private static final ResourceLocation field_110807_a = new ResourceLocation("wa", "textures/obon.png");

	private void renderDisplayItem(EntityObon par1EntityObon) {

        ItemStack itemstack = par1EntityObon.getDisplayedItem();

        if (itemstack != null)
        {
            EntityItem entityitem = new EntityItem(par1EntityObon.worldObj, 0.0F, 0.0F, 0.0F, itemstack);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
            float f2 = 3.0F;
            GL11.glPushMatrix();
            GL11.glScalef(f2, f2, f2);
            GL11.glTranslatef(-0.453125F * Direction.offsetX[par1EntityObon.hangingDirection], -0.18F, -0.453125F * Direction.offsetZ[par1EntityObon.hangingDirection]);
            GL11.glRotatef(180.0F + par1EntityObon.rotationYaw, 1.0F, 0.0F, 0.0F);
            int nRotation = par1EntityObon.getRotation();
            GL11.glRotatef((nRotation), 0.0F, 1.0F, 0.0F);

                RenderItem.renderInFrame = true;
                GL11.glScalef(f2, f2, f2);
                RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.5D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;

            GL11.glPopMatrix();
        }
    }

    public void renderObon(EntityObon par1EntityObon, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4 + 0.05F, (float)par6);
        GL11.glRotatef(par8, 1.0F, 0.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        this.bindEntityTexture(par1EntityObon);
        float f2 = 0.0625F;
        GL11.glScalef(f2, f2, f2);
        this.func_77010_a(par1EntityObon, 16, 16, 0, 0);
        renderDisplayItem(par1EntityObon);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110806_a(EntityObon par1EntityObon)
    {
        return field_110807_a;
    }

    private void func_77010_a(EntityObon par1EntityObon, int par2, int par3, int par4, int par5)
    {
        float f = (-par2) / 2.0F;
        float f1 = (-par3) / 2.0F;
        float f2 = 0.5F;
        float f3 = 0.75F;
        float f4 = 0.8125F;
        float f5 = 0.0F;
        float f6 = 0.0625F;
        float f7 = 0.75F;
        float f8 = 0.8125F;
        float f9 = 0.001953125F;
        float f10 = 0.001953125F;
        float f11 = 0.7519531F;
        float f12 = 0.7519531F;
        float f13 = 0.0F;
        float f14 = 0.0625F;

        for (int i1 = 0; i1 < par2 / 16; ++i1)
        {
            for (int j1 = 0; j1 < par3 / 16; ++j1)
            {
                float f15 = f + ((i1 + 1) * 16);
                float f16 = f + (i1 * 16);
                float f17 = f1 + ((j1 + 1) * 16);
                float f18 = f1 + (j1 * 16);
                this.func_77008_a(par1EntityObon, (f15 + f16) / 2.0F, (f17 + f18) / 2.0F);
                float f19 = (par4 + par2 - i1 * 16) / 256.0F;
                float f20 = (par4 + par2 - (i1 + 1) * 16) / 256.0F;
                float f21 = (par5 + par3 - j1 * 16) / 256.0F;
                float f22 = (par5 + par3 - (j1 + 1) * 16) / 256.0F;
                Tessellator tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addVertexWithUV(f15, f18, (-f2), f20, f21);
                tessellator.addVertexWithUV(f16, f18, (-f2), f19, f21);
                tessellator.addVertexWithUV(f16, f17, (-f2), f19, f22);
                tessellator.addVertexWithUV(f15, f17, (-f2), f20, f22);
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addVertexWithUV(f15, f17, f2, f3, f5);
                tessellator.addVertexWithUV(f16, f17, f2, f4, f5);
                tessellator.addVertexWithUV(f16, f18, f2, f4, f6);
                tessellator.addVertexWithUV(f15, f18, f2, f3, f6);
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                tessellator.addVertexWithUV(f15, f17, (-f2), f7, f9);
                tessellator.addVertexWithUV(f16, f17, (-f2), f8, f9);
                tessellator.addVertexWithUV(f16, f17, f2, f8, f10);
                tessellator.addVertexWithUV(f15, f17, f2, f7, f10);
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                tessellator.addVertexWithUV(f15, f18, f2, f7, f9);
                tessellator.addVertexWithUV(f16, f18, f2, f8, f9);
                tessellator.addVertexWithUV(f16, f18, (-f2), f8, f10);
                tessellator.addVertexWithUV(f15, f18, (-f2), f7, f10);
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV(f15, f17, f2, f12, f13);
                tessellator.addVertexWithUV(f15, f18, f2, f12, f14);
                tessellator.addVertexWithUV(f15, f18, (-f2), f11, f14);
                tessellator.addVertexWithUV(f15, f17, (-f2), f11, f13);
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV(f16, f17, (-f2), f12, f13);
                tessellator.addVertexWithUV(f16, f18, (-f2), f12, f14);
                tessellator.addVertexWithUV(f16, f18, f2, f11, f14);
                tessellator.addVertexWithUV(f16, f17, f2, f11, f13);
                tessellator.draw();
            }
        }
    }

    private void func_77008_a(EntityObon par1EntityObon, float par2, float par3)
    {
        int i = MathHelper.floor_double(par1EntityObon.posX);
        int j = MathHelper.floor_double(par1EntityObon.posY + (par3 / 16.0F));
        int k = MathHelper.floor_double(par1EntityObon.posZ);

        if (par1EntityObon.hangingDirection == 2)
        {
            i = MathHelper.floor_double(par1EntityObon.posX + (par2 / 16.0F));
        }

        if (par1EntityObon.hangingDirection == 1)
        {
            k = MathHelper.floor_double(par1EntityObon.posZ - (par2 / 16.0F));
        }

        if (par1EntityObon.hangingDirection == 0)
        {
            i = MathHelper.floor_double(par1EntityObon.posX - (par2 / 16.0F));
        }

        if (par1EntityObon.hangingDirection == 3)
        {
            k = MathHelper.floor_double(par1EntityObon.posZ + (par2 / 16.0F));
        }

        int l = this.renderManager.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int i1 = l % 65536;
        int j1 = l / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, i1, j1);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110806_a((EntityObon) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderObon((EntityObon)par1Entity, par2, par4, par6, par8, par9);
    }
}
