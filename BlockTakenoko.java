package wa;

import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockTakenoko extends BlockSapling {

	Icon theIcon;
	public BlockTakenoko(int par1) {
		super(par1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		if(par5Random.nextDouble() * 100.0D > Config.筍成長確率) {
			par1World.setBlock(par2, par3, par4, Blocks.take.blockID);
		}
    }

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer,
			int par3, int par4, int par5, int par6) {
		ItemStack itemStack = par2EntityPlayer.getCurrentEquippedItem();
		if(itemStack == null) {
			return;
		}
		if(Item.itemsList[itemStack.itemID] instanceof ItemHoe == false) {
			return;
		}
		// TODO 自動生成されたメソッド・スタブ
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return theIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		theIcon = par1IconRegister.registerIcon("wa:takenoko");
	}
}
