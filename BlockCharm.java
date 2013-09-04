package wa;

import java.util.Set;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCharm extends BlockContainer {

	public static int renderID;

	protected BlockCharm(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCharm();
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float minX = 1.0F;
		float minY = 1.0F;
		float minZ = 1.0F;
		float maxX = 0.0F;
		float maxY = 0.0F;
		float maxZ = 0.0F;

		if ((metadata & 1) != 0) {
			minZ = 0.48F;
			maxZ = 0.52F;
			minX = 0.2F;
			maxX = 0.8F;
			minY = 0.0F;
			maxY = 1.0F;
		}
		else {
			minX = 0.48F;
			maxX = 0.52F;
			minY = 0.0F;
			maxY = 1.0F;
			minZ = 0.2F;
			maxZ = 0.8F;
		}

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	return true;
    }

    @Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
    	int metadata = par1World.getBlockMetadata(par2, par3, par4);
    	if(metadata > 0) {
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
    	}
    	else {
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
    	}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}

	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		Set<PathPoint> list = EntityJoinWorldEventHandler.charmsInDim.get(par1World.provider.dimensionId);
		if(list != null) {
			list.remove(new PathPoint(par2, par3, par4));
			EntityJoinWorldEventHandler.charmsInDim.put(par1World.provider.dimensionId, list);
		}
		if(!par1World.isRemote) {
			TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
			if(tile != null && tile instanceof TileEntityCharm) {
				TileEntityCharm tileCharm = (TileEntityCharm)tile;
				if(tileCharm.damage < 1600) {
					ItemStack itemStack = new ItemStack(Blocks.charm, 1, tileCharm.damage);
					if(tileCharm.charmType == 3) {
						itemStack.addEnchantment(Enchantments.purgation, tileCharm.charmLevel);
					}
					EntityItem entityItem = new EntityItem(par1World, par2, par3, par4, itemStack);
					par1World.spawnEntityInWorld(entityItem);
				}
			}
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	protected void dropBlockAsItem_do(World par1World, int par2, int par3,
			int par4, ItemStack par5ItemStack) {
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		if(tile instanceof TileEntityCharm) {
			((TileEntityCharm)tile).damage = 0;
			((TileEntityCharm)tile).charmType = 1;
			((TileEntityCharm)tile).charmLevel = 1;
			if(par6ItemStack.hasTagCompound()) {
				NBTTagList list = par6ItemStack.getEnchantmentTagList();
				if(list != null) {
					for(int i = 0; i < list.tagCount(); ++i) {
						short id = ((NBTTagCompound)list.tagAt(i)).getShort("id");
						short level = ((NBTTagCompound)list.tagAt(i)).getShort("lvl");
						if (Enchantment.enchantmentsList[id] != null) {
							if(id == Enchantments.purgation.effectId) {
								((TileEntityCharm)tile).charmType = 3;
								((TileEntityCharm)tile).charmLevel = level;
								((TileEntityCharm)tile).damage = par6ItemStack.getItemDamage();
								break;
							}
						}
					}
				}

			}
		}
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase,
				par6ItemStack);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wa:ofuda");
	}

}
