package wa.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import wa.Config;
import wa.Wa;
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
	public static Item 茶碗 = new Item().setUnlocalizedName("wa:teaEmpty").setTextureName("wa:teaEmpty").setCreativeTab(Wa.creativeTab);
	public static Item お茶 = new ItemTea().setUnlocalizedName("wa:tea").setTextureName("wa:tea").setCreativeTab(Wa.creativeTab);

    public static Item 磁鉄鉱インゴット = new Item().setUnlocalizedName("wa:magnetite_ingot").setTextureName("wa:ingotMagnetite").setCreativeTab(Wa.creativeTab);
    public static Item 磁石 = new ItemMagnet().setUnlocalizedName("wa:magnet").setTextureName("wa:magnet").setCreativeTab(Wa.creativeTab);

	public static Item 家紋;// = new 家紋(Config.家紋ID).setUnlocalizedName("wa:kamon").setCreativeTab(Wa.creativeTab);
    public static Item 梅酒 = new ItemRequor().addPotionEffect(Potion.confusion.id, 120, 0).addPotionEffect(Potion.digSpeed.id, 240, 0).addPotionEffect(Potion.moveSpeed.id, 240, 0)
            .setUnlocalizedName("wa:umeshu").setTextureName("wa:umeshu").setCreativeTab(Wa.creativeTab);

    // エキス
    public static Item りんごジュース = new ItemRequor().setUnlocalizedName("wa:appleJuice").setTextureName("wa:appleJuice").setCreativeTab(Wa.creativeTab);
    public static Item 金のりんごジュース = new ItemRequor().setUnlocalizedName("wa:goldenAppleJuice").setTextureName("wa:goldenAppleJuice").setCreativeTab(Wa.creativeTab);
    public static Item じゃがいもエキス = new ItemRequor().setUnlocalizedName("wa:potatoEssence").setTextureName("wa:potatoEssence").setCreativeTab(Wa.creativeTab);
    public static Item にんじんジュース = new ItemRequor().setUnlocalizedName("wa:carrotJuice").setTextureName("wa:carrotJuice").setCreativeTab(Wa.creativeTab);
    public static Item 金のにんじんジュース = new ItemRequor().setUnlocalizedName("wa:goldenCarrotJuice").setTextureName("wa:goldenCarrotJuice").setCreativeTab(Wa.creativeTab);
    public static Item 糖蜜 = new ItemRequor().setUnlocalizedName("wa:syrup").setTextureName("wa:syrup").setCreativeTab(Wa.creativeTab);
    public static Item 麦汁 = new ItemRequor().setUnlocalizedName("wa:wort").setTextureName("wa:wort").setCreativeTab(Wa.creativeTab);
    public static Item サボテンエキス = new ItemRequor().setUnlocalizedName("wa:cactusEssence").setTextureName("wa:cactusEssence").setCreativeTab(Wa.creativeTab);

    // 蒸留酒
    public static Item カルヴァドス = new ItemRequor().setUnlocalizedName("wa:calvados").setTextureName("wa:calvados").setCreativeTab(Wa.creativeTab);
    public static Item 金のカルヴァドス = new ItemRequor().setUnlocalizedName("wa:goldenCalvados").setTextureName("wa:goldenCalvados").setCreativeTab(Wa.creativeTab);
    public static Item スピリタス = new ItemRequor().setUnlocalizedName("wa:spirytus").setTextureName("wa:spirytus").setCreativeTab(Wa.creativeTab);
    public static Item ラム酒 = new ItemRequor().setUnlocalizedName("wa:rum").setTextureName("wa:rum").setCreativeTab(Wa.creativeTab);
    public static Item ウィスキー = new ItemRequor().setUnlocalizedName("wa:whisky").setTextureName("wa:whisky").setCreativeTab(Wa.creativeTab);
    public static Item テキーラ = new ItemRequor().setUnlocalizedName("wa:tequila").setTextureName("wa:tequila").setCreativeTab(Wa.creativeTab);
    public static Item アルコール = new ItemRequor().addPotionEffect(Potion.confusion.id, 400, 0).addPotionEffect(Potion.blindness.id, 400, 0)
            .setUnlocalizedName("wa:alcohol").setTextureName("wa:alcohol").setCreativeTab(Wa.creativeTab);

    // 蒸留酒（レシピなし）
    public static Item 日本酒 = new ItemRequor().addPotionEffect(Potion.confusion.id, 180, 0).addPotionEffect(Potion.damageBoost.id, 400, 0)
            .setUnlocalizedName("wa:sake").setTextureName("wa:sake").setCreativeTab(Wa.creativeTab);

    // 醸造酒
    public static Item シードル = new ItemRequor().addPotionEffect(Potion.confusion.id, 60, 0).addPotionEffect(Potion.regeneration.id, 400, 0)
            .setUnlocalizedName("wa:cider").setTextureName("wa:cider").setCreativeTab(Wa.creativeTab);
    public static Item 金のシードル = new ItemRequor().addPotionEffect(Potion.confusion.id, 60, 0).addPotionEffect(Potion.regeneration.id, 400, 0).addPotionEffect(Potion.heal.id, 400, 0)
            .setUnlocalizedName("wa:goldenCider").setTextureName("wa:goldenCider").setCreativeTab(Wa.creativeTab);
    public static Item 高麗人参酒 = new ItemRequor().addPotionEffect(Potion.confusion.id, 180, 0).addPotionEffect(Potion.nightVision.id, 400, 0)
            .setUnlocalizedName("wa:ginsengLiquor").setTextureName("wa:ginsengLiquor").setCreativeTab(Wa.creativeTab);
    public static Item 金の高麗人参酒 = new ItemRequor().addPotionEffect(Potion.confusion.id, 180, 0).addPotionEffect(Potion.nightVision.id, 400, 0).addPotionEffect(Potion.heal.id, 400, 0)
            .setUnlocalizedName("wa:goldenGinsengLiquor").setTextureName("wa:goldenGinsengLiquor").setCreativeTab(Wa.creativeTab);
    public static Item 馬乳酒 = new ItemRequor().addPotionEffect(Potion.confusion.id, 180, 0).addPotionEffect(Potion.moveSpeed.id, 400, 0)
            .setUnlocalizedName("wa:kumis").setTextureName("wa:kumis").setCreativeTab(Wa.creativeTab);
    public static Item ウォッシュ = new ItemRequor().setUnlocalizedName("wa:wash").setTextureName("wa:wash").setCreativeTab(Wa.creativeTab);
    public static Item プルケ = new ItemRequor().setUnlocalizedName("wa:pulque").setTextureName("wa:pulque").setCreativeTab(Wa.creativeTab);

    // 発酵エキス（醸造樽）
    public static Item じゃがいも発酵エキス = new ItemRequor().setUnlocalizedName("wa:potato_ferment_essence").setTextureName("wa:potato_ferment_essence").setCreativeTab(Wa.creativeTab);
    public static Item 糖蜜発酵エキス = new ItemRequor().setUnlocalizedName("wa:syrup_ferment_essence").setTextureName("wa:syrup_ferment_essence").setCreativeTab(Wa.creativeTab);


    // 混合醸造酒
    public static Item ひれ酒 = new ItemRequor().addPotionEffect(Potion.confusion.id, 180, 0).addPotionEffect(Potion.waterBreathing.id, 400, 0)
            .setUnlocalizedName("wa:finSake").setTextureName("wa:finSake").setCreativeTab(Wa.creativeTab);
    public static Item スライムゼリーソーダ = new ItemRequor().addPotionEffect(Potion.confusion.id, 60, 0).addPotionEffect(Potion.jump.id, 400, 0)
            .setUnlocalizedName("wa:slimeJellySoda").setTextureName("wa:slimeJellySoda").setCreativeTab(Wa.creativeTab);
    public static Item かぼちゃエール = new ItemRequor().addPotionEffect(Potion.confusion.id, 120, 0).addPotionEffect(Potion.resistance.id, 400, 0)
            .setUnlocalizedName("wa:pumpkinAle").setTextureName("wa:pumpkinAle").setCreativeTab(Wa.creativeTab);
    public static Item ヘルリカー = new ItemRequor().addPotionEffect(Potion.confusion.id, 240, 0).addPotionEffect(Potion.digSpeed.id, 400, 0)
            .setUnlocalizedName("wa:hellLiquor").setTextureName("wa:hellLiquor").setCreativeTab(Wa.creativeTab);
    public static Item マグマクリームサワー = new ItemRequor().addPotionEffect(Potion.confusion.id, 240, 0).addPotionEffect(Potion.fireResistance.id, 400, 0)
            .setUnlocalizedName("wa:magmaCreamSour").setTextureName("wa:magmaCreamSour").setCreativeTab(Wa.creativeTab);

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
        GameRegistry.registerItem(茶碗, "chawan");
        GameRegistry.registerItem(お茶, "ocha");

        GameRegistry.registerItem(梅酒, "umeshu");

        GameRegistry.registerItem(りんごジュース, "appleJuice");
        GameRegistry.registerItem(金のりんごジュース, "goldenAppleJuice");
        GameRegistry.registerItem(じゃがいもエキス, "potatoEssence");
        GameRegistry.registerItem(にんじんジュース, "carrotJuice");
        GameRegistry.registerItem(金のにんじんジュース, "goldenCarrotJuice");
        GameRegistry.registerItem(糖蜜, "syrup");
        GameRegistry.registerItem(麦汁, "wort");
        GameRegistry.registerItem(サボテンエキス, "cactusEssence");

        GameRegistry.registerItem(カルヴァドス, "calvados");
        GameRegistry.registerItem(金のカルヴァドス, "goldenCalvados");
        GameRegistry.registerItem(スピリタス, "spirytus");
        GameRegistry.registerItem(ラム酒, "rum");
        GameRegistry.registerItem(ウィスキー, "whisky");
        GameRegistry.registerItem(テキーラ, "tequila");

        GameRegistry.registerItem(アルコール, "alcohol");

        GameRegistry.registerItem(日本酒, "sake");

        GameRegistry.registerItem(シードル, "cider");
        GameRegistry.registerItem(金のシードル, "goldenCider");
        GameRegistry.registerItem(高麗人参酒, "ginsengLiquor");
        GameRegistry.registerItem(金の高麗人参酒, "goldenGinsengLiquor");
        GameRegistry.registerItem(馬乳酒, "kumis");
        GameRegistry.registerItem(ウォッシュ, "wash");
        GameRegistry.registerItem(プルケ, "pulque");

        GameRegistry.registerItem(じゃがいも発酵エキス, "potato_ferment_essence");
        GameRegistry.registerItem(糖蜜発酵エキス, "syrup_ferment_essence");

        GameRegistry.registerItem(ひれ酒, "finSake");
        GameRegistry.registerItem(スライムゼリーソーダ, "slimeJellySoda");
        GameRegistry.registerItem(かぼちゃエール, "pumpkinAle");
        GameRegistry.registerItem(ヘルリカー, "hellLiquor");
        GameRegistry.registerItem(マグマクリームサワー, "magmaCreamSour");

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
