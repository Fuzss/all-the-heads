package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.Color;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class WitherHeadType {
    public static final ResourceKey<HeadType> WITHER = register("wither");
    public static final ResourceKey<HeadType> SHIELED_WITHER = register("wither/shielded");
    public static final ResourceKey<HeadType> BLUE_WITHER = register("wither/blue");
    public static final ResourceKey<HeadType> SHIELDED_BLUE_WITHER = register("wither/blue/shielded");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapWither(context, WITHER, "entity/wither/wither");
        bootstrapWither(context, BLUE_WITHER, "entity/wither/wither_invulnerable");
        bootstrapPoweredWither(context, SHIELED_WITHER, "entity/wither/wither");
        bootstrapPoweredWither(context, SHIELDED_BLUE_WITHER, "entity/wither/wither_invulnerable");
    }

    private static void bootstrapWither(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityTypes.WITHER)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.WITHER_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapPoweredWither(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityTypes.WITHER)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.HUMANOID, Identifier.withDefaultNamespace(textureLocation))
                .dyedModel(ModelType.WITHER_SHIELD,
                        Identifier.withDefaultNamespace("entity/wither/wither_armor"),
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
