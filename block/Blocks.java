package wa.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import wa.*;
import wa.item.*;

public class Blocks extends net.minecraft.init.Blocks {
	public static Material keraMaterial = (new MaterialKera(MapColor.stoneColor));

	public static Block[] colorWood;
	public static Block kera = new BlockKera(keraMaterial).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("wa:kera").setBlockTextureName("wa:kera").setCreativeTab(Wa.creativeTab);
	public static Block koto = new BlockKoto(Material.wood).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:koto").setCreativeTab(Wa.creativeTab);
	public static Block taiko = new BlockTaiko(Material.wood).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:taiko").setCreativeTab(Wa.creativeTab);
	public static Block charcoalBlock = (new WaBlock(Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("wa:mokutan").setBlockTextureName("wa:mokutan").setCreativeTab(Wa.creativeTab);
	public static Block takezumiBlock = (new WaBlock(Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("wa:takezumi").setBlockTextureName("wa:takezumi").setCreativeTab(Wa.creativeTab);
	public static Block tataraBlock = (new BlockTatara(Material.rock)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setBlockName("wa:tatara").setCreativeTab(Wa.creativeTab);
	public static Block portal = new BlockWaPortal(Material.portal).setHardness(-1.0F).setResistance(6000000.0F).setBlockName("wa:portal").setCreativeTab(Wa.creativeTab);
	public static Block wara = new WaBlock(Material.grass).setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("wa:wara").setBlockTextureName("wa:wara").setCreativeTab(Wa.creativeTab);
	public static BlockSlabBase BlockWaHalfBlock = (BlockSlabBase)(new BlockTatami(false, Material.cloth)).setHardness(0.2F).setStepSound(Block.soundTypeCloth).setBlockName("wa:step").setCreativeTab(Wa.creativeTab);
	public static BlockSlabBase BlockWaDoubleBlock = (BlockSlabBase)(new BlockTatami(true, Material.cloth)).setHardness(0.2F).setStepSound(Block.soundTypeCloth).setBlockName("wa:step");
	static public Block shikui = new BlockShikui(Material.clay).setHardness(0.6F).setStepSound(Block.soundTypeGravel).setBlockName("wa:shikui").setCreativeTab(Wa.creativeTab);
	static public Block sakuraWood = (new BlockSakuraWood()).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:sakuraWood").setCreativeTab(Wa.creativeTab);
	static public BlockLeaves sakuraLeaves = (BlockLeaves)(new BlockSakuraLeaves()).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("wa:sakuraLeaves").setCreativeTab(Wa.creativeTab);
	static public Block sakuraPlank = (new BlockSakuraPlank()).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:sakuraLog").setCreativeTab(Wa.creativeTab);
	static public Block noren = (new BlockNoren(Material.cloth)).setHardness(0.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:noren").setCreativeTab(Wa.creativeTab);
	static public Block sakuraSapling = (new BlockSakuraSapling()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("wa:sakuraSapling").setCreativeTab(Wa.creativeTab);

	static public Block take = new BlockTake(Material.grass).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("wa:take").setBlockTextureName("wa:take").setCreativeTab(Wa.creativeTab);
	static public Block ine = new BlockIne().setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("wa:ine");
	static public Block susuki = new BlockSusuki(Material.grass).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("wa:susuki").setBlockTextureName("wa:susuki").setCreativeTab(Wa.creativeTab);
	static public Block takenoko = new BlockTakenoko().setBlockName("wa:takenoko").setCreativeTab(Wa.creativeTab);
	//static Block 家紋 = (new 家紋(Config.家紋ID, Material.cloth)).setHardness(0.2F).setStepSound(Block.soundCloth).setBlockName("wa:kamon").setCreativeTab(Wa.creativeTab);

	public static Block umeWood = (new BlockUmeWood()).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:umeWood").setCreativeTab(Wa.creativeTab);
	public static Block umeLog = (new BlockUmeWood()).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:umeLog").setCreativeTab(Wa.creativeTab);
	public static Block umeSapling = (new BlockUmeSapling()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("wa:umeSapling").setCreativeTab(Wa.creativeTab);

	public static Block brewingBarrel = new BlockBrewingBarrel(Material.wood).setHardness(0.5F).setStepSound(Block.soundTypeWood).setBlockName("wa:brewingBarrel").setCreativeTab(Wa.creativeTab);
	public static Block kawara = (new BlockKawara(Material.rock)).setHardness(1.0F).setStepSound(Block.soundTypeStone).setBlockName("wa:kawara").setCreativeTab(Wa.creativeTab);

	public static Block charm = (new BlockCharm(Material.cloth)).setHardness(1.0F).setStepSound(Block.soundTypeStone).setBlockName("wa:ofuda").setBlockTextureName("wa:ofuda").setCreativeTab(Wa.creativeTab);
	public static ItemBlock itemBlockCharm = (ItemBlock) new ItemBlockCharm(charm).setUnlocalizedName("wa:ofuda").setTextureName("wa:ofuda");

    public static Block oreMagnetite = (new BlockOre(Material.rock)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setBlockName("wa:oreMagnetite").setBlockTextureName("iron_ore").setCreativeTab(Wa.creativeTab);

    // 磁鉄鉱石
    // 落雷で磁鉄鉱石ブロックに置換される
    // 磁鉄鉱石をかまどで磁鉄鉱に
    // 磁鉄鉱をU字にクラフトして磁石に
    // 磁石と砂or砂利をクラフトすると砂鉄が得られる
    // 砂・砂利は消滅
    // または磁石を砂or砂利に使うと砂鉄が得られる
    // メタデータが変更されて2回は取れない
    // 砂鉄はironDust的存在、9個でインゴットに
    
    /* defeatedcrow作成物 */
    public static Block zabuton = (new BlockZabuton(Material.sponge)).setHardness(0.2F).setStepSound(Block.soundTypeCloth).setBlockName("wa:zabuton").setBlockTextureName("wa:zabuton").setCreativeTab(Wa.creativeTab);
    public static Block brewingBarrelII = new BlockBrewingBarrelII().setBlockName("wa:brewingBarrelII").setCreativeTab(Wa.creativeTab);

	//TODO 入手方法を実装

	static Block 蓮;
	static Block 囲炉裏;
	static Block 五徳;
	static Block 看板;
	static Block 灯籠;
	static Block 七輪;
	static Block 地蔵;
	static Block 盆栽;
	static Block 茶碗;
	static Block ふすま;
	static Block 障子;

	public static void preInit() {
//		ItemBlock itemBlockSingle = (ItemBlock) new ItemSlabBase(BlockWaHalfBlock, BlockWaHalfBlock, BlockWaDoubleBlock, false).setUnlocalizedName("wa:step");
//		ItemBlock itemBlockDouble = (ItemBlock) new ItemSlabBase(BlockWaDoubleBlock, BlockWaHalfBlock, BlockWaDoubleBlock, true).setUnlocalizedName("wa:step");
//		GameRegistry.registerBlock(BlockWaHalfBlock, ItemWaSlab.class, "WaHalfBlock", BlockWaHalfBlock, BlockWaDoubleBlock, false);
//		GameRegistry.registerBlock(BlockWaDoubleBlock, ItemWaSlab.class, "WaDoubleBlock", BlockWaHalfBlock, BlockWaDoubleBlock, true);
		GameRegistry.registerBlock(BlockWaHalfBlock, ItemTatami.class, "WaHalfBlock");
		GameRegistry.registerBlock(BlockWaDoubleBlock, ItemTatami.class, "WaDoubleBlock");

		LanguageRegistry.instance().addStringLocalization("tile.wa:step.tatami.name", "en_US", "tatami");
		LanguageRegistry.instance().addStringLocalization("tile.wa:step.tatami.name", "ja_JP", "畳");

        GameRegistry.registerBlock(sakuraWood, "sakuraWood");
        GameRegistry.registerBlock(sakuraPlank, "sakuraLog");
        GameRegistry.registerBlock(sakuraLeaves, "sakuraLeaves");
		ItemBlock itemBlockSapling = (ItemBlock) new ItemBlockSapling(sakuraSapling).setUnlocalizedName("sakuraSapling");
        GameRegistry.registerBlock(sakuraSapling, ItemBlockSapling.class, "sakuraSapling");
		//GameRegistry.registerItem(itemBlockSapling, "WaSakuraSapling");
        GameRegistry.registerBlock(umeWood, "umeWood");
        GameRegistry.registerBlock(umeLog, "umeLog");
		ItemBlock itemBlockUmeSapling = (ItemBlock) new ItemBlockUmeSapling(umeSapling).setUnlocalizedName("wa:umeSapling");
        GameRegistry.registerBlock(umeSapling, ItemBlockUmeSapling.class, "umeSapling");
		//GameRegistry.registerItem(itemBlockUmeSapling, "WaUmeSapling");
		//registerBlock(家紋, "kamon", "家紋");
		/*GameRegistry.registerBlock(家紋, 家紋アイテムブロック.class, "家紋", Wa.modid);
		LanguageRegistry.addName(new ItemStack(家紋, 1, 0), "oda");
		LanguageRegistry.instance().addNameForObject(new ItemStack(家紋, 1, 0), "ja_JP", "織田家家紋");
		LanguageRegistry.addName(new ItemStack(家紋, 1, 1), "mouri");
		LanguageRegistry.instance().addNameForObject(new ItemStack(家紋, 1, 1), "ja_JP", "毛利家家紋");
		LanguageRegistry.addName(new ItemStack(家紋, 1, 2), "tokugawa");
		LanguageRegistry.instance().addNameForObject(new ItemStack(家紋, 1, 2), "ja_JP", "徳川家家紋");*/
        GameRegistry.registerBlock(taiko, "taiko");
        GameRegistry.registerBlock(koto, "koto");
        GameRegistry.registerBlock(kera, "kera");
        GameRegistry.registerBlock(shikui, "shikui");
        GameRegistry.registerBlock(wara, "wara");
        GameRegistry.registerBlock(ine, "ineBlock");
        GameRegistry.registerBlock(susuki, "susuki");
        GameRegistry.registerBlock(take, "take");
		//ItemBlock itemBlockTakenoko = (ItemBlock) new ItemBlockSapling(takenoko).setUnlocalizedName("wa:takenoko");
        GameRegistry.registerBlock(takenoko, ItemBlockSapling.class, "takenoko");
		//GameRegistry.registerItem(itemBlockTakenoko, "WaTakenoko");
        GameRegistry.registerBlock(noren, "noren");
        GameRegistry.registerBlock(charcoalBlock, "mokutan");
        GameRegistry.registerBlock(takezumiBlock, "takezumiBlock");
        GameRegistry.registerBlock(tataraBlock, "tatara");
        GameRegistry.registerBlock(portal, "portal");
		GameRegistry.registerTileEntity(TileEntityTatara.class, "wa.tatara");

        GameRegistry.registerBlock(brewingBarrel, "brewingBarrel");
		GameRegistry.registerTileEntity(TileEntityBrewingBarrel.class, "wa.BrewingBarrel");

        GameRegistry.registerBlock(kawara, "kawara");
        GameRegistry.registerBlock(oreMagnetite, "oreMagnetite");

		//registerBlock(charm, "charm", "御札");
		GameRegistry.registerBlock(charm, ItemBlockCharm.class, "ofuda");
		GameRegistry.registerTileEntity(TileEntityCharm.class, "wa.ofuda");
		//GameRegistry.registerItem(itemBlockCharm, "WaOfuda");
		//LanguageRegistry.instance().addNameForObject(itemBlockCharm, "en_US", "ofuda");
		//LanguageRegistry.instance().addNameForObject(itemBlockCharm, "ja_JP", "御札");

		colorWood = new Block[16];
		for(int i = 0; i < 16; ++i) {
			colorWood[i] = (new BlockColorWood(i)).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("wa:paintedWood"+ "." + ItemDye.field_150923_a[i]).setCreativeTab(Wa.creativeTab);
            //GameRegistry.registerBlock(colorWood[i], "paintedWood(" + ItemDye.field_150923_a[i] + ")", "色付き木材(" + ItemDye.field_150923_a[i] + ")");
            GameRegistry.registerBlock(colorWood[i], "paintedWood(" + ItemDye.field_150923_a[i] + ")");
		}
		
		/* defeatedcrow作成物 */
		GameRegistry.registerBlock(zabuton, ItemZabuton.class, "zabuton");
		GameRegistry.registerBlock(brewingBarrelII, "brewingBarrelII");
		GameRegistry.registerTileEntity(TileEntityBrewingBarrelII.class, "wa.BrewingBarrelII");

		//ケラは金槌が対応ツールになる
		//MinecraftForge.removeBlockEffectiveness(kera, 0, "pickaxe");
		//MinecraftForge.setBlockHarvestLevel(kera, "hummer", 1);
        kera.setHarvestLevel("hummer", 1);


		GameRegistry.registerFuelHandler(new FuelHandler());

		OreDictionary.registerOre("logWood", new ItemStack(sakuraWood, 1, 0));
		OreDictionary.registerOre("plankWood", new ItemStack(sakuraPlank, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(umeWood));
		OreDictionary.registerOre("logWood", new ItemStack(umeLog));
        OreDictionary.registerOre("oreMagnetite", oreMagnetite);
        OreDictionary.registerOre("bamboo", take);
        OreDictionary.registerOre("cropStraw", wara);
        OreDictionary.registerOre("blockCharcoal", charcoalBlock);
        OreDictionary.registerOre("blockCharcoal", takezumiBlock);
	}
/*
	private static void registerBlock(Block block, String name, String 名前) {
		GameRegistry.registerBlock(block, name);
		LanguageRegistry.instance().addNameForObject(block, "en_US", name);
		LanguageRegistry.instance().addNameForObject(block, "ja_JP", 名前);
	}*/
}
