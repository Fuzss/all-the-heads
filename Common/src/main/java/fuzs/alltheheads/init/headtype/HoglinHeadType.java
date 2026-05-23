package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class HoglinHeadType {
    public static final ResourceKey<HeadType> HOGLIN = register("hoglin");
    public static final ResourceKey<HeadType> ZOGLIN = register("zoglin");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapHoglin(context, EntityType.HOGLIN, HOGLIN, "entity/hoglin/hoglin", SoundEvents.HOGLIN_AMBIENT);
        bootstrapHoglin(context, EntityType.ZOGLIN, ZOGLIN, "entity/hoglin/zoglin", SoundEvents.ZOGLIN_AMBIENT);
    }

    private static void bootstrapHoglin(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(14.0, 18.0, 8.0)
                .scale(0.625)
                .model(ModelType.HOGLIN, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(HOGLIN, "Hoglin Head");
        translationConsumer.accept(ZOGLIN, "Zoglin Head");
    }
}
