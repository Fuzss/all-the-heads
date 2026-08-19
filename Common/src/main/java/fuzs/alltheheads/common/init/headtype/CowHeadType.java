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
import net.minecraft.world.entity.animal.cow.CowSoundVariants;
import net.minecraft.world.entity.animal.cow.CowVariant;
import net.minecraft.world.entity.animal.cow.CowVariants;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class CowHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_COW = register("cow/temperate");
    public static final ResourceKey<HeadType> WARM_COW = register("cow/warm");
    public static final ResourceKey<HeadType> COLD_COW = register("cow/cold");
    public static final ResourceKey<HeadType> RED_MOOSHROOM = register("mooshroom/red");
    public static final ResourceKey<HeadType> BROWN_MOOSHROOM = register("mooshroom/brown");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapCow(context, TEMPERATE_COW, ModelType.TEMPERATE_COW, "entity/cow/cow_temperate");
        bootstrapCow(context, WARM_COW, ModelType.WARM_COW, "entity/cow/cow_warm");
        bootstrapCow(context, COLD_COW, ModelType.COLD_COW, "entity/cow/cow_cold");
        bootstrapMooshroom(context, RED_MOOSHROOM, "entity/cow/mooshroom_red");
        bootstrapMooshroom(context, BROWN_MOOSHROOM, "entity/cow/mooshroom_brown");
    }

    private static void bootstrapCow(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 8.0, 6.0)
                .model(modelType, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_SOUNDS.get(CowSoundVariants.SoundSet.CLASSIC).ambientSound())
                .build(context, resourceKey);
    }

    private static void bootstrapMooshroom(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 8.0, 6.0)
                .model(ModelType.TEMPERATE_COW, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_SOUNDS.get(CowSoundVariants.SoundSet.CLASSIC).ambientSound())
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapCow(context, CowVariants.TEMPERATE, TEMPERATE_COW);
        bootstrapCow(context, CowVariants.WARM, WARM_COW);
        bootstrapCow(context, CowVariants.COLD, COLD_COW);
        bootstrapMooshroom(context, MushroomCow.Variant.RED, RED_MOOSHROOM);
        bootstrapMooshroom(context, MushroomCow.Variant.BROWN, BROWN_MOOSHROOM);
    }

    private static void bootstrapCow(BootstrapContext<LootItemCondition> context, ResourceKey<CowVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.COW, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                            context.lookup(Registries.COW_VARIANT).getOrThrow(variant)))
                    .build());
        });
    }

    private static void bootstrapMooshroom(BootstrapContext<LootItemCondition> context, MushroomCow.Variant variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.MOOSHROOM, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.MOOSHROOM_VARIANT, variant))
                    .build());
        });
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
