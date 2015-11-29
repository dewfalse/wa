package wa.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import wa.block.ContainerStill;
import wa.block.TileEntityStill;

import java.util.ArrayList;

/**
 * Created by dew on 2015/11/14.
 */
public class GuiStill extends GuiContainer {

    private TileEntityStill tile;
    private TileEntityStill tileConnected;
    private TileEntityStill inventory;

    public GuiStill(EntityPlayer player, TileEntityStill par2TileEntity, TileEntityStill par3TileEntity) {
        super(new ContainerStill(player, par2TileEntity));
        this.tile = par2TileEntity;
        this.tileConnected = par3TileEntity;
        this.inventory = par2TileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        // インベントリ名の描画
        String s = this.inventory.hasCustomInventoryName() ? this.inventory.getInventoryName() : I18n.format(
                this.inventory.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
                4210752);
    }

    @Override
    public void drawScreen(int x, int y, float par3) {
        super.drawScreen(x, y, par3);

        // 液体情報
        boolean b1 = this.func_146978_c(51, 24, 16, 41, x, y);
        if (b1) {
            ArrayList<String> list2 = new ArrayList<String>();
            list2.add("Fluid : " + this.tile.productTank.getFluidName());
            list2.add("Amount : " + this.tile.productTank.getFluidAmount());
            list2.add("Grade : " + this.tile.getGrade());
            this.drawHoveringText(list2, x, y, fontRendererObj);
        }
        boolean b2 = this.func_146978_c(107, 24, 16, 41, x, y);
        if (b2) {
            if(this.tile.connected()) {
                ArrayList<String> list2 = new ArrayList<String>();
                list2.add("Fluid : " + this.tileConnected.productTank.getFluidName());
                list2.add("Amount : " + this.tileConnected.productTank.getFluidAmount());
                list2.add("Grade : " + this.tileConnected.getGrade());
                this.drawHoveringText(list2, x, y, fontRendererObj);
            }
        }
        boolean b3 = this.func_146978_c(71, 32, 32, 29, x, y);
        if (b3) {
            ArrayList<String> list2 = new ArrayList<String>();
            list2.add(this.tile.getAgingTime() + "/" + this.tile.getDistillingTime());
            this.drawHoveringText(list2, x, y, fontRendererObj);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("wa", "textures/still_gui.png"));

        // かまど描画処理
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        i1 = this.tile.getAgingProgress(33);
        this.drawTexturedModalRect(k + 71, l + 32, 176, 0, i1, 33);

        drawFluid(this.tile.productTank.getFluid(), this.tile.getFluidAmountScaled(41), k + 51, l + 24, 16, 41);
        drawFluid(this.tileConnected.productTank.getFluid(), this.tileConnected.getFluidAmountScaled(41), k + 107, l + 24, 16, 41);
    }

    /**
     * Original code was made by Shift02.
     */
    private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height) {
        if (fluid == null || fluid.getFluid() == null) {
            return;
        }

        ResourceLocation res = null;
        if (fluid.getFluid().getSpriteNumber() == 0) {
            res = TextureMap.locationBlocksTexture;
        } else {
            res = TextureMap.locationItemsTexture;
        }
        mc.getTextureManager().bindTexture(res);

        IIcon icon = fluid.getFluid().getIcon(fluid);
        if (icon == null)
            return;
        mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        setGLColorFromInt(fluid.getFluid().getColor(fluid));

        int widR = width;
        int heiR = level;
        int yR = y + (height - heiR);

        int widL = 0;
        int heiL = 0;

        for (int i = 0; i < widR; i += 16) {
            for (int j = 0; j < heiR; j += 16) {
                widL = Math.min(widR - i, 16);
                heiL = Math.min(heiR - j, 16);
                this.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widL, heiL);
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

    }

    public static void setGLColorFromInt(int color) {
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
    }

}
