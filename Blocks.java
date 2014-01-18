package wa;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static Material keraMaterial = (new MaterialKera(MapColor.stoneColor));

	public static Block[] colorWood;
	public static Block kera = new BlockKera(Config.ケラID, keraMaterial).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("wa:kera").setTextureName("wa:kera").setCreativeTab(Wa.creativeTab);
	public static Block koto = new BlockKoto(Config.琴ID, Material.wood).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:koto").setCreativeTab(Wa.creativeTab);
	public static Block taiko = new BlockTaiko(Config.太鼓ID, Material.wood).setHardness(1.0F).setResistance(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:taiko").setCreativeTab(Wa.creativeTab);
	public static Block charcoalBlock = (new Block(Config.木炭ブロックID, Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("wa:mokutan").setTextureName("wa:mokutan").setCreativeTab(Wa.creativeTab);
	public static Block takezumiBlock = (new Block(Config.竹炭ブロックID, Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("wa:takezumi").setTextureName("wa:takezumi").setCreativeTab(Wa.creativeTab);
	public static Block tataraBlock = (new BlockTatara(Config.たたら製鉄炉ID, Material.rock)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("wa:tatara").setCreativeTab(Wa.creativeTab);
	public static Block portal = new BlockWaPortal(Config.ポータルID, Material.portal).setHardness(-1.0F).setResistance(6000000.0F).setUnlocalizedName("wa:portal").setCreativeTab(Wa.creativeTab);
	public static Block wara = new Block(Config.藁ID, Material.grass).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:wara").setTextureName("wa:wara").setCreativeTab(Wa.creativeTab);
	public static BlockHalfSlab BlockWaHalfBlock = (BlockHalfSlab)(new BlockWaStep(Config.ハーフブロックID, false)).setHardness(1.0F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("wa:step");
	public static BlockHalfSlab BlockWaDoubleBlock = (BlockHalfSlab)(new BlockWaStep(Config.ダブルブロックID, true)).setHardness(1.0F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("wa:step");
	static public Block shikui = new BlockShikui(Config.漆喰ID, Material.clay).setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("wa:shikui").setCreativeTab(Wa.creativeTab);
	static public Block sakuraWood = (new BlockSakuraWood(Config.原木ID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:sakuraWood").setCreativeTab(Wa.creativeTab);
	static public BlockLeaves sakuraLeaves = (BlockLeaves)(new BlockSakuraLeaves(Config.葉ID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:sakuraLeaves").setCreativeTab(Wa.creativeTab);
	static public Block sakuraPlank = (new BlockSakuraPlank(Config.木材ID)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:sakuraLog").setCreativeTab(Wa.creativeTab);
	static public Block noren = (new BlockNoren(Config.暖簾ID, Material.cloth)).setHardness(0.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:noren").setCreativeTab(Wa.creativeTab);
	static public Block sakuraSapling = (new BlockSakuraSapling(Config.苗木ID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:sakuraSapling").setCreativeTab(Wa.creativeTab);

	static public Block take = new BlockTake(Config.竹ID, Material.grass).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:take").setTextureName("wa:take").setCreativeTab(Wa.creativeTab);
	static public Block ine = new BlockIne(Config.稲ブロックID).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:ine");
	static public Block susuki = new BlockSusuki(Config.すすきID, Material.grass).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:susuki").setTextureName("wa:susuki").setCreativeTab(Wa.creativeTab);
	static public Block takenoko = new BlockTakenoko(Config.筍ID).setUnlocalizedName("wa:takenoko").setCreativeTab(Wa.creativeTab);
	//static Block 家紋 = (new 家紋(Config.家紋ID, Material.cloth)).setHardness(0.2F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("wa:kamon").setCreativeTab(Wa.creativeTab);

	public static Block umeWood = (new BlockUmeWood(Config.梅原木ID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:umeWood").setCreativeTab(Wa.creativeTab);
	public static Block umeLog = (new BlockUmeWood(Config.梅木材ID)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:umeLog").setCreativeTab(Wa.creativeTab);
	public static Block umeSapling = (new BlockUmeSapling(Config.梅苗木ID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("wa:umeSapling").setCreativeTab(Wa.creativeTab);

	public static Block brewingBarrel = new BlockBrewingBarrel(Config.醸造樽ID, Material.wood).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:brewingBarrel").setCreativeTab(Wa.creativeTab);
	public static Block kawara = (new BlockKawara(Config.瓦ID, Material.rock)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("wa:kawara").setCreativeTab(Wa.creativeTab);

	public static Block charm = (new BlockCharm(Config.御札ID, Material.cloth)).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("wa:ofuda").setTextureName("wa:ofuda").setCreativeTab(Wa.creativeTab);
	public static ItemBlock itemBlockCharm = (ItemBlock) new ItemBlockCharm(charm.blockID - 256).setUnlocalizedName("wa:ofuda").setTextureName("wa:ofuda");


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
		ItemBlock itemBlockSingle = (ItemBlock) new ItemWaSlab(BlockWaHalfBlock.blockID - 256, BlockWaHalfBlock, BlockWaDoubleBlock, false).setUnlocalizedName("wa:step");
		ItemBlock itemBlockDouble = (ItemBlock) new ItemWaSlab(BlockWaDoubleBlock.blockID - 256, BlockWaHalfBlock, BlockWaDoubleBlock, true).setUnlocalizedName("wa:step");
		GameRegistry.registerItem(itemBlockSingle, "WaHalfBlock");
		GameRegistry.registerItem(itemBlockDouble, "WaDoubleBlock");

		LanguageRegistry.instance().addStringLocalization("tile.wa:step.tatami.name", "en_US", "tatami");
		LanguageRegistry.instance().addStringLocalization("tile.wa:step.tatami.name", "ja_JP", "畳");

		registerBlock(sakuraWood, "sakuraWood", "桜原木");
		registerBlock(sakuraPlank, "sakuraLog", "桜木材");
		registerBlock(sakuraLeaves, "sakuraLeaves", "桜の葉");
		registerBlock(sakuraSapling, "sakuraSapling", "桜苗木");
		ItemBlock itemBlockSapling = (ItemBlock) new ItemBlockSapling(sakuraSapling.blockID - 256).setUnlocalizedName("wa:sakuraSapling");
		GameRegistry.registerItem(itemBlockSapling, "WaSakuraSapling");
		registerBlock(umeWood, "umeWood", "梅原木");
		registerBlock(umeLog, "umeLog", "梅原木");
		registerBlock(umeSapling, "umeSapling", "梅苗木");
		ItemBlock itemBlockUmeSapling = (ItemBlock) new ItemBlockUmeSapling(umeSapling.blockID - 256).setUnlocalizedName("wa:umeSapling");
		GameRegistry.registerItem(itemBlockUmeSapling, "WaUmeSapling");
		//registerBlock(家紋, "kamon", "家紋");
		/*GameRegistry.registerBlock(家紋, 家紋アイテムブロック.class, "家紋", Wa.modid);
		LanguageRegistry.addName(new ItemStack(家紋, 1, 0), "oda");
		LanguageRegistry.instance().addNameForObject(new ItemStack(家紋, 1, 0), "ja_JP", "織田家家紋");
		LanguageRegistry.addName(new ItemStack(家紋, 1, 1), "mouri");
		LanguageRegistry.instance().addNameForObject(new ItemStack(家紋, 1, 1), "ja_JP", "毛利家家紋");
		LanguageRegistry.addName(new ItemStack(家紋, 1, 2), "tokugawa");
		LanguageRegistry.instance().addNameForObject(new ItemStack(家紋, 1, 2), "ja_JP", "徳川家家紋");*/
		registerBlock(taiko, "taiko", "太鼓");
		registerBlock(koto, "koto", "琴");
		registerBlock(kera, "kera", "ケラ");
		registerBlock(shikui, "shikui", "漆喰");
		registerBlock(wara, "wara", "藁");
		registerBlock(ine, "ine", "稲");
		registerBlock(susuki, "susuki", "すすき");
		registerBlock(take, "take", "竹");
		registerBlock(takenoko, "takenoko", "筍");
		ItemBlock itemBlockTakenoko = (ItemBlock) new ItemBlockSapling(takenoko.blockID - 256).setUnlocalizedName("wa:takenoko");
		GameRegistry.registerItem(itemBlockTakenoko, "WaTakenoko");
		registerBlock(noren, "noren", "暖簾");
		registerBlock(charcoalBlock, "mokutan", "木炭ブロック");
		registerBlock(takezumiBlock, "takezumi", "竹炭ブロック");
		registerBlock(tataraBlock, "tatara", "たたら製鉄炉");
		registerBlock(portal, "portal", "ポータル");
		GameRegistry.registerTileEntity(TileEntityTatara.class, "tatara");

		registerBlock(brewingBarrel, "brewingBarrel", "醸造樽");
		GameRegistry.registerTileEntity(TileEntityBrewingBarrel.class, "BrewingBarrel");

		registerBlock(kawara, "kawara", "瓦");

		//registerBlock(charm, "charm", "御札");
		GameRegistry.registerBlock(charm, ItemBlockCharm.class, "ofuda");
		GameRegistry.registerTileEntity(TileEntityCharm.class, "ofuda");
		GameRegistry.registerItem(itemBlockCharm, "WaOfuda");
		LanguageRegistry.instance().addNameForObject(itemBlockCharm, "en_US", "ofuda");
		LanguageRegistry.instance().addNameForObject(itemBlockCharm, "ja_JP", "御札");

		colorWood = new Block[16];
		for(int i = 0; i < 16; ++i) {
			colorWood[i] = (new BlockColorWood(Config.色付き木材ID + i, i)).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("wa:paintedWood"+ ItemDye.dyeColorNames[i]).setCreativeTab(Wa.creativeTab);
			registerBlock(colorWood[i], "paintedWood(" + ItemDye.dyeColorNames[i] + ")", "色付き木材(" + ItemDye.dyeColorNames[i] + ")");
		}

		//ケラは金槌が対応ツールになる
		MinecraftForge.removeBlockEffectiveness(kera, 0, "pickaxe");
		MinecraftForge.setBlockHarvestLevel(kera, "hummer", 1);


		GameRegistry.registerFuelHandler(new FuelHandler());

		OreDictionary.registerOre("logWood", new ItemStack(sakuraWood, 1, 0));
		OreDictionary.registerOre("plankWood", new ItemStack(sakuraPlank, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(umeWood));
		OreDictionary.registerOre("logWood", new ItemStack(umeLog));
	}

	private static void registerBlock(Block block, String name, String 名前) {
		GameRegistry.registerBlock(block, name);
		LanguageRegistry.instance().addNameForObject(block, "en_US", name);
		LanguageRegistry.instance().addNameForObject(block, "ja_JP", 名前);
	}
}
