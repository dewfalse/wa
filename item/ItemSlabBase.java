package wa.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wa.block.BlockSlabBase;

/**
 * バニラのItemSlabが使いにくくて可読性もよろしくないので作成。
 * 独自に作るデメリットは発生すると思う
 * @author defeatedcrow
 */
public abstract class ItemSlabBase extends ItemBlock
{
    public ItemSlabBase(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return Block.getBlockFromItem(this).getIcon(1, meta);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        return getHalf().getUnlocalizedName() + "_" + item.getItemDamage();
    }
    
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
    {
    	if (item == null || Block.getBlockFromItem(item.getItem()).isAir(world, x, y, z))
        {
            return false;
        }
    	else if (getDouble() == null) //ダブルにならないものもある
    	{
    		return super.onItemUse(item, player, world, x, y, z, side, fx, fy, fz);
    	}
    	else if (item.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, side, item))
        {
            return false;
        }
        else
        {
            Block block = world.getBlock(x, y, z);
            int i1 = world.getBlockMetadata(x, y, z);
            int j1 = i1 & 3;
            int k1 = i1 & 7;
            boolean flag = (i1 & 8) != 0;

            if ((side == 1 && !flag || side == 0 && flag) && block == getHalf() && j1 == item.getItemDamage())
            {
                if (world.checkNoEntityCollision(getDouble().getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, getDouble(), k1, 3))
                {
                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), getDouble().stepSound.func_150496_b(), (getDouble().stepSound.getVolume() + 1.0F) / 2.0F, getDouble().stepSound.getPitch() * 0.8F);
                    --item.stackSize;
                }

                return true;
            }
            else
            {
                return this.setSlab(item, player, world, x, y, z, side) ? true : super.onItemUse(item, player, world, x, y, z, side, fx, fy, fz);
            }
        }
    }
    
    private boolean setSlab(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side)
    {
        if (side == 0)
        {
            --y;
        }

        if (side == 1)
        {
            ++y;
        }

        if (side == 2)
        {
            --z;
        }

        if (side == 3)
        {
            ++z;
        }

        if (side == 4)
        {
            --x;
        }

        if (side == 5)
        {
            ++x;
        }

        Block block = world.getBlock(x, y, z);
        int i1 = world.getBlockMetadata(x, y, z);
        int j1 = i1 & 7;

        if (block == getHalf() && j1 == item.getItemDamage())
        {
            if (world.checkNoEntityCollision(getDouble().getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, getDouble(), j1, 3))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), getDouble().stepSound.func_150496_b(), (getDouble().stepSound.getVolume() + 1.0F) / 2.0F, getDouble().stepSound.getPitch() * 0.8F);
                --item.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    
    protected abstract BlockSlabBase getHalf();
    
    protected abstract BlockSlabBase getDouble();

}
