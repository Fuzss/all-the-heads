package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class ChickenHeadType {
    public static final ResourceKey<HeadType> CHICKEN = register("chicken");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapChicken(context, CHICKEN, ModelType.CHICKEN, "entity/chicken");
    }

    private static void bootstrapChicken(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder(EntityType.CHICKEN)
                .shape(4.0, 6.0, 3.0)
                .scale(1.5)
                .model(modelType, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.CHICKEN_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(CHICKEN, "Chicken Head");
    }
}
