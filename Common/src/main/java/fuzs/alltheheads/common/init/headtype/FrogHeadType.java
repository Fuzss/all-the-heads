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
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.frog.FrogVariants;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class FrogHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_FROG = register("frog/temperate");
    public static final ResourceKey<HeadType> WARM_FROG = register("frog/warm");
    public static final ResourceKey<HeadType> COLD_FROG = register("frog/cold");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapFrog(context, TEMPERATE_FROG, "entity/frog/frog_temperate");
        bootstrapFrog(context, WARM_FROG, "entity/frog/frog_warm");
        bootstrapFrog(context, COLD_FROG, "entity/frog/frog_cold");
    }

    private static void bootstrapFrog(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(7.0, 5.0, 5.0)
                .scale(8.0 / 7.0)
                .model(ModelType.FROG, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.FROG_AMBIENT)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapFrog(context, FrogVariants.TEMPERATE, TEMPERATE_FROG);
        bootstrapFrog(context, FrogVariants.WARM, WARM_FROG);
        bootstrapFrog(context, FrogVariants.COLD, COLD_FROG);
    }

    private static void bootstrapFrog(BootstrapContext<LootItemCondition> context, ResourceKey<FrogVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.FROG, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.FROG_VARIANT,
                            context.lookup(Registries.FROG_VARIANT).getOrThrow(variant)))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(TEMPERATE_FROG, "Temperate Frog Head");
        translationConsumer.accept(WARM_FROG, "Warm Frog Head");
        translationConsumer.accept(COLD_FROG, "Cold Frog Head");
    }
}
