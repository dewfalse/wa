package wa;

import net.minecraft.client.Minecraft;
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

import cpw.mods.fml.client.FMLClientHandler;

public class RenderObon extends Render {

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		renderObon((EntityObon)entity, d0, d1, d2, f, f1);

	}

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
            GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[par1EntityObon.hangingDirection], -0.18F, -0.453125F * (float)Direction.offsetZ[par1EntityObon.hangingDirection]);
            GL11.glRotatef(180.0F + par1EntityObon.rotationYaw, 1.0F, 0.0F, 0.0F);
            int nRotation = par1EntityObon.getRotation();
            GL11.glRotatef((float)(nRotation), 0.0F, 1.0F, 0.0F);

                RenderItem.renderInFrame = true;
                GL11.glScalef(f2, f2, f2);
                RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.5D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;

            GL11.glPopMatrix();
        }
    }

	private void renderObon(EntityObon entityObon, double d0, double d1, double d2,
			float f, float f1) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float)d0, (float)d1 + 0.05F, (float)d2);
        GL11.glRotatef(f, 1.0f, 0.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        Minecraft mc = FMLClientHandler.instance().getClient();
        mc.func_110434_K().func_110577_a(func_110775_a(entityObon));
        float f2 = 0.0625F;
        GL11.glScalef(f2, f2, f2);
        func_77010_a(entityObon, 16, 16, 0, 0);
        renderDisplayItem(entityObon);
    	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    	GL11.glPopMatrix();
	}

    private void func_77010_a(EntityObon par1EntityObon, int par2, int par3, int par4, int par5)
    {
        float f = (float)(-par2) / 2.0F;
        float f1 = (float)(-par3) / 2.0F;
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
                float f15 = f + (float)((i1 + 1) * 16);
                float f16 = f + (float)(i1 * 16);
                float f17 = f1 + (float)((j1 + 1) * 16);
                float f18 = f1 + (float)(j1 * 16);
                this.func_77008_a(par1EntityObon, (f15 + f16) / 2.0F, (f17 + f18) / 2.0F);
                float f19 = (float)(par4 + par2 - i1 * 16) / 256.0F;
                float f20 = (float)(par4 + par2 - (i1 + 1) * 16) / 256.0F;
                float f21 = (float)(par5 + par3 - j1 * 16) / 256.0F;
                float f22 = (float)(par5 + par3 - (j1 + 1) * 16) / 256.0F;
                Tessellator tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)(-f2), (double)f20, (double)f21);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)(-f2), (double)f19, (double)f21);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)(-f2), (double)f19, (double)f22);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)(-f2), (double)f20, (double)f22);
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)f2, (double)f3, (double)f5);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)f2, (double)f4, (double)f5);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)f2, (double)f4, (double)f6);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)f2, (double)f3, (double)f6);
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)(-f2), (double)f7, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)(-f2), (double)f8, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)f2, (double)f8, (double)f10);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)f2, (double)f7, (double)f10);
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)f2, (double)f7, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)f2, (double)f8, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)(-f2), (double)f8, (double)f10);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)(-f2), (double)f7, (double)f10);
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)f2, (double)f12, (double)f13);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)f2, (double)f12, (double)f14);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)(-f2), (double)f11, (double)f14);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)(-f2), (double)f11, (double)f13);
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)(-f2), (double)f12, (double)f13);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)(-f2), (double)f12, (double)f14);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)f2, (double)f11, (double)f14);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)f2, (double)f11, (double)f13);
                tessellator.draw();
            }
        }

    }


    private void func_77008_a(EntityObon entityObon, float f, float f1)
    {
        int i = MathHelper.floor_double(entityObon.posX);
        int j = MathHelper.floor_double(entityObon.posY + (double)(f1 / 16F));
        int k = MathHelper.floor_double(entityObon.posZ);
        if(entityObon.hangingDirection == 0)
        {
            i = MathHelper.floor_double(entityObon.posX + (double)(f / 16F));
        }
        if(entityObon.hangingDirection == 1)
        {
            k = MathHelper.floor_double(entityObon.posZ - (double)(f / 16F));
        }
        if(entityObon.hangingDirection == 2)
        {
            i = MathHelper.floor_double(entityObon.posX - (double)(f / 16F));
        }
        if(entityObon.hangingDirection == 3)
        {
            k = MathHelper.floor_double(entityObon.posZ + (double)(f / 16F));
        }
        float f2 = renderManager.worldObj.getLightBrightness(i, j, k);
        GL11.glColor3f(f2, f2, f2);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
        return new ResourceLocation("wa", "/textures/items/obon");
	}
}
