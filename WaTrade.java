package wa;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import wa.block.Blocks;
import wa.item.EnumFood;
import wa.item.Items;

import java.util.Random;

public class WaTrade implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager,
			MerchantRecipeList recipeList, Random random) {
		if(villager.getProfession() == Config.町人ID) {
			//金貨x1⇔銀貨x64
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 64, 0), new ItemStack(Items.貨幣, 1, 1)));
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1, 1), new ItemStack(Items.貨幣, 64, 0)));

			//銀貨x1⇔銭貨x64
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 64, 1), new ItemStack(Items.貨幣, 1, 2)));
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1, 2), new ItemStack(Items.貨幣, 64, 1)));

			//食べ物
			for(int i = 0; i < EnumFood.values().length; ++i) {
				//recipeList.add(new MerchantRecipe( new ItemStack(Items.食べ物, 1, i), new ItemStack(Items.貨幣, 1 + random.nextInt(8), 0)));
				recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1 + random.nextInt(8), 0), new ItemStack(Items.食べ物, 1, i)));
			}
			//魚
			recipeList.add(new MerchantRecipe( new ItemStack(Items.fish), new ItemStack(Items.貨幣, 1 + random.nextInt(3), 0)));
			recipeList.add(new MerchantRecipe( new ItemStack(Items.cooked_fished), new ItemStack(Items.貨幣, 2 + random.nextInt(7), 0)));
			//ダイヤ
			recipeList.add(new MerchantRecipe( new ItemStack(Items.diamond), new ItemStack(Items.貨幣, 1, 2)));
			//金インゴット
			recipeList.add(new MerchantRecipe( new ItemStack(Items.iron_ingot), new ItemStack(Items.貨幣, 4 + random.nextInt(13), 1)));
			//ケーキ
			recipeList.add(new MerchantRecipe( new ItemStack(Items.cake), new ItemStack(Items.貨幣, 10 + random.nextInt(11), 0)));
			//パンプキンパイ
			recipeList.add(new MerchantRecipe( new ItemStack(Items.iron_ingot), new ItemStack(Items.貨幣, 5 + random.nextInt(9), 0)));
			//クッキー
			recipeList.add(new MerchantRecipe( new ItemStack(Items.iron_ingot), new ItemStack(Items.貨幣, 1 + random.nextInt(5), 0)));
			//琴
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1 + random.nextInt(8), 1), new ItemStack(Blocks.koto)));
			//太鼓
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1 + random.nextInt(8), 1), new ItemStack(Blocks.taiko)));
			//毛筆
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1 + random.nextInt(8), 0), new ItemStack(Items.毛筆)));
			//掛け軸
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 5 + random.nextInt(8), 0), new ItemStack(Items.掛け軸)));
			//暖簾
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 5 + random.nextInt(8), 0), new ItemStack(Blocks.noren)));
		}
		else if(villager.getProfession() == Config.刀鍛冶ID) {
			//鉄インゴット→銀貨1～4
			recipeList.add(new MerchantRecipe( new ItemStack(Items.iron_ingot), new ItemStack(Items.貨幣, 1 + random.nextInt(4), 1)));

			//玉鋼→銀貨4～12
			recipeList.add(new MerchantRecipe( new ItemStack(Items.玉鋼), new ItemStack(Items.貨幣, 4 + random.nextInt(9), 1)));

			//玉鋼3～16→刀
			recipeList.add(new MerchantRecipe( new ItemStack(Items.玉鋼, 3 + random.nextInt(14), 1), new ItemStack(Items.刀)));

			//玉鋼10～32→太刀
			recipeList.add(new MerchantRecipe( new ItemStack(Items.玉鋼, 10 + random.nextInt(23), 1), new ItemStack(Items.刀)));

			//金貨1～10→刀
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1 + random.nextInt(10), 2), new ItemStack(Items.刀)));

			//金貨10～20→太刀
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 10 + random.nextInt(11), 2), new ItemStack(Items.太刀)));

			//銀貨1→手裏剣1～4
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1, 1), new ItemStack(Items.手裏剣, 1 + random.nextInt(4))));

            if(random.nextInt(5) == 0) {
                //磁鉄鉱インゴット
                recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 10 + random.nextInt(11), 2), new ItemStack(Items.磁鉄鉱インゴット)));
            }
		}
		else if(villager.getProfession() == Config.茶人ID) {

			//金貨1～10→茶碗
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1, 2), new ItemStack(Items.茶碗)));

			//茶碗→お茶
			recipeList.add(new MerchantRecipe( new ItemStack(Items.茶碗, 1, 0), new ItemStack(Items.お茶)));

			//茶碗＋銀貨1～10→お茶
			recipeList.add(new MerchantRecipe( new ItemStack(Items.茶碗, 1, 0), new ItemStack(Items.貨幣, 1 + random.nextInt(10), 1), new ItemStack(Items.お茶)));
		}
		else if(villager.getProfession() == Config.神官ID) {

			//金貨1～10→茶碗
			recipeList.add(new MerchantRecipe( new ItemStack(Items.貨幣, 1, 2), new ItemStack(Blocks.charm)));
		}
	}

}
