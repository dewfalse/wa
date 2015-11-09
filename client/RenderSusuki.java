package wa.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import wa.block.BlockSusuki;
import wa.block.Blocks;

public class RenderSusuki extends RenderingBase {
	
	private IIcon[] top;
	private IIcon[] middle;
	private IIcon bottom;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		top = new IIcon[] {Blocks.susuki.getIcon(0, 0), Blocks.susuki.getIcon(3, 0), Blocks.susuki.getIcon(4, 0)};
		middle = new IIcon[] {Blocks.susuki.getIcon(1, 0), Blocks.susuki.getIcon(1, 0), Blocks.susuki.getIcon(5, 0)};
		bottom = Blocks.susuki.getIcon(2, 0);
		
		int cood = (x + z) & 3;
		if (cood > 2) cood = 2; 
		float ajust = cood == 1 ? 1.0F : (cood == 2 ? -1.0F : 0.0F);
		
		if (modelId == this.getRenderId()) {
			boolean connectTop = world.getBlock(x, y + 1, z) == Blocks.susuki;
			boolean connectBottom = world.getBlock(x, y - 1, z) == Blocks.susuki;
			
			if (connectTop) {
				if (connectBottom) {
					
					renderer.setOverrideBlockTexture(middle[cood]);
					block.setBlockBounds(ajust/16.0F, 0.0F/16.0F, ajust/16.0F, (16.0F + ajust)/16.0F, 16.0F/16.0F, (16.0F + ajust)/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderCrossedSquares(block, x, y, z);
				}
				else {
					
					renderer.setOverrideBlockTexture(bottom);
					block.setBlockBounds(ajust/16.0F, 0.0F/16.0F, ajust/16.0F, (16.0F + ajust)/16.0F, 16.0F/16.0F, (16.0F + ajust)/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderCrossedSquares(block, x, y, z);
				}
			}
			else {
				renderer.setOverrideBlockTexture(top[cood]);
				block.setBlockBounds(ajust/16.0F, 0.0F/16.0F, ajust/16.0F, (16.0F + ajust)/16.0F, 16.0F/16.0F, (16.0F + ajust)/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderCrossedSquares(block, x, y, z);
			}
			
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int a) {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockSusuki.renderID;
	}

}
