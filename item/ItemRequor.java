package wa.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

public class ItemRequor extends ItemLiquorBase {

    List<PotionEffect> potionEffects = new LinkedList<PotionEffect>();

	public ItemRequor() {
		super();
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote) {
			addPotionEffects(par1ItemStack, par3EntityPlayer);
		}

		return par1ItemStack.stackSize <= 0 ? null : par1ItemStack;
	}

    public ItemRequor addPotionEffect(int potionID, int duration, int amplifier) {
        potionEffects.add(new PotionEffect(potionID, duration, amplifier));
        return this;
    }

    public ItemRequor addPotionEffect(PotionEffect potionEffect) {
        potionEffects.add(potionEffect);
        return this;
    }

    public void addPotionEffects(ItemStack par1ItemStack, EntityPlayer par3EntityPlayer) {
        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        int grade = 100;
        if (nbt != null && nbt.hasKey("Grade")) {
            grade = nbt.getInteger("Grade");
        }
        for(PotionEffect potionEffect : potionEffects) {
            Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            if(potion.isBadEffect()) {
                if (grade < 200) {
                    PotionEffect effect = new PotionEffect(potionEffect.getPotionID(), (int) (potionEffect.getDuration() * (200 - grade) / 100.0F), potionEffect.getAmplifier());
                    par3EntityPlayer.addPotionEffect(effect);
                }
            }
            else {
                PotionEffect effect = new PotionEffect(potionEffect.getPotionID(), (int) (potionEffect.getDuration() * grade / 100.0F), potionEffect.getAmplifier());
                par3EntityPlayer.addPotionEffect(effect);
            }
        }
	}

    @Override
    @SideOnly(Side.CLIENT)
    // マウスオーバー時の表示情報
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        int grade = 100;
        if (nbt != null && nbt.hasKey("Grade")) {
            grade = nbt.getInteger("Grade");
        }

        for(PotionEffect potionEffect : potionEffects) {
            String s1 = StatCollector.translateToLocal(potionEffect.getEffectName()).trim();
            Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            // gradeを数値として表示。

            if (potion.isBadEffect())
            {
                if (potionEffect.getDuration() > 20)
                {
                    if (grade < 200) {
                        int i = (int) (potionEffect.getDuration() * (200 - grade) / 100.0F);
                        s1 = s1 + " (" + StringUtils.ticksToElapsedTime(i) + ")";
                        par3List.add(EnumChatFormatting.RED + s1);
                    }
                }
            }
            else
            {
                if (potionEffect.getDuration() > 20)
                {
                    int i = (int) (potionEffect.getDuration() * grade / 100.0F);
                    s1 = s1 + " (" + StringUtils.ticksToElapsedTime(i) + ")";
                    par3List.add(EnumChatFormatting.GRAY + s1);
                }
            }
        }

    }

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		return new ItemStack(Items.glass_bottle, itemStack.stackSize, 0);
	}
}
