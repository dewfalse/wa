package wa;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = Wa.modid, name = Wa.modid, version = "1.0")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = true,
		channels = Wa.modid,
		packetHandler = PacketHandler.class
	)
public class Wa {
	public static final String modid = "Wa";

	@Instance("Wa")
	public static Wa instance;

	@SidedProxy(clientSide = "wa.ClientProxy", serverSide = "wa.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs creativeTab = new WaCreativeTabs();

	private WaTrade 倭人取引;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		Config.preInit(event.getSuggestedConfigurationFile());
		Blocks.preInit();
		Items.preInit();
		Achievements.preInit();
		proxy.preInit();

	}

	@Init
	public void init(FMLInitializationEvent event) {

		EntityRegistry.registerModEntity(EntityObon.class, "Obon", 0, this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityShuriken.class, "Shuriken", 1, this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityKakejiku.class, "Kakejiku", 2, this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityCoin.class, "Coin", 3, this, 80, 1, true);
		//TODO レシピ追加
		Recipes.init();
		Items.init();
		//Entities.init();
		//Biomes.init();
		//Dimentions.init();
		WorldChunkManagerWa.init();
		DimensionManager.registerProviderType(Config.providerType, WorldProviderWa.class, false);
		DimensionManager.registerDimension(Config.dimensionID, Config.providerType);
		GameRegistry.registerWorldGenerator(new WorldGenTorii());
		proxy.init();
		//TODO 追加ディメンション内では和の音楽がなるようにする
		//TODO 城の生成
		//城の生成は天守閣から開始
		//天守閣→階段→通路（縦・横）→左右の部屋・通路の折れ曲がり

		倭人取引 = new WaTrade();
		VillagerRegistry.instance().registerVillagerType(Config.町人ID, "/mods/wa/textures/villager.png");
		VillagerRegistry.instance().registerVillagerType(Config.刀鍛冶ID, "/mods/wa/textures/swordsmith.png");
		VillagerRegistry.instance().registerVillageTradeHandler(Config.町人ID, 倭人取引);
		VillagerRegistry.instance().registerVillageTradeHandler(Config.刀鍛冶ID, 倭人取引);

		BiomeManager.addVillageBiome(WorldChunkManagerWa.spring, true);
		BiomeManager.addVillageBiome(WorldChunkManagerWa.summer, true);
		BiomeManager.addVillageBiome(WorldChunkManagerWa.autumn, true);
		BiomeManager.addVillageBiome(WorldChunkManagerWa.winter, true);
		MinecraftForge.TERRAIN_GEN_BUS.register(new GetVillageBlockIDEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new SaplingGrowTreeEventHandler());
		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityJoinWorldEventHandler());
		MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());
		GameRegistry.registerWorldGenerator(new WorldGenTakenoko());
		GameRegistry.registerCraftingHandler((ICraftingHandler) Items.太刀);

		String[] categories = {
				ChestGenHooks.MINESHAFT_CORRIDOR,
				ChestGenHooks.PYRAMID_DESERT_CHEST,
				ChestGenHooks.PYRAMID_JUNGLE_CHEST,
				ChestGenHooks.STRONGHOLD_CORRIDOR,
				ChestGenHooks.STRONGHOLD_LIBRARY,
				ChestGenHooks.STRONGHOLD_CROSSING,
				ChestGenHooks.DUNGEON_CHEST,
				};
		for(String category : categories) {
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.刀), 1, 1, 100));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.太刀), 1, 1, 100));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.玉鋼), 1, 4, 80));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.金槌), 1, 1, 60));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.鋼の斧), 1, 1, 80));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.鋼のツルハシ), 1, 1, 80));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.鋼のシャベル), 1, 1, 80));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.鋼のクワ), 1, 1, 80));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.お盆), 1, 1, 30));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.手裏剣), 1, 16, 30));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.毛筆), 1, 1, 30));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.掛け軸), 1, 1, 30));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.竹炭), 1, 16, 30));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Blocks.taiko), 1, 1, 70));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Blocks.noren), 1, 1, 70));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Blocks.sakuraSapling), 1, 16, 40));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Blocks.takenoko), 1, 16, 40));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.種籾), 1, 16, 40));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.貨幣, 1, 0), 1, 16, 40));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.貨幣, 1, 1), 1, 6, 60));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.貨幣, 1, 2), 1, 1, 80));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.勾玉), 1, 1, 100));
			ChestGenHooks.addItem(category, new WeightedRandomChestContent(new ItemStack(Items.御鏡), 1, 1, 100));
		}
	}

}
