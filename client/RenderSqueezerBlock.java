package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import wa.block.BlockSqueezer;
import wa.block.Blocks;

/**
 * Created by dew on 2015/11/19.
 */
public class RenderSqueezerBlock implements ISimpleBlockRenderingHandler {
    int angle =0;
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        Block wick = Blocks.iron_block;
        renderer.setOverrideBlockTexture(wick.getIcon(0, 0));

        // 底
        block.setBlockBounds(0/16.0F, 0/16.0F, 0/16.0F, 16/16.0F, 1/16.0F, 16/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        // 四方
        block.setBlockBounds(0/16.0F, 1/16.0F, 0/16.0F, 2/16.0F, 10/16.0F, 14/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        block.setBlockBounds(0/16.0F, 1/16.0F, 14/16.0F, 14/16.0F, 10/16.0F, 16/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        block.setBlockBounds(2/16.0F, 1/16.0F, 0/16.0F, 16/16.0F, 10/16.0F, 2/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        block.setBlockBounds(14/16.0F, 2/16.0F, 2/16.0F, 16/16.0F, 10/16.0F, 16/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);


        float h = 2.0F;

        block.setBlockBounds(7 / 16.0F, (h + 6) / 16.0F, 7 / 16.0F, 9 / 16.0F, (h + 11) / 16.0F, 9 / 16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        block.setBlockBounds(2/16.0F, (h+5)/16.0F, 2/16.0F, 14/16.0F, (h+6)/16.0F, 14/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

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
        return BlockSqueezer.renderID;
    }
}
