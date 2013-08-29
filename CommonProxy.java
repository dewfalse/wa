package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == Config.醸造樽GUIID) {
			TileEntity e = world.getBlockTileEntity(x, y, z);
			if(e instanceof TileEntityBrewingBarrel) {
				return new ContainerBrewingBarrel(player.inventory, (TileEntityBrewingBarrel) e);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == Config.醸造樽GUIID) {
			TileEntity e = world.getBlockTileEntity(x, y, z);
			if(e instanceof TileEntityBrewingBarrel) {
				return new GuiBrewingBarrel(player.inventory, (TileEntityBrewingBarrel) e);
			}
		}
		return null;
	}

	public void init() {
		// TODO 自動生成されたメソッド・スタブ

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

}
