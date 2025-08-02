package fuzs.alltheheads.data.loot;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModEntityLootProvider extends AbstractLootProvider.Simple {

    public ModEntityLootProvider(DataProviderContext context) {
        super(LootContextParamSets.ENTITY, context);
    }

    @Override
    public void addLootTables() {
        this.registries().lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY).listElements().forEach(this::dropHead);
    }

    public final void dropHead(Holder.Reference<HeadType> headType) {
        headType.value()
                .loot()
                .lootTable()
                .ifPresent((ResourceKey<LootTable> resourceKey) -> this.add(resourceKey,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(ModRegistry.MOB_HEAD_ITEM.value()))
                                        .apply(SetComponentsFunction.setComponent(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(),
                                                headType))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(
                                                this.registries(),
                                                0.025F,
                                                0.01F)))));
    }
}
