package wa;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUmenomi extends Item {
	private int blockID;

	public ItemUmenomi(int par1) {
		super(par1);
		blockID = Blocks.umeSapling.blockID;
	}

	public int getBlockID() {
		return Blocks.umeSapling.blockID;
	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		int i1 = par3World.getBlockId(par4, par5, par6);

		if (i1 == Block.snow.blockID
				&& (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1) {
			par7 = 1;
		} else if (i1 != Block.vine.blockID
				&& i1 != Block.tallGrass.blockID
				&& i1 != Block.deadBush.blockID
				&& (Block.blocksList[i1] == null || !Block.blocksList[i1]
						.isBlockReplaceable(par3World, par4, par5, par6))) {
			if (par7 == 0) {
				--par5;
			}

			if (par7 == 1) {
				++par5;
			}

			if (par7 == 2) {
				--par6;
			}

			if (par7 == 3) {
				++par6;
			}

			if (par7 == 4) {
				--par4;
			}

			if (par7 == 5) {
				++par4;
			}
		}

		if (par1ItemStack.stackSize == 0) {
			return false;
		} else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
				par1ItemStack)) {
			return false;
		} else if (par5 == 255
				&& Block.blocksList[this.blockID].blockMaterial.isSolid()) {
			return false;
		} else if (par3World.canPlaceEntityOnSide(this.blockID, par4, par5,
				par6, false, par7, par2EntityPlayer, par1ItemStack)) {
			Block block = Block.blocksList[this.blockID];
			int j1 = this.getMetadata(par1ItemStack.getItemDamage());
			int k1 = Block.blocksList[this.blockID].onBlockPlaced(par3World,
					par4, par5, par6, par7, par8, par9, par10, j1);

			if (placeBlockAt(par1ItemStack, par2EntityPlayer, par3World, par4,
					par5, par6, par7, par8, par9, par10, k1)) {
				par3World.playSoundEffect((double) ((float) par4 + 0.5F),
						(double) ((float) par5 + 0.5F),
						(double) ((float) par6 + 0.5F),
						block.stepSound.getPlaceSound(),
						(block.stepSound.getVolume() + 1.0F) / 2.0F,
						block.stepSound.getPitch() * 0.8F);
				--par1ItemStack.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean canPlaceItemBlockOnSide(World par1World, int par2, int par3,
			int par4, int par5, EntityPlayer par6EntityPlayer,
			ItemStack par7ItemStack) {
		int i1 = par1World.getBlockId(par2, par3, par4);

		if (i1 == Block.snow.blockID) {
			par5 = 1;
		} else if (i1 != Block.vine.blockID
				&& i1 != Block.tallGrass.blockID
				&& i1 != Block.deadBush.blockID
				&& (Block.blocksList[i1] == null || !Block.blocksList[i1]
						.isBlockReplaceable(par1World, par2, par3, par4))) {
			if (par5 == 0) {
				--par3;
			}

			if (par5 == 1) {
				++par3;
			}

			if (par5 == 2) {
				--par4;
			}

			if (par5 == 3) {
				++par4;
			}

			if (par5 == 4) {
				--par2;
			}

			if (par5 == 5) {
				++par2;
			}
		}

		return par1World.canPlaceEntityOnSide(this.getBlockID(), par2, par3,
				par4, false, par5, (Entity) null, par7ItemStack);
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ, int metadata) {
		if (!world.setBlock(x, y, z, this.blockID, metadata, 3)) {
			return false;
		}

		if (world.getBlockId(x, y, z) == this.blockID) {
			Block.blocksList[this.blockID].onBlockPlacedBy(world, x, y, z,
					player, stack);
			Block.blocksList[this.blockID].onPostBlockPlaced(world, x, y, z,
					metadata);
		}

		return true;
	}
}
