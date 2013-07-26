package wa;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

		if(Config.たたら製鉄炉レシピ簡易化) {
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
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sakuraWood, 4, 0),
				Blocks.sakuraWood);
	}

}
