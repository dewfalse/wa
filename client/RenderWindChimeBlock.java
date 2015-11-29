package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import wa.block.BlockWindChime;
import wa.block.Blocks;

/**
 * Created by dew on 2015/11/28.
 */
public class RenderWindChimeBlock implements ISimpleBlockRenderingHandler {

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        //Block stone = Blocks.stone;
        //renderer.setOverrideBlockTexture(Blocks.windChime.getIcon(2, 2));
        block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 16F/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        Block wick = Blocks.wool;
        renderer.setOverrideBlockTexture(wick.getIcon(0, 0));
        block.setBlockBounds(7.8F/16.0F, -4.0F/16.0F, 7.8F/16.0F, 8.2F/16.0F, 32.0F/16.0F, 8.2F/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setOverrideBlockTexture(Blocks.windChime.getIcon(6, 6));
        block.setBlockBounds(8.0F/16.0F, -14.0F/16.0F, 7.0F/16.0F, 8.0F/16.0F, -4.0F/16.0F, 9.0F/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);


        renderer.clearOverrideBlockTexture();
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);

        return true;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return BlockWindChime.renderID;
    }
}
