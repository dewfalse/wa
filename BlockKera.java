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
		if(eq != null && (eq.itemID == Items.金槌.itemID || eq.itemID == Items.鋼の金槌.itemID)) {
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
			return Item.ingotIron.itemID;
		}
		return 0;
	}

}
