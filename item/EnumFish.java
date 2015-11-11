package wa.item;

public enum EnumFish {
	FishRaw   ("Fish Raw",   "魚",                   16, 16, 0, 0, 450, 550),
	Tai       ("Tai",        "タイ",                 16, 16, 16, 0, 450, 550),
	Sake      ("Sake",       "シャケ",               32, 16, 0, 16, 900, 1500),
	Ei        ("Ei",         "エイ",                 32, 32, 0, 32, 1000, 1800),
	Tako      ("Tako",       "タコ",                 16, 16, 64, 0, 100, 300),
	Maguro    ("Maguro",     "マグロ",               32, 16, 32, 16, 600, 3000),
	Manbou    ("Manbou",     "マンボウ",             64, 64, 0, 64, 2000, 3300),
	Zarigani  ("Zarigani",   "ザリガニ",             16, 16, 32, 0, 200, 500),
	Koi       ("Koi",        "コイ",                 16, 16, 80, 0, 400, 800),
	Nijimasu  ("Nijimasu",   "ニジマス",             16, 16, 96, 0, 400, 1000),
	Wakasagi  ("Wakasagi",   "ワカサギ",             16, 16, 48, 0, 100, 150),
	Namazu    ("Namazu",     "ナマズ",               32, 16, 96, 16, 500, 1600),
	Anago     ("Anago",      "アナゴ",               32, 16, 64, 16, 500, 1400),
	Ankou     ("Ankou",      "チョウチンアンコウ",   32, 16, 128, 16, 400, 1600),
	Sakuramasu("Sakuramasu", "サクラマス",           16, 16, 96, 0, 400, 600),
	Blackbass ("Blackbass",  "ブラックバス",         16, 16, 112, 0, 200, 600),
	Chingyo   ("Chingyo",    "チンギョ",             16, 16, 128, 0, 300, 500),
	Sunaei    ("Sunaei",     "スナエイ",             32, 32, 32, 32, 1000, 1800),
	GoldenFish("GoldenFish", "ゴールデンフィッシュ", 16, 16, 144, 0, 100, 200);

	String fishName;
	String japaneseName;
	int sizeX;
	int sizeY;
	int offsetX;
	int offsetY;
	int minSize;
	int maxSize;

	private EnumFish(String fishName, String japaneseName, int sizeX, int sizeY, int offsetX, int offsetY, int minSize, int maxSize) {
		this.fishName = fishName;
		this.japaneseName = japaneseName;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}
}
