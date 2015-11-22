package wa.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
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

    	// キャストエラー回避
    	// RC足跡ブロックなど、空気として振る舞うTileEntityにキャストエラーを食らうことがあるので念のため
        TileEntitySpiritLamp tileEntitySpiritLamp = null;
        if (world.getTileEntity(x, y, z) instanceof TileEntitySpiritLamp){
        	tileEntitySpiritLamp = (TileEntitySpiritLamp)world.getTileEntity(x, y, z);
        }
        if (tileEntitySpiritLamp != null ){
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
        	
        	Block out = Blocks.stained_glass;
            renderer.setOverrideBlockTexture(out.getIcon(0, 0));
            block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F);
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderStandardBlock(block, x, y, z);

//            int amount = tileEntitySpiritLamp.getAmount();
//            if(amount > 0) {
//                // アルコールランプ内のアルコールを表示したいけどアルコールは透明だしレンダラーで半透明描画できないしなので水ブロックで残量がわかるように液面を上下させるに留める
//            	
//                if (block instanceof BlockSpiritLamp){
//                	IIcon icon = ((BlockSpiritLamp)block).getIcon(0, 1);
//
//                    // 液面の高さ。
//                    float height = (amount / (tileEntitySpiritLamp.getCapacity() * 1.0F)) * 4.0F + 1.0F;
//
//                    renderer.setOverrideBlockTexture(icon);
//    				block.setBlockBounds(1.0F/16.0F, 0.9F/16.0F, 1.0F/15.0F, 15.0F/16.0F, height/16.0F,
//    						15.0F/16.0F);
//    				renderer.setRenderBoundsFromBlock(block);
//    				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 2.0F, 2.0F, 2.0F);
//                }
//                
//            }
            

            Block wick = Blocks.wool;
            renderer.setOverrideBlockTexture(wick.getIcon(0, 0));
            block.setBlockBounds(7.0F/16.0F, 1.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 8.0F/16.0F, 9.0F/16.0F);
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
        return BlockSpiritLamp.renderID;
    }
}
