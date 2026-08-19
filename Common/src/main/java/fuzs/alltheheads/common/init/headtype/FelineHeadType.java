package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.feline.CatSoundVariants;
import net.minecraft.world.entity.animal.feline.CatVariant;
import net.minecraft.world.entity.animal.feline.CatVariants;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
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

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        HeadType.builder()
                .shape(5.0, 4.0, 5.0)
                .scale(1.6)
                .model(ModelType.FELINE, Identifier.withDefaultNamespace("entity/cat/ocelot"))
                .noteBlockSound(SoundEvents.OCELOT_AMBIENT)
                .build(context, OCELOT);
        bootstrapCat(context, TABBY_CAT, "entity/cat/cat_tabby");
        bootstrapCat(context, BLACK_CAT, "entity/cat/cat_black");
        bootstrapCat(context, RED_CAT, "entity/cat/cat_red");
        bootstrapCat(context, SIAMESE_CAT, "entity/cat/cat_siamese");
        bootstrapCat(context, BRITISH_SHORTHAIR_CAT, "entity/cat/cat_british_shorthair");
        bootstrapCat(context, CALICO_CAT, "entity/cat/cat_calico");
        bootstrapCat(context, PERSIAN_CAT, "entity/cat/cat_persian");
        bootstrapCat(context, RAGDOLL_CAT, "entity/cat/cat_ragdoll");
        bootstrapCat(context, WHITE_CAT, "entity/cat/cat_white");
        bootstrapCat(context, JELLIE_CAT, "entity/cat/cat_jellie");
        bootstrapCat(context, ALL_BLACK_CAT, "entity/cat/cat_all_black");
    }

    private static void bootstrapCat(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder()
                .shape(5.0, 4.0, 5.0)
                .scale(1.6)
                .model(ModelType.FELINE, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.CAT_SOUNDS.get(CatSoundVariants.SoundSet.CLASSIC)
                        .adultSounds()
                        .ambientSound())
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrap(context, OCELOT, EntityTypes.OCELOT);
        bootstrapCat(context, CatVariants.TABBY, TABBY_CAT);
        bootstrapCat(context, CatVariants.BLACK, BLACK_CAT);
        bootstrapCat(context, CatVariants.RED, RED_CAT);
        bootstrapCat(context, CatVariants.SIAMESE, SIAMESE_CAT);
        bootstrapCat(context, CatVariants.BRITISH_SHORTHAIR, BRITISH_SHORTHAIR_CAT);
        bootstrapCat(context, CatVariants.CALICO, CALICO_CAT);
        bootstrapCat(context, CatVariants.PERSIAN, PERSIAN_CAT);
        bootstrapCat(context, CatVariants.RAGDOLL, RAGDOLL_CAT);
        bootstrapCat(context, CatVariants.WHITE, WHITE_CAT);
        bootstrapCat(context, CatVariants.JELLIE, JELLIE_CAT);
        bootstrapCat(context, CatVariants.ALL_BLACK, ALL_BLACK_CAT);
    }

    private static void bootstrapCat(BootstrapContext<LootItemCondition> context, ResourceKey<CatVariant> variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.CAT, (EntityPredicate.Builder builder) -> {
            builder.components(DataComponentMatchers.Builder.components()
                    .exact(DataComponentExactPredicate.expect(DataComponents.CAT_VARIANT,
                            context.lookup(Registries.CAT_VARIANT).getOrThrow(variant)))
                    .build());
        });
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
