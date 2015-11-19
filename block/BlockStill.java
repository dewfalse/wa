package wa.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wa.Config;
import wa.Wa;

import java.util.ArrayList;

/**
 * Created by dew on 2015/11/11.
 */
public class BlockStill extends BlockContainer {
    public static int renderID;

    public BlockStill(Material material) {
        super(material);
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
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        // 鉄製だけど素手で回収可
        return true;
    }

    @Override
    public int getRenderType() {
        return renderID;
    }

    @Override
    public void onPostBlockPlaced(World worldObj, int x, int y, int z, int metadata) {
        for(ForgeDirection d : new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}) {
            // 周囲のブロックをチェック
            Block block = worldObj.getBlock(x + d.offsetX, y + d.offsetY, z + d.offsetZ);
            int meta = worldObj.getBlockMetadata(x + d.offsetX, y + d.offsetY, z + d.offsetZ);

            // メタデータで接続を表現する(0:未接続, 1:蒸留元, 2:NORTHが蒸留元, , 3:EASTが蒸留元, , 4:SOUTHが蒸留元, , 5:WESTが蒸留元,
            if(block == Blocks.still && meta == 0) {
                // 未接続の蒸留器があったので接続する
                int c = 0;
                if(d == ForgeDirection.NORTH) c = 4;
                if(d == ForgeDirection.EAST) c = 5;
                if(d == ForgeDirection.SOUTH) c = 2;
                if(d == ForgeDirection.WEST) c = 3;
                worldObj.setBlockMetadataWithNotify(x + d.offsetX, y + d.offsetY, z + d.offsetZ, c, 3);
                worldObj.setBlockMetadataWithNotify(x, y, z, 1, 3);
                return;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityStill();
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4,
                           Block par5, int par6) {

        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if(tile instanceof TileEntityStill) {
            TileEntityStill still = (TileEntityStill)tile;
            for(int i = 0; i < still.getSizeInventory(); ++i) {
                ItemStack itemStack = still.getStackInSlot(i);
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
        // 自分が受けフラスコ側なら蒸留元を壊す
        int metadata = par6;
        if(metadata == 1) {
            for(ForgeDirection d : new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}) {
                Block block = par1World.getBlock(par2+d.offsetX, par3+d.offsetY, par4+d.offsetZ);
                int meta = par1World.getBlockMetadata(par2+d.offsetX, par3+d.offsetY, par4+d.offsetZ);
                if(block != null && Blocks.still == block && meta >= 2) {
                    boolean isPair = false;
                    if(meta == 2 && d == ForgeDirection.SOUTH) isPair = true;
                    if(meta == 3 && d == ForgeDirection.WEST) isPair = true;
                    if(meta == 4 && d == ForgeDirection.NORTH) isPair = true;
                    if(meta == 5 && d == ForgeDirection.EAST) isPair = true;
                    if(isPair) {
                        block.dropBlockAsItem(par1World, par2+d.offsetX, par3+d.offsetY, par4+d.offsetZ, meta, 0);
                        par1World.setBlockToAir(par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);
                        break;
                    }
                }
            }
        }
        if(metadata >= 2) {
            ForgeDirection d = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST}[metadata-2];
            Block block = par1World.getBlock(par2+d.offsetX, par3+d.offsetY, par4+d.offsetZ);
            int meta = par1World.getBlockMetadata(par2+d.offsetX, par3+d.offsetY, par4+d.offsetZ);
            if(block != null && block == Blocks.still) {
                //block.breakBlock(par1World, par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ, block, meta);
                block.dropBlockAsItem(par1World, par2+d.offsetX, par3+d.offsetY, par4+d.offsetZ, meta, 0);
                par1World.setBlockToAir(par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);
            }
        }

    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3,
                                    int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
                                    float par8, float par9) {

        if (par1World.isRemote) {
            return true;
        }

        int metadata = par1World.getBlockMetadata(par2, par3, par4);
        if(metadata == 1) {
            // 蒸留元側
            TileEntityStill self = (TileEntityStill) par1World.getTileEntity(par2, par3, par4);
            if(self != null) {
                par5EntityPlayer.openGui(Wa.instance, Config.蒸留器GUIID, par1World, par2, par3, par4);
            }
        }
        else {
            // 受けフラスコ側
            ForgeDirection d = ForgeDirection.UNKNOWN;
            if(metadata == 2) d = ForgeDirection.NORTH;
            if(metadata == 3) d = ForgeDirection.EAST;
            if(metadata == 4) d = ForgeDirection.SOUTH;
            if(metadata == 5) d = ForgeDirection.WEST;
            // 周囲のブロックをチェック
            Block block = par1World.getBlock(par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);
            int meta = par1World.getBlockMetadata(par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);
            if(block == Blocks.still && meta == 1) {
                // 蒸留元を発見
                TileEntity tile = par1World.getTileEntity(par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);

                if (tile != null) {
                    par5EntityPlayer.openGui(Wa.instance, Config.蒸留器GUIID, par1World, par2 + d.offsetX, par3 + d.offsetY, par4 + d.offsetZ);
                }
            }
        }

        return true;
    }
}
