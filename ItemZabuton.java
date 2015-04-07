package wa;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author defeatedcrow
 */
public class ItemZabuton extends ItemBlock {
	
	private final Block zabutonBlock;

	public ItemZabuton(Block block)
    {
        super(block);
        zabutonBlock = block;
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
    	int m = item.getItemDamage() & 8;
        return super.getUnlocalizedName() + "_" + m;
    }
    
    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
    {
    	if (item == null || Block.getBlockFromItem(item.getItem()).isAir(world, x, y, z))
        {
            return false;
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
            int j1 = i1 & 7;
            int k1 = i1 & 8;
            boolean flag = (i1 & 8) != 0;

            if (block == zabutonBlock && k1 == item.getItemDamage())
            {
            	if (j1 < 7 )
            	{
            		if (world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 3))
                    {
                        world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), field_150939_a.stepSound.func_150496_b(), (field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, field_150939_a.stepSound.getPitch() * 0.8F);
                        --item.stackSize;
                    }
            	}
            	else if (world.isAirBlock(x, y + 1, z))
            	{
            		if (world.setBlock(x, y + 1, z, block, k1, 3))
                    {
                        world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), field_150939_a.stepSound.func_150496_b(), (field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, field_150939_a.stepSound.getPitch() * 0.8F);
                        --item.stackSize;
                    }
            	}

                return true;
            }
            else
            {
                return super.onItemUse(item, player, world, x, y, z, side, fx, fy, fz);
            }
        }
    }

}
