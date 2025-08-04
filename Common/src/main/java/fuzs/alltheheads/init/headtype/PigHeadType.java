package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.PigVariant;
import net.minecraft.world.entity.animal.PigVariants;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class PigHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_PIG = register("pig/temperate");
    public static final ResourceKey<HeadType> WARM_PIG = register("pig/warm");
    public static final ResourceKey<HeadType> COLD_PIG = register("pig/cold");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        PigHeadType.bootstrapPig(context, PigVariants.TEMPERATE, PigHeadType.TEMPERATE_PIG, "entity/pig/temperate_pig");
        PigHeadType.bootstrapPig(context, PigVariants.WARM, PigHeadType.WARM_PIG, "entity/pig/warm_pig");
        PigHeadType.bootstrapPig(context, PigVariants.COLD, PigHeadType.COLD_PIG, "entity/pig/cold_pig");
    }

    private static void bootstrapPig(BootstrapContext<HeadType> context, ResourceKey<PigVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.PIG)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.PIG_VARIANT,
                                    context.lookup(Registries.PIG_VARIANT).getOrThrow(variant)))
                            .build());
                })
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PIG, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.PIG_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(TEMPERATE_PIG, "Temperate Pig Head");
        translationConsumer.accept(WARM_PIG, "Warm Pig Head");
        translationConsumer.accept(COLD_PIG, "Cold Pig Head");
    }
}
