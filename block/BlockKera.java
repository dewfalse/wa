package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import wa.*;
import wa.item.Items;
import wa.recipe.RecipeDifficulty;

import java.util.Random;

public class BlockKera extends Block {

	public BlockKera(Material par2Material) {
		super(par2Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x,
			int y, int z) {
		ItemStack eq = player.getCurrentEquippedItem();
		if(eq != null && (eq.getItem() == Items.金槌 || eq.getItem() == Items.鋼の金槌 || eq.getItem() == Items.石の金槌)) {
			int metadata = world.getBlockMetadata(x, y, z);
			if(metadata < Config.鍛練回数) {
				world.setBlockMetadataWithNotify(x, y, z, metadata + 1, 3);
				return true;
			}
		}
		super.removedByPlayer(world, player, x, y, z);
		return true;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		if(par1 >= Config.鍛練回数) {
			if(par2Random.nextInt(100) <= Config.玉鋼出現率){
				Wa.proxy.addStat(Achievements.tatara, 1);
				return Items.玉鋼;
			}
			if(Config.レシピ難易度 == RecipeDifficulty.HARD) {
				return Items.左下鉄;
			}
			else if(Config.レシピ難易度 == RecipeDifficulty.VERY_HARD) {
				return Items.ズク;
			}
			else if(Config.レシピ難易度 <= RecipeDifficulty.ULTRA_HARD) {
				return Items.ズク破片;
			}
			return Items.iron_ingot;
		}
		return null;
	}

	@Override
	protected void dropBlockAsItem(World par1World, int par2, int par3,
			int par4, ItemStack par5ItemStack) {
		if(par5ItemStack.getItem() == Items.ズク破片) {
			par5ItemStack.stackSize = 5 + par1World.rand.nextInt(10);
		}
		super.dropBlockAsItem(par1World, par2, par3, par4, par5ItemStack);
	}
}
