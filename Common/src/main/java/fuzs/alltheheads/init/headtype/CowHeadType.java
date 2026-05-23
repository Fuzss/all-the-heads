package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntitySubPredicates;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.MushroomCow;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class CowHeadType {
    public static final ResourceKey<HeadType> COW = register("cow");
    public static final ResourceKey<HeadType> RED_MOOSHROOM = register("mooshroom/red");
    public static final ResourceKey<HeadType> BROWN_MOOSHROOM = register("mooshroom/brown");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapCow(context, COW, ModelType.TEMPERATE_COW, "entity/cow/cow");
        bootstrapMooshroom(context, MushroomCow.MushroomType.RED, RED_MOOSHROOM, "entity/cow/red_mooshroom");
        bootstrapMooshroom(context, MushroomCow.MushroomType.BROWN, BROWN_MOOSHROOM, "entity/cow/brown_mooshroom");
    }

    private static void bootstrapCow(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder(EntityType.COW)
                .shape(8.0, 8.0, 6.0)
                .model(modelType, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapMooshroom(BootstrapContext<HeadType> context, MushroomCow.MushroomType variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.MOOSHROOM)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(EntitySubPredicates.MOOSHROOM.createPredicate(variant));
                })
                .shape(8.0, 8.0, 6.0)
                .model(ModelType.TEMPERATE_COW, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        // Cows
        translationConsumer.accept(COW, "Cow Head");

        // Mooshrooms
        translationConsumer.accept(RED_MOOSHROOM, "Red Mooshroom Head");
        translationConsumer.accept(BROWN_MOOSHROOM, "Brown Mooshroom Head");
    }
}
