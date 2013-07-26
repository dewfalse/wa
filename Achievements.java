package wa;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Achievements {

	public static Achievement travelToDimension;
	public static Achievement ninja;
	public static Achievement kotetsu;
	public static Achievement tatara;
	public static Achievement katana;
	public static Achievement kome;
	public static Achievement coinThrow;
	public static Achievement moneyMonger;

	public static final String ACHIEVEMENT_PAGE_NAME = "Wa";

	public static void addNinjaAchievement() {
		if(AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().contains(ninja) == false) {
			// ツーハンデッド・カタナブレードツルギまたはベッピンを手に入れる
			ninja  = new Achievement(Config.achivementID+1, "ninja", -1, -1, new ItemStack(Items.太刀), travelToDimension).setIndependent().setSpecial().registerAchievement();
			LanguageRegistry.instance().addStringLocalization(ninja.statName, "en_US", "Ninja!");
			LanguageRegistry.instance().addStringLocalization(ninja.statName, "ja_JP", "ニンジャナンデ？");
			LanguageRegistry.instance().addStringLocalization(ninja.statName + ".desc", "en_US", "Get the ninja blade");
			LanguageRegistry.instance().addStringLocalization(ninja.statName + ".desc", "ja_JP", "ニンジャの使う刀を手に入れる");
			AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().add(ninja);
		}
	}

	public static void addKotetsuAchievement() {
		if(AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().contains(kotetsu) == false) {
			// 虎徹を手に入れる
			kotetsu  = new Achievement(Config.achivementID+2, "kotetsu", 1, -1, new ItemStack(Items.太刀), travelToDimension).setIndependent().setSpecial().registerAchievement();
			LanguageRegistry.instance().addStringLocalization(kotetsu.statName, "en_US", "Bloody blade");
			LanguageRegistry.instance().addStringLocalization(kotetsu.statName, "ja_JP", "今宵の虎徹は血に飢えておる");
			LanguageRegistry.instance().addStringLocalization(kotetsu.statName + ".desc", "en_US", "Get the kotetsu blade");
			LanguageRegistry.instance().addStringLocalization(kotetsu.statName + ".desc", "ja_JP", "虎徹を手に入れる");
			AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().add(kotetsu);
		}
	}

	public static void preInit() {
		// TODO 実績の追加
		// 追加ディメンションに行く
		travelToDimension  = new Achievement(Config.achivementID, "travelToDimension", 0, 0, new ItemStack(Items.食べ物, 1, 0), null).setIndependent().registerAchievement();
		LanguageRegistry.instance().addStringLocalization(travelToDimension.statName, "en_US", "Travel to 'Wa' dimension");
		LanguageRegistry.instance().addStringLocalization(travelToDimension.statName, "ja_JP", "東方見聞録");
		LanguageRegistry.instance().addStringLocalization(travelToDimension.statName + ".desc", "en_US", "Travel to 'Wa' dimension");
		LanguageRegistry.instance().addStringLocalization(travelToDimension.statName + ".desc", "ja_JP", "鳥居をくぐる");
		// 玉鋼を手に入れる
		tatara  = new Achievement(Config.achivementID+3, "tatara",-2, 0, new ItemStack(Items.玉鋼), travelToDimension).setIndependent().registerAchievement();
		LanguageRegistry.instance().addStringLocalization(tatara.statName, "en_US", "Tatara");
		LanguageRegistry.instance().addStringLocalization(tatara.statName, "ja_JP", "たたら製鉄");
		LanguageRegistry.instance().addStringLocalization(tatara.statName + ".desc", "en_US", "Get the tamahagane");
		LanguageRegistry.instance().addStringLocalization(tatara.statName + ".desc", "ja_JP", "玉鋼を手に入れる");
		// 刀か太刀をクラフトする
		katana  = new Achievement(Config.achivementID+4, "katana", 1, 1, new ItemStack(Items.刀), travelToDimension).setIndependent().registerAchievement();
		LanguageRegistry.instance().addStringLocalization(katana.statName, "en_US", "Japanese sword smith");
		LanguageRegistry.instance().addStringLocalization(katana.statName, "ja_JP", "刀鍛冶");
		LanguageRegistry.instance().addStringLocalization(katana.statName + ".desc", "en_US", "Craft japanese sword");
		LanguageRegistry.instance().addStringLocalization(katana.statName + ".desc", "ja_JP", "刀をクラフトする");
		// 米をクラフトする
		kome  = new Achievement(Config.achivementID+5, "kome", -1, 3, new ItemStack(Items.米), travelToDimension).setIndependent().registerAchievement();
		LanguageRegistry.instance().addStringLocalization(kome.statName, "en_US", "Rice farm");
		LanguageRegistry.instance().addStringLocalization(kome.statName, "ja_JP", "年貢の納めどき");
		LanguageRegistry.instance().addStringLocalization(kome.statName + ".desc", "en_US", "Craft rice");
		LanguageRegistry.instance().addStringLocalization(kome.statName + ".desc", "ja_JP", "米をクラフトする");
		// 貨幣を投げる
		coinThrow  = new Achievement(Config.achivementID+6, "coinThrow", 2, 0, new ItemStack(Items.貨幣, 1, 0), null).setIndependent().registerAchievement();
		LanguageRegistry.instance().addStringLocalization(coinThrow.statName, "en_US", "Coin throw");
		LanguageRegistry.instance().addStringLocalization(coinThrow.statName, "ja_JP", "銭形平次");
		LanguageRegistry.instance().addStringLocalization(coinThrow.statName + ".desc", "en_US", "Throw the coin");
		LanguageRegistry.instance().addStringLocalization(coinThrow.statName + ".desc", "ja_JP", "貨幣を投げる");
		// 貨幣を投げる
		moneyMonger  = new Achievement(Config.achivementID+7, "moneyMonger", 3, 2, new ItemStack(Items.貨幣, 1, 2), coinThrow).setIndependent().registerAchievement();
		LanguageRegistry.instance().addStringLocalization(moneyMonger.statName, "en_US", "Money Monger");
		LanguageRegistry.instance().addStringLocalization(moneyMonger.statName, "ja_JP", "金は命より重い・・・！");
		LanguageRegistry.instance().addStringLocalization(moneyMonger.statName + ".desc", "en_US", "Throw the gold coin");
		LanguageRegistry.instance().addStringLocalization(moneyMonger.statName + ".desc", "ja_JP", "金貨を投げる");



		Achievement[] achievements = new Achievement[] { travelToDimension, tatara, katana, kome, coinThrow, moneyMonger };
		AchievementPage.registerAchievementPage(new AchievementPage(ACHIEVEMENT_PAGE_NAME, achievements));

	}

}
