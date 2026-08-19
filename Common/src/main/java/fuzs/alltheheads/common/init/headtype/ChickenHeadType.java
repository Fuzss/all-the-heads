package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.chicken.ChickenSoundVariants;
import net.minecraft.world.entity.animal.chicken.ChickenVariant;
import net.minecraft.world.entity.animal.chicken.ChickenVariants;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class ChickenHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_CHICKEN = register("chicken/temperate");
    public static final ResourceKey<HeadType> WARM_CHICKEN = register("chicken/warm");
    public static final ResourceKey<HeadType> COLD_CHICKEN = register("chicken/cold");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapChicken(context, TEMPERATE_CHICKEN, ModelType.CHICKEN, "entity/chicken/chicken_temperate");
        bootstrapChicken(context, WARM_CHICKEN, ModelType.CHICKEN, "entity/chicken/chicken_warm");
        bootstrapChicken(context, COLD_CHICKEN, ModelType.COLD_CHICKEN, "entity/chicken/chicken_cold");
    }

    private static void bootstrapChicken(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder()
                .shape(4.0, 6.0, 3.0)
                .scale(1.5)
                .model(modelType, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.CHICKEN_SOUNDS.get(ChickenSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .ambientSound())
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapChicken(context, ChickenVariants.TEMPERATE, TEMPERATE_CHICKEN);
        bootstrapChicken(context, ChickenVariants.WARM, WARM_CHICKEN);
        bootstrapChicken(context, ChickenVariants.COLD, COLD_CHICKEN);
    }

    private static void bootstrapChicken(BootstrapContext<LootItemCondition> context, ResourceKey<ChickenVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.CHICKEN, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.CHICKEN_VARIANT,
                            context.lookup(Registries.CHICKEN_VARIANT).getOrThrow(variant)))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(TEMPERATE_CHICKEN, "Temperate Chicken Head");
        translationConsumer.accept(WARM_CHICKEN, "Warm Chicken Head");
        translationConsumer.accept(COLD_CHICKEN, "Cold Chicken Head");
    }
}
