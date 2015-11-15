package wa.block;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by dew on 2015/11/11.
 */
public class TileEntitySqueezer extends TileEntitySqueezerBase {
    @Override
    public String getInventoryName()  {
        return "wa.inv.Squeezer";
    }
}
