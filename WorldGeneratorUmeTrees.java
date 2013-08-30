package wa;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

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
			int blockID = par1World.getBlockId(par3, j, par5);

			Block block = Block.blocksList[blockID];

			if (blockID != 0 && !block.isLeaves(par1World, par3, j, par5)
					&& blockID != Block.grass.blockID
					&& blockID != Block.dirt.blockID
					&& blockID != Blocks.umeSapling.blockID
					&& !block.isWood(par1World, par3, j, par5)) {
				flag = false;
			}

		}
		if (!flag) {
			return false;
		} else {
			par1World.setBlock(par3, par4, par5, 0, 0, 4);
			int blockID = par1World.getBlockId(par3, par4 - 1, par5);
			Block soil = Block.blocksList[blockID];
			boolean isSoil = (soil != null && soil.canSustainPlant(par1World,
					par3, par4 - 1, par5, ForgeDirection.UP,
					(BlockSapling) Blocks.umeSapling));

			if (isSoil) {
				soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4,
						par5);
				for (int j = 0; j < 3; ++j) {
					blockID = par1World.getBlockId(par3, par4 + j, par5);
					Block block = Block.blocksList[blockID];
					if (blockID == 0 || block == null) {
						this.setBlockAndMetadata(par1World, par3, par4 + j,
								par5, Blocks.umeWood.blockID, 0);
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
					blockID = par1World.getBlockId(x, y, z);
					Block block = Block.blocksList[blockID];
					if (blockID == 0 || block == null) {
						this.setBlockAndMetadata(par1World, x, y, z,
								Blocks.umeWood.blockID, this.metaWood);
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}
}
