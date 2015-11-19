package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wa.Config;
import wa.Wa;

/**
 * Created by dew on 2015/11/11.
 */
public class BlockSqueezer extends BlockContainer {

    public static int renderID;
    IIcon[] icons;

    public BlockSqueezer() {
        super(Material.wood);
        this.setHardness(0.3F);
        this.setResistance(15.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntitySqueezer();
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
    public int getRenderType() {
        return renderID;
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4,
                           Block par5, int par6) {
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if(tile instanceof TileEntitySqueezer) {
            TileEntitySqueezer squeezer = (TileEntitySqueezer)tile;
            for(int i = 0; i < squeezer.getSizeInventory(); ++i) {
                ItemStack itemStack = squeezer.getStackInSlot(i);
                if(itemStack != null) {
                    double x = par2 + par1World.rand.nextFloat() * 0.8F + 0.1F;
                    double y = par3 + par1World.rand.nextFloat() * 0.8F + 0.1F;
                    double z = par4 + par1World.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(par1World, x, y, z, itemStack);
                    par1World.spawnEntityInWorld(entityItem);
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3,
                                    int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
                                    float par8, float par9) {

        if (par1World.isRemote) {
            return true;
        }

        TileEntity tile = par1World.getTileEntity(par2, par3, par4);

        if (tile != null) {
            par5EntityPlayer.openGui(Wa.instance, Config.圧搾機GUIID, par1World, par2, par3, par4);
        }

        return true;
    }
}
