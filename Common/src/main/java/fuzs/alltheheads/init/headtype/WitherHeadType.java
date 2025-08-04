package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.Color;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class WitherHeadType {
    public static final ResourceKey<HeadType> WITHER = register("wither");
    public static final ResourceKey<HeadType> SHIELED_WITHER = register("wither/shielded");
    public static final ResourceKey<HeadType> BLUE_WITHER = register("wither/blue");
    public static final ResourceKey<HeadType> SHIELDED_BLUE_WITHER = register("wither/blue/shielded");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapWither(context, WitherHeadType.WITHER, "entity/wither/wither");
        bootstrapWither(context, WitherHeadType.BLUE_WITHER, "entity/wither/wither_invulnerable");
        bootstrapPoweredWither(context, WitherHeadType.SHIELED_WITHER, "entity/wither/wither");
        bootstrapPoweredWither(context, WitherHeadType.SHIELDED_BLUE_WITHER, "entity/wither/wither_invulnerable");
    }

    private static void bootstrapWither(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.WITHER)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapPoweredWither(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.WITHER)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .dyedModel(ModelType.WITHER_SHIELD,
                        ResourceLocationHelper.withDefaultNamespace("entity/wither/wither_armor"),
                        new Color.Constant(0xFF808080))
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(BLUE_WITHER, "Blue Wither Head");
        translationConsumer.accept(SHIELDED_BLUE_WITHER, "Shielded Blue Wither Head");
        translationConsumer.accept(SHIELED_WITHER, "Shielded Wither Head");
        translationConsumer.accept(WITHER, "Wither Head");
    }
}
