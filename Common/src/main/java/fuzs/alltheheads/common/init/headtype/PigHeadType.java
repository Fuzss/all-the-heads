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
import net.minecraft.world.entity.animal.pig.PigSoundVariants;
import net.minecraft.world.entity.animal.pig.PigVariant;
import net.minecraft.world.entity.animal.pig.PigVariants;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class PigHeadType {
    public static final ResourceKey<HeadType> TEMPERATE_PIG = register("pig/temperate");
    public static final ResourceKey<HeadType> WARM_PIG = register("pig/warm");
    public static final ResourceKey<HeadType> COLD_PIG = register("pig/cold");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapPig(context, TEMPERATE_PIG, "entity/pig/pig_temperate");
        bootstrapPig(context, WARM_PIG, "entity/pig/pig_warm");
        bootstrapPig(context, COLD_PIG, "entity/pig/pig_cold");
    }

    private static void bootstrapPig(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PIG, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.PIG_SOUNDS.get(PigSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .ambientSound())
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapPig(context, PigVariants.TEMPERATE, TEMPERATE_PIG);
        bootstrapPig(context, PigVariants.WARM, WARM_PIG);
        bootstrapPig(context, PigVariants.COLD, COLD_PIG);
    }

    private static void bootstrapPig(BootstrapContext<LootItemCondition> context, ResourceKey<PigVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.PIG, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.PIG_VARIANT,
                            context.lookup(Registries.PIG_VARIANT).getOrThrow(variant)))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(TEMPERATE_PIG, "Temperate Pig Head");
        translationConsumer.accept(WARM_PIG, "Warm Pig Head");
        translationConsumer.accept(COLD_PIG, "Cold Pig Head");
    }
}
