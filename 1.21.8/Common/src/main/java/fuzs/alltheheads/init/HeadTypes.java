package fuzs.alltheheads.init;

import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.CowVariants;
import net.minecraft.world.entity.variant.ModelAndTexture;

import java.util.List;
import java.util.Optional;

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
        HolderGetter<SoundEvent> soundEventLookup = context.lookup(Registries.SOUND_EVENT);
        context.register(ENDERMAN,
                new HeadType(EntityType.ENDERMAN,
                        new HeadType.Shape(8.0),
                        List.of(new ModelAndTexture<>(HeadType.ModelType.ENDERMAN,
                                        ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman")),
                                new ModelAndTexture<>(HeadType.ModelType.ENDERMAN_EYES,
                                        ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman_eyes"))),
                        getSoundEventHolder(soundEventLookup, SoundEvents.ENDERMAN_AMBIENT)));
        context.register(BLAZE,
                new HeadType(EntityType.BLAZE,
                        new HeadType.Shape(8.0),
                        List.of(new ModelAndTexture<>(HeadType.ModelType.BLAZE,
                                ResourceLocationHelper.withDefaultNamespace("entity/blaze"))),
                        getSoundEventHolder(soundEventLookup, SoundEvents.BLAZE_AMBIENT)));
        context.register(TEMPERATE_COW,
                new HeadType(EntityType.COW,
                        new HeadType.Shape(8.0, 8.0, 6.0),
                        new ModelAndTexture<>(HeadType.ModelType.TEMPERATE_COW,
                                ResourceLocationHelper.withDefaultNamespace("entity/cow/temperate_cow")),
                        getSoundEventHolder(soundEventLookup, SoundEvents.COW_AMBIENT),
                        EntityPredicate.Builder.entity()
                                .components(DataComponentMatchers.Builder.components()
                                        .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                                                (context.lookup(Registries.COW_VARIANT)
                                                        .getOrThrow(CowVariants.TEMPERATE))))
                                        .build())
                                .build(),
                        TEMPERATE_COW));
        context.register(WARM_COW,
                new HeadType(EntityType.COW,
                        new HeadType.Shape(8.0, 8.0, 6.0),
                        new ModelAndTexture<>(HeadType.ModelType.WARM_COW,
                                ResourceLocationHelper.withDefaultNamespace("entity/cow/warm_cow")),
                        getSoundEventHolder(soundEventLookup, SoundEvents.COW_AMBIENT),
                        EntityPredicate.Builder.entity()
                                .components(DataComponentMatchers.Builder.components()
                                        .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                                                (context.lookup(Registries.COW_VARIANT).getOrThrow(CowVariants.WARM))))
                                        .build())
                                .build(),
                        WARM_COW));
        context.register(COLD_COW,
                new HeadType(EntityType.COW,
                        new HeadType.Shape(8.0, 8.0, 6.0),
                        new ModelAndTexture<>(HeadType.ModelType.COLD_COW,
                                ResourceLocationHelper.withDefaultNamespace("entity/cow/cold_cow")),
                        getSoundEventHolder(soundEventLookup, SoundEvents.COW_AMBIENT),
                        EntityPredicate.Builder.entity()
                                .components(DataComponentMatchers.Builder.components()
                                        .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                                                (context.lookup(Registries.COW_VARIANT).getOrThrow(CowVariants.COLD))))
                                        .build())
                                .build(),
                        COLD_COW));
        context.register(OCELOT,
                new HeadType(EntityType.OCELOT,
                        new HeadType.Shape(5.0, 4.0).scale(1.6F),
                        List.of(new ModelAndTexture<>(HeadType.ModelType.OCELOT,
                                ResourceLocationHelper.withDefaultNamespace("entity/cat/ocelot"))),
                        getSoundEventHolder(soundEventLookup, SoundEvents.OCELOT_AMBIENT)));
    }

    private static Optional<Holder<SoundEvent>> getSoundEventHolder(HolderGetter<SoundEvent> soundEventLookup, SoundEvent soundEvent) {
        return Optional.of(soundEventLookup.getOrThrow(ResourceKey.create(Registries.SOUND_EVENT,
                soundEvent.location())));
    }

    private static ResourceKey<HeadType> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY, path);
    }
}