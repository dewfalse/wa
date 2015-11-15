package wa.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import wa.FluidInit;
import wa.item.Items;

/**
 * Created by dew on 2015/11/11.
 */
public class BlockSpiritLamp extends BlockContainer {
    public static int renderID;

    // アルコールランプ
    public BlockSpiritLamp(Material material) {
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
    public int getRenderType() {
        return renderID;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySpiritLamp();
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null) {
            TileEntitySpiritLamp tileEntitySpiritLamp = (TileEntitySpiritLamp) tile;
            if (tileEntitySpiritLamp.getIgnited()) {
                return 13;
            }
        }
        return super.getLightValue(world, x, y, z);
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
            TileEntitySpiritLamp tileEntitySpiritLamp = (TileEntitySpiritLamp)tile;

            ItemStack itemStack = par5EntityPlayer.getHeldItem();
            FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(itemStack);

            if(fluidStack != null && fluidStack.getFluid() == FluidRegistry.getFluid("wa.fluid.alcohol")) {
                // 手持ちのアイテムがアルコールなら投入する
                tileEntitySpiritLamp.fill(ForgeDirection.UNKNOWN, fluidStack, true);
            }
            else if(itemStack.getItem() == Items.flint_and_steel && !tileEntitySpiritLamp.getIgnited()) {
                // 火打ち石と打ち金なら着火
                tileEntitySpiritLamp.setIgnited(!tileEntitySpiritLamp.getIgnited());
            }
            else if(itemStack == null && tileEntitySpiritLamp.getIgnited() ) {
                // 素手なら消火
                tileEntitySpiritLamp.setIgnited(!tileEntitySpiritLamp.getIgnited());
            }
            else {
                return true;
            }
            par1World.markBlockForUpdate(par2, par3, par4);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public String getItemIconName()
    {
        return "wa:spiritLamp";
    }
}
