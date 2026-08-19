package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class AquaticHeadType {
    public static final ResourceKey<HeadType> COD = register("cod");
    public static final ResourceKey<HeadType> DOLPHIN = register("dolphin");
    public static final ResourceKey<HeadType> GLOW_SQUID = register("glow_squid");
    public static final ResourceKey<HeadType> PUFFERFISH = register("pufferfish");
    public static final ResourceKey<HeadType> SALMON = register("salmon");
    public static final ResourceKey<HeadType> SQUID = register("squid");
    public static final ResourceKey<HeadType> TADPOLE = register("tadpole");
    public static final ResourceKey<HeadType> TURTLE = register("turtle");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        HeadType.builder()
                .shape(2.0, 4.0, 3.0)
                .scale(1.5)
                .model(ModelType.COD, Identifier.withDefaultNamespace("entity/fish/cod"))
                .noteBlockSound(SoundEvents.COD_FLOP)
                .build(context, COD);
        HeadType.builder()
                .shape(8.0, 7.0, 6.0)
                .model(ModelType.DOLPHIN, Identifier.withDefaultNamespace("entity/dolphin/dolphin"))
                .noteBlockSound(SoundEvents.DOLPHIN_AMBIENT_WATER)
                .build(context, DOLPHIN);
        HeadType.builder()
                .shape(12.0, 16.0, 12.0)
                .scale(2.0 / 3.0)
                .litModel(ModelType.SQUID, Identifier.withDefaultNamespace("entity/squid/glow_squid"))
                .noteBlockSound(SoundEvents.GLOW_SQUID_AMBIENT)
                .build(context, GLOW_SQUID);
        HeadType.builder()
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PUFFERFISH, Identifier.withDefaultNamespace("entity/fish/pufferfish"))
                .noteBlockSound(SoundEvents.PUFFER_FISH_STING)
                .build(context, PUFFERFISH);
        HeadType.builder()
                .shape(2.0, 4.0, 3.0)
                .scale(1.5)
                .model(ModelType.SALMON, Identifier.withDefaultNamespace("entity/fish/salmon"))
                .noteBlockSound(SoundEvents.SALMON_FLOP)
                .build(context, SALMON);
        HeadType.builder()
                .shape(12.0, 16.0, 12.0)
                .scale(2.0 / 3.0)
                .model(ModelType.SQUID, Identifier.withDefaultNamespace("entity/squid/squid"))
                .noteBlockSound(SoundEvents.SQUID_AMBIENT)
                .build(context, SQUID);
        HeadType.builder()
                .shape(3.0, 2.0, 3.0)
                .scale(2.0)
                .model(ModelType.TADPOLE, Identifier.withDefaultNamespace("entity/tadpole/tadpole"))
                .noteBlockSound(SoundEvents.TADPOLE_FLOP)
                .build(context, TADPOLE);
        HeadType.builder()
                .shape(6.0, 5.0, 6.0)
                .scale(4.0 / 3.0)
                .model(ModelType.TURTLE, Identifier.withDefaultNamespace("entity/turtle/turtle"))
                .noteBlockSound(SoundEvents.TURTLE_AMBIENT_LAND)
                .build(context, TURTLE);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrap(context, COD, EntityTypes.COD);
        bootstrap(context, DOLPHIN, EntityTypes.DOLPHIN);
        bootstrap(context, GLOW_SQUID, EntityTypes.GLOW_SQUID);
        bootstrap(context, PUFFERFISH, EntityTypes.PUFFERFISH);
        bootstrap(context, SALMON, EntityTypes.SALMON);
        bootstrap(context, SQUID, EntityTypes.SQUID);
        bootstrap(context, TADPOLE, EntityTypes.TADPOLE);
        bootstrap(context, TURTLE, EntityTypes.TURTLE);
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
