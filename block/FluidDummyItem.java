package wa.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class FluidDummyItem extends ItemBlock {
	private IIcon icons[];
	private String[] fluidName = {"umesyu", "sake"};

	public FluidDummyItem(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		return super.getUnlocalizedName() + "_" + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	// 設置禁止
		@Override
		public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
			return stack;
		}
}
