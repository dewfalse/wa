package wa;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCoin extends EntityThrowable {
	private static final int TYPE_INDEX = 29;

	public EntityCoin(World par1World) {
		super(par1World);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityCoin(World par1World, EntityLivingBase par2EntityLiving) {
		super(par1World, par2EntityLiving);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityCoin(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	protected void entityInit() {
		this.getDataWatcher().addObject(TYPE_INDEX, 0);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

		if (par1MovingObjectPosition.entityHit != null) {

			if(this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP) {
				((EntityPlayerMP)this.getThrower()).addStat(Achievements.coinThrow, 1);
			}
			int damage = 1;
			switch(getCoinType()) {
			case 1:
				damage = 3;
			case 2:
				if(this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP) {
					((EntityPlayerMP)this.getThrower()).addStat(Achievements.moneyMonger, 1);
				}
				damage = 15;
			}
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
		}
		else {
			if (!this.worldObj.isRemote) {
				if(this.worldObj.rand.nextInt(100) < 95) {
					this.worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(Items.貨幣, 1, getCoinType())));
				}
			}
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setInteger("CoinType", this.getCoinType());
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		this.setCoinType(par1NBTTagCompound.getInteger("CoinType"));
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	public int getCoinType() {
		return this.getDataWatcher().getWatchableObjectInt(TYPE_INDEX);
	}

	public void setCoinType(int par1) {
		this.getDataWatcher().updateObject(TYPE_INDEX, par1);
	}

}
