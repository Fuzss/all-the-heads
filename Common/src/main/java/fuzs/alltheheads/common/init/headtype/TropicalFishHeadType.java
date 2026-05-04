package fuzs.alltheheads.common.init.headtype;

import com.google.common.collect.ImmutableMap;
import fuzs.alltheheads.common.advancements.critereon.TropicalFishPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.Color;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import fuzs.alltheheads.common.world.item.component.headtype.Shape;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.fish.TropicalFish;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class TropicalFishHeadType {
    public static final ResourceKey<HeadType> ANEMONE = register("tropical_fish/anemone");
    public static final ResourceKey<HeadType> BLACK_TANG = register("tropical_fish/black_tang");
    public static final ResourceKey<HeadType> BLUE_TANG = register("tropical_fish/blue_tang");
    public static final ResourceKey<HeadType> BUTTERFLYFISH = register("tropical_fish/butterflyfish");
    public static final ResourceKey<HeadType> CICHLID = register("tropical_fish/cichlid");
    public static final ResourceKey<HeadType> CLOWNFISH = register("tropical_fish/clownfish");
    public static final ResourceKey<HeadType> COTTON_CANDY_BETTA = register("tropical_fish/cotton_candy_betta");
    public static final ResourceKey<HeadType> DOTTYBACK = register("tropical_fish/dottyback");
    public static final ResourceKey<HeadType> EMPEROR_RED_SNAPPER = register("tropical_fish/emperor_red_snapper");
    public static final ResourceKey<HeadType> GOATFISH = register("tropical_fish/goatfish");
    public static final ResourceKey<HeadType> MOORISH_IDOL = register("tropical_fish/moorish_idol");
    public static final ResourceKey<HeadType> ORNATE_BUTTERFLYFISH = register("tropical_fish/ornate_butterflyfish");
    public static final ResourceKey<HeadType> PARROTFISH = register("tropical_fish/parrotfish");
    public static final ResourceKey<HeadType> QUEEN_ANGELFISH = register("tropical_fish/queen_angelfish");
    public static final ResourceKey<HeadType> RED_CICHLID = register("tropical_fish/red_cichlid");
    public static final ResourceKey<HeadType> RED_LIPPED_BLENNY = register("tropical_fish/red_lipped_blenny");
    public static final ResourceKey<HeadType> RED_SNAPPER = register("tropical_fish/red_snapper");
    public static final ResourceKey<HeadType> THREADFIN = register("tropical_fish/threadfin");
    public static final ResourceKey<HeadType> TOMATO_CLOWNFISH = register("tropical_fish/tomato_clownfish");
    public static final ResourceKey<HeadType> TRIGGERFISH = register("tropical_fish/triggerfish");
    public static final ResourceKey<HeadType> YELLOWTAIL_PARROTFISH = register("tropical_fish/yellowtail_parrotfish");
    public static final ResourceKey<HeadType> YELLOW_TANG = register("tropical_fish/yellow_tang");
    private static final List<ResourceKey<HeadType>> COMMON_TROPICAL_FISH_VARIANTS = List.of(ANEMONE,
            BLACK_TANG,
            BLUE_TANG,
            BUTTERFLYFISH,
            CICHLID,
            CLOWNFISH,
            COTTON_CANDY_BETTA,
            DOTTYBACK,
            EMPEROR_RED_SNAPPER,
            GOATFISH,
            MOORISH_IDOL,
            ORNATE_BUTTERFLYFISH,
            PARROTFISH,
            QUEEN_ANGELFISH,
            RED_CICHLID,
            RED_LIPPED_BLENNY,
            RED_SNAPPER,
            THREADFIN,
            TOMATO_CLOWNFISH,
            TRIGGERFISH,
            YELLOWTAIL_PARROTFISH,
            YELLOW_TANG);
    private static final Map<TropicalFish.Pattern, Identifier> TROPICAL_FISH_PATTERN_TEXTURES = ImmutableMap.<TropicalFish.Pattern, Identifier>builder()
            .put(TropicalFish.Pattern.KOB, Identifier.withDefaultNamespace("entity/fish/tropical_a_pattern_1"))
            .put(TropicalFish.Pattern.SUNSTREAK, Identifier.withDefaultNamespace("entity/fish/tropical_a_pattern_2"))
            .put(TropicalFish.Pattern.SNOOPER, Identifier.withDefaultNamespace("entity/fish/tropical_a_pattern_3"))
            .put(TropicalFish.Pattern.DASHER, Identifier.withDefaultNamespace("entity/fish/tropical_a_pattern_4"))
            .put(TropicalFish.Pattern.BRINELY, Identifier.withDefaultNamespace("entity/fish/tropical_a_pattern_5"))
            .put(TropicalFish.Pattern.SPOTTY, Identifier.withDefaultNamespace("entity/fish/tropical_a_pattern_6"))
            .put(TropicalFish.Pattern.FLOPPER, Identifier.withDefaultNamespace("entity/fish/tropical_b_pattern_1"))
            .put(TropicalFish.Pattern.STRIPEY, Identifier.withDefaultNamespace("entity/fish/tropical_b_pattern_2"))
            .put(TropicalFish.Pattern.GLITTER, Identifier.withDefaultNamespace("entity/fish/tropical_b_pattern_3"))
            .put(TropicalFish.Pattern.BLOCKFISH, Identifier.withDefaultNamespace("entity/fish/tropical_b_pattern_4"))
            .put(TropicalFish.Pattern.BETTY, Identifier.withDefaultNamespace("entity/fish/tropical_b_pattern_5"))
            .put(TropicalFish.Pattern.CLAYFISH, Identifier.withDefaultNamespace("entity/fish/tropical_b_pattern_6"))
            .build();

    public static void bootstrap(BootstrapContext<HeadType> context) {
        for (int i = 0; i < COMMON_TROPICAL_FISH_VARIANTS.size(); i++) {
            TropicalFish.Variant variant = TropicalFish.COMMON_VARIANTS.get(i);
            ResourceKey<HeadType> resourceKey = COMMON_TROPICAL_FISH_VARIANTS.get(i);
            boostrapTropicalFish(context, variant, resourceKey);
        }
    }

    private static void boostrapTropicalFish(BootstrapContext<HeadType> context, TropicalFish.Variant variant, ResourceKey<HeadType> resourceKey) {
        switch (variant.pattern().base()) {
            case LARGE -> boostrapTropicalFish(context,
                    variant,
                    resourceKey,
                    "entity/fish/tropical_b",
                    new Shape(2.0, 6.0, 4.0),
                    ModelType.TROPICAL_FISH_LARGE);
            case SMALL -> boostrapTropicalFish(context,
                    variant,
                    resourceKey,
                    "entity/fish/tropical_a",
                    new Shape(2.0, 3.0, 4.0),
                    ModelType.TROPICAL_FISH_SMALL);
        }
    }

    private static void boostrapTropicalFish(BootstrapContext<HeadType> context, TropicalFish.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation, Shape shape, ModelType modelType) {
        HeadType.builder(EntityType.TROPICAL_FISH)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    // we cannot check all three tropical fish components in a single entity predicate,
                    // so use this custom subpredicate instead
                    builder.subPredicate(TropicalFishPredicate.hasVariant(variant));
                })
                .shape(shape)
                .scale(1.5)
                .dyedModel(modelType,
                        Identifier.withDefaultNamespace(textureLocation),
                        new Color.Dye(variant.baseColor()))
                .dyedModel(modelType,
                        TROPICAL_FISH_PATTERN_TEXTURES.get(variant.pattern()),
                        new Color.Dye(variant.patternColor()))
                .noteBlockSound(SoundEvents.TROPICAL_FISH_FLOP)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(ANEMONE, "Anemone Head");
        translationConsumer.accept(BLACK_TANG, "Black Tang Head");
        translationConsumer.accept(BLUE_TANG, "Blue Tang Head");
        translationConsumer.accept(BUTTERFLYFISH, "Butterflyfish Head");
        translationConsumer.accept(CICHLID, "Cichlid Head");
        translationConsumer.accept(CLOWNFISH, "Clownfish Head");
        translationConsumer.accept(COTTON_CANDY_BETTA, "Cotton Candy Betta Head");
        translationConsumer.accept(DOTTYBACK, "Dottyback Head");
        translationConsumer.accept(EMPEROR_RED_SNAPPER, "Emperor Red Snapper Head");
        translationConsumer.accept(GOATFISH, "Goatfish Head");
        translationConsumer.accept(MOORISH_IDOL, "Moorish Idol Head");
        translationConsumer.accept(ORNATE_BUTTERFLYFISH, "Ornate Butterflyfish Head");
        translationConsumer.accept(PARROTFISH, "Parrotfish Head");
        translationConsumer.accept(QUEEN_ANGELFISH, "Queen Angelfish Head");
        translationConsumer.accept(RED_CICHLID, "Red Cichlid Head");
        translationConsumer.accept(RED_LIPPED_BLENNY, "Red Lipped Blenny Head");
        translationConsumer.accept(RED_SNAPPER, "Red Snapper Head");
        translationConsumer.accept(THREADFIN, "Threadfin Head");
        translationConsumer.accept(TOMATO_CLOWNFISH, "Tomato Clownfish Head");
        translationConsumer.accept(TRIGGERFISH, "Triggerfish Head");
        translationConsumer.accept(YELLOWTAIL_PARROTFISH, "Yellowtail Parrotfish Head");
        translationConsumer.accept(YELLOW_TANG, "Yellow Tang Head");
    }
}
