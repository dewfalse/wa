package wa;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import wa.block.Blocks;

import java.util.Set;

public class ItemKanazuchi extends ItemTool {
	static final Set blocks = Sets.newHashSet(new Block[]{Blocks.kera});

	public ItemKanazuchi(Item.ToolMaterial material) {
		super(0, material, blocks);
	}

}
