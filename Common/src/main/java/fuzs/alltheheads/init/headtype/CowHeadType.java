package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.cow.CowVariants;
import net.minecraft.world.entity.animal.cow.MushroomCow;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class CowHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_COW = register("cow/temperate");
    public static final ResourceKey<HeadType> WARM_COW = register("cow/warm");
    public static final ResourceKey<HeadType> COLD_COW = register("cow/cold");
    public static final ResourceKey<HeadType> RED_MOOSHROOM = register("mooshroom/red");
    public static final ResourceKey<HeadType> BROWN_MOOSHROOM = register("mooshroom/brown");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapCow(context,
                CowVariants.TEMPERATE,
                TEMPERATE_COW,
                ModelType.TEMPERATE_COW,
                "entity/cow/temperate_cow");
        bootstrapCow(context, CowVariants.WARM, WARM_COW, ModelType.WARM_COW, "entity/cow/warm_cow");
        bootstrapCow(context, CowVariants.COLD, COLD_COW, ModelType.COLD_COW, "entity/cow/cold_cow");
        bootstrapMooshroom(context, MushroomCow.Variant.RED, RED_MOOSHROOM, "entity/cow/red_mooshroom");
        bootstrapMooshroom(context, MushroomCow.Variant.BROWN, BROWN_MOOSHROOM, "entity/cow/brown_mooshroom");
    }

    private static void bootstrapCow(BootstrapContext<HeadType> context, ResourceKey<CowVariant> variant, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder(EntityType.COW)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                                    context.lookup(Registries.COW_VARIANT).getOrThrow(variant)))
                            .build());
                })
                .shape(8.0, 8.0, 6.0)
                .model(modelType, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapMooshroom(BootstrapContext<HeadType> context, MushroomCow.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.MOOSHROOM)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.MOOSHROOM_VARIANT, variant))
                            .build());
                })
                .shape(8.0, 8.0, 6.0)
                .model(ModelType.TEMPERATE_COW, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        // Cows
        translationConsumer.accept(TEMPERATE_COW, "Temperate Cow Head");
        translationConsumer.accept(WARM_COW, "Warm Cow Head");
        translationConsumer.accept(COLD_COW, "Cold Cow Head");

        // Mooshrooms
        translationConsumer.accept(RED_MOOSHROOM, "Red Mooshroom Head");
        translationConsumer.accept(BROWN_MOOSHROOM, "Brown Mooshroom Head");
    }
}
