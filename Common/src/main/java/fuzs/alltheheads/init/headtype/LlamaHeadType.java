package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Llama;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class LlamaHeadType {
    public static final ResourceKey<HeadType> CREAMY_LLAMA = register("llama/creamy");
    public static final ResourceKey<HeadType> WHITE_LLAMA = register("llama/white");
    public static final ResourceKey<HeadType> BROWN_LLAMA = register("llama/brown");
    public static final ResourceKey<HeadType> GRAY_LLAMA = register("llama/gray");
    public static final ResourceKey<HeadType> CREAMY_TRADER_LLAMA = register("trader_llama/creamy");
    public static final ResourceKey<HeadType> WHITE_TRADER_LLAMA = register("trader_llama/white");
    public static final ResourceKey<HeadType> BROWN_TRADER_LLAMA = register("trader_llama/brown");
    public static final ResourceKey<HeadType> GRAY_TRADER_LLAMA = register("trader_llama/gray");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        LlamaHeadType.bootstrapLlama(context, Llama.Variant.CREAMY, LlamaHeadType.CREAMY_LLAMA, "entity/llama/creamy");
        LlamaHeadType.bootstrapLlama(context, Llama.Variant.WHITE, LlamaHeadType.WHITE_LLAMA, "entity/llama/white");
        LlamaHeadType.bootstrapLlama(context, Llama.Variant.BROWN, LlamaHeadType.BROWN_LLAMA, "entity/llama/brown");
        LlamaHeadType.bootstrapLlama(context, Llama.Variant.GRAY, LlamaHeadType.GRAY_LLAMA, "entity/llama/gray");
        LlamaHeadType.bootstrapTraderLlama(context,
                Llama.Variant.CREAMY,
                LlamaHeadType.CREAMY_TRADER_LLAMA,
                "entity/llama/creamy");
        LlamaHeadType.bootstrapTraderLlama(context,
                Llama.Variant.WHITE,
                LlamaHeadType.WHITE_TRADER_LLAMA,
                "entity/llama/white");
        LlamaHeadType.bootstrapTraderLlama(context,
                Llama.Variant.BROWN,
                LlamaHeadType.BROWN_TRADER_LLAMA,
                "entity/llama/brown");
        LlamaHeadType.bootstrapTraderLlama(context,
                Llama.Variant.GRAY,
                LlamaHeadType.GRAY_TRADER_LLAMA,
                "entity/llama/gray");
    }

    private static void bootstrapLlama(BootstrapContext<HeadType> context, Llama.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.LLAMA)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.LLAMA_VARIANT, variant))
                            .build());
                })
                .shape(8.0, 10.0, 6.0)
                .model(ModelType.LLAMA, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.LLAMA_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapTraderLlama(BootstrapContext<HeadType> context, Llama.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.TRADER_LLAMA)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.LLAMA_VARIANT, variant))
                            .build());
                })
                .shape(8.0, 10.0, 6.0)
                .model(ModelType.LLAMA, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .model(ModelType.LLAMA_DECOR,
                        ResourceLocationHelper.withDefaultNamespace("entity/equipment/llama_body/trader_llama"))
                .noteBlockSound(SoundEvents.LLAMA_AMBIENT)
                .build(context, resourceKey);
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
