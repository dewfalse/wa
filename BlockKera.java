package wa;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockKera extends Block {

	public BlockKera(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x,
			int y, int z) {
		ItemStack eq = player.getCurrentEquippedItem();
		if(eq != null && (eq.itemID == Items.金槌.itemID || eq.itemID == Items.鋼の金槌.itemID || eq.itemID == Items.石の金槌.itemID)) {
			int metadata = world.getBlockMetadata(x, y, z);
			if(metadata < Config.鍛練回数) {
				world.setBlockMetadataWithNotify(x, y, z, metadata + 1, 3);
				return true;
			}
		}
		super.removeBlockByPlayer(world, player, x, y, z);
		return true;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		if(par1 >= Config.鍛練回数) {
			if(par2Random.nextInt(100) <= Config.玉鋼出現率){
				Wa.proxy.addStat(Achievements.tatara, 1);
				return Items.玉鋼.itemID;
			}
			if(Config.レシピ難易度 == RecipeDifficulty.HARD) {
				return Items.左下鉄.itemID;
			}
			else if(Config.レシピ難易度 == RecipeDifficulty.VERY_HARD) {
				return Items.ズク.itemID;
			}
			else if(Config.レシピ難易度 <= RecipeDifficulty.ULTRA_HARD) {
				return Items.ズク破片.itemID;
			}
			return Item.ingotIron.itemID;
		}
		return 0;
	}

	@Override
	protected void dropBlockAsItem_do(World par1World, int par2, int par3,
			int par4, ItemStack par5ItemStack) {
		if(par5ItemStack.itemID == Items.ズク破片.itemID) {
			par5ItemStack.stackSize = 5 + par1World.rand.nextInt(10);
		}
		super.dropBlockAsItem_do(par1World, par2, par3, par4, par5ItemStack);
	}
}
