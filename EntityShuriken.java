package wa;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShuriken extends EntityThrowable {
	private static final int DIR_INDEX = 29;

	public EntityShuriken(World par1World) {
		super(par1World);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityShuriken(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityShuriken(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	protected void entityInit() {
		this.getDataWatcher().addObject(DIR_INDEX, 0);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

		if (par1MovingObjectPosition.entityHit != null) {

			par1MovingObjectPosition.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, this.getThrower()), 3);
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		setItemRotation(getRotation() + 1);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setInteger("ItemRotation", this.getRotation());
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		this.setItemRotation(par1NBTTagCompound.getInteger("ItemRotation"));
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	public int getRotation() {
		return this.getDataWatcher().getWatchableObjectInt(DIR_INDEX);
	}

	public void setItemRotation(int par1) {
		this.getDataWatcher().updateObject(DIR_INDEX, par1 % 360);
	}

}
