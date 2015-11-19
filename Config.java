package wa;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

public class Config {

	public static int 色付き木材ID;
	public static int 町人ID;
	public static int dimensionID;
	public static int 刀鍛冶ID;
	public static int 玉鋼出現率;
	public static int 鍛練回数;
	public static int providerType;
	public static int biomeID;
	public static int achivementID;
	public static double 鳥居出現率;
	public static double 筍成長確率;
    public static int レシピ難易度;
    public static int 酒造レシピ難易度;
	public static int 玉鋼強度;
	public static int 醸造樽GUIID;
	public static int 茶人ID;
	public static int echantmentBaseID;
	public static int 神官ID;
    public static int 圧搾機GUIID;
    public static int 蒸留器GUIID;

    public static void preInit(File file) {
		Configuration cfg = new Configuration(file);
		try {
			cfg.load();

			町人ID = cfg.get(Configuration.CATEGORY_GENERAL, "町人ID", 8).getInt();
			刀鍛冶ID = cfg.get(Configuration.CATEGORY_GENERAL, "刀鍛冶ID", 9).getInt();
			茶人ID = cfg.get(Configuration.CATEGORY_GENERAL, "茶人ID", 10).getInt();
			神官ID = cfg.get(Configuration.CATEGORY_GENERAL, "神官ID", 11).getInt();
			dimensionID = cfg.get(Configuration.CATEGORY_GENERAL, "dimensionID", 9).getInt();
			providerType = cfg.get(Configuration.CATEGORY_GENERAL, "providerType", 9).getInt();
			achivementID = cfg.get(Configuration.CATEGORY_GENERAL, "achivementID", 21320).getInt();
			biomeID = cfg.get(Configuration.CATEGORY_GENERAL, "biomeID", 50).getInt();
			玉鋼出現率 = cfg.get(Configuration.CATEGORY_GENERAL, "玉鋼出現率", 20).getInt();
			鳥居出現率 = cfg.get(Configuration.CATEGORY_GENERAL, "鳥居出現率", 1.0D).getDouble(1.0D);
			筍成長確率 = cfg.get(Configuration.CATEGORY_GENERAL, "筍成長確率", 1.0D).getDouble(1.0D);
			鍛練回数 = cfg.get(Configuration.CATEGORY_GENERAL, "鍛練回数", 2).getInt();
			レシピ難易度 = cfg.get(Configuration.CATEGORY_GENERAL, "レシピ難易度", 0, "0:UltraHard, 1:Hard, 2:Normal, 3:Vanilla").getInt();
			玉鋼強度 = cfg.get(Configuration.CATEGORY_GENERAL, "玉鋼強度", 10).getInt();
			醸造樽GUIID = cfg.get(Configuration.CATEGORY_GENERAL, "醸造樽GUIID", 17).getInt();
			echantmentBaseID = cfg.get(Configuration.CATEGORY_GENERAL, "echantmentBaseID", 70).getInt();
            圧搾機GUIID = cfg.get(Configuration.CATEGORY_GENERAL, "圧搾機GUIID", 18).getInt();
            蒸留器GUIID = cfg.get(Configuration.CATEGORY_GENERAL, "蒸留器GUIID", 19).getInt();
            酒造レシピ難易度 = cfg.get(Configuration.CATEGORY_GENERAL, "酒造レシピ難易度", 0, "0:UltraHard, 1:Hard, 2:Normal, 3:Vanilla").getInt();
			cfg.save();
		} catch (Exception e) {
			FMLLog.log(Level.ERROR, e, Wa.modid + " load config exception");
		} finally {
			cfg.save();
		}

	}

}
