package wa;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockIne extends BlockCrops {
	private Icon[] iconArray;

	protected BlockIne(int par1) {
		super(par1);
		disableStats();
	}

	@Override
	protected int getSeedItem() {
		return Items.種籾.itemID;
	}

	@Override
	protected int getCropItem() {
		return Items.稲.itemID;
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		if (par2 < 0 || par2 > 7) {
			par2 = 7;
		}

		return this.iconArray[par2];
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[8];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("wa:ine_" + i);
		}
	}
}
