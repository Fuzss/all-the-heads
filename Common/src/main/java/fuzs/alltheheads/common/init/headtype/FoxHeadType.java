package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class FoxHeadType {
    public static final ResourceKey<HeadType> FOX = register("fox");
    public static final ResourceKey<HeadType> SNOW_FOX = register("fox/snow");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapFox(context, FOX, "entity/fox/fox");
        bootstrapFox(context, SNOW_FOX, "entity/fox/fox_snow");
    }

    private static void bootstrapFox(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 6.0, 6.0)
                .model(ModelType.FOX, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.FOX_AMBIENT)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapFox(context, Fox.Variant.RED, FOX);
        bootstrapFox(context, Fox.Variant.SNOW, SNOW_FOX);
    }

    private static void bootstrapFox(BootstrapContext<LootItemCondition> context, Fox.Variant variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.FOX, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.FOX_VARIANT, variant))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(FOX, "Fox Head");
        translationConsumer.accept(SNOW_FOX, "Snow Fox Head");
    }
}
