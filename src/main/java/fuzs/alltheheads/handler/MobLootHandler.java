package fuzs.alltheheads.handler;

import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.registry.SkullManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MobLootHandler {
    @SubscribeEvent
    public void onLivingDrops(final LivingDropsEvent evt) {
        LivingEntity target = evt.getEntityLiving();
        SkullManager.INSTANCE.getSkullTypeByEntity(target.getType()).ifPresent(skullType -> {
            if (skullType.dropsFromChargedCreepers()) {
                this.dropCustomDeathLoot(target, evt.getSource(), skullType);
            }
            // injecting into loot tables directly is preferable, just leaving this here in case loot tables don't work with some mod
//            if (evt.isRecentlyHit()) {
//                float dropChance = skullType.getDropRate() + evt.getLootingLevel() * skullType.getLootingBonus();
//                if (target.getRandom().nextFloat() < dropChance) {
//                    target.spawnAtLocation(skullType.item.get());
//                }
//            }
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
    public void onLootTableLoad(final LootTableLoadEvent evt) {
        SkullManager.INSTANCE.getSkullTypeByLootTable(evt.getName()).ifPresent(skullType -> {
            evt.getTable().addPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(skullType.block.get()))
                    .when(LootItemKilledByPlayerCondition.killedByPlayer())
                    .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(skullType.getDropRate(), skullType.getLootingBonus()))
                    .build());
        });
    }
}
