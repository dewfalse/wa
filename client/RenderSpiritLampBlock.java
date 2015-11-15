package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import wa.FluidInit;
import wa.block.BlockSpiritLamp;
import wa.block.Blocks;
import wa.block.TileEntitySpiritLamp;

/**
 * Created by dew on 2015/11/12.
 */
public class RenderSpiritLampBlock implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        TileEntitySpiritLamp tileEntitySpiritLamp = (TileEntitySpiritLamp)world.getTileEntity(x, y, z);
        if(tileEntitySpiritLamp.getIgnited()) {
            Tessellator tessellator = Tessellator.instance;
            tessellator.setBrightness(Blocks.fire.getMixedBrightnessForBlock(world, x, y, z));
            int l = Blocks.fire.colorMultiplier(world, x, y, z);
            float f = (float)(l >> 16 & 255) / 255.0F;
            float f1 = (float)(l >> 8 & 255) / 255.0F;
            float f2 = (float)(l & 255) / 255.0F;
            tessellator.setColorOpaque_F(f, f1, f2);
            tessellator.addTranslation(0.0F, 0.6F, 0.0F);
            renderer.drawCrossedSquares(Blocks.fire.getFireIcon(0), x, y, z, 0.75F);
            tessellator.addTranslation(0.0F, -0.6F, 0.0F);
        }

        int amount = tileEntitySpiritLamp.getAmount();
        if(amount > 0) {
            // アルコールランプ内のアルコールを表示したいけどアルコールは透明だしレンダラーで半透明描画できないしなので水ブロックで残量がわかるように液面を上下させるに留める
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            Block in = Blocks.water;
            int l = in.colorMultiplier(world, x, y, z);
            float f = (float)(l >> 16 & 255) / 255.0F;
            float f1 = (float)(l >> 8 & 255) / 255.0F;
            float f2 = (float)(l & 255) / 255.0F;

            // 液面の高さ。
            float height = (amount / (tileEntitySpiritLamp.getCapacity() * 1.0F)) * 4.0F + 1.0F;

            renderer.setOverrideBlockTexture(in.getIcon(0, 0));
            block.setBlockBounds(1.0F/16.0F, height/16.0F, 1.0F/16.0F, 15.0F/16.0F, height/16.0F, 15.0F/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlockWithAmbientOcclusion(block, x, y, z, 200.0F, 200.0F, 200.0F);

            GL11.glDepthMask(true);

            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }

        Block out = Blocks.stained_glass;
        renderer.setOverrideBlockTexture(out.getIcon(0, 0));
        block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F);
        renderer.setRenderBoundsFromBlock(block);
        renderer.renderStandardBlock(block, x, y, z);

        Block wick = Blocks.wool;
        renderer.setOverrideBlockTexture(wick.getIcon(0, 0));
        block.setBlockBounds(7.0F/16.0F, 1.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 8.0F/16.0F, 9.0F/16.0F);
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
        return BlockSpiritLamp.renderID;
    }
}
