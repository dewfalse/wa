package wa;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class Config {

	public static int 原木ID;
	public static int 木材ID;
	public static int 葉ID;
	public static int 苗木ID;
	public static int 琴ID;
	public static int 太鼓ID;
	public static int ケラID;
	public static int ポータルID;

	public static int 玉鋼ID;
	public static int 食べ物ID;
	public static int 金槌ID;
	public static int 色付き木材ID;
	public static int 家紋ID;
	public static int 藁ID;
	public static int すすきID;
	public static int 竹ID;
	public static int ハーフブロックID;
	public static int ダブルブロックID;
	public static int 座布団ID;
	public static int 暖簾ID;
	public static int 貨幣ID;
	public static int 町人ID;
	public static int dimensionID;
	public static int 木炭ブロックID;
	public static int 竹炭ブロックID;
	public static int お盆ID;
	public static int 太刀ID;
	public static int 刀ID;
	public static int 鋼の斧ID;
	public static int 鋼のツルハシID;
	public static int 鋼のシャベルID;
	public static int 鋼の金槌ID;
	public static int 鋼のクワID;
	public static int 竹炭ID;
	public static int 掛け軸ID;
	public static int 刀鍛冶ID;
	public static int たたら製鉄炉ID;
	public static int 玉鋼出現率;
	public static int 手裏剣ID;
	public static int 毛筆ID;
	public static int 鍛練回数;
	public static int providerType;
	public static int 漆喰ID;
	public static int 米ID;
	public static int biomeID;
	public static int achivementID;
	public static int 種籾ID;
	public static int 稲ID;
	public static int 稲ブロックID;
	public static int 筍ID;
	public static double 鳥居出現率;
	public static double 筍成長確率;
	public static int 御鏡ID;
	public static int 勾玉ID;
	public static int レシピ難易度;
	public static int 玉鋼強度;
	public static int 石の金槌ID;
	public static int ズクID;
	public static int ズク破片ID;
	public static int 左下鉄ID;

	public static void preInit(File file) {
		Configuration cfg = new Configuration(file);
		try {
			cfg.load();
			原木ID = cfg.getBlock("原木ID", 3500).getInt();
			木材ID = cfg.getBlock("木材ID", 3501).getInt();
			葉ID = cfg.getBlock("葉ID", 3502).getInt();
			苗木ID = cfg.getBlock("苗木ID", 3503).getInt();
			琴ID = cfg.getBlock("琴ID", 3525).getInt();
			太鼓ID = cfg.getBlock("太鼓ID", 3526).getInt();
			ケラID = cfg.getBlock("ケラID", 3527).getInt();
			ポータルID = cfg.getBlock("ポータルID", 3528).getInt();
			暖簾ID = cfg.getBlock("暖簾ID", 3529).getInt();
			稲ブロックID = cfg.getBlock("稲ブロックID", 3531).getInt();
			藁ID = cfg.getBlock("藁ID", 3532).getInt();
			すすきID = cfg.getBlock("すすきID", 3533).getInt();
			竹ID = cfg.getBlock("竹ID", 3534).getInt();
			ダブルブロックID = cfg.getBlock("ダブルブロックID", 3535).getInt();
			ハーフブロックID = cfg.getBlock("ハーフブロックID", 3536).getInt();
			座布団ID = cfg.getBlock("座布団ID", 3537).getInt();
			木炭ブロックID = cfg.getBlock("木炭ブロックID", 3538).getInt();
			竹炭ブロックID = cfg.getBlock("竹炭ブロックID", 3539).getInt();
			お盆ID = cfg.getBlock("お盆ID", 3540).getInt();
			たたら製鉄炉ID = cfg.getBlock("たたら製鉄炉ID", 3541).getInt();
			漆喰ID = cfg.getBlock("漆喰ID", 3542).getInt();
			筍ID = cfg.getBlock("筍ID", 3543).getInt();

			色付き木材ID = cfg.getBlock("色付き木材ID", 3560).getInt();

			玉鋼ID = cfg.getItem("玉鋼ID", 5100).getInt();
			食べ物ID = cfg.getItem("食べ物ID", 5101).getInt();
			金槌ID = cfg.getItem("金槌ID", 5102).getInt();
			家紋ID = cfg.getItem("家紋ID", 5103).getInt();
			貨幣ID = cfg.getItem("貨幣ID", 5104).getInt();
			太刀ID = cfg.getItem("太刀ID", 5105).getInt();
			刀ID = cfg.getItem("刀ID", 5106).getInt();
			鋼の斧ID = cfg.getItem("鋼の斧ID", 5107).getInt();
			鋼のツルハシID = cfg.getItem("鋼のツルハシID", 5108).getInt();
			鋼のシャベルID = cfg.getItem("鋼のシャベルID", 5109).getInt();
			鋼のクワID = cfg.getItem("鋼のクワID", 5110).getInt();
			鋼の金槌ID = cfg.getItem("鋼の金槌ID", 5111).getInt();
			竹炭ID = cfg.getItem("竹炭ID", 5112).getInt();
			掛け軸ID = cfg.getItem("掛け軸ID", 5113).getInt();
			手裏剣ID = cfg.getItem("手裏剣ID", 5114).getInt();
			毛筆ID = cfg.getItem("毛筆ID", 5115).getInt();
			米ID = cfg.getItem("米ID", 5116).getInt();
			種籾ID = cfg.getItem("種籾ID", 5117).getInt();
			稲ID = cfg.getItem("稲ID", 5118).getInt();
			御鏡ID = cfg.getItem("御鏡ID", 5119).getInt();
			勾玉ID = cfg.getItem("勾玉ID", 5120).getInt();
			石の金槌ID = cfg.getItem("石の金槌ID", 5121).getInt();
			ズクID = cfg.getItem("ズクID", 5122).getInt();
			ズク破片ID = cfg.getItem("ズク破片ID", 5123).getInt();
			左下鉄ID = cfg.getItem("左下鉄ID", 5124).getInt();

			町人ID = cfg.get(Configuration.CATEGORY_GENERAL, "町人ID", 8).getInt();
			刀鍛冶ID = cfg.get(Configuration.CATEGORY_GENERAL, "刀鍛冶ID", 9).getInt();
			dimensionID = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionID", 9).getInt();
			providerType = cfg.get(Configuration.CATEGORY_GENERAL, "providerType", 9).getInt();
			achivementID = cfg.get(Configuration.CATEGORY_GENERAL, "achivementID", 21320).getInt();
			biomeID = cfg.get(Configuration.CATEGORY_GENERAL, "biomeID", 50).getInt();
			玉鋼出現率 = cfg.get(Configuration.CATEGORY_GENERAL, "玉鋼出現率", 20).getInt();
			鳥居出現率 = cfg.get(Configuration.CATEGORY_GENERAL, "鳥居出現率", 1.0D).getDouble(1.0D);
			筍成長確率 = cfg.get(Configuration.CATEGORY_GENERAL, "筍成長確率", 1.0D).getDouble(1.0D);
			鍛練回数 = cfg.get(Configuration.CATEGORY_GENERAL, "鍛練回数", 2).getInt();
			レシピ難易度 = cfg.get(Configuration.CATEGORY_GENERAL, "レシピ難易度", 0).getInt();
			玉鋼強度 = cfg.get(Configuration.CATEGORY_GENERAL, "玉鋼強度", 10).getInt();
			cfg.save();
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Wa.modid + " load config exception");
		} finally {
			cfg.save();
		}

	}

}
