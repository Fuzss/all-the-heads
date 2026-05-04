package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class CamelHeadType {
    public static final ResourceKey<HeadType> CAMEL = register("camel");
    public static final ResourceKey<HeadType> CAMEL_HUSK = register("camel_husk");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapCamel(context, EntityType.CAMEL, CAMEL, "entity/camel/camel", SoundEvents.CAMEL_AMBIENT);
        bootstrapCamel(context,
                EntityType.CAMEL_HUSK,
                CAMEL_HUSK,
                "entity/camel/camel_husk",
                SoundEvents.CAMEL_HUSK_AMBIENT);
    }

    private static void bootstrapCamel(BootstrapContext<HeadType> context, EntityType<?> entityType, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(entityType)
                .shape(7.0, 14.0, 7.0)
                .scale(6.0 / 7.0)
                .model(ModelType.CAMEL, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(CAMEL, "Camel Head");
        translationConsumer.accept(CAMEL_HUSK, "Camel Husk Head");
    }
}
