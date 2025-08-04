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
import net.minecraft.world.entity.animal.axolotl.Axolotl;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class AxolotlHeadType {
    public static final ResourceKey<HeadType> LUCY_AXOLOTL = register("axolotl/lucy");
    public static final ResourceKey<HeadType> WILD_AXOLOTL = register("axolotl/wild");
    public static final ResourceKey<HeadType> GOLD_AXOLOTL = register("axolotl/gold");
    public static final ResourceKey<HeadType> CYAN_AXOLOTL = register("axolotl/cyan");
    public static final ResourceKey<HeadType> BLUE_AXOLOTL = register("axolotl/blue");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        AxolotlHeadType.bootstrapAxolotl(context,
                Axolotl.Variant.LUCY,
                AxolotlHeadType.LUCY_AXOLOTL,
                "entity/axolotl/axolotl_lucy");
        AxolotlHeadType.bootstrapAxolotl(context,
                Axolotl.Variant.WILD,
                AxolotlHeadType.WILD_AXOLOTL,
                "entity/axolotl/axolotl_wild");
        AxolotlHeadType.bootstrapAxolotl(context,
                Axolotl.Variant.GOLD,
                AxolotlHeadType.GOLD_AXOLOTL,
                "entity/axolotl/axolotl_gold");
        AxolotlHeadType.bootstrapAxolotl(context,
                Axolotl.Variant.CYAN,
                AxolotlHeadType.CYAN_AXOLOTL,
                "entity/axolotl/axolotl_cyan");
        AxolotlHeadType.bootstrapAxolotl(context,
                Axolotl.Variant.BLUE,
                AxolotlHeadType.BLUE_AXOLOTL,
                "entity/axolotl/axolotl_blue");
    }

    private static void bootstrapAxolotl(BootstrapContext<HeadType> context, Axolotl.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.AXOLOTL)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.AXOLOTL_VARIANT, variant))
                            .build());
                })
                .shape(8.0, 5.0, 5.0)
                .model(ModelType.AXOLOTL, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.AXOLOTL_IDLE_AIR)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(LUCY_AXOLOTL, "Lucy Axolotl Head");
        translationConsumer.accept(WILD_AXOLOTL, "Wild Axolotl Head");
        translationConsumer.accept(GOLD_AXOLOTL, "Gold Axolotl Head");
        translationConsumer.accept(CYAN_AXOLOTL, "Cyan Axolotl Head");
        translationConsumer.accept(BLUE_AXOLOTL, "Blue Axolotl Head");
    }
}
