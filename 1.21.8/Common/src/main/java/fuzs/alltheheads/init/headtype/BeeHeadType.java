package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.BeePredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;

import java.util.Optional;
import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class BeeHeadType {
    public static final ResourceKey<HeadType> BEE = register("bee");
    public static final ResourceKey<HeadType> POLLINATED_BEE = register("bee/pollinated");
    public static final ResourceKey<HeadType> ANGRY_BEE = register("bee/angry");
    public static final ResourceKey<HeadType> POLLINATED_ANGRY_BEE = register("bee/angry/pollinated");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        BeeHeadType.bootstrapBee(context, false, false, BeeHeadType.BEE, "entity/bee/bee");
        BeeHeadType.bootstrapBee(context, false, true, BeeHeadType.POLLINATED_BEE, "entity/bee/bee_nectar");
        BeeHeadType.bootstrapBee(context, true, false, BeeHeadType.ANGRY_BEE, "entity/bee/bee_angry");
        BeeHeadType.bootstrapBee(context, true, true, BeeHeadType.POLLINATED_ANGRY_BEE, "entity/bee/bee_angry_nectar");
    }

    private static void bootstrapBee(BootstrapContext<HeadType> context, boolean angry, boolean hasPollen, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.BEE)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(new BeePredicate(Optional.of(angry), Optional.of(hasPollen)));
                })
                .shape(7.0, 7.0, 6.0)
                .model(ModelType.BEE, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.BEE_LOOP)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(BEE, "Bee Head");
        translationConsumer.accept(POLLINATED_BEE, "Pollinated Bee Head");
        translationConsumer.accept(ANGRY_BEE, "Angry Bee Head");
        translationConsumer.accept(POLLINATED_ANGRY_BEE, "Pollinated Angry Bee Head");
    }
}
