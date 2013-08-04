package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagatama extends Item {

	public ItemMagatama(int par1) {
		super(par1);
		setMaxDamage(30);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}

}
