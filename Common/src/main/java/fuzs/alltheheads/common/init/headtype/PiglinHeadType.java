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

public class PiglinHeadType {
    public static final ResourceKey<HeadType> ZOMBIFIED_PIGLIN = register("zombified_piglin");
    public static final ResourceKey<HeadType> PIGLIN_BRUTE = register("piglin_brute");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapPiglin(context, ZOMBIFIED_PIGLIN,
                "entity/piglin/zombified_piglin",
                SoundEvents.ZOMBIFIED_PIGLIN_AMBIENT);
        bootstrapPiglin(context, PIGLIN_BRUTE,
                "entity/piglin/piglin_brute",
                SoundEvents.PIGLIN_BRUTE_AMBIENT);
    }

    private static void bootstrapPiglin(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(10.0, 8.0, 8.0)
                .model(ModelType.PIGLIN, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrap(context, ZOMBIFIED_PIGLIN, EntityTypes.ZOMBIFIED_PIGLIN);
        bootstrap(context, PIGLIN_BRUTE, EntityTypes.PIGLIN_BRUTE);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(ZOMBIFIED_PIGLIN, "Zombified Piglin Head");
        translationConsumer.accept(PIGLIN_BRUTE, "Piglin Brute Head");
    }
}
