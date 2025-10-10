package fuzs.alltheheads.handler;

import com.google.common.base.Suppliers;
import fuzs.alltheheads.data.tags.ModHeadTypeTagsProvider;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.MobHeadItem;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HeadLootHandler {
    private static final Supplier<Map<ResourceLocation, TagKey<HeadType>>> HEAD_TAGS = Suppliers.memoize(() -> {
        return ModHeadTypeTagsProvider.getDefaultLootTables(BuiltInRegistries.ENTITY_TYPE.stream())
                .collect(Collectors.toMap(ResourceKey::location, ModHeadTypeTagsProvider::getHeadTypeTagKey));
    });

    public static EventResult onLivingDrops(LivingEntity livingEntity, DamageSource damageSource, Collection<ItemEntity> itemDrops, boolean recentlyHit) {
        if (livingEntity.level() instanceof ServerLevel serverLevel && livingEntity.shouldDropLoot()) {
            if (damageSource.getEntity() instanceof Creeper creeper && creeper.canDropMobsSkull()) {
                livingEntity.registryAccess()
                        .lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                        .listElements()
                        .forEach((Holder.Reference<HeadType> headType) -> {
                            if (headType.value().loot().chargedCreeperDrop() && headType.value()
                                    .matches(livingEntity)) {
                                ItemStack itemStack = MobHeadItem.createHead(headType);
                                livingEntity.spawnAtLocation(serverLevel, itemStack);
                                creeper.increaseDroppedSkulls();
                            }
                        });
            }
        }

        return EventResult.PASS;
    }

    public static void onLootTableLoad(ResourceLocation resourceLocation, LootTable.Builder lootTable, HolderLookup.Provider registries) {
        if (HEAD_TAGS.get().containsKey(resourceLocation)) {
            registries.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                    .get(HEAD_TAGS.get().get(resourceLocation))
                    .ifPresent((HolderSet.Named<HeadType> holderSet) -> {
                        holderSet.forEach((Holder<HeadType> headType) -> {
                            headType.value().loot().lootTable().ifPresent((ResourceKey<LootTable> resourceKey) -> {
                                // adding each one in as a separate pool allows for multiple heads to drop at once when conditions apply,
                                // which is not ideal, but wrapping all of them in an "alternatives" entry did not succeed
                                lootTable.withPool(LootPool.lootPool()
                                        .add(NestedLootTable.lootTableReference(resourceKey)
                                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                                        headType.value().entityPredicate()))));
                            });
                        });
                    });
        }
    }
}
