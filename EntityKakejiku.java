package wa;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityKakejiku extends EntityHanging {

	private static final int TYPE_INDEX = 30;

	public EntityKakejiku(World par1World) {
		super(par1World);
	}

	public EntityKakejiku(World par1World, int par2, int par3, int par4,
			int par5) {
		super(par1World, par2, par3, par4, par5);
		setMotive(EnumKakejiku.empty);

		this.setDirection(par5);
	}

	@SideOnly(Side.CLIENT)
	public EntityKakejiku(World par1World, int par2, int par3, int par4,
			int par5, String par6Str) {
		this(par1World, par2, par3, par4, par5);

		for (int j1 = 0; j1 < EnumKakejiku.values().length; ++j1) {
			EnumKakejiku motive = EnumKakejiku.values()[j1];

			if (motive.title.equals(par6Str)) {
				dataWatcher.updateObject(TYPE_INDEX, motive.title);
				break;
			}
		}

		this.setDirection(par5);
	}


	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		if(getMotive() == EnumKakejiku.empty)
		{
			ItemStack eq = par1EntityPlayer.getCurrentEquippedItem();
			if (eq != null && eq.itemID == Items.毛筆.itemID) {

				EnumKakejiku motive = EnumKakejiku.values()[1+this.rand.nextInt(EnumKakejiku.values().length-1)];

				if(this.worldObj.isRemote == false) {
					setMotive(motive);
				}
			}
		}
		return super.interactFirst(par1EntityPlayer);
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(TYPE_INDEX, EnumKakejiku.empty.title);
	}

	public EnumKakejiku getMotive() {
		String s = dataWatcher.getWatchableObjectString(TYPE_INDEX);
		return EnumKakejiku.valueOf(s);
	}

	public void setMotive(EnumKakejiku motive) {
		dataWatcher.updateObject(TYPE_INDEX, motive.title);
	}

	@Override
	public void setPositionAndRotation2(double par1, double par3, double par5,
			float par7, float par8, int par9) {
		// 位置ずれ対策にオーバーライド
		// EntityのsetPositionAndRotation2が上にずらしてしまうため
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setString("Motive", getMotive().title);
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		String s = par1NBTTagCompound.getString("Motive");
		dataWatcher.updateObject(TYPE_INDEX, s);

		super.readEntityFromNBT(par1NBTTagCompound);
	}

	@Override
	public int getWidthPixels() {
		return this.getMotive().sizeX;
	}

	@Override
	public int getHeightPixels() {
		return this.getMotive().sizeY;
	}

	@Override
	public void onBroken(Entity entity) {
		ItemStack itemStack = new ItemStack(Items.掛け軸);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("Motive", getMotive().title);
		itemStack.setTagCompound(tag);
		entityDropItem(itemStack, 0.0F);
	}
}
