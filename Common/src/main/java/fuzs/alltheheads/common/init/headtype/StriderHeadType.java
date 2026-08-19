package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.StriderPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class StriderHeadType {
    public static final ResourceKey<HeadType> STRIDER = register("strider");
    public static final ResourceKey<HeadType> COLD_STRIDER = register("strider/cold");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapStrider(context, STRIDER, "entity/strider/strider", SoundEvents.STRIDER_HAPPY);
        bootstrapStrider(context, COLD_STRIDER, "entity/strider/strider_cold", SoundEvents.STRIDER_AMBIENT);
    }

    private static void bootstrapStrider(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(16.0, 14.0, 16.0)
                .scale(0.625)
                .model(ModelType.STRIDER, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapStrider(context, false, STRIDER);
        bootstrapStrider(context, true, COLD_STRIDER);
    }

    private static void bootstrapStrider(BootstrapContext<LootItemCondition> context, boolean isCold, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.STRIDER, (EntityPredicate.Builder builder) -> {
            builder.put(StriderPredicate.CODEC, StriderPredicate.isCold(isCold));
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(STRIDER, "Strider Head");
        translationConsumer.accept(COLD_STRIDER, "Cold Strider Head");
    }
}
