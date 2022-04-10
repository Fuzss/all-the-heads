package fuzs.alltheheads.handler;

import com.google.common.collect.Lists;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.resources.SkullManager;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class MobLootHandler {
    @SubscribeEvent
    public void onLivingDrops(final LivingDropsEvent evt) {
        LivingEntity target = evt.getEntityLiving();
        SkullManager.INSTANCE.getSkullTypeByEntity(target.getType()).ifPresent(skullTypes -> {
            for (ModSkullType skullType : skullTypes) {
                if (skullType.dropsFromChargedCreepers() && skullType.matchesNbtVariant(target)) {
                    this.dropCustomDeathLoot(target, evt.getSource(), skullType);
                    break;
                }
            }
        });
    }

    private void dropCustomDeathLoot(LivingEntity target, DamageSource source, ModSkullType skullType) {
        Entity entity = source.getEntity();
        if (entity instanceof Creeper creeper) {
            if (creeper.canDropMobsSkull()) {
                creeper.increaseDroppedSkulls();
                target.spawnAtLocation(skullType.item.get());
            }
        }
    }

    @SubscribeEvent
    public void onLivingVisibility(LivingEvent.LivingVisibilityEvent evt) {
        if (evt.getLookingEntity() != null) {
            ItemStack helmet = evt.getEntityLiving().getItemBySlot(EquipmentSlot.HEAD);
            SkullManager.INSTANCE.getSkullTypeByEntity(evt.getLookingEntity().getType()).ifPresent(skullTypes -> {
                for (ModSkullType skullType : skullTypes) {
                    if (skullType.worksAsMobDisguise() && helmet.is(skullType.item.get()) && skullType.matchesNbtVariant(evt.getLookingEntity())) {
                        evt.modifyVisibility(evt.getVisibilityModifier() * 0.5);
                        break;
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(final LootTableLoadEvent evt) {
        SkullManager.INSTANCE.getSkullTypeByLootTable(evt.getName()).ifPresent(skullTypes -> {
            LootPool.Builder builder = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F));
            if (skullTypes.isEmpty()) {
                throw new IllegalStateException("List cannot possibly be empty");
            } else if (skullTypes.size() == 1) {
                ModSkullType skullType = skullTypes.get(0);
                builder.add(LootItem.lootTableItem(skullType.block.get()))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(skullType.getDropRate(), skullType.getLootingBonus()));
            } else {
                List<LootPoolEntryContainer.Builder<?>> builders = Lists.newArrayList();
                for (ModSkullType skullType : skullTypes) {
                    builders.add(LootItem.lootTableItem(skullType.block.get())
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(skullType.getDropRate(), skullType.getLootingBonus()))
                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity()
                                    .nbt(new NbtPredicate(skullType.getNbtPredicate())))));
                }
                builder.add(AlternativesEntry.alternatives(builders.toArray(LootPoolEntryContainer.Builder<?>[]::new)));
            }
            evt.getTable().addPool(builder.build());
        });
    }
}
