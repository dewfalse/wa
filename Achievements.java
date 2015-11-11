package wa;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import wa.item.Items;

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
			ninja  = new Achievement("ninja", "ninja", -1, -1, new ItemStack(Items.太刀), travelToDimension).initIndependentStat().setSpecial().registerStat();
			AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().add(ninja);
		}
	}

	public static void addKotetsuAchievement() {
		if(AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().contains(kotetsu) == false) {
			// 虎徹を手に入れる
			kotetsu  = new Achievement("kotetsu", "kotetsu", 1, -1, new ItemStack(Items.太刀), travelToDimension).initIndependentStat().setSpecial().registerStat();
			AchievementPage.getAchievementPage(ACHIEVEMENT_PAGE_NAME).getAchievements().add(kotetsu);
		}
	}

	public static void preInit() {
		// TODO 実績の追加
		// 追加ディメンションに行く
		travelToDimension  = new Achievement("travelToDimension", "travelToDimension", 0, 0, new ItemStack(Items.食べ物, 1, 0), null).initIndependentStat().registerStat();
		// 玉鋼を手に入れる
		tatara  = new Achievement("tatara", "tatara", -2, 0, new ItemStack(Items.玉鋼), travelToDimension).initIndependentStat().registerStat();
		// 刀か太刀をクラフトする
		katana  = new Achievement("katana", "katana", 1, 1, new ItemStack(Items.刀), travelToDimension).initIndependentStat().registerStat();
		// 米をクラフトする
		kome  = new Achievement("kome", "kome", -1, 3, new ItemStack(Items.米), travelToDimension).initIndependentStat().registerStat();
		// 貨幣を投げる
		coinThrow  = new Achievement("coinThrow", "coinThrow", 2, 0, new ItemStack(Items.貨幣, 1, 0), null).initIndependentStat().registerStat();
		// 貨幣を投げる
		moneyMonger  = new Achievement("moneyMonger", "moneyMonger", 3, 2, new ItemStack(Items.貨幣, 1, 2), coinThrow).initIndependentStat().registerStat();



		Achievement[] achievements = new Achievement[] { travelToDimension, tatara, katana, kome, coinThrow, moneyMonger };
		AchievementPage.registerAchievementPage(new AchievementPage(ACHIEVEMENT_PAGE_NAME, achievements));

	}

}
