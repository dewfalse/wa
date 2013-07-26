package wa;

public enum EnumKakejiku {

    empty("empty", 64, 16, 0, 0),
    syogyo("syogyo", 64, 16, 64, 0),
    inga("inga", 64, 16, 0, 32),
    yudan("yudan", 64, 16, 64, 32),
    geijyutu("geijyutu", 64, 16, 0, 64),
    bakuhatsu("bakuhatsu", 64, 16, 64, 64),
    yakiniku("yakiniku", 64, 16, 0, 96),
    touzai("touzai", 64, 16, 64, 96),
    honda("honda", 64, 16, 0, 128),
    kingin("kingin", 64, 16, 64, 128),
    kirisute("kirisute", 64, 16, 0, 160),
    syunka("syunka", 64, 16, 64, 160);

    /** Holds the maximum length of paintings art title. */
    public static final int maxArtTitleLength = "syunka".length();

    /** Painting Title. */
    public final String title;
    public final int sizeX;
    public final int sizeY;
    public final int offsetX;
    public final int offsetY;

    private EnumKakejiku(String par3Str, int par4, int par5, int par6, int par7)
    {
        this.title = par3Str;
        this.sizeX = par4;
        this.sizeY = par5;
        this.offsetX = par6;
        this.offsetY = par7;
    }
}
