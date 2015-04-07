package wa.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import wa.Items;

public class BlockIne extends BlockCrops {
	private IIcon[] iconArray;

	protected BlockIne() {
		super();
		disableStats();
	}

	@Override
	protected Item func_149866_i() {
		return Items.種籾;
	}

	@Override
	protected Item func_149865_P() {
		return Items.稲;
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		if (par2 < 0 || par2 > 7) {
			par2 = 7;
		}

		return this.iconArray[par2];
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.iconArray = new IIcon[8];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("wa:ine_" + i);
		}
	}
	
	
}
