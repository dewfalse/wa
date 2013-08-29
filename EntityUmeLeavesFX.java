package wa;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class EntityUmeLeavesFX extends EntityFX {

	public static int rendererId;
	private float portalParticleScale;
	private double blockPosX;
	private double blockPosY;
	private double blockPosZ;

	public EntityUmeLeavesFX(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
		this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		this.blockPosX = this.posX = par2;
		this.blockPosY = this.posY = par4;
		this.blockPosZ = this.posZ = par6;
		particleMaxAge = particleMaxAge * 4;
	}

	public EntityUmeLeavesFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		this.blockPosX = this.posX = par2;
		this.blockPosY = this.posY = par4;
		this.blockPosZ = this.posZ = par6;
		particleMaxAge = particleMaxAge * 4;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public int getBrightnessForRender(float par1) {
		int i = super.getBrightnessForRender(par1);
		float f1 = (float) this.particleAge / (float) this.particleMaxAge;
		f1 *= f1;
		f1 *= f1;
		int j = i & 255;
		int k = i >> 16 & 255;
		k += (int) (f1 * 15.0F * 16.0F);

		if (k > 240) {
			k = 240;
		}

		return j | k << 16;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1,
			float f2, float f3, float f4, float f5) {

		// GL11.glEnable (GL11.GL_BLEND);
		// GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, rendererId);
		float f6 = 0f;
		float f7 = 1.0f;

		float f8 = 0f;// (float)(func_40100_q() / 16) / 16F;
		float f9 = 1f; // f8 + 0.0624375F;
		float f10 = 0.1F * particleScale;
		float f11 = (float) ((prevPosX + (posX - prevPosX) * (double) f) - interpPosX);
		float f12 = (float) ((prevPosY + (posY - prevPosY) * (double) f) - interpPosY);
		float f13 = (float) ((prevPosZ + (posZ - prevPosZ) * (double) f) - interpPosZ);
		float f14 = 1.0F;

		tessellator.setColorOpaque_F(particleRed * f14, particleGreen * f14,
				particleBlue * f14);// 0.18f);
		// tessellator.setColorOpaque_F(particleRed * f14, particleGreen * f14,
		// particleBlue * f14);
		tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10,
				f13 - f3 * f10 - f5 * f10, f7, f9);
		tessellator.addVertexWithUV((f11 - f1 * f10) + f4 * f10,
				f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f7, f8);
		tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10,
				f13 + f3 * f10 + f5 * f10, f6, f8);
		tessellator.addVertexWithUV((f11 + f1 * f10) - f4 * f10,
				f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f6, f9);
		// GL11.glDisable (GL11.GL_BLEND);
	}

}
