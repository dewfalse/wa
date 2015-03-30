package wa;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import wa.block.Blocks;

public class Items extends net.minecraft.init.Items {

	public static Item.ToolMaterial tamahagane = EnumHelper.addToolMaterial("tamahagane", Config.玉鋼強度 < 5 ? 2 : 3, 57 + Config.玉鋼強度 * 40, Config.玉鋼強度 * 0.3F + 4.0F, 3, 18);
	public static Item 刀 = new ItemSword(tamahagane).setUnlocalizedName("wa:katana").setTextureName("wa:katana").setCreativeTab(Wa.creativeTab);
	public static Item 太刀 = new ItemTachi(tamahagane).setUnlocalizedName("wa:tachi").setTextureName("wa:tachi").setCreativeTab(Wa.creativeTab);
	public static Item 玉鋼 = new Item().setUnlocalizedName("wa:tamahagane").setTextureName("wa:tamahagane").setCreativeTab(Wa.creativeTab);
	public static Item 食べ物 = (new ItemWaFood(5, 0.6F, false)).setUnlocalizedName("wa:food").setCreativeTab(Wa.creativeTab);
	public static Item 金槌 = new ItemKanazuchi(Item.ToolMaterial.IRON).setUnlocalizedName("wa:kanaduchi").setTextureName("wa:kanaduchi").setCreativeTab(Wa.creativeTab);
	public static Item 竹炭 = new Item().setUnlocalizedName("wa:takezumi").setTextureName("wa:takezumi").setCreativeTab(Wa.creativeTab);
	public static Item 鋼の斧 = new ItemSteelAxe(tamahagane).setUnlocalizedName("wa:axe").setTextureName("wa:axe").setCreativeTab(Wa.creativeTab);
	public static Item 鋼のツルハシ = new ItemSteelPickaxe(tamahagane).setUnlocalizedName("wa:pickaxe").setTextureName("wa:pickaxe").setCreativeTab(Wa.creativeTab);
	public static Item 鋼のシャベル = new ItemSpade(tamahagane).setUnlocalizedName("wa:shovel").setTextureName("wa:shovel").setCreativeTab(Wa.creativeTab);
	public static Item 鋼のクワ = new ItemHoe(tamahagane).setUnlocalizedName("wa:hoe").setTextureName("wa:hoe").setCreativeTab(Wa.creativeTab);
	public static Item 鋼の金槌 = new ItemKanazuchi(tamahagane).setUnlocalizedName("wa:steelKanaduchi").setTextureName("wa:steelKanaduchi").setCreativeTab(Wa.creativeTab);
	public static Item 貨幣 = new ItemCoin().setUnlocalizedName("wa:kahei").setCreativeTab(Wa.creativeTab);
	public static Item お盆 = new ItemObon().setUnlocalizedName("wa:obon").setTextureName("wa:obon").setCreativeTab(Wa.creativeTab);
	public static Item 手裏剣 = new ItemShuriken().setUnlocalizedName("wa:shuriken").setTextureName("wa:shuriken").setCreativeTab(Wa.creativeTab);
	public static Item 毛筆 = (new Item()).setMaxDamage(32).setUnlocalizedName("wa:mouhitsu").setTextureName("wa:mouhitsu").setCreativeTab(Wa.creativeTab);
	public static Item 掛け軸 = (new ItemShodo()).setUnlocalizedName("wa:kakejiku").setTextureName("wa:kakejiku").setCreativeTab(Wa.creativeTab);
	public static Item 米 = new Item().setUnlocalizedName("wa:kome").setTextureName("wa:kome").setCreativeTab(Wa.creativeTab);
	public static Item 種籾 = new ItemSeeds(Blocks.ine, Blocks.farmland).setUnlocalizedName("wa:tanemomi").setTextureName("wa:tanemomi").setCreativeTab(Wa.creativeTab);
	public static Item 稲 = new ItemIne().setUnlocalizedName("wa:ine").setTextureName("wa:ine").setCreativeTab(Wa.creativeTab);
	public static Item 御鏡 = new ItemMirror().setUnlocalizedName("wa:mikagami").setTextureName("wa:mikagami").setCreativeTab(Wa.creativeTab);
	public static Item 勾玉 = new ItemMagatama().setUnlocalizedName("wa:magatama").setTextureName("wa:magatama").setCreativeTab(Wa.creativeTab);
	public static Item ズク = new Item().setUnlocalizedName("wa:zuku").setTextureName("wa:zuku").setCreativeTab(Wa.creativeTab);
	public static Item ズク破片 = new Item().setUnlocalizedName("wa:zukuDust").setTextureName("wa:zukuDust").setCreativeTab(Wa.creativeTab);
	public static Item 石の金槌 = new ItemKanazuchi(Item.ToolMaterial.STONE).setUnlocalizedName("wa:stoneKanaduchi").setTextureName("wa:stoneKanaduchi").setCreativeTab(Wa.creativeTab);
	public static Item 左下鉄 = new Item().setUnlocalizedName("wa:sagegane").setTextureName("wa:sagegane").setCreativeTab(Wa.creativeTab);
	public static Item 梅の実 = new ItemUmenomi().setUnlocalizedName("wa:umenomi").setTextureName("wa:umenomi").setCreativeTab(Wa.creativeTab);
	public static Item 梅干し = new ItemUmeboshi().setUnlocalizedName("wa:umeboshi").setTextureName("wa:umeboshi").setCreativeTab(Wa.creativeTab);
	public static Item 梅酒 = new ItemUmeshu().setUnlocalizedName("wa:umeshu").setTextureName("wa:umeshu").setCreativeTab(Wa.creativeTab);
	public static Item 茶碗 = new Item().setUnlocalizedName("wa:teaEmpty").setTextureName("wa:teaEmpty").setCreativeTab(Wa.creativeTab);
	public static Item お茶 = new ItemTea().setUnlocalizedName("wa:tea").setTextureName("wa:tea").setCreativeTab(Wa.creativeTab);

