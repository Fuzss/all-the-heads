package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.GhastPredicate;
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

public class GhastHeadType {
    public static final ResourceKey<HeadType> GHAST = register("ghast");
    public static final ResourceKey<HeadType> CHARGING_GHAST = register("ghast/charging");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapGhast(context, false, GHAST, "entity/ghast/ghast", SoundEvents.GHAST_AMBIENT);
        bootstrapGhast(context, true, CHARGING_GHAST, "entity/ghast/ghast_shooting", SoundEvents.GHAST_WARN);
    }

    private static void bootstrapGhast(BootstrapContext<HeadType> context, boolean charging, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.GHAST)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(GhastPredicate.isCharging(charging));
                })
                .shape(16.0, 16.0, 16.0)
                .scale(0.625)
                .model(ModelType.GHAST, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(GHAST, "Ghast Head");
        translationConsumer.accept(CHARGING_GHAST, "Charging Ghast Head");
    }
}
