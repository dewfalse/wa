package wa;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wa.block.ContainerBrewingBarrelII;
import wa.block.TileEntityBrewingBarrelII;
import wa.block.TileEntityKoto;
import wa.block.TileEntityZabuton;
import wa.client.GuiBrewingBarrelII;

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
