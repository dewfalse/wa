package wa.api;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/*
 * 醸造レシピ。
 * 
 * 暫定版の仕様：
 * ・主材料・副材料・水バケツを入れた時点で醸造が開始される。
 *  （バケツを最後に入れないと、材料を入れきらないうちに醸造が開始してしまうかも。）
 * ・規定日数経過で、アウトプットの液体ができる。（液体自体にはグレード情報無し）
 * ・水入り瓶をつかって酒を瓶にくんだときに、材料や日数に応じてグレード判定が生じて、酒瓶アイテムにグレード情報（NBT）を載せる。
 *  グレードは酒瓶の飲食効果の発生時にボーナスが付く。
 *            
 */
public interface IWaBrewingRecipe {
	
	/*
	 * 主材料。必ず入れるもの
	 */
	Object getInput();
	
	/*
	 * 副材料。これも何かしらは入れる
	 */
	Object getSecondInput();

	/*
	 * できた酒の液体
	 */
	FluidStack getOutput();

	/*
	 * Object型からItemStack型に変換したもの
	 */
	List<ItemStack> getProcessedInput();
	
	List<ItemStack> getSecondProcessedInput();

	/*
	 * 必要個数
	 */
	int getInputRequire();
	
	int getSecondRequire();

	/*
	 * 醸造可能判定
	 */
	boolean matches(ItemStack[] items);

	/*
	 * 必要Tick
	 */
	int getBrewingTime();
	
	/*
	 * グレードの判定メソッド
	 */
	int getOutputGrade(TileEntity tile, int inputNum, int secondNum);

}
