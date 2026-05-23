package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.SheepPredicate;
import fuzs.alltheheads.world.item.component.headtype.Color;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class SheepHeadType {
    public static final ResourceKey<HeadType> SHEEP = register("sheep");
    public static final ResourceKey<HeadType> WHITE_SHEEP = register("sheep/white");
    public static final ResourceKey<HeadType> ORANGE_SHEEP = register("sheep/orange");
    public static final ResourceKey<HeadType> MAGENTA_SHEEP = register("sheep/magenta");
    public static final ResourceKey<HeadType> LIGHT_BLUE_SHEEP = register("sheep/light_blue");
    public static final ResourceKey<HeadType> YELLOW_SHEEP = register("sheep/yellow");
    public static final ResourceKey<HeadType> LIME_SHEEP = register("sheep/lime");
    public static final ResourceKey<HeadType> PINK_SHEEP = register("sheep/pink");
    public static final ResourceKey<HeadType> GRAY_SHEEP = register("sheep/gray");
    public static final ResourceKey<HeadType> LIGHT_GRAY_SHEEP = register("sheep/light_gray");
    public static final ResourceKey<HeadType> CYAN_SHEEP = register("sheep/cyan");
    public static final ResourceKey<HeadType> PURPLE_SHEEP = register("sheep/purple");
    public static final ResourceKey<HeadType> BLUE_SHEEP = register("sheep/blue");
    public static final ResourceKey<HeadType> BROWN_SHEEP = register("sheep/brown");
    public static final ResourceKey<HeadType> GREEN_SHEEP = register("sheep/green");
    public static final ResourceKey<HeadType> RED_SHEEP = register("sheep/red");
    public static final ResourceKey<HeadType> BLACK_SHEEP = register("sheep/black");
    public static final ResourceKey<HeadType> RAINBOW_SHEEP = register("sheep/rainbow");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapSheep(context, SHEEP);
        bootstrapWoollySheep(context, DyeColor.WHITE, WHITE_SHEEP);
        bootstrapWoollySheep(context, DyeColor.ORANGE, ORANGE_SHEEP);
        bootstrapWoollySheep(context, DyeColor.MAGENTA, MAGENTA_SHEEP);
        bootstrapWoollySheep(context, DyeColor.LIGHT_BLUE, LIGHT_BLUE_SHEEP);
        bootstrapWoollySheep(context, DyeColor.YELLOW, YELLOW_SHEEP);
        bootstrapWoollySheep(context, DyeColor.LIME, LIME_SHEEP);
        bootstrapWoollySheep(context, DyeColor.PINK, PINK_SHEEP);
        bootstrapWoollySheep(context, DyeColor.GRAY, GRAY_SHEEP);
        bootstrapWoollySheep(context, DyeColor.LIGHT_GRAY, LIGHT_GRAY_SHEEP);
        bootstrapWoollySheep(context, DyeColor.CYAN, CYAN_SHEEP);
        bootstrapWoollySheep(context, DyeColor.PURPLE, PURPLE_SHEEP);
        bootstrapWoollySheep(context, DyeColor.BLUE, BLUE_SHEEP);
        bootstrapWoollySheep(context, DyeColor.BROWN, BROWN_SHEEP);
        bootstrapWoollySheep(context, DyeColor.GREEN, GREEN_SHEEP);
        bootstrapWoollySheep(context, DyeColor.RED, RED_SHEEP);
        bootstrapWoollySheep(context, DyeColor.BLACK, BLACK_SHEEP);
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.nbt(new NbtPredicate(Util.make(new CompoundTag(),
                                    (CompoundTag tag) -> tag.putString("CustomName",
                                            Component.Serializer.toJson(Component.literal("jeb_"), RegistryAccess.EMPTY)))))
                            .subPredicate(SheepPredicate.hasWool(true));
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .dyedModel(ModelType.SHEEP_WOOL,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_fur"),
                        new Color.Rainbow())
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, RAINBOW_SHEEP);
    }

    private static void bootstrapSheep(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(SheepPredicate.hasWool(false));
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapWoollySheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(SheepPredicate.forColor(dyeColor, true));
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .dyedModel(ModelType.SHEEP_WOOL,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_fur"),
                        new Color.Sheep(dyeColor))
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(SHEEP, "Sheep Head");
        translationConsumer.accept(WHITE_SHEEP, "White Sheep Head");
        translationConsumer.accept(ORANGE_SHEEP, "Orange Sheep Head");
        translationConsumer.accept(MAGENTA_SHEEP, "Magenta Sheep Head");
        translationConsumer.accept(LIGHT_BLUE_SHEEP, "Light Blue Sheep Head");
        translationConsumer.accept(YELLOW_SHEEP, "Yellow Sheep Head");
        translationConsumer.accept(LIME_SHEEP, "Lime Sheep Head");
        translationConsumer.accept(PINK_SHEEP, "Pink Sheep Head");
        translationConsumer.accept(GRAY_SHEEP, "Gray Sheep Head");
        translationConsumer.accept(LIGHT_GRAY_SHEEP, "Light Gray Sheep Head");
        translationConsumer.accept(CYAN_SHEEP, "Cyan Sheep Head");
        translationConsumer.accept(PURPLE_SHEEP, "Purple Sheep Head");
        translationConsumer.accept(BLUE_SHEEP, "Blue Sheep Head");
        translationConsumer.accept(BROWN_SHEEP, "Brown Sheep Head");
        translationConsumer.accept(GREEN_SHEEP, "Green Sheep Head");
        translationConsumer.accept(RED_SHEEP, "Red Sheep Head");
        translationConsumer.accept(BLACK_SHEEP, "Black Sheep Head");
        translationConsumer.accept(RAINBOW_SHEEP, "Rainbow Sheep Head");
    }
}
