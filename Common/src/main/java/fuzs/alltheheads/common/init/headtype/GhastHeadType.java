package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.GhastPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class GhastHeadType {
    public static final ResourceKey<HeadType> GHAST = register("ghast");
    public static final ResourceKey<HeadType> CHARGING_GHAST = register("ghast/charging");
    public static final ResourceKey<HeadType> HAPPY_GHAST = register("happy_ghast");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapGhast(context, GHAST, "entity/ghast/ghast", SoundEvents.GHAST_AMBIENT);
        bootstrapGhast(context, CHARGING_GHAST, "entity/ghast/ghast_shooting", SoundEvents.GHAST_WARN);
        HeadType.builder()
                .shape(16.0, 16.0, 16.0)
                .scale(0.625)
                .model(ModelType.HAPPY_GHAST, Identifier.withDefaultNamespace("entity/ghast/happy_ghast"))
                .noteBlockSound(SoundEvents.HAPPY_GHAST_AMBIENT)
                .build(context, HAPPY_GHAST);
    }

    private static void bootstrapGhast(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(16.0, 16.0, 16.0)
                .scale(0.625)
                .model(ModelType.GHAST, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapGhast(context, false, GHAST);
        bootstrapGhast(context, true, CHARGING_GHAST);
        bootstrap(context, HAPPY_GHAST, EntityTypes.HAPPY_GHAST);
    }

    private static void bootstrapGhast(BootstrapContext<LootItemCondition> context, boolean charging, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.GHAST, (EntityPredicate.Builder builder) -> {
            builder.put(GhastPredicate.CODEC, GhastPredicate.isCharging(charging));
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(GHAST, "Ghast Head");
        translationConsumer.accept(CHARGING_GHAST, "Charging Ghast Head");
        translationConsumer.accept(HAPPY_GHAST, "Happy Ghast Head");
    }
}
