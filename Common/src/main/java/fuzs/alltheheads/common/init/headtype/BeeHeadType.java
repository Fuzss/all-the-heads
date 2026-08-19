package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.BeePredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;

import java.util.Optional;
import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class BeeHeadType {
    public static final ResourceKey<HeadType> BEE = register("bee");
    public static final ResourceKey<HeadType> POLLINATED_BEE = register("bee/pollinated");
    public static final ResourceKey<HeadType> ANGRY_BEE = register("bee/angry");
    public static final ResourceKey<HeadType> POLLINATED_ANGRY_BEE = register("bee/angry/pollinated");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        bootstrapBee(context, false, false, BEE, "entity/bee/bee");
        bootstrapBee(context, false, true, POLLINATED_BEE, "entity/bee/bee_nectar");
        bootstrapBee(context, true, false, ANGRY_BEE, "entity/bee/bee_angry");
        bootstrapBee(context, true, true, POLLINATED_ANGRY_BEE, "entity/bee/bee_angry_nectar");
    }

    private static void bootstrapBee(BootstrapContext<HeadType> context, boolean angry, boolean hasPollen, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityTypes.BEE)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.put(BeePredicate.CODEC, new BeePredicate(Optional.of(angry), Optional.of(hasPollen)));
                })
                .shape(7.0, 7.0, 6.0)
                .model(ModelType.BEE, Identifier.withDefaultNamespace(textureLocation))
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
