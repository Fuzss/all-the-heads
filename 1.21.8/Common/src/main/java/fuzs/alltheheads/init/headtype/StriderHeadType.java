package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.StriderPredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class StriderHeadType {
    public static final ResourceKey<HeadType> STRIDER = register("strider");
    public static final ResourceKey<HeadType> COLD_STRIDER = register("strider/cold");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        StriderHeadType.bootstrapStrider(context,
                false,
                StriderHeadType.STRIDER,
                "entity/strider/strider",
                SoundEvents.STRIDER_HAPPY);
        StriderHeadType.bootstrapStrider(context,
                true,
                StriderHeadType.COLD_STRIDER,
                "entity/strider/strider_cold",
                SoundEvents.STRIDER_AMBIENT);
    }

    private static void bootstrapStrider(BootstrapContext<HeadType> context, boolean isCold, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.STRIDER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(StriderPredicate.isCold(isCold));
                })
                .shape(16.0, 14.0, 16.0)
                .scale(0.625)
                .model(ModelType.STRIDER, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(STRIDER, "Strider Head");
        translationConsumer.accept(COLD_STRIDER, "Cold Strider Head");
    }
}
