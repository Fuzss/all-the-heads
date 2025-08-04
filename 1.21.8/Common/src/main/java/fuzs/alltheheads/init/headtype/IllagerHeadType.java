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

public class IllagerHeadType {
    public static final ResourceKey<HeadType> VINDICATOR = register("vindicator");
    public static final ResourceKey<HeadType> EVOKER = register("evoker");
    public static final ResourceKey<HeadType> PILLAGER = register("pillager");
    public static final ResourceKey<HeadType> ILLUSIONER = register("illusioner");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        IllagerHeadType.bootstrapIllager(context,
                EntityType.VINDICATOR,
                IllagerHeadType.VINDICATOR,
                "entity/illager/vindicator",
                SoundEvents.VINDICATOR_AMBIENT);
        IllagerHeadType.bootstrapIllager(context,
                EntityType.EVOKER,
                IllagerHeadType.EVOKER,
                "entity/illager/evoker",
                SoundEvents.EVOKER_AMBIENT);
        IllagerHeadType.bootstrapIllager(context,
                EntityType.PILLAGER,
                IllagerHeadType.PILLAGER,
                "entity/illager/pillager",
                SoundEvents.PILLAGER_AMBIENT);
        IllagerHeadType.bootstrapIllager(context,
                EntityType.ILLUSIONER,
                IllagerHeadType.ILLUSIONER,
                "entity/illager/illusioner",
                SoundEvents.ILLUSIONER_AMBIENT);
    }

    private static void bootstrapIllager(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(VINDICATOR, "Vindicator Head");
        translationConsumer.accept(EVOKER, "Evoker Head");
        translationConsumer.accept(PILLAGER, "Pillager Head");
        translationConsumer.accept(ILLUSIONER, "Illusioner Head");
    }
}
