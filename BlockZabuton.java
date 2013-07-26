package wa;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockZabuton extends BlockHalfSlab {

	Icon[] icons;

	public BlockZabuton(int par1, boolean par2, Material par3Material) {
		super(par1, par2, par3Material);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public String getFullSlabName(int i) {
		return super.getUnlocalizedName() + "." + "zabuton";
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return icons[0];
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 2, par1 & 7);
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[1];
		icons[0] = par1IconRegister.registerIcon("wa:zabuton");
	}
}
