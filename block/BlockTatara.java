package wa.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wa.item.Items;

import java.util.Random;

//たたら製鉄システム
//たたら製鉄は砂＋鉄ブロック＋木炭ブロックで製鉄炉ブロックを作成する
//製鉄炉ブロックを3x3x3設置し着火する
//着火中は炎のエフェクトがでる
//燃え尽きると製鉄炉ブロックを壊してケラブロックが得られる
public class BlockTatara extends BlockContainer {

	// メタデータ0:単一ブロックとして設置された
	// メタデータ1:たたら製鉄炉の複合ブロックに変化した
	// メタデータ2:たたら製鉄炉に火が入った
	// メタデータ3:たたら製鉄が終わった

	private IIcon[] icons;

	public BlockTatara(Material par2Material) {
		super(par2Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityTatara();
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		ItemStack eq = par5EntityPlayer.getCurrentEquippedItem();
		if(eq != null && eq.getItem() == Items.flint_and_steel) {

			int metadata = par1World.getBlockMetadata(par2, par3, par4);
			if(metadata == 1) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
				TileEntityTatara tile = (TileEntityTatara) par1World.getTileEntity(par2, par3, par4);
				tile.burnTime = 3600;
			}
		}

		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		if(world.getBlockMetadata(x, y, z) == 2) {
			return 12;
		}
		return super.getLightValue(world, x, y, z);
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return icons[par2];
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[4];
		for(int i = 0; i < icons.length; ++i) {
			icons[i] = par1IconRegister.registerIcon("wa:tatara" + String.valueOf(i));
		}
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		TileEntityTatara tile = (TileEntityTatara) par1World.getTileEntity(par2, par3, par4);
		if(tile.isBurning()) {
			for(int i = 0; i < 4; ++i) {
				double d0 = (double) ((float) par2 + par5Random.nextFloat());
				double d1 = (double) ((float) par3 + par5Random.nextFloat());
				double d2 = (double) ((float) par4 + par5Random.nextFloat());
				double d3 = 0.0D;
				double d4 = 0.0D;
				double d5 = 0.0D;
				par1World.spawnParticle("flame", d0, d1, d2, d3, d4, d5);
			}
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		if(par1 != 3) {
			return Item.getItemFromBlock(this);
		}
		return Item.getItemFromBlock(Blocks.kera);
	}

	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, par1World.rand, par7) != Item.getItemFromBlock(this))
        {
            int j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }

}
