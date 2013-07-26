package wa;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class ItemKamon extends Item {

	/*
	 * 全方位に貼り付けられるようにする
	 * 貼り付け済みの箇所にもう一回右クリックすると家紋が切り替わる
	 * 設置済みの場所にもう一回設置しようとしてもなにも起こらない
	 */

	public ItemKamon(int par1) {
		super(par1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 == 0)
        {
            return false;
        }
        else if (par7 == 1)
        {
            return false;
        }
        else
        {
            int i1 = Direction.facingToDirection[par7];
            EntityKamon entityKamon = new EntityKamon(par3World, par4, par5, par6, i1);

            if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
            {
                return false;
            }
            else
            {
                if (entityKamon != null && entityKamon.onValidSurface())
                {
                    if (!par3World.isRemote)
                    {
                        par3World.spawnEntityInWorld(entityKamon);
                    }

                    --par1ItemStack.stackSize;
                }

                return true;
            }
        }
    }

}
