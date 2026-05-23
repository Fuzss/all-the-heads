package fuzs.alltheheads.handler;

import com.google.common.base.Suppliers;
import fuzs.alltheheads.data.tags.ModHeadTypeTagsProvider;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.MobHeadItem;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.core.Holder;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
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
                                livingEntity.spawnAtLocation(itemStack);
                                creeper.increaseDroppedSkulls();
                            }
                        });
            }

            // Our LootTableLoadCallback does not have tags loaded yet in 1.21.1, so we cannot use it.
            // Instead, drop heads manually during this event, with the loot conditions for the specific entity type being baked into the loot table.
            ResourceLocation resourceLocation = livingEntity.getType().getDefaultLootTable().location();
            if (HEAD_TAGS.get().containsKey(resourceLocation)) {
                serverLevel.registryAccess()
                        .lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                        .get(HEAD_TAGS.get().get(resourceLocation))
                        .ifPresent((HolderSet.Named<HeadType> holderSet) -> {
                            holderSet.forEach((Holder<HeadType> headType) -> {
                                headType.value().loot().lootTable().ifPresent((ResourceKey<LootTable> resourceKey) -> {
                                    // adding each one in as a separate pool allows for multiple heads to drop at once when conditions apply,
                                    // which is not ideal, but wrapping all of them in an "alternatives" entry did not succeed
                                    dropFromLootTable(serverLevel,
                                            livingEntity,
                                            resourceKey,
                                            damageSource,
                                            recentlyHit,
                                            (ItemStack itemStack) -> {
                                                ItemEntity itemEntity = new ItemEntity(serverLevel,
                                                        livingEntity.getX(),
                                                        livingEntity.getY(),
                                                        livingEntity.getZ(),
                                                        itemStack);
                                                itemEntity.setDefaultPickUpDelay();
                                                itemDrops.add(itemEntity);
                                            });
                                });
                            });
                        });
            }
        }

        return EventResult.PASS;
    }

    /**
     * @see LivingEntity#dropFromLootTable(DamageSource, boolean)
     */
    private static void dropFromLootTable(ServerLevel serverLevel, LivingEntity livingEntity, ResourceKey<LootTable> resourceKey, DamageSource damageSource, boolean hitByPlayer, Consumer<ItemStack> output) {
        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(resourceKey);
        LootParams.Builder builder = new LootParams.Builder(serverLevel).withParameter(LootContextParams.THIS_ENTITY,
                        livingEntity)
                .withParameter(LootContextParams.ORIGIN, livingEntity.position())
                .withParameter(LootContextParams.DAMAGE_SOURCE, damageSource)
                .withOptionalParameter(LootContextParams.ATTACKING_ENTITY, damageSource.getEntity())
                .withOptionalParameter(LootContextParams.DIRECT_ATTACKING_ENTITY, damageSource.getDirectEntity());
        if (hitByPlayer && livingEntity.getKillCredit() instanceof Player lastHurtByPlayer) {
            builder = builder.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, lastHurtByPlayer)
                    .withLuck(lastHurtByPlayer.getLuck());
        }

        LootParams lootParams = builder.create(LootContextParamSets.ENTITY);
        lootTable.getRandomItems(lootParams, livingEntity.getLootTableSeed(), output);
    }
}
