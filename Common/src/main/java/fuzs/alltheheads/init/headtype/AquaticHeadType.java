package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class AquaticHeadType {
    public static final ResourceKey<HeadType> COD = register("cod");
    public static final ResourceKey<HeadType> DOLPHIN = register("dolphin");
    public static final ResourceKey<HeadType> GLOW_SQUID = register("glow_squid");
    public static final ResourceKey<HeadType> PUFFERFISH = register("pufferfish");
    public static final ResourceKey<HeadType> SALMON = register("salmon");
    public static final ResourceKey<HeadType> SQUID = register("squid");
    public static final ResourceKey<HeadType> TADPOLE = register("tadpole");
    public static final ResourceKey<HeadType> TURTLE = register("turtle");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        HeadType.builder(EntityType.COD)
                .shape(2.0, 4.0, 3.0)
                .scale(1.5)
                .model(ModelType.COD, ResourceLocationHelper.withDefaultNamespace("entity/fish/cod"))
                .noteBlockSound(SoundEvents.COD_AMBIENT)
                .build(context, AquaticHeadType.COD);
        HeadType.builder(EntityType.DOLPHIN)
                .shape(8.0, 7.0, 6.0)
                .model(ModelType.DOLPHIN, ResourceLocationHelper.withDefaultNamespace("entity/dolphin"))
                .noteBlockSound(SoundEvents.DOLPHIN_AMBIENT)
                .build(context, AquaticHeadType.DOLPHIN);
        HeadType.builder(EntityType.GLOW_SQUID)
                .shape(12.0, 16.0, 12.0)
                .scale(2.0 / 3.0)
                .litModel(ModelType.SQUID, ResourceLocationHelper.withDefaultNamespace("entity/squid/glow_squid"))
                .noteBlockSound(SoundEvents.GLOW_SQUID_AMBIENT)
                .build(context, AquaticHeadType.GLOW_SQUID);
        HeadType.builder(EntityType.PUFFERFISH)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PUFFERFISH, ResourceLocationHelper.withDefaultNamespace("entity/fish/pufferfish"))
                .noteBlockSound(SoundEvents.PUFFER_FISH_STING)
                .build(context, AquaticHeadType.PUFFERFISH);
        HeadType.builder(EntityType.SALMON)
                .shape(2.0, 4.0, 3.0)
                .scale(1.5)
                .model(ModelType.SALMON, ResourceLocationHelper.withDefaultNamespace("entity/fish/salmon"))
                .noteBlockSound(SoundEvents.SALMON_AMBIENT)
                .build(context, AquaticHeadType.SALMON);
        HeadType.builder(EntityType.SQUID)
                .shape(12.0, 16.0, 12.0)
                .scale(2.0 / 3.0)
                .model(ModelType.SQUID, ResourceLocationHelper.withDefaultNamespace("entity/squid/squid"))
                .noteBlockSound(SoundEvents.SQUID_AMBIENT)
                .build(context, AquaticHeadType.SQUID);
        HeadType.builder(EntityType.TADPOLE)
                .shape(3.0, 2.0, 3.0)
                .scale(2.0)
                .model(ModelType.TADPOLE, ResourceLocationHelper.withDefaultNamespace("entity/tadpole/tadpole"))
                .noteBlockSound(SoundEvents.TADPOLE_FLOP)
                .build(context, AquaticHeadType.TADPOLE);
        HeadType.builder(EntityType.TURTLE)
                .shape(6.0, 5.0, 6.0)
                .scale(4.0 / 3.0)
                .model(ModelType.TURTLE, ResourceLocationHelper.withDefaultNamespace("entity/turtle/big_sea_turtle"))
                .noteBlockSound(SoundEvents.TURTLE_AMBIENT_LAND)
                .build(context, AquaticHeadType.TURTLE);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(COD, "Cod Head");
        translationConsumer.accept(DOLPHIN, "Dolphin Head");
        translationConsumer.accept(GLOW_SQUID, "Glow Squid Head");
        translationConsumer.accept(PUFFERFISH, "Pufferfish Head");
        translationConsumer.accept(SALMON, "Salmon Head");
        translationConsumer.accept(SQUID, "Squid Head");
        translationConsumer.accept(TADPOLE, "Tadpole Head");
        translationConsumer.accept(TURTLE, "Turtle Head");
    }
}
