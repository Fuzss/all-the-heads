package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class CubeMobHeadType {
    public static final ResourceKey<HeadType> MAGMA_CUBE = register("magma_cube");
    public static final ResourceKey<HeadType> SLIME = register("slime");
    public static final ResourceKey<HeadType> SULFUR_CUBE = register("sulfur_cube");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        HeadType.builder(EntityTypes.MAGMA_CUBE)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MAGMA_CUBE, Identifier.withDefaultNamespace("entity/slime/magmacube"))
                .noteBlockSound(SoundEvents.MAGMA_CUBE_SQUISH_SMALL)
                .build(context, MAGMA_CUBE);
        HeadType.builder(EntityTypes.SLIME)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SLIME, Identifier.withDefaultNamespace("entity/slime/slime"))
                .model(ModelType.SLIME_GEL, Identifier.withDefaultNamespace("entity/slime/slime"))
                .noteBlockSound(SoundEvents.SLIME_SQUISH)
                .build(context, SLIME);
        // TODO the sound is different when there is a block inside, make sure to use it when adding those variants
        HeadType.builder(EntityTypes.SULFUR_CUBE)
                .shape(18.0, 18.0, 18.0)
                .scale(8.0 / 18.0)
                .model(ModelType.SULFUR_CUBE, Identifier.withDefaultNamespace("entity/sulfur_cube/sulfur_cube_inner"))
                .model(ModelType.SULFUR_CUBE_GEL,
                        Identifier.withDefaultNamespace("entity/sulfur_cube/sulfur_cube_outer"))
                .noteBlockSound(SoundEvents.SULFUR_CUBE_SQUISH)
                .build(context, SULFUR_CUBE);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(MAGMA_CUBE, "Magma Cube Head");
        translationConsumer.accept(SLIME, "Slime Head");
        translationConsumer.accept(SULFUR_CUBE, "Sulfur Cube Head");
    }
}
