package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
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

public class HoglinHeadType {
    public static final ResourceKey<HeadType> HOGLIN = register("hoglin");
    public static final ResourceKey<HeadType> ZOGLIN = register("zoglin");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapHoglin(context, HOGLIN, "entity/hoglin/hoglin", SoundEvents.HOGLIN_AMBIENT);
        bootstrapHoglin(context, ZOGLIN, "entity/hoglin/zoglin", SoundEvents.ZOGLIN_AMBIENT);
    }

    private static void bootstrapHoglin(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(14.0, 18.0, 8.0)
                .scale(0.625)
                .model(ModelType.HOGLIN, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrap(context, HOGLIN, EntityTypes.HOGLIN);
        bootstrap(context, ZOGLIN, EntityTypes.ZOGLIN);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(HOGLIN, "Hoglin Head");
        translationConsumer.accept(ZOGLIN, "Zoglin Head");
    }
}
