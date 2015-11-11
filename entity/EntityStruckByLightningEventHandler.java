package wa.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import wa.block.Blocks;

/**
 * Created by dew on 2014/10/07.
 */
public class EntityStruckByLightningEventHandler {

    @SubscribeEvent
    public void onEntityStruckByLightningEvent(EntityStruckByLightningEvent event) {
        if(event.entity.worldObj.isRemote == false) {
            int x = (int) event.entity.posX;
            int z = (int) event.entity.posZ;
            for(int y = (int) event.entity.posY; 0 < y; --y) {
                Block block = event.entity.worldObj.getBlock(x, y, z);
                if(block == Blocks.stone) {
                    if(event.entity.worldObj.rand.nextInt(40) == 0) {
                        event.entity.worldObj.setBlock(x, y, z, Blocks.oreMagnetite);
                    }
                }
            }
        }
    }
}
