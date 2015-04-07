package wa.client;

import wa.block.BlockUmeWood;
import wa.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderUmeLog extends RenderingBase {
	
	private IIcon leavesIcon;
	private IIcon woodIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		this.woodIcon = Blocks.umeLog.getIcon(1, 0);
		this.leavesIcon = Blocks.umeLog.getIcon(2, 0);
		
		if (modelID == this.getRenderId())
		{
			//box
			renderInvCuboid(renderer, block,  1.0F/16.0F, 2.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 15.0F/16.0F, 15.0F/16.0F,  this.leavesIcon);
			renderInvCuboid(renderer, block,  6.0F/16.0F, 0.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F,  this.woodIcon);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		this.woodIcon = Blocks.umeLog.getIcon(1, 0);
		this.leavesIcon = Blocks.umeLog.getIcon(2, 0);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (modelId == this.getRenderId()) {
			
			int[] conected = {0,0,0,0,0,0};
			int conectSize = 0;
			
			//各方向の接続を確認
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				Block get = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
				if (get == block || get.getMaterial() == Material.ground || get.getMaterial() == Material.grass) {
					conected[dir.ordinal()] = 1;
					conectSize++;
				}
			}
			
			//metadata 1以上にすると、強制的に花になる
			if (conectSize < 2 || meta > 0)
			{
				renderer.setOverrideBlockTexture(this.leavesIcon);
				block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderCrossedSquares(block, x, y, z);
				
				renderer.setOverrideBlockTexture(this.woodIcon);
				block.setBlockBounds(7.0F/16.0F, 7.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 9.0F/16.0F, 9.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				
				/* 接続条件にあわせて接続部分の描画。ゴリ押し実装… */
				
				if (conected[0] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(6.0F/16.0F, 0.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[1] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(6.0F/16.0F, 10.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 16.0F/16.0F, 10.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[2] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(6.0F/16.0F, 6.0F/16.0F, 0.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F, 6.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				if (conected[3] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(6.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F, 16.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[4] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(0.0F/16.0F, 6.0F/16.0F, 6.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[5] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(10.0F/16.0F, 6.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
			}
			else
			{
				renderer.setOverrideBlockTexture(this.woodIcon);
				block.setBlockBounds(4.0F/16.0F, 4.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				
				if (conected[0] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(4.0F/16.0F, 0.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[1] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 16.0F/16.0F, 12.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[2] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(4.0F/16.0F, 4.0F/16.0F, 0.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				if (conected[3] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(4.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 16.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[4] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(0.0F/16.0F, 4.0F/16.0F, 4.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
				
				if (conected[5] == 1)
				{
					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(12.0F/16.0F, 4.0F/16.0F, 4.0F/16.0F, 16.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
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
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockUmeWood.renderID;
	}

}
