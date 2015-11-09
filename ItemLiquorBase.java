package wa;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemLiquorBase extends Item {
	
	public ItemLiquorBase() {
		super();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		int grade = 0;
		if (nbt != null && nbt.hasKey("Grade")) {
			grade = nbt.getInteger("Grade");
		}

		// gradeを数値として表示。
		// 数値によって良さのコメントが出る方が面白いかもしれない
		String s = new String("GRADE : " + grade);
		par3List.add(s);
	}

}
