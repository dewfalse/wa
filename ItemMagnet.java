package wa;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import wa.block.Blocks;

/**
 * Created by dew on 2014/10/07.
 */
public class ItemMagnet extends Item {
    protected ItemMagnet() {
        super();
        setMaxStackSize(1);
        setMaxDamage(1250);
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entityPlayer, false);

        if (movingobjectposition == null) {
            return itemStack;
        }
        else {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                Block block = world.getBlock(i, j, k);
                if(block == Blocks.gravel || block == Blocks.sand) {
                    if(world.isRemote == false) {
                        for(int n = 0; n < world.rand.nextInt(5); ++n) {
                            world.spawnEntityInWorld(new EntityItem(world, i, j + 0.5F, k, new ItemStack(Items.ズク破片, 1 + world.rand.nextInt(5))));
                        }
                    }
                    world.setBlockToAir(i, j, k);
                    itemStack.damageItem(1, entityPlayer);
                }
            }
        }
        return super.onItemRightClick(itemStack, world, entityPlayer);
    }
}