    public static Item 磁鉄鉱インゴット = new Item().setUnlocalizedName("wa:magnetite_ingot").setTextureName("wa:ingotMagnetite").setCreativeTab(Wa.creativeTab);
    public static Item 磁石 = new ItemMagnet().setUnlocalizedName("wa:magnet").setTextureName("wa:magnet").setCreativeTab(Wa.creativeTab);

	static Item 家紋;// = new 家紋(Config.家紋ID).setUnlocalizedName("wa:kamon").setCreativeTab(Wa.creativeTab);

	//static Item 陣笠;
	//static Item 提灯;
	//static Item クナイ;

	public static void preInit() {
	}

	public static void init() {
        GameRegistry.registerItem(玉鋼, "tamahagane");
        GameRegistry.registerItem(食べ物, "food");

		//TODO 家紋の名前を設定する
		//TODO 家紋使用時の動作を実装する


        GameRegistry.registerItem(貨幣, "coin");
        GameRegistry.registerItem(石の金槌, "StoneKanaduchi");
        GameRegistry.registerItem(金槌, "kanaduchi");
        GameRegistry.registerItem(鋼の金槌, "SteelKanaduchi");

		//金槌は専用のツールカテゴリに登録する
		//MinecraftForge.setToolClass(石の金槌, "hummer", 1);
		//MinecraftForge.setToolClass(金槌, "hummer", 2);
		//MinecraftForge.setToolClass(鋼の金槌, "hummer", 3);
        石の金槌.setHarvestLevel("hummer", 1);
        金槌.setHarvestLevel("hummer", 2);
        鋼の金槌.setHarvestLevel("hummer", 3);

        GameRegistry.registerItem(太刀, "tachi");
        GameRegistry.registerItem(刀, "katana");
        GameRegistry.registerItem(鋼の斧, "steelAxe");
        GameRegistry.registerItem(鋼のツルハシ, "steelPickaxe");
        GameRegistry.registerItem(鋼のシャベル, "steelShovel");
        GameRegistry.registerItem(鋼のクワ, "steelHoe");
		//addNameForObject(家紋, "kamon", "家紋");
        GameRegistry.registerItem(竹炭, "takezumi");
        GameRegistry.registerItem(お盆, "obon");
        GameRegistry.registerItem(手裏剣, "shuriken");
        GameRegistry.registerItem(掛け軸, "kakejiku");
        GameRegistry.registerItem(毛筆, "mouhitsu");
        GameRegistry.registerItem(米, "kome");
        GameRegistry.registerItem(稲, "ine");
        GameRegistry.registerItem(種籾, "tanemomi");
        GameRegistry.registerItem(御鏡, "mikagami");
        GameRegistry.registerItem(勾玉, "magatama");
        GameRegistry.registerItem(ズク, "zuku");
        GameRegistry.registerItem(ズク破片, "zukuDust");
        GameRegistry.registerItem(左下鉄, "sagegane");
        GameRegistry.registerItem(梅の実, "umenomi");
        GameRegistry.registerItem(梅干し, "umeboshi");
        GameRegistry.registerItem(梅酒, "umeshu");
        GameRegistry.registerItem(茶碗, "chawan");
        GameRegistry.registerItem(お茶, "ocha");

        GameRegistry.registerItem(磁鉄鉱インゴット, "magnetite_ingot");
        GameRegistry.registerItem(磁石, "magnet");

        OreDictionary.registerOre("ingotMagnetite", 磁鉄鉱インゴット);
        OreDictionary.registerOre("rice", 米);

        OreDictionary.registerOre("charcoal", 竹炭);
	}

/*
	static String getStringLocalization(String key, String lang) {
		 return LanguageRegistry.instance().getStringLocalization(key, lang);
	}

	static void addNameForObject(Item item, String name, String 名前) {
		LanguageRegistry.instance().addNameForObject(item, "en_US", name);
		LanguageRegistry.instance().addNameForObject(item, "ja_JP", 名前);
	}

	static void addNameForObject(ItemStack itemStack, String name, String 名前) {
		LanguageRegistry.instance().addNameForObject(itemStack, "en_US", name);
		LanguageRegistry.instance().addNameForObject(itemStack, "ja_JP", 名前);
	}*/
}
