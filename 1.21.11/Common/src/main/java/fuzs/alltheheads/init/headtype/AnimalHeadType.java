package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.resources.Identifier;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class AnimalHeadType {
    public static final ResourceKey<HeadType> ARMADILLO = register("armadillo");
    public static final ResourceKey<HeadType> BAT = register("bat");
    public static final ResourceKey<HeadType> GOAT = register("goat");
    public static final ResourceKey<HeadType> IRON_GOLEM = register("iron_golem");
    public static final ResourceKey<HeadType> POLAR_BEAR = register("polar_bear");
    public static final ResourceKey<HeadType> SNIFFER = register("sniffer");
    public static final ResourceKey<HeadType> SNOW_GOLEM = register("snow_golem");
    public static final ResourceKey<HeadType> WANDERING_TRADER = register("wandering_trader");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        HeadType.builder(EntityType.ARMADILLO)
                .shape(3.0, 5.0, 2.0)
                .scale(1.6)
                .model(ModelType.ARMADILLO, Identifier.withDefaultNamespace("entity/armadillo"))
                .noteBlockSound(SoundEvents.ARMADILLO_AMBIENT)
                .build(context, ARMADILLO);
        HeadType.builder(EntityType.BAT)
                .shape(4.0, 3.0, 2.0)
                .scale(1.5)
                .model(ModelType.BAT, Identifier.withDefaultNamespace("entity/bat"))
                .noteBlockSound(SoundEvents.BAT_AMBIENT)
                .build(context, BAT);
        HeadType.builder(EntityType.GOAT)
                .shape(5.0, 10.0, 7.0)
                .scale(0.8)
                .model(ModelType.GOAT, Identifier.withDefaultNamespace("entity/goat/goat"))
                .noteBlockSound(SoundEvents.GOAT_SCREAMING_AMBIENT)
                .build(context, GOAT);
        HeadType.builder(EntityType.IRON_GOLEM)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.IRON_GOLEM,
                        Identifier.withDefaultNamespace("entity/iron_golem/iron_golem"))
                .noteBlockSound(SoundEvents.IRON_GOLEM_REPAIR)
                .build(context, IRON_GOLEM);
        HeadType.builder(EntityType.POLAR_BEAR)
                .shape(7.0, 7.0, 7.0)
                .scale(8.0 / 7.0)
                .model(ModelType.POLAR_BEAR, Identifier.withDefaultNamespace("entity/bear/polarbear"))
                .noteBlockSound(SoundEvents.POLAR_BEAR_AMBIENT)
                .build(context, POLAR_BEAR);
        HeadType.builder(EntityType.SNIFFER)
                .shape(13.0, 14.0, 20.0)
                .scale(0.5)
                .model(ModelType.SNIFFER, Identifier.withDefaultNamespace("entity/sniffer/sniffer"))
                .noteBlockSound(SoundEvents.SNIFFER_IDLE)
                .build(context, SNIFFER);
        HeadType.builder(EntityType.SNOW_GOLEM)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, Identifier.withDefaultNamespace("entity/snow_golem"))
                .noteBlockSound(SoundEvents.SNOW_GOLEM_SHOOT)
                .build(context, SNOW_GOLEM);
        HeadType.builder(EntityType.WANDERING_TRADER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.VILLAGER, Identifier.withDefaultNamespace("entity/wandering_trader"))
                .noteBlockSound(SoundEvents.WANDERING_TRADER_AMBIENT)
                .build(context, WANDERING_TRADER);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(ARMADILLO, "Armadillo Head");
        translationConsumer.accept(BAT, "Bat Head");
        translationConsumer.accept(GOAT, "Goat Head");
        translationConsumer.accept(IRON_GOLEM, "Iron Golem Head");
        translationConsumer.accept(POLAR_BEAR, "Polar Bear Head");
        translationConsumer.accept(SNIFFER, "Sniffer Head");
        translationConsumer.accept(SNOW_GOLEM, "Snow Golem Head");
        translationConsumer.accept(WANDERING_TRADER, "Wandering Trader Head");
    }
}
