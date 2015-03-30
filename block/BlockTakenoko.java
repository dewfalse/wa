package wa.block;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wa.Config;

import java.util.Random;

public class BlockTakenoko extends BlockSapling {

	IIcon theIcon;
	public BlockTakenoko() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
    public void func_149878_d(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		if(par5Random.nextDouble() * 100.0D > Config.筍成長確率) {
			par1World.setBlock(par2, par3, par4, Blocks.take);
		}
    }

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer,
			int par3, int par4, int par5, int par6) {
		ItemStack itemStack = par2EntityPlayer.getCurrentEquippedItem();
		if(itemStack == null) {
			return;
		}
		if(itemStack.getItem() instanceof ItemHoe == false) {
			return;
		}
		// TODO 自動生成されたメソッド・スタブ
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return theIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		theIcon = par1IconRegister.registerIcon("wa:takenoko");
	}
}
