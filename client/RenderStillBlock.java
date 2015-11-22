package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import wa.block.BlockStill;
import wa.block.Blocks;

/**
 * Created by dew on 2015/11/15.
 */
public class RenderStillBlock implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Block wick = Blocks.iron_block;
        renderer.setOverrideBlockTexture(wick.getIcon(0, 0));

        int metadata = world.getBlockMetadata(x, y, z);
        if(metadata == 1) {
            block.setBlockBounds(2.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);
            block.setBlockBounds(7.0F/16.0F, 10.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 14.0F/16.0F, 9.0F/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);
        }
        else {
            block.setBlockBounds(4.0F/16.0F, 0.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 8.0F/16.0F, 12.0F/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);
            block.setBlockBounds(7.0F/16.0F, 8.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 14.0F/16.0F, 9.0F/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);
        }

        if(metadata >= 2) {
            int angle = new int[]{-90, 0, 90, -180}[metadata - 2];
            double r = Math.toRadians(angle);
            double sx = 2 * Math.cos(r) - 0 * Math.sin(r);
            double sz = 2 * Math.sin(r) + 0 * Math.cos(r);
            double ex = 14 * Math.cos(r) - 0 * Math.sin(r);
            double ez = 14 * Math.sin(r) + 0 * Math.cos(r);
            float ax = (float) Math.min(sx, ex);
            float az = (float) Math.min(sz, ez);
            float bx = (float) Math.max(sx, ex);
            float bz = (float) Math.max(sz, ez);
            block.setBlockBounds((ax+7)/16.0F, 12/16.0F, (az+7)/16.0F, (bx+9)/16.0F, 14/16.0F, (bz+9)/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);

        }
        if(metadata == 1) {
            block.setBlockBounds(11/16.0F, 10/16.0F, 11/16.0F, 13/16.0F, 16/16.0F, 13/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);
        }

        renderer.clearOverrideBlockTexture();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return BlockStill.renderID;
    }
}
