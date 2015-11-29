package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by dew on 2015/11/22.
 */
public class BlockWindChime extends Block {
    public static int renderID;
    private IIcon[] icons;

    public BlockWindChime(Material material) {
        super(material);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }

    @Override
    public int getRenderType() {
        return renderID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        Block upside = p_149742_1_.getBlock(p_149742_2_, p_149742_3_+1, p_149742_4_);
        Block downside = p_149742_1_.getBlock(p_149742_2_, p_149742_3_-1, p_149742_4_);
        return upside.getMaterial().isSolid() && downside.isAir(p_149742_1_, p_149742_2_, p_149742_3_-1, p_149742_4_);
    }

    @Override
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        if(p_149734_1_.isRemote) {
            if(p_149734_5_.nextInt(40) == 0) {
                p_149734_1_.playSound(p_149734_2_, p_149734_3_, p_149734_4_, "wa:wind_chime", 0.1F, 1.0F, false);
            }
        }
        super.randomDisplayTick(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_, p_149734_5_);
    }

    @Override
    public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
        Block upside = p_149718_1_.getBlock(p_149718_2_, p_149718_3_+1, p_149718_4_);
        Block downside = p_149718_1_.getBlock(p_149718_2_, p_149718_3_-1, p_149718_4_);
        return upside.getMaterial().isSolid() && downside.isAir(p_149718_1_, p_149718_2_, p_149718_3_-1, p_149718_4_);
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(p_149727_1_.isRemote) {
            if(p_149727_5_.getCurrentEquippedItem() == null) {
                float f = p_149727_7_ * p_149727_9_;
                p_149727_1_.playSound(p_149727_2_, p_149727_3_, p_149727_4_, "wa:wind_chime", 0.1F, f, false);
            }
        }
        return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    public String getItemIconName() {
        return "wa:wind_chime";
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if(p_149691_1_ == 0) return icons[0];
        if(p_149691_1_ == 1) return icons[1];
        if(p_149691_1_ == 6) return icons[3];
        return icons[2];
    }

    @Override
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
        if(p_149673_5_ == 0) return icons[0];
        if(p_149673_5_ == 1) return icons[1];
        return icons[2];
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        icons = new IIcon[4];
        icons[0] = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
        icons[1] = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        icons[2] = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        icons[3] = p_149651_1_.registerIcon(this.getTextureName() + "_strip");
    }

}
