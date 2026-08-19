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
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class LlamaHeadType {
    public static final ResourceKey<HeadType> CREAMY_LLAMA = register("llama/creamy");
    public static final ResourceKey<HeadType> WHITE_LLAMA = register("llama/white");
    public static final ResourceKey<HeadType> BROWN_LLAMA = register("llama/brown");
    public static final ResourceKey<HeadType> GRAY_LLAMA = register("llama/gray");
    public static final ResourceKey<HeadType> CREAMY_TRADER_LLAMA = register("trader_llama/creamy");
    public static final ResourceKey<HeadType> WHITE_TRADER_LLAMA = register("trader_llama/white");
    public static final ResourceKey<HeadType> BROWN_TRADER_LLAMA = register("trader_llama/brown");
    public static final ResourceKey<HeadType> GRAY_TRADER_LLAMA = register("trader_llama/gray");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapLlama(context, CREAMY_LLAMA, "entity/llama/llama_creamy");
        bootstrapLlama(context, WHITE_LLAMA, "entity/llama/llama_white");
        bootstrapLlama(context, BROWN_LLAMA, "entity/llama/llama_brown");
        bootstrapLlama(context, GRAY_LLAMA, "entity/llama/llama_gray");
        bootstrapTraderLlama(context, CREAMY_TRADER_LLAMA, "entity/llama/llama_creamy");
        bootstrapTraderLlama(context, WHITE_TRADER_LLAMA, "entity/llama/llama_white");
        bootstrapTraderLlama(context, BROWN_TRADER_LLAMA, "entity/llama/llama_brown");
        bootstrapTraderLlama(context, GRAY_TRADER_LLAMA, "entity/llama/llama_gray");
    }

    private static void bootstrapLlama(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 10.0, 6.0)
                .model(ModelType.LLAMA, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.LLAMA_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapTraderLlama(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 10.0, 6.0)
                .model(ModelType.LLAMA, Identifier.withDefaultNamespace(textureLocation))
                .model(ModelType.LLAMA_DECOR,
                        Identifier.withDefaultNamespace("entity/equipment/llama_body/trader_llama"))
                .noteBlockSound(SoundEvents.LLAMA_AMBIENT)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapLlama(context, Llama.Variant.CREAMY, CREAMY_LLAMA);
        bootstrapLlama(context, Llama.Variant.WHITE, WHITE_LLAMA);
        bootstrapLlama(context, Llama.Variant.BROWN, BROWN_LLAMA);
        bootstrapLlama(context, Llama.Variant.GRAY, GRAY_LLAMA);
        bootstrapTraderLlama(context, Llama.Variant.CREAMY, CREAMY_TRADER_LLAMA);
        bootstrapTraderLlama(context, Llama.Variant.WHITE, WHITE_TRADER_LLAMA);
        bootstrapTraderLlama(context, Llama.Variant.BROWN, BROWN_TRADER_LLAMA);
        bootstrapTraderLlama(context, Llama.Variant.GRAY, GRAY_TRADER_LLAMA);
    }

    private static void bootstrapLlama(BootstrapContext<LootItemCondition> context, Llama.Variant variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.LLAMA, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.LLAMA_VARIANT, variant))
                    .build());
        });
    }

    private static void bootstrapTraderLlama(BootstrapContext<LootItemCondition> context, Llama.Variant variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.TRADER_LLAMA, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.LLAMA_VARIANT, variant))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(CREAMY_LLAMA, "Creamy Llama Head");
        translationConsumer.accept(WHITE_LLAMA, "White Llama Head");
        translationConsumer.accept(BROWN_LLAMA, "Brown Llama Head");
        translationConsumer.accept(GRAY_LLAMA, "Gray Llama Head");
        translationConsumer.accept(CREAMY_TRADER_LLAMA, "Creamy Trader Llama Head");
        translationConsumer.accept(WHITE_TRADER_LLAMA, "White Trader Llama Head");
        translationConsumer.accept(BROWN_TRADER_LLAMA, "Brown Trader Llama Head");
        translationConsumer.accept(GRAY_TRADER_LLAMA, "Gray Trader Llama Head");
    }
}
