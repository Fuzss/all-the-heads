package fuzs.alltheheads.data.loot;

import com.google.common.collect.ImmutableMap;
import fuzs.alltheheads.init.AnimalHeadTypes;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Map;
import java.util.function.BiConsumer;

public class ModEntityLootProvider extends AbstractLootProvider.Simple {
    private static final Map<ResourceKey<HeadType>, RandomChanceWithLooting> HEAD_TYPE_LOOT_DROP_CHANCES;

    static {
        ImmutableMap.Builder<ResourceKey<HeadType>, RandomChanceWithLooting> builder = ImmutableMap.builder();
        registerDropChance(AnimalHeadTypes.LUCY_AXOLOTL, 0.2F, 0.05F, builder::put);
        registerDropChance(AnimalHeadTypes.WILD_AXOLOTL, 0.2F, 0.05F, builder::put);
        registerDropChance(AnimalHeadTypes.GOLD_AXOLOTL, 0.2F, 0.05F, builder::put);
        registerDropChance(AnimalHeadTypes.CYAN_AXOLOTL, 0.2F, 0.05F, builder::put);
        registerDropChance(AnimalHeadTypes.BLUE_AXOLOTL, 1.0F, 0.0F, builder::put);
        HEAD_TYPE_LOOT_DROP_CHANCES = builder.build();
    }

    private static void registerDropChance(ResourceKey<HeadType> headType, float base, float perLevelAfterFirst, BiConsumer<ResourceKey<HeadType>, RandomChanceWithLooting> builder) {
        builder.accept(headType, new RandomChanceWithLooting(base, perLevelAfterFirst));
    }

    public ModEntityLootProvider(DataProviderContext context) {
        super(LootContextParamSets.ENTITY, context);
    }

    @Override
    public void addLootTables() {
        this.registries().lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY).listElements().forEach(this::dropHead);
    }

    public final void dropHead(Holder.Reference<HeadType> headType) {
        headType.value().loot().lootTable().ifPresent((ResourceKey<LootTable> resourceKey) -> {
            RandomChanceWithLooting randomChanceWithLooting = HEAD_TYPE_LOOT_DROP_CHANCES.getOrDefault(headType.key(),
                    RandomChanceWithLooting.DEFAULT);
            this.add(resourceKey,
                    LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1.0F))
                                    .add(LootItem.lootTableItem(ModRegistry.MOB_HEAD_ITEM.value()))
                                    .apply(SetComponentsFunction.setComponent(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(),
                                            headType))
                                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                            headType.value().entityPredicate()))
                                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                    .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(
                                            this.registries(),
                                            randomChanceWithLooting.base(),
                                            randomChanceWithLooting.perLevelAfterFirst()))));
        });
    }

    private record RandomChanceWithLooting(float base, float perLevelAfterFirst) {
        public static final RandomChanceWithLooting DEFAULT = new RandomChanceWithLooting(0.025F, 0.01F);
    }
}
