package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.resources.Identifier;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class PiglinHeadType {
    public static final ResourceKey<HeadType> ZOMBIFIED_PIGLIN = register("zombified_piglin");
    public static final ResourceKey<HeadType> PIGLIN_BRUTE = register("piglin_brute");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapPiglin(context,
                EntityType.ZOMBIFIED_PIGLIN,
                ZOMBIFIED_PIGLIN,
                "entity/piglin/zombified_piglin",
                SoundEvents.ZOMBIFIED_PIGLIN_AMBIENT);
        bootstrapPiglin(context,
                EntityType.PIGLIN_BRUTE,
                PIGLIN_BRUTE,
                "entity/piglin/piglin_brute",
                SoundEvents.PIGLIN_BRUTE_AMBIENT);
    }

    private static void bootstrapPiglin(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(10.0, 8.0, 8.0)
                .model(ModelType.PIGLIN, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(ZOMBIFIED_PIGLIN, "Zombified Piglin Head");
        translationConsumer.accept(PIGLIN_BRUTE, "Piglin Brute Head");
    }
}
