package wa.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import wa.block.Blocks;

import java.util.Random;

public class WorldGeneratorUmeTrees extends WorldGenerator {

	private final int minTreeHeight;
	private final int metaWood;
	private final int metaLeaves;

	public WorldGeneratorUmeTrees(boolean par1) {
		this(par1, 2, 0, 0, false);
	}

	public WorldGeneratorUmeTrees(boolean par1, int par2, int par3, int par4,
			boolean par5) {
		super(par1);
		this.minTreeHeight = par2;
		this.metaWood = par3;
		this.metaLeaves = par4;
	}

	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {
		int l = par2Random.nextInt(3) + this.minTreeHeight;
		boolean flag = true;

		for (int j = 255; j >= Math.max(1, par4); --j) {
            Block block = par1World.getBlock(par3, j, par5);

			if (par1World.isAirBlock(par3, j, par5) == false && !block.isLeaves(par1World, par3, j, par5)
					&& block != Blocks.grass
					&& block != Blocks.dirt
					&& block != Blocks.umeSapling
					&& !block.isWood(par1World, par3, j, par5)) {
				flag = false;
			}

		}
		if (!flag) {
			return false;
		} else {
			par1World.setBlockToAir(par3, par4, par5);
            Block soil = par1World.getBlock(par3, par4 - 1, par5);
			boolean isSoil = (soil != null && soil.canSustainPlant(par1World,
					par3, par4 - 1, par5, ForgeDirection.UP,
					(BlockSapling) Blocks.umeSapling));

			if (isSoil) {
				soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4,
						par5);
				for (int j = 0; j < 3; ++j) {
					Block block = par1World.getBlock(par3, par4 + j, par5);
					if (par1World.isAirBlock(par3, par4 + j, par5) || block == null) {
						this.setBlockAndNotifyAdequately(par1World, par3, par4 + j,
								par5, Blocks.umeWood, 0);
					} else {
						return true;
					}
				}

				ForgeDirection[] dirs = {
						ForgeDirection.UP,
						par2Random.nextInt(2) == 0 ? ForgeDirection.WEST
								: ForgeDirection.EAST,
						par2Random.nextInt(2) == 0 ? ForgeDirection.NORTH
								: ForgeDirection.SOUTH };
				int x = par3;
				int y = par4 + 2;
				int z = par5;
				while (true) {
					if (l > 0) {
						--l;
					} else {
						if (par2Random.nextInt(5) == 0) {
							return true;
						}
					}
					ForgeDirection dir = dirs[par2Random.nextInt(dirs.length)];
					x += dir.offsetX;
					y += dir.offsetY;
					z += dir.offsetZ;
                    Block block = par1World.getBlock(x, y, z);
					if (par1World.isAirBlock(x, y, z) || block == null) {
						this.setBlockAndNotifyAdequately(par1World, x, y, z,
								Blocks.umeWood, this.metaWood);
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}
}
