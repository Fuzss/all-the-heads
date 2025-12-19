package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.fox.Fox;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class FoxHeadType {
    public static final ResourceKey<HeadType> FOX = register("fox");
    public static final ResourceKey<HeadType> SNOW_FOX = register("fox/snow");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapFox(context, Fox.Variant.RED, FOX, "entity/fox/fox");
        bootstrapFox(context, Fox.Variant.SNOW, SNOW_FOX, "entity/fox/snow_fox");
    }

    private static void bootstrapFox(BootstrapContext<HeadType> context, Fox.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.FOX)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.FOX_VARIANT, variant))
                            .build());
                })
                .shape(8.0, 6.0, 6.0)
                .model(ModelType.FOX, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.FOX_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(FOX, "Fox Head");
        translationConsumer.accept(SNOW_FOX, "Snow Fox Head");
    }
}
