package wa.client;

import wa.EntityZabuton;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 何も描画しない透明Entityのためのクラス
 * @author defeatedcrow
 */
@SideOnly(Side.CLIENT)
public class RenderZabutonEntity extends Render
{
	//仮
	private static final ResourceLocation tex = new ResourceLocation("wa:textures/entity/zabuton.png");

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		//なにもしない
	}
	
	protected ResourceLocation getStunTextures(EntityZabuton par1Entity)
    {
        return tex;
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getStunTextures((EntityZabuton)entity);
	}

}
