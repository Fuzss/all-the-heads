package fuzs.alltheheads.common.data.loot;

import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {

    public ModBlockLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.add(ModRegistry.MOB_HEAD_BLOCK.value(), this::createHeadDrop);
    }

    @Override
    public final LootTable.Builder createHeadDrop(Block block) {
        // explosion condition is not applied on purpose; all vanilla heads are explosion-resistant
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                                        .include(DataComponents.NOTE_BLOCK_SOUND)
                                        .include(DataComponents.CUSTOM_NAME)
                                        .include(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value())))
                        .unwrap());
    }
}
