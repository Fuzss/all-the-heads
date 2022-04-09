package fuzs.alltheheads.handler;

import fuzs.alltheheads.registry.SkullType;
import fuzs.alltheheads.registry.SkullManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobLootHandler {
    @SubscribeEvent
    public void onLivingDrops(final LivingDropsEvent evt) {
        LivingEntity target = evt.getEntityLiving();
        SkullManager.INSTANCE.getSkullTypeByEntity(target.getType()).ifPresent(skullType -> {
            if (skullType.dropsFromChargedCreepers()) {
                this.dropCustomDeathLoot(target, evt.getSource(), skullType);
            }
        });
    }

    private void dropCustomDeathLoot(LivingEntity target, DamageSource source, SkullType skullType) {
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
            SkullManager.INSTANCE.getSkullTypeByEntity(evt.getLookingEntity().getType()).ifPresent(skullType -> {
                if (skullType.worksAsMobDisguise() && helmet.is(skullType.item.get())) {
                    evt.modifyVisibility(evt.getVisibilityModifier() * 0.5);
                }
            });
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(final LootTableLoadEvent evt) {
        SkullManager.INSTANCE.getSkullTypeByLootTable(evt.getName()).ifPresent(skullType -> {
            if (skullType.getDropRate() > 0.0F || skullType.getLootingBonus() > 0.0F)
            evt.getTable().addPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(skullType.block.get()))
                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(skullType.getDropRate(), skullType.getLootingBonus()))
                    .build());
        });
    }
}
