package fuzs.alltheheads.init;

import fuzs.alltheheads.world.item.component.HeadType;
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
import net.minecraft.world.entity.animal.CowVariant;
import net.minecraft.world.entity.animal.CowVariants;

public class HeadTypes {
    public static final ResourceKey<HeadType> ENDERMAN = register("enderman");
    public static final ResourceKey<HeadType> BLAZE = register("blaze");
    public static final ResourceKey<HeadType> SPIDER = register("spider");
    public static final ResourceKey<HeadType> CAVE_SPIDER = register("cave_spider");
    public static final ResourceKey<HeadType> WITCH = register("witch");
    public static final ResourceKey<HeadType> SQUID = register("squid");
    public static final ResourceKey<HeadType> TEMPERATE_COW = register("cow/temperate");
    public static final ResourceKey<HeadType> WARM_COW = register("cow/warm");
    public static final ResourceKey<HeadType> COLD_COW = register("cow/cold");
    public static final ResourceKey<HeadType> OCELOT = register("ocelot");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        HeadType.builder(EntityType.ENDERMAN)
                .shape(8.0)
                .model(HeadType.ModelType.ENDERMAN,
                        ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman"))
                .model(HeadType.ModelType.ENDERMAN_EYES,
                        ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman_eyes"))
                .noteBlockSound(SoundEvents.ENDERMAN_AMBIENT)
                .build(context, ENDERMAN);
        HeadType.builder(EntityType.BLAZE)
                .shape(8.0)
                .model(HeadType.ModelType.MOB, ResourceLocationHelper.withDefaultNamespace("entity/blaze"))
                .noteBlockSound(SoundEvents.BLAZE_AMBIENT)
                .build(context, BLAZE);
        bootstrapCow(context,
                CowVariants.TEMPERATE,
                TEMPERATE_COW,
                HeadType.ModelType.TEMPERATE_COW,
                "entity/cow/temperate_cow");
        bootstrapCow(context, CowVariants.WARM, WARM_COW, HeadType.ModelType.WARM_COW, "entity/cow/warm_cow");
        bootstrapCow(context, CowVariants.COLD, COLD_COW, HeadType.ModelType.COLD_COW, "entity/cow/cold_cow");
        HeadType.builder(EntityType.OCELOT)
                .shape(5.0, 4.0)
                .scale(1.6)
                .model(HeadType.ModelType.OCELOT, ResourceLocationHelper.withDefaultNamespace("entity/cat/ocelot"))
                .noteBlockSound(SoundEvents.OCELOT_AMBIENT)
                .build(context, OCELOT);
    }

    private static void bootstrapCow(BootstrapContext<HeadType> context, ResourceKey<CowVariant> cowVariant, ResourceKey<HeadType> resourceKey, HeadType.ModelType modelType, String textureLocation) {
        HeadType.builder(EntityType.COW)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                                    (context.lookup(Registries.COW_VARIANT).getOrThrow(cowVariant))))
                            .build());
                })
                .shape(8.0, 8.0, 6.0)
                .model(modelType, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    private static ResourceKey<HeadType> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY, path);
    }
}