package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SheepPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

import java.util.Optional;
import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class SheepHeadType {
    public static final ResourceKey<HeadType> WHITE_SHEEP = register("sheep/white");
    public static final ResourceKey<HeadType> WHITE_WOOLLY_SHEEP = register("sheep/woolly/white");
    public static final ResourceKey<HeadType> ORANGE_SHEEP = register("sheep/orange");
    public static final ResourceKey<HeadType> ORANGE_WOOLLY_SHEEP = register("sheep/woolly/orange");
    public static final ResourceKey<HeadType> MAGENTA_SHEEP = register("sheep/magenta");
    public static final ResourceKey<HeadType> MAGENTA_WOOLLY_SHEEP = register("sheep/woolly/magenta");
    public static final ResourceKey<HeadType> LIGHT_BLUE_SHEEP = register("sheep/light_blue");
    public static final ResourceKey<HeadType> LIGHT_BLUE_WOOLLY_SHEEP = register("sheep/woolly/light_blue");
    public static final ResourceKey<HeadType> YELLOW_SHEEP = register("sheep/yellow");
    public static final ResourceKey<HeadType> YELLOW_WOOLLY_SHEEP = register("sheep/woolly/yellow");
    public static final ResourceKey<HeadType> LIME_SHEEP = register("sheep/lime");
    public static final ResourceKey<HeadType> LIME_WOOLLY_SHEEP = register("sheep/woolly/lime");
    public static final ResourceKey<HeadType> PINK_SHEEP = register("sheep/pink");
    public static final ResourceKey<HeadType> PINK_WOOLLY_SHEEP = register("sheep/woolly/pink");
    public static final ResourceKey<HeadType> GRAY_SHEEP = register("sheep/gray");
    public static final ResourceKey<HeadType> GRAY_WOOLLY_SHEEP = register("sheep/woolly/gray");
    public static final ResourceKey<HeadType> LIGHT_GRAY_SHEEP = register("sheep/light_gray");
    public static final ResourceKey<HeadType> LIGHT_GRAY_WOOLLY_SHEEP = register("sheep/woolly/light_gray");
    public static final ResourceKey<HeadType> CYAN_SHEEP = register("sheep/cyan");
    public static final ResourceKey<HeadType> CYAN_WOOLLY_SHEEP = register("sheep/woolly/cyan");
    public static final ResourceKey<HeadType> PURPLE_SHEEP = register("sheep/purple");
    public static final ResourceKey<HeadType> PURPLE_WOOLLY_SHEEP = register("sheep/woolly/purple");
    public static final ResourceKey<HeadType> BLUE_SHEEP = register("sheep/blue");
    public static final ResourceKey<HeadType> BLUE_WOOLLY_SHEEP = register("sheep/woolly/blue");
    public static final ResourceKey<HeadType> BROWN_SHEEP = register("sheep/brown");
    public static final ResourceKey<HeadType> BROWN_WOOLLY_SHEEP = register("sheep/woolly/brown");
    public static final ResourceKey<HeadType> GREEN_SHEEP = register("sheep/green");
    public static final ResourceKey<HeadType> GREEN_WOOLLY_SHEEP = register("sheep/woolly/green");
    public static final ResourceKey<HeadType> RED_SHEEP = register("sheep/red");
    public static final ResourceKey<HeadType> RED_WOOLLY_SHEEP = register("sheep/woolly/red");
    public static final ResourceKey<HeadType> BLACK_SHEEP = register("sheep/black");
    public static final ResourceKey<HeadType> BLACK_WOOLLY_SHEEP = register("sheep/woolly/black");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapSheep(context, DyeColor.WHITE, WHITE_SHEEP, WHITE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.ORANGE, ORANGE_SHEEP, ORANGE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.MAGENTA, MAGENTA_SHEEP, MAGENTA_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.LIGHT_BLUE, LIGHT_BLUE_SHEEP, LIGHT_BLUE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.YELLOW, YELLOW_SHEEP, YELLOW_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.LIME, LIME_SHEEP, LIME_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.PINK, PINK_SHEEP, PINK_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.GRAY, GRAY_SHEEP, GRAY_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.LIGHT_GRAY, LIGHT_GRAY_SHEEP, LIGHT_GRAY_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.CYAN, CYAN_SHEEP, CYAN_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.PURPLE, PURPLE_SHEEP, PURPLE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.BLUE, BLUE_SHEEP, BLUE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.BROWN, BROWN_SHEEP, BROWN_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.GREEN, GREEN_SHEEP, GREEN_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.RED, RED_SHEEP, RED_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.BLACK, BLACK_SHEEP, BLACK_WOOLLY_SHEEP);
    }

    private static void bootstrapSheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> sheep, ResourceKey<HeadType> woollySheep) {
        bootstrapSheep(context, dyeColor, sheep);
        bootstrapWoollySheep(context, dyeColor, woollySheep);
    }

    private static void bootstrapSheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.SHEEP_COLOR, dyeColor))
                            .build()).subPredicate(new SheepPredicate(Optional.of(true)));
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .dyedModel(ModelType.SHEEP,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_wool_undercoat"),
                        getModifiedColor(dyeColor, 0.75F))
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapWoollySheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.SHEEP_COLOR, dyeColor))
                            .build()).subPredicate(SheepPredicate.hasWool());
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .dyedModel(ModelType.SHEEP,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_wool_undercoat"),
                        getModifiedColor(dyeColor, 0.75F))
                .dyedModel(ModelType.SHEEP_WOOL,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_wool"),
                        getModifiedColor(dyeColor, 0.75F))
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, resourceKey);
    }

    /**
     * Copied from client-only class.
     *
     * @see net.minecraft.client.color.ColorLerper#getModifiedColor(DyeColor, float)
     */
    private static int getModifiedColor(DyeColor color, float brightness) {
        if (color == DyeColor.WHITE) {
            return -1644826;
        } else {
            int textureDiffuseColor = color.getTextureDiffuseColor();
            return ARGB.color(255,
                    Mth.floor(ARGB.red(textureDiffuseColor) * brightness),
                    Mth.floor(ARGB.green(textureDiffuseColor) * brightness),
                    Mth.floor(ARGB.blue(textureDiffuseColor) * brightness));
        }
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(WHITE_SHEEP, "White Sheep Head");
        translationConsumer.accept(WHITE_WOOLLY_SHEEP, "White Woolly Sheep Head");
        translationConsumer.accept(ORANGE_SHEEP, "Orange Sheep Head");
        translationConsumer.accept(ORANGE_WOOLLY_SHEEP, "Orange Woolly Sheep Head");
        translationConsumer.accept(MAGENTA_SHEEP, "Magenta Sheep Head");
        translationConsumer.accept(MAGENTA_WOOLLY_SHEEP, "Magenta Woolly Sheep Head");
        translationConsumer.accept(LIGHT_BLUE_SHEEP, "Light Blue Sheep Head");
        translationConsumer.accept(LIGHT_BLUE_WOOLLY_SHEEP, "Light Blue Woolly Sheep Head");
        translationConsumer.accept(YELLOW_SHEEP, "Yellow Sheep Head");
        translationConsumer.accept(YELLOW_WOOLLY_SHEEP, "Yellow Woolly Sheep Head");
        translationConsumer.accept(LIME_SHEEP, "Lime Sheep Head");
        translationConsumer.accept(LIME_WOOLLY_SHEEP, "Lime Woolly Sheep Head");
        translationConsumer.accept(PINK_SHEEP, "Pink Sheep Head");
        translationConsumer.accept(PINK_WOOLLY_SHEEP, "Pink Woolly Sheep Head");
        translationConsumer.accept(GRAY_SHEEP, "Gray Sheep Head");
        translationConsumer.accept(GRAY_WOOLLY_SHEEP, "Gray Woolly Sheep Head");
        translationConsumer.accept(LIGHT_GRAY_SHEEP, "Light Gray Sheep Head");
        translationConsumer.accept(LIGHT_GRAY_WOOLLY_SHEEP, "Light Gray Woolly Sheep Head");
        translationConsumer.accept(CYAN_SHEEP, "Cyan Sheep Head");
        translationConsumer.accept(CYAN_WOOLLY_SHEEP, "Cyan Woolly Sheep Head");
        translationConsumer.accept(PURPLE_SHEEP, "Purple Sheep Head");
        translationConsumer.accept(PURPLE_WOOLLY_SHEEP, "Purple Woolly Sheep Head");
        translationConsumer.accept(BLUE_SHEEP, "Blue Sheep Head");
        translationConsumer.accept(BLUE_WOOLLY_SHEEP, "Blue Woolly Sheep Head");
        translationConsumer.accept(BROWN_SHEEP, "Brown Sheep Head");
        translationConsumer.accept(BROWN_WOOLLY_SHEEP, "Brown Woolly Sheep Head");
        translationConsumer.accept(GREEN_SHEEP, "Green Sheep Head");
        translationConsumer.accept(GREEN_WOOLLY_SHEEP, "Green Woolly Sheep Head");
        translationConsumer.accept(RED_SHEEP, "Red Sheep Head");
        translationConsumer.accept(RED_WOOLLY_SHEEP, "Red Woolly Sheep Head");
        translationConsumer.accept(BLACK_SHEEP, "Black Sheep Head");
        translationConsumer.accept(BLACK_WOOLLY_SHEEP, "Black Woolly Sheep Head");
    }
}
