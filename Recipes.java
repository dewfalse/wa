package wa;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(Items.刀),
				"I",
				"I",
				"P",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.太刀),
				"II",
				"II",
				"PP",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼のクワ),
				"II",
				"P ",
				"P ",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼のシャベル),
				"I",
				"P",
				"P",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼のツルハシ),
				"III",
				" P ",
				" P ",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼の斧),
				"II",
				"PI",
				"P ",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.鋼の金槌),
				"III",
				" P ",
				'I', Items.玉鋼,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.金槌),
				"III",
				" P ",
				'I', Item.ingotIron,
				'P', Item.stick);
		GameRegistry.addShapedRecipe(new ItemStack(Items.石の金槌),
				"III",
				" P ",
				'I', Block.cobblestone,
				'P', Item.stick);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.掛け軸),
				Item.painting);
		GameRegistry.addShapedRecipe(new ItemStack(Items.毛筆),
				"A",
				"B",
				"C",
				'A', Item.stick,
				'B', Item.feather,
				'C', new ItemStack(Item.dyePowder, 1, 0));
		GameRegistry.addSmelting(Blocks.take.blockID, new ItemStack(Items.竹炭, 1, 0), 0.15F);
		GameRegistry.addSmelting(Blocks.sakuraWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		GameRegistry.addSmelting(Blocks.umeWood.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.takezumiBlock),
				"AAA",
				"AAA",
				"AAA",
				'A', Items.竹炭);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.charcoalBlock),
				"AAA",
				"AAA",
				"AAA",
				'A', new ItemStack(Item.coal, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.竹炭, 9),
				Blocks.takezumiBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 9, 1),
				Blocks.charcoalBlock);

		for(int i = 0; i < 16; ++i) {
			GameRegistry.addRecipe((new ShapelessOreRecipe(new ItemStack(Blocks.colorWood[i]),
					"logWood", new ItemStack(Item.dyePowder, 1, i))));
		}
		GameRegistry.addShapelessRecipe(new ItemStack(Items.お盆),
				Block.woodSingleSlab);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.米),
				Items.稲);
		GameRegistry.addShapedRecipe(new ItemStack(Items.食べ物.itemID, 1, EnumFood.onigiri.ordinal()),
				" A ",
				"AAA",
				'A', Items.米);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.BlockWaHalfBlock, 2, 0),
				"AAA",
				"AAA",
				"AAA",
				'A', Blocks.wara);

		if(Config.レシピ難易度 == RecipeDifficulty.VERY_EASY) {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Block.sand, new ItemStack(Item.coal, 1, 1), Block.oreIron);
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Block.sand, Items.竹炭, Block.oreIron);
		}
		else {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Block.sand, Blocks.charcoalBlock, Block.oreIron);
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tataraBlock),
					Block.sand, Blocks.takezumiBlock, Block.oreIron);

		}
		GameRegistry.addShapelessRecipe(new ItemStack(Items.手裏剣, 16, 0),
				Items.玉鋼, Items.玉鋼);

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.shikui),
				Block.dirt, Blocks.wara, new ItemStack(Item.dyePowder, 1, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sakuraPlank, 4, 0),
				Blocks.sakuraWood);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.stick, 8, 0),
				Blocks.umeWood);

		GameRegistry.addShapedRecipe(new ItemStack(Block.torchWood, 4),
				"X",
				"#",
				'X', Items.竹炭,
				'#', Item.stick);

		//梅干し
		GameRegistry.addShapelessRecipe(new ItemStack(Items.梅干し, 1, 0),
				Items.梅の実);
		//醸造樽
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.brewingBarrel, 1, 0),
				"ABA",
				"A A",
				"ABA",
				'A', "logWood",
				'B', "slabWood"));
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
				int id = ((Map.Entry<Integer, ItemStack>)e).getKey();
				ItemStack result = ((Map.Entry<Integer, ItemStack>)e).getValue();
				if(result.itemID == Item.ingotIron.itemID) {
					FurnaceRecipes.smelting().getSmeltingList().put(id, new ItemStack(replace));
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
		GameRegistry.addSmelting(Items.左下鉄.itemID, new ItemStack(Item.ingotIron), 0.7F);

	}

}
