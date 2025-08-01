package fuzs.alltheheads.handler;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.ModSkullBlockItem;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collection;

public class HeadLootHandler {

    public static EventResult onLivingDrops(LivingEntity livingEntity, DamageSource damageSource, Collection<ItemEntity> itemDrops, boolean recentlyHit) {
        if (damageSource.getEntity() instanceof Creeper creeper && creeper.canDropMobsSkull()) {
            livingEntity.registryAccess()
                    .lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                    .listElements()
                    .forEach(headType -> {
                        if (headType.value().loot().chargedCreeperDrop() && headType.value().matches(livingEntity)) {
                            ItemStack itemStack = ModSkullBlockItem.createHead(headType);
                            livingEntity.spawnAtLocation((ServerLevel) livingEntity.level(), itemStack);
                            creeper.increaseDroppedSkulls();
                        }
                    });
        }

        return EventResult.PASS;
    }

    public static void onLootTableLoad(ResourceLocation resourceLocation, LootTable.Builder lootTable, HolderLookup.Provider registries) {

    }

//    @SubscribeEvent
//    public void onLootTableLoad(final LootTableLoadEvent evt) {
//        SkullManager.INSTANCE.getSkullTypeByLootTable(evt.getName()).ifPresent(skullTypes -> {
//            LootPool.Builder builder = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F));
//            if (skullTypes.isEmpty()) {
//                throw new IllegalStateException("List cannot possibly be empty");
//            } else if (skullTypes.size() == 1) {
//                ModSkullType skullType = skullTypes.get(0);
//                builder.add(LootItem.lootTableItem(skullType.block.get()))
//                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
//                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(skullType.getDropRate(),
//                                skullType.getLootingBonus()));
//            } else {
//                List<LootPoolEntryContainer.Builder<?>> builders = Lists.newArrayList();
//                for (ModSkullType skullType : skullTypes) {
//                    builders.add(LootItem.lootTableItem(skullType.block.get())
//                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
//                            .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(skullType.getDropRate(),
//                                    skullType.getLootingBonus()))
//                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
//                                    EntityPredicate.Builder.entity()
//                                            .nbt(new NbtPredicate(skullType.getNbtPredicate())))));
//                }
//                builder.add(AlternativesEntry.alternatives(builders.toArray(LootPoolEntryContainer.Builder<?>[]::new)));
//            }
//            evt.getTable().addPool(builder.build());
//        });
//    }
}
