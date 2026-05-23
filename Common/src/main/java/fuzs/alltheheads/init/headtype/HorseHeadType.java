package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.HorsePredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntitySubPredicates;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Markings;
import net.minecraft.world.entity.animal.horse.Variant;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class HorseHeadType {
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
                    builder.subPredicate(EntitySubPredicates.HORSE.createPredicate(variant))
                            .subPredicate(HorsePredicate.forMarkings(Markings.NONE));
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
                    builder.subPredicate(EntitySubPredicates.HORSE.createPredicate(variant))
                            .subPredicate(HorsePredicate.forMarkings(markings));
                })
                .shape(4.0, 16.0, 8.0)
                .scale(0.75)
                .model(ModelType.HORSE, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .model(ModelType.HORSE_MARKINGS, ResourceLocationHelper.withDefaultNamespace(markingsLocation))
                .noteBlockSound(SoundEvents.HORSE_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        // Horses
        translationConsumer.accept(SKELETON_HORSE, "Skeleton Horse Head");
        translationConsumer.accept(ZOMBIE_HORSE, "Zombie Horse Head");
        translationConsumer.accept(DONKEY, "Donkey Head");
        translationConsumer.accept(MULE, "Mule Head");

        // White Horses
        translationConsumer.accept(WHITE_HORSE, "White Horse Head");
        translationConsumer.accept(WHITE_WHITE_HORSE, "White Blaze White Horse Head");
        translationConsumer.accept(WHITE_FIELD_WHITE_HORSE, "White Field White Horse Head");
        translationConsumer.accept(WHITE_DOTS_WHITE_HORSE, "White Spots White Horse Head");
        translationConsumer.accept(BLACK_DOTS_WHITE_HORSE, "Black Dots White Horse Head");

        // Creamy Horses
        translationConsumer.accept(CREAMY_HORSE, "Creamy Horse Head");
        translationConsumer.accept(WHITE_CREAMY_HORSE, "White Blaze Creamy Horse Head");
        translationConsumer.accept(WHITE_FIELD_CREAMY_HORSE, "White Field Creamy Horse Head");
        translationConsumer.accept(WHITE_DOTS_CREAMY_HORSE, "White Spots Creamy Horse Head");
        translationConsumer.accept(BLACK_DOTS_CREAMY_HORSE, "Black Dots Creamy Horse Head");

        // Chestnut Horses
        translationConsumer.accept(CHESTNUT_HORSE, "Chestnut Horse Head");
        translationConsumer.accept(WHITE_CHESTNUT_HORSE, "White Blaze Chestnut Horse Head");
        translationConsumer.accept(WHITE_FIELD_CHESTNUT_HORSE, "White Field Chestnut Horse Head");
        translationConsumer.accept(WHITE_DOTS_CHESTNUT_HORSE, "White Spots Chestnut Horse Head");
        translationConsumer.accept(BLACK_DOTS_CHESTNUT_HORSE, "Black Dots Chestnut Horse Head");

        // Brown Horses
        translationConsumer.accept(BROWN_HORSE, "Brown Horse Head");
        translationConsumer.accept(WHITE_BROWN_HORSE, "White Blaze Brown Horse Head");
        translationConsumer.accept(WHITE_FIELD_BROWN_HORSE, "White Field Brown Horse Head");
        translationConsumer.accept(WHITE_DOTS_BROWN_HORSE, "White Spots Brown Horse Head");
        translationConsumer.accept(BLACK_DOTS_BROWN_HORSE, "Black Dots Brown Horse Head");

        // Black Horses
        translationConsumer.accept(BLACK_HORSE, "Black Horse Head");
        translationConsumer.accept(WHITE_BLACK_HORSE, "White Blaze Black Horse Head");
        translationConsumer.accept(WHITE_FIELD_BLACK_HORSE, "White Field Black Horse Head");
        translationConsumer.accept(WHITE_DOTS_BLACK_HORSE, "White Spots Black Horse Head");
        translationConsumer.accept(BLACK_DOTS_BLACK_HORSE, "Black Dots Black Horse Head");

        // Gray Horses
        translationConsumer.accept(GRAY_HORSE, "Gray Horse Head");
        translationConsumer.accept(WHITE_GRAY_HORSE, "White Blaze Gray Horse Head");
        translationConsumer.accept(WHITE_FIELD_GRAY_HORSE, "White Field Gray Horse Head");
        translationConsumer.accept(WHITE_DOTS_GRAY_HORSE, "White Spots Gray Horse Head");
        translationConsumer.accept(BLACK_DOTS_GRAY_HORSE, "Black Dots Gray Horse Head");

        // Dark Brown Horses
        translationConsumer.accept(DARK_BROWN_HORSE, "Dark Brown Horse Head");
        translationConsumer.accept(WHITE_DARK_BROWN_HORSE, "White Blaze Dark Brown Horse Head");
        translationConsumer.accept(WHITE_FIELD_DARK_BROWN_HORSE, "White Field Dark Brown Horse Head");
        translationConsumer.accept(WHITE_DOTS_DARK_BROWN_HORSE, "White Spots Dark Brown Horse Head");
        translationConsumer.accept(BLACK_DOTS_DARK_BROWN_HORSE, "Black Dots Dark Brown Horse Head");
    }
}