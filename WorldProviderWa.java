package wa;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderWa extends WorldProvider {

	@Override
	public String getDimensionName() {
		return "Wa";
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderWa(worldObj, worldObj.getSeed(), true);
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		int i = worldObj.getFirstUncoveredBlock(par1, par2);

		if (i == 0) {
			return false;
		} else {
			return Block.blocksList[i].blockMaterial.blocksMovement();
		}
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public float getCloudHeight() {
		return 128.0F;
	}

	@Override
	public boolean isSkyColored() {
		return true;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(0, 64, 0);
	}

	@Override
	public int getAverageGroundLevel() {
		return 64;
	}

	@Override
	protected void registerWorldChunkManager() {
		worldChunkMgr =new WorldChunkManagerWa(worldObj);
		this.dimensionId = Config.dimensionID;
		hasNoSky = false;
	}

}
