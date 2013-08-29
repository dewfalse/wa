package wa;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GuiBrewingBarrel extends GuiContainer {

	TileEntityBrewingBarrel inventory;

	public GuiBrewingBarrel(InventoryPlayer inventory,
			TileEntityBrewingBarrel tile) {
		super(new ContainerBrewingBarrel(inventory, tile));
		this.inventory = tile;
		ySize = 165;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/mods/wa/textures/brewingBarrel.png");
		short h = 165;
		int var4 = h - 108;
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (inventory.isBrewing()) {
			int l = inventory.getProcessTimeRemainingSc(12);
			//drawTexturedModalRect(x + 86, y + 45 - l, 176, 12 - l, 14, l + 2);
		}

		int i1 = inventory.getProgressSc(21);
		drawTexturedModalRect(x + 79, y + 55 - i1, 176, 21 - i1, 18, 21);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString("Brewing Barrel", 58, 5, 4210752);
	}

}
