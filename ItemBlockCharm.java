package wa;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;

public class ItemBlockCharm extends ItemBlock {

	private int _blockID;
	public ItemBlockCharm(int par1) {
		super(par1);
		_blockID = par1 + 256;
		this.setMaxStackSize(1);
		this.setMaxDamage(1600);
		this.setCreativeTab(Wa.creativeTab);
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return this.itemIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon("wa:ofuda");
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		// TODO 自動生成されたメソッド・スタブ
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		if(par1ItemStack.hasTagCompound()) {
			NBTTagList list = par1ItemStack.getEnchantmentTagList();
			if(list != null) {
				for(int i = 0; i < list.tagCount(); ++i) {
					short id = ((NBTTagCompound)list.tagAt(i)).getShort("id");
					if (Enchantment.enchantmentsList[id] != null) {
						if(id == Enchantments.purgation.effectId) {
							return "清めの御札";
						}
					}
				}
			}
		}

		return super.getItemDisplayName(par1ItemStack);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return par1ItemStack.hasTagCompound() ? EnumRarity.rare : EnumRarity.common;
	}

	@Override
	public int getItemEnchantability() {
		return 1;
	}

}
