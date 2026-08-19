package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class CubeMobHeadType {
    public static final ResourceKey<HeadType> MAGMA_CUBE = register("magma_cube");
    public static final ResourceKey<HeadType> SLIME = register("slime");
    public static final ResourceKey<HeadType> SULFUR_CUBE = register("sulfur_cube");
    public static final ResourceKey<HeadType> REGULAR_SULFUR_CUBE = register("sulfur_cube/regular");
    public static final ResourceKey<HeadType> BOUNCY_SULFUR_CUBE = register("sulfur_cube/bouncy");
    public static final ResourceKey<HeadType> SLOW_BOUNCY_SULFUR_CUBE = register("sulfur_cube/slow_bouncy");
    public static final ResourceKey<HeadType> SLOW_FLAT_SULFUR_CUBE = register("sulfur_cube/slow_flat");
    public static final ResourceKey<HeadType> FAST_FLAT_SULFUR_CUBE = register("sulfur_cube/fast_flat");
    public static final ResourceKey<HeadType> LIGHT_SULFUR_CUBE = register("sulfur_cube/light");
    public static final ResourceKey<HeadType> FAST_SLIDING_SULFUR_CUBE = register("sulfur_cube/fast_sliding");
    public static final ResourceKey<HeadType> SLOW_SLIDING_SULFUR_CUBE = register("sulfur_cube/slow_sliding");
    public static final ResourceKey<HeadType> HIGH_RESISTANCE_SULFUR_CUBE = register("sulfur_cube/high_resistance");
    public static final ResourceKey<HeadType> STICKY_SULFUR_CUBE = register("sulfur_cube/sticky");
    public static final ResourceKey<HeadType> EXPLOSIVE_SULFUR_CUBE = register("sulfur_cube/explosive");
    public static final ResourceKey<HeadType> HOT_SULFUR_CUBE = register("sulfur_cube/hot");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        HeadType.builder()
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MAGMA_CUBE, Identifier.withDefaultNamespace("entity/slime/magmacube"))
                .noteBlockSound(SoundEvents.MAGMA_CUBE_SQUISH_SMALL)
                .build(context, MAGMA_CUBE);
        HeadType.builder()
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SLIME, Identifier.withDefaultNamespace("entity/slime/slime"))
                .model(ModelType.SLIME_GEL, Identifier.withDefaultNamespace("entity/slime/slime"))
                .noteBlockSound(SoundEvents.SLIME_SQUISH)
                .build(context, SLIME);
        HeadType.builder()
                .shape(18.0, 18.0, 18.0)
                .scale(8.0 / 18.0)
                .model(ModelType.SULFUR_CUBE, Identifier.withDefaultNamespace("entity/sulfur_cube/sulfur_cube_inner"))
                .model(ModelType.SULFUR_CUBE_GEL,
                        Identifier.withDefaultNamespace("entity/sulfur_cube/sulfur_cube_outer"))
                .noteBlockSound(SoundEvents.SULFUR_CUBE_SQUISH)
                .build(context, SULFUR_CUBE);
        bootstrapSulfurCube(context, REGULAR_SULFUR_CUBE, ModelType.SULFUR_CUBE_REGULAR);
        bootstrapSulfurCube(context, BOUNCY_SULFUR_CUBE, ModelType.SULFUR_CUBE_BOUNCY);
        bootstrapSulfurCube(context, SLOW_BOUNCY_SULFUR_CUBE, ModelType.SULFUR_CUBE_SLOW_BOUNCY);
        bootstrapSulfurCube(context, SLOW_FLAT_SULFUR_CUBE, ModelType.SULFUR_CUBE_SLOW_FLAT);
        bootstrapSulfurCube(context, FAST_FLAT_SULFUR_CUBE, ModelType.SULFUR_CUBE_FAST_FLAT);
        bootstrapSulfurCube(context, LIGHT_SULFUR_CUBE, ModelType.SULFUR_CUBE_LIGHT);
        bootstrapSulfurCube(context, FAST_SLIDING_SULFUR_CUBE, ModelType.SULFUR_CUBE_FAST_SLIDING);
        bootstrapSulfurCube(context, SLOW_SLIDING_SULFUR_CUBE, ModelType.SULFUR_CUBE_SLOW_SLIDING);
        bootstrapSulfurCube(context, HIGH_RESISTANCE_SULFUR_CUBE, ModelType.SULFUR_CUBE_HIGH_RESISTANCE);
        bootstrapSulfurCube(context, STICKY_SULFUR_CUBE, ModelType.SULFUR_CUBE_STICKY);
        bootstrapSulfurCube(context, EXPLOSIVE_SULFUR_CUBE, ModelType.SULFUR_CUBE_EXPLOSIVE);
        bootstrapSulfurCube(context, HOT_SULFUR_CUBE, ModelType.SULFUR_CUBE_HOT);
    }

    private static void bootstrapSulfurCube(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, ModelType modelType) {
        // The squish sound is different when there is a block inside.
        HeadType.builder()
                .shape(18.0, 18.0, 18.0)
                .scale(8.0 / 18.0)
                .specialModel(modelType)
                .model(ModelType.SULFUR_CUBE_GEL,
                        Identifier.withDefaultNamespace("entity/sulfur_cube/sulfur_cube_outer"))
                .noteBlockSound(SoundEvents.SULFUR_CUBE_BOUNCE)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrap(context, MAGMA_CUBE, EntityTypes.MAGMA_CUBE);
        bootstrap(context, SLIME, EntityTypes.SLIME);
        bootstrap(context, SULFUR_CUBE, EntityTypes.SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_REGULAR, REGULAR_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_BOUNCY, BOUNCY_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY, SLOW_BOUNCY_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_FLAT, SLOW_FLAT_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_FAST_FLAT, FAST_FLAT_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_LIGHT, LIGHT_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_FAST_SLIDING, FAST_SLIDING_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_SLIDING, SLOW_SLIDING_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_HIGH_RESISTANCE, HIGH_RESISTANCE_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_STICKY, STICKY_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_EXPLOSIVE, EXPLOSIVE_SULFUR_CUBE);
        bootstrapSulfurCube(context, ItemTags.SULFUR_CUBE_ARCHETYPE_HOT, HOT_SULFUR_CUBE);
    }

    private static void bootstrapSulfurCube(BootstrapContext<LootItemCondition> context, TagKey<Item> archetype, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.SULFUR_CUBE, (EntityPredicate.Builder builder) -> {
            builder.equipment(EntityEquipmentPredicate.Builder.equipment()
                    .body(ItemPredicate.Builder.item().of(context.lookup(Registries.ITEM), archetype)));
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(MAGMA_CUBE, "Magma Cube Head");
        translationConsumer.accept(SLIME, "Slime Head");
        translationConsumer.accept(SULFUR_CUBE, "Sulfur Cube Head");
        translationConsumer.accept(REGULAR_SULFUR_CUBE, "Regular Sulfur Cube Head");
        translationConsumer.accept(BOUNCY_SULFUR_CUBE, "Bouncy Sulfur Cube Head");
        translationConsumer.accept(SLOW_BOUNCY_SULFUR_CUBE, "Slow Bouncy Sulfur Cube Head");
        translationConsumer.accept(SLOW_FLAT_SULFUR_CUBE, "Slow Flat Sulfur Cube Head");
        translationConsumer.accept(FAST_FLAT_SULFUR_CUBE, "Fast Flat Sulfur Cube Head");
        translationConsumer.accept(LIGHT_SULFUR_CUBE, "Light Sulfur Cube Head");
        translationConsumer.accept(FAST_SLIDING_SULFUR_CUBE, "Fast Sliding Sulfur Cube Head");
        translationConsumer.accept(SLOW_SLIDING_SULFUR_CUBE, "Slow Sliding Sulfur Cube Head");
        translationConsumer.accept(HIGH_RESISTANCE_SULFUR_CUBE, "High Resistance Sulfur Cube Head");
        translationConsumer.accept(STICKY_SULFUR_CUBE, "Sticky Sulfur Cube Head");
        translationConsumer.accept(EXPLOSIVE_SULFUR_CUBE, "Explosive Sulfur Cube Head");
        translationConsumer.accept(HOT_SULFUR_CUBE, "Hot Sulfur Cube Head");
    }
}
