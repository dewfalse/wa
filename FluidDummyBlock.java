package wa;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class FluidDummyBlock extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon baseIcon[];

	private String[] iconType = new String[] { "umesyu_still", "sake_still" };

	public FluidDummyBlock() {
		super(Material.water);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		MathHelper.clamp_int(meta, 0, 1);
		return this.baseIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.baseIcon = new IIcon[2];
		for (int i = 0; i < 2; ++i) {
			this.baseIcon[i] = par1IconRegister.registerIcon("wa:fluid/" + this.iconType[i]);
		}

	}
}
