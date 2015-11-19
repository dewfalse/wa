package wa.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import wa.Achievements;

public class ItemTachi extends ItemSword {

	public static String[] names = {"ツーハンデッド・カタナブレードツルギ", "ベッピン", "備前長船", "虎徹", "村正", "菊一文字", "童子切安綱", "三日月宗近", "鬼丸国綱", "数珠丸", "大典太"};

	public ItemTachi(ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
		setMaxDamage(par2EnumToolMaterial.getMaxUses() * 2);
	}

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
    }

    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {

        if(event.crafting.getItem() == Items.刀) {
            event.player.addStat(Achievements.katana, 1);
        }
        if(event.crafting.getItem() == Items.太刀) {
            // クラフト時は無銘の太刀しか作れない
            // 銘刀は村人との取引のみ
            event.crafting.setStackDisplayName("無銘の太刀");

            event.player.addStat(Achievements.katana, 1);
        }

    }

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		// 銘刀は右クリックすると銘とエンチャントがつく
		// 村人取引時に銘＆エンチャントを固定させないための苦肉の策
		if(par2World.isRemote == false && par1ItemStack.hasDisplayName() == false) {
			String name = names[par2World.rand.nextInt(names.length)];
			par1ItemStack.setStackDisplayName(name);
			EnchantmentHelper.addRandomEnchantment(par2World.rand, par1ItemStack, 5 + par2World.rand.nextInt(15));
		}
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}

}
