package wa.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import wa.Config;
import wa.item.EnumFood;
import wa.block.Blocks;
import wa.item.Items;

import java.util.List;
import java.util.Map;

public class Recipes {

	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(Items.刀),
				"I",
				"I",
				"P",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.太刀),
				"II",
				"II",
				"PP",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼のクワ),
				"II",
				"P ",
				"P ",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼のシャベル),
				"I",
				"P",
				"P",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼のツルハシ),
				"III",
				" P ",
				" P ",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼の斧),
				"II",
				"PI",
				"P ",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼の金槌),
				"III",
				" P ",
				'I', Items.玉鋼,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.金槌),
				"III",
				" P ",
				'I', Items.iron_ingot,
				'P', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.石の金槌),
				"III",
				" P ",
				'I', Blocks.cobblestone,
				'P', Items.stick);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.掛け軸),
				Items.painting);
		GameRegistry.addShapedRecipe(new ItemStack(Items.毛筆),
				"A",
				"B",
				"C",
				'A', Items.stick,
				'B', Items.feather,
				'C', new ItemStack(Items.dye, 1, 0));
		GameRegistry.addSmelting(Blocks.take, new ItemStack(Items.竹炭, 1, 0), 0.15F);
		GameRegistry.addSmelting(Blocks.sakuraWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		GameRegistry.addSmelting(Blocks.umeWood, new ItemStack(Items.coal, 1, 1), 0.15F);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.takezumiBlock),
				"AAA",
				"AAA",
				"AAA",
				'A', Items.竹炭);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.charcoalBlock),
				"AAA",
				"AAA",
				"AAA",
				'A', new ItemStack(Items.coal, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.竹炭, 9),
				Blocks.takezumiBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 9, 1),
				Blocks.charcoalBlock);

		for(int i = 0; i < 16; ++i) {
			GameRegistry.addRecipe((new ShapelessOreRecipe(new ItemStack(Blocks.colorWood[i]),
					"logWood", new ItemStack(Items.dye, 1, i))));
		}
		GameRegistry.addShapelessRecipe(new ItemStack(Items.お盆),
				Blocks.wooden_slab);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.米),
				Items.稲);
		GameRegistry.addShapedRecipe(new ItemStack(Items.食べ物, 1, EnumFood.onigiri.ordinal()),
				" A ",
				"AAA",
				'A', Items.米);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.BlockWaHalfBlock, 2, 0),
				"AAA",
				"AAA",
				"AAA",
				'A', Blocks.wara);
		
		/**
		 * @author defeatedcrow
		 * ススキ→藁のレシピ追加
		 */
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.wara, 1, 0),
				"AA",
				"AA",
				'A', Blocks.susuki);

		if(Config.レシピ難易度 == RecipeDifficulty.VERY_EASY) {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Blocks.sand, new ItemStack(Items.coal, 1, 1), Blocks.iron_ore);
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Blocks.sand, Items.竹炭, Blocks.iron_ore);
		}
		else {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Blocks.sand, Blocks.charcoalBlock, Blocks.iron_ore);
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Blocks.sand, Blocks.takezumiBlock, Blocks.iron_ore);

		}
		GameRegistry.addShapelessRecipe(new ItemStack(Items.手裏剣, 16, 0),
				Items.玉鋼, Items.玉鋼);

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.shikui),
				Blocks.dirt, Blocks.wara, new ItemStack(Items.dye, 1, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sakuraPlank, 4, 0),
				Blocks.sakuraWood);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.stick, 8, 0),
				Blocks.umeWood);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.stick, 8, 0),
				Blocks.umeLog);

		GameRegistry.addShapedRecipe(new ItemStack(Blocks.torch, 4),
				"X",
				"#",
				'X', Items.竹炭,
				'#', Items.stick);

		//梅干し
		GameRegistry.addShapelessRecipe(new ItemStack(Items.梅干し, 1, 0),
				Items.梅の実);
		//醸造樽
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.brewingBarrelII, 1, 0),
				"ABA",
				"A A",
				"ABA",
				'A', "logWood",
				'B', "slabWood"));

        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.brewingBarrelII, 1, 0),
                Blocks.brewingBarrel);

        //蒸留器
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.still, 1, 0),
                Items.bucket, Items.glass_bottle);
        //圧搾機
        GameRegistry.addRecipe(new ItemStack(Blocks.squeezer, 1, 0),
                "A",
                "B",
                'A', Blocks.piston,
                'B', Items.cauldron);
        //アルコールランプ
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.spiritLamp, 1, 0),
                "ABA",
                "A A",
                "AAA",
                'A', "blockGlass",
                'B', Items.string));


        GameRegistry.addShapedRecipe(new ItemStack(Blocks.kawara, 3, 0),
                "  A",
                " AA",
                "AA ",
                'A', Items.brick);
        GameRegistry.addShapedRecipe(new ItemStack(Items.磁石, 1, 5000),
                "M M",
                "M M",
                "MMM",
                'M', Items.磁鉄鉱インゴット);

        GameRegistry.addShapedRecipe(new ItemStack(Blocks.zabuton, 4),
                "MM",
                "MM",
                'M', Blocks.carpet);

        // dye red
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.zabuton, 1, 8),
                Blocks.zabuton, new ItemStack(Items.dye, 1, 1));
        // dye blue
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.zabuton, 1, 0),
                new ItemStack(Blocks.zabuton, 1, 8), new ItemStack(Items.dye, 1, 4));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.iron_ingot, 1),
                Items.玉鋼);

        GameRegistry.addSmelting(Blocks.oreMagnetite, new ItemStack(Items.磁鉄鉱インゴット), 0.7F);
	}

	public static void postInit() {

		/*
		 * ULTRA_HARDはズク破片→ズク→左下鉄→鉄インゴット
		 * VERY_HARDはズク→左下鉄→鉄インゴット
		 * HARDは左下鉄→鉄インゴット
		 */
		if(Config.レシピ難易度 <= RecipeDifficulty.HARD) {
			Item replace = Items.左下鉄;
			if(Config.レシピ難易度 == RecipeDifficulty.VERY_HARD) {
				replace = Items.ズク;
			}
			else if(Config.レシピ難易度 == RecipeDifficulty.ULTRA_HARD) {
				replace = Items.ズク破片;
			}

			for(Object e : FurnaceRecipes.smelting().getSmeltingList().entrySet()) {
				ItemStack input = ((Map.Entry<ItemStack, ItemStack>)e).getKey();
				ItemStack result = ((Map.Entry<Integer, ItemStack>)e).getValue();
				if(result.getItem() == Items.iron_ingot) {
					FurnaceRecipes.smelting().getSmeltingList().put(input, new ItemStack(replace));
				}
			}
            List list =  CraftingManager.getInstance().getRecipeList();
            for(int i = 0; i < list.size(); ++i) {
                Object o = list.get(i);
                if(o instanceof ShapedRecipes) {
                    ShapedRecipes recipe = (ShapedRecipes)o;
                    ItemStack output = recipe.getRecipeOutput();
                    if(recipe.recipeItems.length == 1 && recipe.recipeItems[0].getItem() == Item.getItemFromBlock(Blocks.iron_block)) {
                    }
                    else if(output.getItem() == Items.iron_ingot) {
                        if(Config.レシピ難易度 <= RecipeDifficulty.HARD) {
                            list.set(i, new ShapedRecipes(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, new ItemStack(Items.ズク破片, output.stackSize, output.getItemDamage())));
                        }
                        else if(Config.レシピ難易度 == RecipeDifficulty.NORMAL) {
                            list.set(i, new ShapedRecipes(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, new ItemStack(Items.ズク, output.stackSize, output.getItemDamage())));
                        }
                    }
                }
            }
		}

		GameRegistry.addShapedRecipe(new ItemStack(Items.ズク),
				"AAA",
				"AAA",
				"AAA",
				'A', Items.ズク破片);
		GameRegistry.addShapedRecipe(new ItemStack(Items.左下鉄),
				"AA",
				"AA",
				'A', Items.ズク);
		GameRegistry.addSmelting(Items.左下鉄, new ItemStack(Items.iron_ingot), 0.7F);

	}

}
