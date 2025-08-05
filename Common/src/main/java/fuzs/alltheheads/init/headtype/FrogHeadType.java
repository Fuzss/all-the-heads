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
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.entity.animal.frog.FrogVariants;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class FrogHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_FROG = register("frog/temperate");
    public static final ResourceKey<HeadType> WARM_FROG = register("frog/warm");
    public static final ResourceKey<HeadType> COLD_FROG = register("frog/cold");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapFrog(context, FrogVariants.TEMPERATE, TEMPERATE_FROG, "entity/frog/temperate_frog");
        bootstrapFrog(context, FrogVariants.WARM, WARM_FROG, "entity/frog/warm_frog");
        bootstrapFrog(context, FrogVariants.COLD, COLD_FROG, "entity/frog/cold_frog");
    }

    private static void bootstrapFrog(BootstrapContext<HeadType> context, ResourceKey<FrogVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.FROG)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.FROG_VARIANT,
                                    context.lookup(Registries.FROG_VARIANT).getOrThrow(variant)))
                            .build());
                })
                .shape(7.0, 5.0, 5.0)
                .scale(8.0 / 7.0)
                .model(ModelType.FROG, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.FROG_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(TEMPERATE_FROG, "Temperate Frog Head");
        translationConsumer.accept(WARM_FROG, "Warm Frog Head");
        translationConsumer.accept(COLD_FROG, "Cold Frog Head");
    }
}
