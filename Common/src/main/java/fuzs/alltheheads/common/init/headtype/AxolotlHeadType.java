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
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class AxolotlHeadType {
    public static final ResourceKey<HeadType> LUCY_AXOLOTL = register("axolotl/lucy");
    public static final ResourceKey<HeadType> WILD_AXOLOTL = register("axolotl/wild");
    public static final ResourceKey<HeadType> GOLD_AXOLOTL = register("axolotl/gold");
    public static final ResourceKey<HeadType> CYAN_AXOLOTL = register("axolotl/cyan");
    public static final ResourceKey<HeadType> BLUE_AXOLOTL = register("axolotl/blue");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapAxolotl(context, LUCY_AXOLOTL, "entity/axolotl/axolotl_lucy");
        bootstrapAxolotl(context, WILD_AXOLOTL, "entity/axolotl/axolotl_wild");
        bootstrapAxolotl(context, GOLD_AXOLOTL, "entity/axolotl/axolotl_gold");
        bootstrapAxolotl(context, CYAN_AXOLOTL, "entity/axolotl/axolotl_cyan");
        bootstrapAxolotl(context, BLUE_AXOLOTL, "entity/axolotl/axolotl_blue");
    }

    private static void bootstrapAxolotl(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(8.0, 5.0, 5.0)
                .model(ModelType.AXOLOTL, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.AXOLOTL_IDLE_AIR)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapAxolotl(context, Axolotl.Variant.LUCY, LUCY_AXOLOTL);
        bootstrapAxolotl(context, Axolotl.Variant.WILD, WILD_AXOLOTL);
        bootstrapAxolotl(context, Axolotl.Variant.GOLD, GOLD_AXOLOTL);
        bootstrapAxolotl(context, Axolotl.Variant.CYAN, CYAN_AXOLOTL);
        bootstrapAxolotl(context, Axolotl.Variant.BLUE, BLUE_AXOLOTL);
    }

    private static void bootstrapAxolotl(BootstrapContext<LootItemCondition> context, Axolotl.Variant variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.AXOLOTL, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.AXOLOTL_VARIANT, variant))
                    .build());
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(LUCY_AXOLOTL, "Lucy Axolotl Head");
        translationConsumer.accept(WILD_AXOLOTL, "Wild Axolotl Head");
        translationConsumer.accept(GOLD_AXOLOTL, "Gold Axolotl Head");
        translationConsumer.accept(CYAN_AXOLOTL, "Cyan Axolotl Head");
        translationConsumer.accept(BLUE_AXOLOTL, "Blue Axolotl Head");
    }
}
