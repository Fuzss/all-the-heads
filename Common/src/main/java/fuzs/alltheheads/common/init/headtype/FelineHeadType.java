package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.feline.CatSoundVariants;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.feline.CatVariants;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class FelineHeadType {
    public static final ResourceKey<HeadType> OCELOT = register("ocelot");
    public static final ResourceKey<HeadType> TABBY_CAT = register("cat/tabby");
    public static final ResourceKey<HeadType> BLACK_CAT = register("cat/black");
    public static final ResourceKey<HeadType> RED_CAT = register("cat/red");
    public static final ResourceKey<HeadType> SIAMESE_CAT = register("cat/siamese");
    public static final ResourceKey<HeadType> BRITISH_SHORTHAIR_CAT = register("cat/british_shorthair");
    public static final ResourceKey<HeadType> CALICO_CAT = register("cat/calico");
    public static final ResourceKey<HeadType> PERSIAN_CAT = register("cat/persian");
    public static final ResourceKey<HeadType> RAGDOLL_CAT = register("cat/ragdoll");
    public static final ResourceKey<HeadType> WHITE_CAT = register("cat/white");
    public static final ResourceKey<HeadType> JELLIE_CAT = register("cat/jellie");
    public static final ResourceKey<HeadType> ALL_BLACK_CAT = register("cat/all_black");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        HeadType.builder(EntityType.OCELOT)
                .shape(5.0, 4.0, 5.0)
                .scale(1.6)
                .model(ModelType.FELINE, Identifier.withDefaultNamespace("entity/cat/ocelot"))
                .noteBlockSound(SoundEvents.OCELOT_AMBIENT)
                .build(context, OCELOT);
        bootstrapCat(context, CatVariants.TABBY, TABBY_CAT, "entity/cat/cat_tabby");
        bootstrapCat(context, CatVariants.BLACK, BLACK_CAT, "entity/cat/cat_black");
        bootstrapCat(context, CatVariants.RED, RED_CAT, "entity/cat/cat_red");
        bootstrapCat(context, CatVariants.SIAMESE, SIAMESE_CAT, "entity/cat/cat_siamese");
        bootstrapCat(context, CatVariants.BRITISH_SHORTHAIR, BRITISH_SHORTHAIR_CAT, "entity/cat/cat_british_shorthair");
        bootstrapCat(context, CatVariants.CALICO, CALICO_CAT, "entity/cat/cat_calico");
        bootstrapCat(context, CatVariants.PERSIAN, PERSIAN_CAT, "entity/cat/cat_persian");
        bootstrapCat(context, CatVariants.RAGDOLL, RAGDOLL_CAT, "entity/cat/cat_ragdoll");
        bootstrapCat(context, CatVariants.WHITE, WHITE_CAT, "entity/cat/cat_white");
        bootstrapCat(context, CatVariants.JELLIE, JELLIE_CAT, "entity/cat/cat_jellie");
        bootstrapCat(context, CatVariants.ALL_BLACK, ALL_BLACK_CAT, "entity/cat/cat_all_black");
    }

    private static void bootstrapCat(BootstrapContext<HeadType> context, ResourceKey<CatVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.CAT)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.CAT_VARIANT,
                                    context.lookup(Registries.CAT_VARIANT).getOrThrow(variant)))
                            .build());
                })
                .shape(5.0, 4.0, 5.0)
                .scale(1.6)
                .model(ModelType.FELINE, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.CAT_SOUNDS.get(CatSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .ambientSound())
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(OCELOT, "Ocelot Head");
        translationConsumer.accept(TABBY_CAT, "Tabby Cat Head");
        translationConsumer.accept(BLACK_CAT, "Black Cat Head");
        translationConsumer.accept(RED_CAT, "Red Cat Head");
        translationConsumer.accept(SIAMESE_CAT, "Siamese Cat Head");
        translationConsumer.accept(BRITISH_SHORTHAIR_CAT, "British Shorthair Cat Head");
        translationConsumer.accept(CALICO_CAT, "Calico Cat Head");
        translationConsumer.accept(PERSIAN_CAT, "Persian Cat Head");
        translationConsumer.accept(RAGDOLL_CAT, "Ragdoll Cat Head");
        translationConsumer.accept(WHITE_CAT, "White Cat Head");
        translationConsumer.accept(JELLIE_CAT, "Jellie Cat Head");
        translationConsumer.accept(ALL_BLACK_CAT, "All Black Cat Head");
    }
}
