package wa.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dew on 2015/11/22.
 */
public class ItemPackagedLiquor extends Item {
    public static List<Item> list = new ArrayList<Item>();

    static {
        list.add(Items.シードル);
        list.add(Items.金のシードル);
        list.add(Items.高麗人参酒);
        list.add(Items.金の高麗人参酒);
        list.add(Items.馬乳酒);
        list.add(Items.プルケ);
        list.add(Items.どぶろく);
        list.add(Items.カルヴァドス);
        list.add(Items.金のカルヴァドス);
        list.add(Items.スピリタス);
        list.add(Items.ラム);
        list.add(Items.ウィスキー);
        list.add(Items.テキーラ);
        list.add(Items.アルヒ);
        list.add(Items.日本酒);
        list.add(Items.梅酒);
        list.add(Items.ひれ酒);
        list.add(Items.スライムゼリーソーダ);
        list.add(Items.かぼちゃエール);
        list.add(Items.ヘルリカー);
        list.add(Items.マグマクリームサワー);
    }

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if(!p_77659_2_.isRemote) {
            if (!list.isEmpty()) {
                ItemStack itemStack = new ItemStack(list.get(p_77659_2_.rand.nextInt(list.size())));
                NBTTagCompound tag = new NBTTagCompound();
                tag.setShort("Grade", (short) (100 + Math.abs(p_77659_2_.rand.nextGaussian()) * 200));
                itemStack.setTagCompound(tag);
                if (--p_77659_1_.stackSize <= 0) {
                    return itemStack;
                }

                if(!p_77659_3_.inventory.addItemStackToInventory(itemStack)) {
                    p_77659_3_.dropPlayerItemWithRandomChoice(itemStack, false);
                }
            }
        }
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
