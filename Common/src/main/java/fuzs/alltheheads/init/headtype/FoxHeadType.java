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
import net.minecraft.world.entity.animal.Fox;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class FoxHeadType {
    public static final ResourceKey<HeadType> FOX = register("fox");
    public static final ResourceKey<HeadType> SNOW_FOX = register("fox/snow");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapFox(context, Fox.Type.RED, FOX, "entity/fox/fox");
        bootstrapFox(context, Fox.Type.SNOW, SNOW_FOX, "entity/fox/snow_fox");
    }

    private static void bootstrapFox(BootstrapContext<HeadType> context, Fox.Type variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.FOX)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(EntitySubPredicates.FOX.createPredicate(variant));
                })
                .shape(8.0, 6.0, 6.0)
                .model(ModelType.FOX, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.FOX_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(FOX, "Fox Head");
        translationConsumer.accept(SNOW_FOX, "Snow Fox Head");
    }
}
