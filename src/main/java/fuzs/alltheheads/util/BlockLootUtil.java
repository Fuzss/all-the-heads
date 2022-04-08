package fuzs.alltheheads.util;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootTable;

public class BlockLootUtil extends BlockLoot {
    public static LootTable.Builder createSingleItemTable(ItemLike p_124127_) {
        return BlockLoot.createSingleItemTable(p_124127_);
    }
}
