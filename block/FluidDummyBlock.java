package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class FluidDummyBlock extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon[] baseIcon;

	private String[] iconType = new String[] { "alcohol", "apple_juice", "cactus_essence", "calvados", "carrot_juice"
			,"cider", "fin_sake", "ginseng_liquor", "golden_apple_juice", "golden_calvados", "golden_carrot_juice"
			,"golden_ginseng_liquor", "infernal_liquor", "kumis", "magma_cream_sour", "potato_essence"
			,"potato_ferment_essence", "pulque", "pumpkin_ale", "rum", "sake", "spirytus", "syrup_ferment_essence"
			,"syrup", "tequila", "umesyu", "wash", "whisky", "wort", "young_rum", "young_tequila", "young_whisky"
			,"slime_jelly_soda", ""};

	public FluidDummyBlock() {
		super(Material.water);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		MathHelper.clamp_int(meta, 0, baseIcon.length - 1);
		return this.baseIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.baseIcon = new IIcon[iconType.length];
		for (int i = 0; i < iconType.length; ++i) {
			this.baseIcon[i] = par1IconRegister.registerIcon("wa:fluid/" + this.iconType[i] + "_still");
		}
	}
}
