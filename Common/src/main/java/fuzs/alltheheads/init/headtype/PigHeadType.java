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

public class PigHeadType {
    public static final ResourceKey<HeadType> PIG = register("pig");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapPig(context, PIG, "entity/pig/pig");
    }

    private static void bootstrapPig(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.PIG)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PIG, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.PIG_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(PIG, "Pig Head");
    }
}
