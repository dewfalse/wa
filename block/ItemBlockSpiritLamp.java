package wa.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by dew on 2015/11/16.
 */
public class ItemBlockSpiritLamp extends ItemBlock {
    public ItemBlockSpiritLamp(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        // アルコール残量のNBTがあるアイテムの場合は設置時にTileEntityに残量を設定する
        if(super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata)) {
            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile != null) {
                TileEntitySpiritLamp tileEntitySpiritLamp = (TileEntitySpiritLamp) tile;
                if(stack.hasTagCompound() && stack.getTagCompound().hasKey("amount")) {
                    tileEntitySpiritLamp.setAmount(stack.getTagCompound().getInteger("amount"));
                }
            }
            return true;
        }
        return false;
    }
}
