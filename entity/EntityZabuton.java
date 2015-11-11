package wa.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wa.block.Blocks;

public class EntityZabuton extends Entity {
	
	protected float mountHeight = 0.0F;

	public EntityZabuton(World world) {
		super(world);
		this.preventEntitySpawning = true;
        this.setSize(0.5F, 0.125F);
        this.yOffset = mountHeight;
	}
	
	public EntityZabuton(World par1World, double par2, double par4, double par6)
    {
        this(par1World);
        this.setPosition(par2, par4 + (double)this.yOffset, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
        this.rotationYaw = 0.0F;
    }
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
        	this.setBeenAttacked();
        	if (par1DamageSource instanceof EntityDamageSource)
            {
                Entity by = ((EntityDamageSource)par1DamageSource).getEntity();
                if (by != null && by instanceof EntityPlayer)
                {
                	int x = MathHelper.floor_double(posX);
                	int y = MathHelper.floor_double(posY);
                	int z = MathHelper.floor_double(posZ);
                	Block block = worldObj.getBlock(x, y, z);
                	
                	if (block != null && block == Blocks.zabuton && worldObj.setBlockToAir(x, y, z)) {
            			
            			if (this.riddenByEntity != null)
                        {
                            this.riddenByEntity.mountEntity(this);
                        }
            			
            			this.setDead();
            			return true;
            		}
                }
            }
        }
        return false;
    }

	@Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

	@Override
	protected void entityInit() {}
	
	@Override
    public AxisAlignedBB getCollisionBox(Entity par1Entity)
    {
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }
    
    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if (worldObj.isRemote)
    	{
//    		this.worldObj.spawnParticle("crit", this.posX, this.posY + 1, this.posZ, this.motionX, this.motionY + 0.2D, this.motionZ);
    	}
    	else
    	{
        	int x = MathHelper.floor_double(posX);
        	int y = MathHelper.floor_double(posY);
        	int z = MathHelper.floor_double(posZ);
        	Block block = worldObj.getBlock(x, y, z);
        	
        	if (block == null || block.isAir(worldObj, x, y, z) || !worldObj.isAirBlock(x, y + 1, z))
        	{
        		Logger logger = LogManager.getLogger("Wa");
        		logger.info("dead.");
        		this.setDead();
        	}
        	else
        	{
        		if (block == Blocks.zabuton)
        		{
        			int size = worldObj.getBlockMetadata(x, y, z) & 7;
        			float f = 0.125F * (size + 1);
        			if (mountHeight != f)
        			{
        				setMountHeight(f);
        			}
        		}
        	}
    	}
    }

    @Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
    	mountHeight = nbt.getFloat("mount");
    }

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setFloat("mount", mountHeight);
	}
	
	// 騎乗位置調整
	
	protected float getMountHeight()
	{
		return mountHeight;
	}
	
	protected void setMountHeight(float f)
	{
		mountHeight = f;
	}
	
	@Override
    public double getMountedYOffset()
    {
        return (double)getMountHeight() - 0.125D + 0.06000001192092896D;
    }
	
	@Override
    public boolean interactFirst(EntityPlayer par1EntityPlayer)
    {
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer)
        {
            return true;
        }
		else
		{
			ItemStack item = par1EntityPlayer.getCurrentEquippedItem();
			
			if (item == null || item.getItem() == null)
			{
	            if (!this.worldObj.isRemote)
	            {
	                par1EntityPlayer.mountEntity(this);
	            }

	            return true;
	        }
	        else
	        {
	        	int x = MathHelper.floor_double(posX);
	        	int y = MathHelper.floor_double(posY);
	        	int z = MathHelper.floor_double(posZ);
	        	return item.getItem().onItemUse(item, par1EntityPlayer, worldObj, x, y, z, 0, 0.5F, mountHeight, 0.5F);
	        }
		}
    }
}
