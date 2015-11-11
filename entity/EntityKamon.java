package wa.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wa.item.EnumKamon;
import wa.item.Items;

import java.util.ArrayList;

public class EntityKamon extends EntityHanging {
	public EnumKamon kamon;

	public EntityKamon(World par1World)
	{
		super(par1World);
	}

	public EntityKamon(World par1World, int par2, int par3, int par4, int par5)
	{
		super(par1World, par2, par3, par4, par5);
		ArrayList arraylist = new ArrayList();
		EnumKamon[] aenumart = EnumKamon.values();
		int i1 = aenumart.length;

		for (int j1 = 0; j1 < i1; ++j1)
		{
			EnumKamon enumart = aenumart[j1];
			this.kamon = enumart;
			this.setDirection(par5);

			if (this.onValidSurface())
			{
				arraylist.add(enumart);
			}
		}

		if (!arraylist.isEmpty())
		{
			this.kamon = (EnumKamon)arraylist.get(this.rand.nextInt(arraylist.size()));
		}

		this.setDirection(par5);
	}

	@SideOnly(Side.CLIENT)
	public EntityKamon(World par1World, int par2, int par3, int par4, int par5, String par6Str)
	{
		this(par1World, par2, par3, par4, par5);
		EnumKamon[] aenumart = EnumKamon.values();
		int i1 = aenumart.length;

		for (int j1 = 0; j1 < i1; ++j1)
		{
			EnumKamon enumart = aenumart[j1];

			if (enumart.title.equals(par6Str))
			{
				this.kamon = enumart;
				break;
			}
		}

		this.setDirection(par5);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setString("Motive", this.kamon.title);
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		String s = par1NBTTagCompound.getString("Motive");
		EnumKamon[] aenumart = EnumKamon.values();
		int i = aenumart.length;

		for (int j = 0; j < i; ++j)
		{
			EnumKamon enumart = aenumart[j];

			if (enumart.title.equals(s))
			{
				this.kamon = enumart;
			}
		}

		if (this.kamon == null)
		{
			this.kamon = EnumKamon.Kebab;
		}

		super.readEntityFromNBT(par1NBTTagCompound);
	}

	@Override
	public int getWidthPixels()
	{
		return this.kamon.sizeX;
	}

	@Override
	public int getHeightPixels()
	{
		return this.kamon.sizeY;
	}

	@Override
	public void onBroken(Entity entity)
	{
		this.entityDropItem(new ItemStack(Items.家紋), 0.0F);
	}
}
