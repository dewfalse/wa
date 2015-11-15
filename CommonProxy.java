package wa;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import wa.block.*;
import wa.client.GuiBrewingBarrelII;
import wa.client.GuiSqueezer;
import wa.client.GuiStill;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
        if(ID == Config.醸造樽GUIID) {
            TileEntity e = world.getTileEntity(x, y, z);
            if(e instanceof TileEntityBrewingBarrelII) {
                return new ContainerBrewingBarrelII(player, (TileEntityBrewingBarrelII) e);
            }
        }
        if(ID == Config.圧搾機GUIID) {
            TileEntity e = world.getTileEntity(x, y, z);
            if(e instanceof TileEntitySqueezer) {
                return new ContainerSqueezer(player, (TileEntitySqueezer) e);
            }
        }
        if(ID == Config.蒸留器GUIID) {
            TileEntity e = world.getTileEntity(x, y, z);
            if(e instanceof TileEntityStill) {
                return new ContainerStill(player, (TileEntityStill) e);
            }
        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
        if(ID == Config.醸造樽GUIID) {
            TileEntity e = world.getTileEntity(x, y, z);
            if(e instanceof TileEntityBrewingBarrelII) {
                return new GuiBrewingBarrelII(player, (TileEntityBrewingBarrelII) e);
            }
        }
        if(ID == Config.圧搾機GUIID) {
            TileEntity e = world.getTileEntity(x, y, z);
            if(e instanceof TileEntitySqueezer) {
                return new GuiSqueezer(player, (TileEntitySqueezer) e);
            }
        }
        if(ID == Config.蒸留器GUIID) {
            TileEntity e = world.getTileEntity(x, y, z);
            if(e instanceof TileEntityStill) {
                TileEntityStill still = (TileEntityStill)e;
                ForgeDirection dir = still.getPairDir();
                TileEntityStill connected = dir == ForgeDirection.UNKNOWN ? null : (TileEntityStill) world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
                return new GuiStill(player, still, connected);
            }
        }
		return null;
	}

	public void init() {
		// TODO 自動生成されたメソッド・スタブ
		
		// defeatedcrow追加物
		GameRegistry.registerTileEntity(TileEntityZabuton.class, "wa.tileentityZabuton");
		GameRegistry.registerTileEntity(TileEntityKoto.class, "wa.tileentityKoto");
	}

	public World getClientWorld() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void addStat(Achievement ach, int i) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void preInit() {
		// TODO 自動生成されたメソッド・スタブ

	}
	
	/* defeatedcrow追加 */
	public void registerFluidTex() {
		
	}

}
