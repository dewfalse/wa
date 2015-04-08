package wa.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKoto extends ModelBase
{
	  //fields
	    ModelRenderer base1;
	    ModelRenderer base2;
	    ModelRenderer base3;
	    ModelRenderer kaku1;
	    ModelRenderer kaku2;
	    ModelRenderer ashi1;
	    ModelRenderer ashi2;
	    ModelRenderer ji1;
	    ModelRenderer ji2;
	    ModelRenderer ji3;
	    ModelRenderer ji4;
	    ModelRenderer ji5;
	    ModelRenderer ji6;
	  
	  public ModelKoto()
	  {
	    textureWidth = 64;
	    textureHeight = 64;
	    
	      base1 = new ModelRenderer(this, 0, 0);
	      base1.addBox(-10F, 4F, -4F, 20, 3, 8);
	      base1.setRotationPoint(0F, 16F, 0F);
	      base1.setTextureSize(64, 64);
	      base1.mirror = true;
	      setRotation(base1, 0F, 0F, 0.0523599F);
	      base2 = new ModelRenderer(this, 0, 14);
	      base2.addBox(-14F, 3.5F, -4F, 4, 3, 8);
	      base2.setRotationPoint(0F, 16F, 0F);
	      base2.setTextureSize(64, 64);
	      base2.mirror = true;
	      setRotation(base2, 0F, 0F, 0F);
	      base3 = new ModelRenderer(this, 29, 14);
	      base3.addBox(10F, 4F, -4F, 7, 3, 8);
	      base3.setRotationPoint(0F, 16F, 0F);
	      base3.setTextureSize(64, 64);
	      base3.mirror = true;
	      setRotation(base3, 0F, 0F, 0.0523599F);
	      kaku1 = new ModelRenderer(this, 0, 28);
	      kaku1.addBox(-11F, 2.5F, -4F, 1, 1, 8);
	      kaku1.setRotationPoint(0F, 16F, 0F);
	      kaku1.setTextureSize(64, 64);
	      kaku1.mirror = true;
	      setRotation(kaku1, 0F, 0F, 0F);
	      kaku2 = new ModelRenderer(this, 0, 28);
	      kaku2.addBox(10F, 3.5F, -4F, 1, 1, 8);
	      kaku2.setRotationPoint(0F, 16F, 0F);
	      kaku2.setTextureSize(64, 64);
	      kaku2.mirror = true;
	      setRotation(kaku2, 0F, 0F, 0F);
	      ashi1 = new ModelRenderer(this, 24, 28);
	      ashi1.addBox(-13F, 6F, -3F, 2, 2, 2);
	      ashi1.setRotationPoint(0F, 16F, 0F);
	      ashi1.setTextureSize(64, 64);
	      ashi1.mirror = true;
	      setRotation(ashi1, 0F, 0F, 0F);
	      ashi2 = new ModelRenderer(this, 24, 28);
	      ashi2.addBox(-13F, 6F, 1F, 2, 2, 2);
	      ashi2.setRotationPoint(0F, 16F, 0F);
	      ashi2.setTextureSize(64, 64);
	      ashi2.mirror = true;
	      setRotation(ashi2, 0F, 0F, 0F);
	      ji1 = new ModelRenderer(this, 33, 28);
	      ji1.addBox(-8F, 3F, 2F, 1, 1, 1);
	      ji1.setRotationPoint(0F, 16F, 0F);
	      ji1.setTextureSize(64, 64);
	      ji1.mirror = true;
	      setRotation(ji1, 0F, 0F, 0F);
	      ji2 = new ModelRenderer(this, 33, 28);
	      ji2.addBox(-5F, 3F, 1F, 1, 1, 1);
	      ji2.setRotationPoint(0F, 16F, 0F);
	      ji2.setTextureSize(64, 64);
	      ji2.mirror = true;
	      setRotation(ji2, 0F, 0F, 0F);
	      ji3 = new ModelRenderer(this, 33, 28);
	      ji3.addBox(-3F, 3F, 0F, 1, 1, 1);
	      ji3.setRotationPoint(0F, 16F, 0F);
	      ji3.setTextureSize(64, 64);
	      ji3.mirror = true;
	      setRotation(ji3, 0F, 0F, 0F);
	      ji4 = new ModelRenderer(this, 33, 28);
	      ji4.addBox(0F, 3F, -1F, 1, 1, 1);
	      ji4.setRotationPoint(0F, 16F, 0F);
	      ji4.setTextureSize(64, 64);
	      ji4.mirror = true;
	      setRotation(ji4, 0F, 0F, 0F);
	      ji5 = new ModelRenderer(this, 33, 28);
	      ji5.addBox(2F, 3F, -2F, 1, 1, 1);
	      ji5.setRotationPoint(0F, 16F, 0F);
	      ji5.setTextureSize(64, 64);
	      ji5.mirror = true;
	      setRotation(ji5, 0F, 0F, 0F);
	      ji6 = new ModelRenderer(this, 33, 28);
	      ji6.addBox(5F, 3F, -3F, 1, 1, 1);
	      ji6.setRotationPoint(0F, 16F, 0F);
	      ji6.setTextureSize(64, 64);
	      ji6.mirror = true;
	      setRotation(ji6, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5);
	    base1.render(f5);
	    base2.render(f5);
	    base3.render(f5);
	    kaku1.render(f5);
	    kaku2.render(f5);
	    ashi1.render(f5);
	    ashi2.render(f5);
	    ji1.render(f5);
	    ji2.render(f5);
	    ji3.render(f5);
	    ji4.render(f5);
	    ji5.render(f5);
	    ji6.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	  }

	}
