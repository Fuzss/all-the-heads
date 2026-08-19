package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class RabbitHeadType {
    public static final ResourceKey<HeadType> BROWN_RABBIT = register("rabbit/brown");
    public static final ResourceKey<HeadType> WHITE_RABBIT = register("rabbit/white");
    public static final ResourceKey<HeadType> BLACK_RABBIT = register("rabbit/black");
    public static final ResourceKey<HeadType> WHITE_SPLOTCHED_RABBIT = register("rabbit/white_splotched");
    public static final ResourceKey<HeadType> GOLD_RABBIT = register("rabbit/gold");
    public static final ResourceKey<HeadType> SALT_RABBIT = register("rabbit/salt");
    public static final ResourceKey<HeadType> EVIL_RABBIT = register("rabbit/evil");
    public static final ResourceKey<HeadType> TOAST_RABBIT = register("rabbit/toast");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapRabbit(context, BROWN_RABBIT, "entity/rabbit/rabbit_brown");
        bootstrapRabbit(context, WHITE_RABBIT, "entity/rabbit/rabbit_white");
        bootstrapRabbit(context, BLACK_RABBIT, "entity/rabbit/rabbit_black");
        bootstrapRabbit(context, WHITE_SPLOTCHED_RABBIT, "entity/rabbit/rabbit_white_splotched");
        bootstrapRabbit(context, GOLD_RABBIT, "entity/rabbit/rabbit_gold");
        bootstrapRabbit(context, SALT_RABBIT, "entity/rabbit/rabbit_salt");
        bootstrapRabbit(context, EVIL_RABBIT, "entity/rabbit/rabbit_caerbannog", SoundEvents.RABBIT_ATTACK);
        HeadType.builder()
                .shape(5.0, 5.0, 5.0)
                .scale(1.2)
                .model(ModelType.RABBIT, Identifier.withDefaultNamespace("entity/rabbit/rabbit_toast"))
                .noteBlockSound(SoundEvents.RABBIT_AMBIENT)
                .build(context, TOAST_RABBIT);
    }

    private static void bootstrapRabbit(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        bootstrapRabbit(context, resourceKey, textureLocation, SoundEvents.RABBIT_AMBIENT);
    }

    private static void bootstrapRabbit(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(5.0, 5.0, 5.0)
                .scale(1.2)
                .model(ModelType.RABBIT, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapRabbit(context, Rabbit.Variant.BROWN, BROWN_RABBIT);
        bootstrapRabbit(context, Rabbit.Variant.WHITE, WHITE_RABBIT);
        bootstrapRabbit(context, Rabbit.Variant.BLACK, BLACK_RABBIT);
        bootstrapRabbit(context, Rabbit.Variant.WHITE_SPLOTCHED, WHITE_SPLOTCHED_RABBIT);
        bootstrapRabbit(context, Rabbit.Variant.GOLD, GOLD_RABBIT);
        bootstrapRabbit(context, Rabbit.Variant.SALT, SALT_RABBIT);
        bootstrapRabbit(context, Rabbit.Variant.EVIL, EVIL_RABBIT);
        bootstrap(context, TOAST_RABBIT, EntityTypes.RABBIT, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.CUSTOM_NAME,
                            Component.literal("Toast")))
                    .build());
        });
    }

    private static void bootstrapRabbit(BootstrapContext<LootItemCondition> context, Rabbit.Variant variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.RABBIT, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.RABBIT_VARIANT, variant))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(BLACK_RABBIT, "Black Rabbit Head");
        translationConsumer.accept(BROWN_RABBIT, "Brown Rabbit Head");
        translationConsumer.accept(EVIL_RABBIT, "Evil Rabbit Head");
        translationConsumer.accept(GOLD_RABBIT, "Gold Rabbit Head");
        translationConsumer.accept(SALT_RABBIT, "Salt Rabbit Head");
        translationConsumer.accept(TOAST_RABBIT, "Toast Rabbit Head");
        translationConsumer.accept(WHITE_RABBIT, "White Rabbit Head");
        translationConsumer.accept(WHITE_SPLOTCHED_RABBIT, "White Splotched Rabbit Head");
    }
}
