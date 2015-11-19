package wa.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by dew on 2015/11/15.
 */
public class WaFluid extends Fluid {
    public WaFluid(String fluidName) {
        super(fluidName);
    }

    @Override
    public WaFluid setDensity(int density) {
        this.density = density;
        return this;
    }

    @Override
    public WaFluid setBlock(Block block) {
        super.setBlock(block);
        return this;
    }

    @Override
    public WaFluid setGaseous(boolean isGaseous) {
        super.setGaseous(isGaseous);
        return this;
    }

    @Override
    public WaFluid setLuminosity(int luminosity) {
        super.setLuminosity(luminosity);
        return this;
    }

    @Override
    public WaFluid setRarity(EnumRarity rarity) {
        super.setRarity(rarity);
        return this;
    }

    @Override
    public WaFluid setTemperature(int temperature) {
        super.setTemperature(temperature);
        return this;
    }

    @Override
    public WaFluid setUnlocalizedName(String unlocalizedName) {
        super.setUnlocalizedName(unlocalizedName);
        return this;
    }

    @Override
    public WaFluid setViscosity(int viscosity) {
        super.setViscosity(viscosity);
        return this;
    }


    public WaFluid registerFluid() {
        FluidRegistry.registerFluid(this);
        return this;
    }
    public WaFluid registerFluidContainer(int amount, ItemStack filledContainer, ItemStack emptyContainer) {
        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidName, amount),
                filledContainer,
                emptyContainer);
        return this;
    }


}
