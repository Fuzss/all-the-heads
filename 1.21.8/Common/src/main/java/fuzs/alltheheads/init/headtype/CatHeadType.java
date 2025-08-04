package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.animal.CatVariants;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class CatHeadType {
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
                .model(ModelType.FELINE, ResourceLocationHelper.withDefaultNamespace("entity/cat/ocelot"))
                .noteBlockSound(SoundEvents.OCELOT_AMBIENT)
                .build(context, CatHeadType.OCELOT);
        CatHeadType.bootstrapCat(context, CatVariants.TABBY, CatHeadType.TABBY_CAT, "entity/cat/tabby");
        CatHeadType.bootstrapCat(context, CatVariants.BLACK, CatHeadType.BLACK_CAT, "entity/cat/black");
        CatHeadType.bootstrapCat(context, CatVariants.RED, CatHeadType.RED_CAT, "entity/cat/red");
        CatHeadType.bootstrapCat(context, CatVariants.SIAMESE, CatHeadType.SIAMESE_CAT, "entity/cat/siamese");
        CatHeadType.bootstrapCat(context,
                CatVariants.BRITISH_SHORTHAIR,
                CatHeadType.BRITISH_SHORTHAIR_CAT,
                "entity/cat/british_shorthair");
        CatHeadType.bootstrapCat(context, CatVariants.CALICO, CatHeadType.CALICO_CAT, "entity/cat/calico");
        CatHeadType.bootstrapCat(context, CatVariants.PERSIAN, CatHeadType.PERSIAN_CAT, "entity/cat/persian");
        CatHeadType.bootstrapCat(context, CatVariants.RAGDOLL, CatHeadType.RAGDOLL_CAT, "entity/cat/ragdoll");
        CatHeadType.bootstrapCat(context, CatVariants.WHITE, CatHeadType.WHITE_CAT, "entity/cat/white");
        CatHeadType.bootstrapCat(context, CatVariants.JELLIE, CatHeadType.JELLIE_CAT, "entity/cat/jellie");
        CatHeadType.bootstrapCat(context, CatVariants.ALL_BLACK, CatHeadType.ALL_BLACK_CAT, "entity/cat/all_black");
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
                .model(ModelType.FELINE, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.CAT_AMBIENT)
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
