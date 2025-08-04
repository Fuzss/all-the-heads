package fuzs.alltheheads.init;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.config.CommonConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.HashMap;
import java.util.Map;

public class ModLootTables {
    private static final Map<ResourceLocation, ResourceKey<LootTable>> LOOT_TABLE_INJECTIONS = new HashMap<>();
    public static final ResourceKey<LootTable> ZOMBIE_INJECTION = registerLootTableInjection(EntityType.ZOMBIE);
    public static final ResourceKey<LootTable> SKELETON_INJECTION = registerLootTableInjection(EntityType.SKELETON);
    public static final ResourceKey<LootTable> CREEPER_INJECTION = registerLootTableInjection(EntityType.CREEPER);
    public static final ResourceKey<LootTable> PIGLIN_INJECTION = registerLootTableInjection(EntityType.PIGLIN);
    public static final ResourceKey<LootTable> ENDER_DRAGON_INJECTION = registerLootTableInjection(EntityType.ENDER_DRAGON);

    public static void bootstrap() {
        // NO-OP
    }

    private static ResourceKey<LootTable> registerLootTableInjection(EntityType<?> entityType) {
        return registerLootTableInjection(entityType.getDefaultLootTable().orElseThrow());
    }

    private static ResourceKey<LootTable> registerLootTableInjection(ResourceKey<LootTable> resourceKey) {
        ResourceKey<LootTable> newResourceKey = ModRegistry.REGISTRIES.makeResourceKey(Registries.LOOT_TABLE,
                "inject/" + resourceKey.location().toString().replace(':', '/'));
        LOOT_TABLE_INJECTIONS.put(resourceKey.location(), newResourceKey);
        return newResourceKey;
    }

    public static void onLootTableLoad(ResourceLocation resourceLocation, LootTable.Builder lootTable, HolderLookup.Provider registries) {
        if (!AllTheHeads.CONFIG.get(CommonConfig.class).vanillaHeadDrops) {
            return;
        }
        
        if (LOOT_TABLE_INJECTIONS.containsKey(resourceLocation)) {
            lootTable.withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(NestedLootTable.lootTableReference(LOOT_TABLE_INJECTIONS.get(resourceLocation))));
        }
    }
}
