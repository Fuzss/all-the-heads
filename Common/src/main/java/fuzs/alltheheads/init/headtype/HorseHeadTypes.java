package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.HorsePredicate;
import fuzs.alltheheads.init.HeadTypes;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Markings;
import net.minecraft.world.entity.animal.horse.Variant;

public class HorseHeadTypes {
    // Horses
    public static final ResourceKey<HeadType> SKELETON_HORSE = register("skeleton_horse");
    public static final ResourceKey<HeadType> ZOMBIE_HORSE = register("zombie_horse");
    public static final ResourceKey<HeadType> DONKEY = register("donkey");
    public static final ResourceKey<HeadType> MULE = register("mule");
    // White Horses
    public static final ResourceKey<HeadType> WHITE_HORSE = register("horse/white");
    public static final ResourceKey<HeadType> WHITE_WHITE_HORSE = register("horse/white/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_WHITE_HORSE = register("horse/white/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_WHITE_HORSE = register("horse/white/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_WHITE_HORSE = register("horse/white/black_dots");
    // Creamy Horses
    public static final ResourceKey<HeadType> CREAMY_HORSE = register("horse/creamy");
    public static final ResourceKey<HeadType> WHITE_CREAMY_HORSE = register("horse/creamy/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_CREAMY_HORSE = register("horse/creamy/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_CREAMY_HORSE = register("horse/creamy/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_CREAMY_HORSE = register("horse/creamy/black_dots");
    // Chestnut Horses
    public static final ResourceKey<HeadType> CHESTNUT_HORSE = register("horse/chestnut");
    public static final ResourceKey<HeadType> WHITE_CHESTNUT_HORSE = register("horse/chestnut/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_CHESTNUT_HORSE = register("horse/chestnut/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_CHESTNUT_HORSE = register("horse/chestnut/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_CHESTNUT_HORSE = register("horse/chestnut/black_dots");
    // Brown Horses
    public static final ResourceKey<HeadType> BROWN_HORSE = register("horse/brown");
    public static final ResourceKey<HeadType> WHITE_BROWN_HORSE = register("horse/brown/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_BROWN_HORSE = register("horse/brown/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_BROWN_HORSE = register("horse/brown/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_BROWN_HORSE = register("horse/brown/black_dots");
    // Black Horses
    public static final ResourceKey<HeadType> BLACK_HORSE = register("horse/black");
    public static final ResourceKey<HeadType> WHITE_BLACK_HORSE = register("horse/black/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_BLACK_HORSE = register("horse/black/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_BLACK_HORSE = register("horse/black/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_BLACK_HORSE = register("horse/black/black_dots");
    // Gray Horses
    public static final ResourceKey<HeadType> GRAY_HORSE = register("horse/gray");
    public static final ResourceKey<HeadType> WHITE_GRAY_HORSE = register("horse/gray/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_GRAY_HORSE = register("horse/gray/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_GRAY_HORSE = register("horse/gray/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_GRAY_HORSE = register("horse/gray/black_dots");
    // Dark Brown Horses
    public static final ResourceKey<HeadType> DARK_BROWN_HORSE = register("horse/dark_brown");
    public static final ResourceKey<HeadType> WHITE_DARK_BROWN_HORSE = register("horse/dark_brown/white");
    public static final ResourceKey<HeadType> WHITE_FIELD_DARK_BROWN_HORSE = register("horse/dark_brown/white_field");
    public static final ResourceKey<HeadType> WHITE_DOTS_DARK_BROWN_HORSE = register("horse/dark_brown/white_dots");
    public static final ResourceKey<HeadType> BLACK_DOTS_DARK_BROWN_HORSE = register("horse/dark_brown/black_dots");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        // Horses
        bootstrapHorse(context,
                EntityType.SKELETON_HORSE,
                SKELETON_HORSE,
                "entity/horse/horse_skeleton",
                SoundEvents.SKELETON_HORSE_AMBIENT);
        bootstrapHorse(context,
                EntityType.ZOMBIE_HORSE,
                ZOMBIE_HORSE,
                "entity/horse/horse_zombie",
                SoundEvents.ZOMBIE_HORSE_AMBIENT);
        bootstrapHorse(context, EntityType.DONKEY, DONKEY, "entity/horse/donkey", SoundEvents.DONKEY_AMBIENT);
        bootstrapHorse(context, EntityType.MULE, MULE, "entity/horse/mule", SoundEvents.MULE_AMBIENT);

        // White Horses
        bootstrapHorse(context, Variant.WHITE, WHITE_HORSE, "entity/horse/horse_white");
        bootstrapHorse(context,
                Variant.WHITE,
                Markings.WHITE,
                WHITE_WHITE_HORSE,
                "entity/horse/horse_white",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.WHITE,
                Markings.WHITE_FIELD,
                WHITE_FIELD_WHITE_HORSE,
                "entity/horse/horse_white",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.WHITE,
                Markings.WHITE_DOTS,
                WHITE_DOTS_WHITE_HORSE,
                "entity/horse/horse_white",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.WHITE,
                Markings.BLACK_DOTS,
                BLACK_DOTS_WHITE_HORSE,
                "entity/horse/horse_white",
                "entity/horse/horse_markings_blackdots");

        // Creamy Horses
        bootstrapHorse(context, Variant.CREAMY, CREAMY_HORSE, "entity/horse/horse_creamy");
        bootstrapHorse(context,
                Variant.CREAMY,
                Markings.WHITE,
                WHITE_CREAMY_HORSE,
                "entity/horse/horse_creamy",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.CREAMY,
                Markings.WHITE_FIELD,
                WHITE_FIELD_CREAMY_HORSE,
                "entity/horse/horse_creamy",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.CREAMY,
                Markings.WHITE_DOTS,
                WHITE_DOTS_CREAMY_HORSE,
                "entity/horse/horse_creamy",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.CREAMY,
                Markings.BLACK_DOTS,
                BLACK_DOTS_CREAMY_HORSE,
                "entity/horse/horse_creamy",
                "entity/horse/horse_markings_blackdots");

        // Chestnut Horses
        bootstrapHorse(context, Variant.CHESTNUT, CHESTNUT_HORSE, "entity/horse/horse_chestnut");
        bootstrapHorse(context,
                Variant.CHESTNUT,
                Markings.WHITE,
                WHITE_CHESTNUT_HORSE,
                "entity/horse/horse_chestnut",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.CHESTNUT,
                Markings.WHITE_FIELD,
                WHITE_FIELD_CHESTNUT_HORSE,
                "entity/horse/horse_chestnut",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.CHESTNUT,
                Markings.WHITE_DOTS,
                WHITE_DOTS_CHESTNUT_HORSE,
                "entity/horse/horse_chestnut",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.CHESTNUT,
                Markings.BLACK_DOTS,
                BLACK_DOTS_CHESTNUT_HORSE,
                "entity/horse/horse_chestnut",
                "entity/horse/horse_markings_blackdots");

        // Brown Horses
        bootstrapHorse(context, Variant.BROWN, BROWN_HORSE, "entity/horse/horse_brown");
        bootstrapHorse(context,
                Variant.BROWN,
                Markings.WHITE,
                WHITE_BROWN_HORSE,
                "entity/horse/horse_brown",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.BROWN,
                Markings.WHITE_FIELD,
                WHITE_FIELD_BROWN_HORSE,
                "entity/horse/horse_brown",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.BROWN,
                Markings.WHITE_DOTS,
                WHITE_DOTS_BROWN_HORSE,
                "entity/horse/horse_brown",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.BROWN,
                Markings.BLACK_DOTS,
                BLACK_DOTS_BROWN_HORSE,
                "entity/horse/horse_brown",
                "entity/horse/horse_markings_blackdots");

        // Black Horses
        bootstrapHorse(context, Variant.BLACK, BLACK_HORSE, "entity/horse/horse_black");
        bootstrapHorse(context,
                Variant.BLACK,
                Markings.WHITE,
                WHITE_BLACK_HORSE,
                "entity/horse/horse_black",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.BLACK,
                Markings.WHITE_FIELD,
                WHITE_FIELD_BLACK_HORSE,
                "entity/horse/horse_black",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.BLACK,
                Markings.WHITE_DOTS,
                WHITE_DOTS_BLACK_HORSE,
                "entity/horse/horse_black",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.BLACK,
                Markings.BLACK_DOTS,
                BLACK_DOTS_BLACK_HORSE,
                "entity/horse/horse_black",
                "entity/horse/horse_markings_blackdots");

        // Gray Horses
        bootstrapHorse(context, Variant.GRAY, GRAY_HORSE, "entity/horse/horse_gray");
        bootstrapHorse(context,
                Variant.GRAY,
                Markings.WHITE,
                WHITE_GRAY_HORSE,
                "entity/horse/horse_gray",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.GRAY,
                Markings.WHITE_FIELD,
                WHITE_FIELD_GRAY_HORSE,
                "entity/horse/horse_gray",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.GRAY,
                Markings.WHITE_DOTS,
                WHITE_DOTS_GRAY_HORSE,
                "entity/horse/horse_gray",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.GRAY,
                Markings.BLACK_DOTS,
                BLACK_DOTS_GRAY_HORSE,
                "entity/horse/horse_gray",
                "entity/horse/horse_markings_blackdots");

        // Dark Brown Horses
        bootstrapHorse(context, Variant.DARK_BROWN, DARK_BROWN_HORSE, "entity/horse/horse_darkbrown");
        bootstrapHorse(context,
                Variant.DARK_BROWN,
                Markings.WHITE,
                WHITE_DARK_BROWN_HORSE,
                "entity/horse/horse_darkbrown",
                "entity/horse/horse_markings_white");
        bootstrapHorse(context,
                Variant.DARK_BROWN,
                Markings.WHITE_FIELD,
                WHITE_FIELD_DARK_BROWN_HORSE,
                "entity/horse/horse_darkbrown",
                "entity/horse/horse_markings_whitefield");
        bootstrapHorse(context,
                Variant.DARK_BROWN,
                Markings.WHITE_DOTS,
                WHITE_DOTS_DARK_BROWN_HORSE,
                "entity/horse/horse_darkbrown",
                "entity/horse/horse_markings_whitedots");
        bootstrapHorse(context,
                Variant.DARK_BROWN,
                Markings.BLACK_DOTS,
                BLACK_DOTS_DARK_BROWN_HORSE,
                "entity/horse/horse_darkbrown",
                "entity/horse/horse_markings_blackdots");
    }

    private static void bootstrapHorse(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(4.0, 16.0, 8.0)
                .scale(0.75)
                .model(ModelType.HORSE, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    private static void bootstrapHorse(BootstrapContext<HeadType> context, Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.HORSE)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.HORSE_VARIANT, variant))
                            .build()).subPredicate(HorsePredicate.forMarkings(Markings.NONE));
                })
                .shape(4.0, 16.0, 8.0)
                .scale(0.75)
                .model(ModelType.HORSE, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.HORSE_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapHorse(BootstrapContext<HeadType> context, Variant variant, Markings markings, ResourceKey<HeadType> resourceKey, String textureLocation, String markingsLocation) {
        HeadType.builder(EntityType.HORSE)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.HORSE_VARIANT, variant))
                            .build()).subPredicate(HorsePredicate.forMarkings(markings));
                })
                .shape(4.0, 16.0, 8.0)
                .scale(0.75)
                .model(ModelType.HORSE, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .model(ModelType.HORSE_MARKINGS, ResourceLocationHelper.withDefaultNamespace(markingsLocation))
                .noteBlockSound(SoundEvents.HORSE_AMBIENT)
                .build(context, resourceKey);
    }

    private static ResourceKey<HeadType> register(String path) {
        return HeadTypes.register(path);
    }
}