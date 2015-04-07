package wa.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZabuton extends ModelBase
{
  //fields
    ModelRenderer zabuton1;
    ModelRenderer zabuton2;
    ModelRenderer zabuton3;
    ModelRenderer zabuton4;
    ModelRenderer zabuton5;
    ModelRenderer zabuton6;
    ModelRenderer zabuton7;
    ModelRenderer zabuton8;
  
  public ModelZabuton()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      zabuton1 = new ModelRenderer(this, 0, 0);
      zabuton1.addBox(-8F, 6F, -8F, 16, 2, 16);
      zabuton1.setRotationPoint(0F, 16F, 0F);
      zabuton1.setTextureSize(64, 32);
      zabuton1.mirror = true;
      setRotation(zabuton1, 0F, 0F, 0F);
      zabuton2 = new ModelRenderer(this, 0, 0);
      zabuton2.addBox(-8F, 4F, -8F, 16, 2, 16);
      zabuton2.setRotationPoint(0F, 16F, 0F);
      zabuton2.setTextureSize(64, 32);
      zabuton2.mirror = true;
      setRotation(zabuton2, 0F, 0.1396263F, 0F);
      zabuton3 = new ModelRenderer(this, 0, 0);
      zabuton3.addBox(-8F, 2F, -8F, 16, 2, 16);
      zabuton3.setRotationPoint(0F, 16F, 0F);
      zabuton3.setTextureSize(64, 32);
      zabuton3.mirror = true;
      setRotation(zabuton3, 0F, -0.0349066F, 0F);
      zabuton4 = new ModelRenderer(this, 0, 0);
      zabuton4.addBox(-8F, 0F, -9F, 16, 2, 16);
      zabuton4.setRotationPoint(0F, 16F, 0F);
      zabuton4.setTextureSize(64, 32);
      zabuton4.mirror = true;
      setRotation(zabuton4, 0.0349066F, -0.0698132F, 0F);
      zabuton5 = new ModelRenderer(this, 0, 0);
      zabuton5.addBox(-8F, -2F, -8F, 16, 2, 16);
      zabuton5.setRotationPoint(0F, 16F, 0F);
      zabuton5.setTextureSize(64, 32);
      zabuton5.mirror = true;
      setRotation(zabuton5, 0F, 0F, 0F);
      zabuton6 = new ModelRenderer(this, 0, 0);
      zabuton6.addBox(-8F, -4F, -8F, 16, 2, 16);
      zabuton6.setRotationPoint(0F, 16F, 0F);
      zabuton6.setTextureSize(64, 32);
      zabuton6.mirror = true;
      setRotation(zabuton6, -0.0174533F, 0.0523599F, 0F);
      zabuton7 = new ModelRenderer(this, 0, 0);
      zabuton7.addBox(-8F, -6F, -8F, 16, 2, 16);
      zabuton7.setRotationPoint(0F, 16F, 0F);
      zabuton7.setTextureSize(64, 32);
      zabuton7.mirror = true;
      setRotation(zabuton7, 0F, -0.1745329F, 0F);
      zabuton8 = new ModelRenderer(this, 0, 0);
      zabuton8.addBox(-8F, -8F, -8F, 16, 2, 16);
      zabuton8.setRotationPoint(0F, 16F, 0F);
      zabuton8.setTextureSize(64, 32);
      zabuton8.mirror = true;
      setRotation(zabuton8, 0F, 0.0349066F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int meta)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    switch (meta)
    {
    case 7:
    	zabuton8.render(f5);
    case 6:
    	zabuton7.render(f5);
    case 5:
    	zabuton6.render(f5);
    case 4:
    	zabuton5.render(f5);
    case 3:
    	zabuton4.render(f5);
    case 2:
    	zabuton3.render(f5);
    case 1:
    	zabuton2.render(f5);
    	default:
    		break;
    }
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
