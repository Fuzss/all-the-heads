package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.resources.Identifier;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class IllagerHeadType {
    public static final ResourceKey<HeadType> VINDICATOR = register("vindicator");
    public static final ResourceKey<HeadType> EVOKER = register("evoker");
    public static final ResourceKey<HeadType> PILLAGER = register("pillager");
    public static final ResourceKey<HeadType> ILLUSIONER = register("illusioner");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapIllager(context,
                EntityType.VINDICATOR,
                VINDICATOR,
                "entity/illager/vindicator",
                SoundEvents.VINDICATOR_AMBIENT);
        bootstrapIllager(context, EntityType.EVOKER, EVOKER, "entity/illager/evoker", SoundEvents.EVOKER_AMBIENT);
        bootstrapIllager(context,
                EntityType.PILLAGER,
                PILLAGER,
                "entity/illager/pillager",
                SoundEvents.PILLAGER_AMBIENT);
        bootstrapIllager(context,
                EntityType.ILLUSIONER,
                ILLUSIONER,
                "entity/illager/illusioner",
                SoundEvents.ILLUSIONER_AMBIENT);
    }

    private static void bootstrapIllager(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, Identifier.withDefaultNamespace(textureLocation))
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
