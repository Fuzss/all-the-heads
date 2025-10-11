package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.CopperGolemPredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.WeatheringCopper;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class CopperGolemHeadType {
    public static final ResourceKey<HeadType> COPPER_GOLEM = register("copper_golem");
    public static final ResourceKey<HeadType> EXPOSED_COPPER_GOLEM = register("copper_golem/exposed");
    public static final ResourceKey<HeadType> OXIDIZED_COPPER_GOLEM = register("copper_golem/oxidized");
    public static final ResourceKey<HeadType> WEATHERED_COPPER_GOLEM = register("copper_golem/weathered");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapCopperGolem(context,
                WeatheringCopper.WeatherState.UNAFFECTED,
                COPPER_GOLEM,
                "entity/copper_golem/copper_golem",
                "entity/copper_golem/copper_golem_eyes",
                SoundEvents.COPPER_GOLEM_SPIN);
        bootstrapCopperGolem(context,
                WeatheringCopper.WeatherState.EXPOSED,
                EXPOSED_COPPER_GOLEM,
                "entity/copper_golem/exposed_copper_golem",
                "entity/copper_golem/exposed_copper_golem_eyes",
                SoundEvents.COPPER_GOLEM_SPIN);
        bootstrapCopperGolem(context,
                WeatheringCopper.WeatherState.OXIDIZED,
                OXIDIZED_COPPER_GOLEM,
                "entity/copper_golem/oxidized_copper_golem",
                "entity/copper_golem/oxidized_copper_golem_eyes",
                SoundEvents.COPPER_GOLEM_OXIDIZED_SPIN);
        bootstrapCopperGolem(context,
                WeatheringCopper.WeatherState.WEATHERED,
                WEATHERED_COPPER_GOLEM,
                "entity/copper_golem/weathered_copper_golem",
                "entity/copper_golem/weathered_copper_golem_eyes",
                SoundEvents.COPPER_GOLEM_WEATHERED_SPIN);
    }

    private static void bootstrapCopperGolem(BootstrapContext<HeadType> context, WeatheringCopper.WeatherState state, ResourceKey<HeadType> resourceKey, String textureLocation, String eyesLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.COPPER_GOLEM)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(CopperGolemPredicate.hasState(state));
                })
                .shape(8.0, 5.0, 10.0)
                .model(ModelType.COPPER_GOLEM, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .model(ModelType.COPPER_GOLEM_EYES, ResourceLocationHelper.withDefaultNamespace(eyesLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(COPPER_GOLEM, "Copper Golem Head");
        translationConsumer.accept(EXPOSED_COPPER_GOLEM, "Exposed Copper Golem Head");
        translationConsumer.accept(OXIDIZED_COPPER_GOLEM, "Oxidized Copper Golem Head");
        translationConsumer.accept(WEATHERED_COPPER_GOLEM, "Weathered Copper Golem Head");
    }
}
