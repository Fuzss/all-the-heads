package fuzs.alltheheads.init;

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
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.EitherHolder;

public class HeadTypes {
    // Monsters
    public static final ResourceKey<HeadType> ENDERMAN = register("enderman");
    public static final ResourceKey<HeadType> BLAZE = register("blaze");
    public static final ResourceKey<HeadType> WITCH = register("witch");
    public static final ResourceKey<HeadType> RAVAGER = register("ravager");
    // Zombies
    public static final ResourceKey<HeadType> HUSK = register("husk");
    public static final ResourceKey<HeadType> DROWNED = register("drowned");
    // Skeletons
    public static final ResourceKey<HeadType> STRAY = register("stray");
    public static final ResourceKey<HeadType> BOGGED = register("bogged");
    // Slimes
    public static final ResourceKey<HeadType> SLIME = register("slime");
    public static final ResourceKey<HeadType> MAGMA_CUBE = register("magma_cube");
    // Animals
    public static final ResourceKey<HeadType> DOLPHIN = register("dolphin");
    public static final ResourceKey<HeadType> GOAT = register("goat");
    public static final ResourceKey<HeadType> TURTLE = register("turtle");
    public static final ResourceKey<HeadType> BAT = register("bat");
    public static final ResourceKey<HeadType> ALLAY = register("allay");
    // Llamas
    public static final ResourceKey<HeadType> CREAMY_LLAMA = register("llama/creamy");
    public static final ResourceKey<HeadType> WHITE_LLAMA = register("llama/white");
    public static final ResourceKey<HeadType> BROWN_LLAMA = register("llama/brown");
    public static final ResourceKey<HeadType> GRAY_LLAMA = register("llama/gray");
    public static final ResourceKey<HeadType> CREAMY_TRADER_LLAMA = register("trader_llama/creamy");
    public static final ResourceKey<HeadType> WHITE_TRADER_LLAMA = register("trader_llama/white");
    public static final ResourceKey<HeadType> BROWN_TRADER_LLAMA = register("trader_llama/brown");
    public static final ResourceKey<HeadType> GRAY_TRADER_LLAMA = register("trader_llama/gray");
    // Spiders
    public static final ResourceKey<HeadType> SPIDER = register("spider");
    public static final ResourceKey<HeadType> CAVE_SPIDER = register("cave_spider");
    // Vexes
    public static final ResourceKey<HeadType> VEX = register("vex");
    public static final ResourceKey<HeadType> CHARGING_VEX = register("vex/charging");
    // Illagers
    public static final ResourceKey<HeadType> VINDICATOR = register("vindicator");
    public static final ResourceKey<HeadType> EVOKER = register("evoker");
    public static final ResourceKey<HeadType> PILLAGER = register("pillager");
    public static final ResourceKey<HeadType> ILLUSIONER = register("illusioner");
    // Squid
    public static final ResourceKey<HeadType> SQUID = register("squid");
    public static final ResourceKey<HeadType> GLOW_SQUID = register("glow_squid");
    // Cows
    public static final ResourceKey<HeadType> TEMPERATE_COW = register("cow/temperate");
    public static final ResourceKey<HeadType> WARM_COW = register("cow/warm");
    public static final ResourceKey<HeadType> COLD_COW = register("cow/cold");
    // Mooshrooms
    public static final ResourceKey<HeadType> RED_MOOSHROOM = register("mooshroom/red");
    public static final ResourceKey<HeadType> BROWN_MOOSHROOM = register("mooshroom/brown");
    // Chickens
    public static final ResourceKey<HeadType> TEMPERATE_CHICKEN = register("chicken/temperate");
    public static final ResourceKey<HeadType> WARM_CHICKEN = register("chicken/warm");
    public static final ResourceKey<HeadType> COLD_CHICKEN = register("chicken/cold");
    // Pigs
    public static final ResourceKey<HeadType> TEMPERATE_PIG = register("pig/temperate");
    public static final ResourceKey<HeadType> WARM_PIG = register("pig/warm");
    public static final ResourceKey<HeadType> COLD_PIG = register("pig/cold");
    // Cats
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
    // Axolotls
    public static final ResourceKey<HeadType> LUCY_AXOLOTL = register("axolotl/lucy");
    public static final ResourceKey<HeadType> WILD_AXOLOTL = register("axolotl/wild");
    public static final ResourceKey<HeadType> GOLD_AXOLOTL = register("axolotl/gold");
    public static final ResourceKey<HeadType> CYAN_AXOLOTL = register("axolotl/cyan");
    public static final ResourceKey<HeadType> BLUE_AXOLOTL = register("axolotl/blue");


    public static void bootstrap(BootstrapContext<HeadType> context) {
        // Monsters
        HeadType.builder(EntityType.ENDERMAN)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.ENDERMAN, ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman"))
                .model(ModelType.ENDERMAN_EYES,
                        ResourceLocationHelper.withDefaultNamespace("entity/enderman/enderman_eyes"))
                .noteBlockSound(SoundEvents.ENDERMAN_AMBIENT)
                .build(context, ENDERMAN);
        HeadType.builder(EntityType.BLAZE)
                .shape(8.0, 8.0, 8.0)
                .litModel(ModelType.MOB, ResourceLocationHelper.withDefaultNamespace("entity/blaze"), 15)
                .noteBlockSound(SoundEvents.BLAZE_AMBIENT)
                .build(context, BLAZE);
        HeadType.builder(EntityType.WITCH)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.WITCH, ResourceLocationHelper.withDefaultNamespace("entity/witch"))
                .noteBlockSound(SoundEvents.WITCH_AMBIENT)
                .build(context, WITCH);
        HeadType.builder(EntityType.RAVAGER)
                .shape(16.0, 20.0, 16.0)
                .scale(0.5)
                .model(ModelType.RAVAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/ravager"))
                .noteBlockSound(SoundEvents.RAVAGER_AMBIENT)
                .build(context, RAVAGER);

        // Zombies
        HeadType.builder(EntityType.HUSK)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace("entity/zombie/husk"))
                .noteBlockSound(SoundEvents.HUSK_AMBIENT)
                .build(context, HUSK);
        HeadType.builder(EntityType.DROWNED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.HUMANOID, ResourceLocationHelper.withDefaultNamespace("entity/zombie/drowned"))
                .model(ModelType.HUMANOID_OVERLAY,
                        ResourceLocationHelper.withDefaultNamespace("entity/zombie/drowned_outer_layer"))
                .noteBlockSound(SoundEvents.DROWNED_AMBIENT)
                .build(context, DROWNED);

        // Skeletons
        HeadType.builder(EntityType.STRAY)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.MOB, ResourceLocationHelper.withDefaultNamespace("entity/skeleton/stray"))
                .model(ModelType.MOB_OVERLAY,
                        ResourceLocationHelper.withDefaultNamespace("entity/skeleton/stray_overlay"))
                .noteBlockSound(SoundEvents.STRAY_AMBIENT)
                .build(context, STRAY);
        HeadType.builder(EntityType.BOGGED)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.BOGGED, ResourceLocationHelper.withDefaultNamespace("entity/skeleton/bogged"))
                .model(ModelType.MOB_OVERLAY,
                        ResourceLocationHelper.withDefaultNamespace("entity/skeleton/bogged_overlay"))
                .noteBlockSound(SoundEvents.BOGGED_AMBIENT)
                .build(context, BOGGED);

        // Slime
        HeadType.builder(EntityType.SLIME)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SLIME, ResourceLocationHelper.withDefaultNamespace("entity/slime/slime"))
                .model(ModelType.SLIME_GEL, ResourceLocationHelper.withDefaultNamespace("entity/slime/slime"))
                .noteBlockSound(SoundEvents.SLIME_SQUISH_SMALL)
                .build(context, SLIME);
        HeadType.builder(EntityType.MAGMA_CUBE)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.MAGMA_CUBE, ResourceLocationHelper.withDefaultNamespace("entity/slime/magmacube"))
                .noteBlockSound(SoundEvents.MAGMA_CUBE_SQUISH_SMALL)
                .build(context, MAGMA_CUBE);

        // Animals
        HeadType.builder(EntityType.DOLPHIN)
                .shape(8.0, 7.0, 6.0)
                .model(ModelType.DOLPHIN, ResourceLocationHelper.withDefaultNamespace("entity/dolphin"))
                .noteBlockSound(SoundEvents.DOLPHIN_AMBIENT)
                .build(context, DOLPHIN);
        HeadType.builder(EntityType.GOAT)
                .shape(5.0, 10.0, 7.0)
                .scale(0.8)
                .model(ModelType.GOAT, ResourceLocationHelper.withDefaultNamespace("entity/goat/goat"))
                .noteBlockSound(SoundEvents.GOAT_SCREAMING_AMBIENT)
                .build(context, GOAT);
        HeadType.builder(EntityType.TURTLE)
                .shape(6.0, 5.0, 6.0)
                .scale(4.0 / 3.0)
                .model(ModelType.TURTLE, ResourceLocationHelper.withDefaultNamespace("entity/turtle/big_sea_turtle"))
                .noteBlockSound(SoundEvents.TURTLE_AMBIENT_LAND)
                .build(context, TURTLE);
        HeadType.builder(EntityType.BAT)
                .shape(4.0, 3.0, 2.0)
                .scale(2.0)
                .model(ModelType.BAT, ResourceLocationHelper.withDefaultNamespace("entity/bat"))
                .noteBlockSound(SoundEvents.BAT_AMBIENT)
                .build(context, BAT);
        HeadType.builder(EntityType.ALLAY)
                .shape(5.0, 5.0, 5.0)
                .scale(1.6)
                .model(ModelType.ALLAY, ResourceLocationHelper.withDefaultNamespace("entity/allay/allay"))
                .noteBlockSound(SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM)
                .build(context, ALLAY);

        // Llamas
        bootstrapLlama(context, Llama.Variant.CREAMY, CREAMY_LLAMA, "entity/llama/creamy");
        bootstrapLlama(context, Llama.Variant.WHITE, WHITE_LLAMA, "entity/llama/white");
        bootstrapLlama(context, Llama.Variant.BROWN, BROWN_LLAMA, "entity/llama/brown");
        bootstrapLlama(context, Llama.Variant.GRAY, GRAY_LLAMA, "entity/llama/gray");
        bootstrapTraderLlama(context, Llama.Variant.CREAMY, CREAMY_TRADER_LLAMA, "entity/llama/creamy");
        bootstrapTraderLlama(context, Llama.Variant.WHITE, WHITE_TRADER_LLAMA, "entity/llama/white");
        bootstrapTraderLlama(context, Llama.Variant.BROWN, BROWN_TRADER_LLAMA, "entity/llama/brown");
        bootstrapTraderLlama(context, Llama.Variant.GRAY, GRAY_TRADER_LLAMA, "entity/llama/gray");

        // Spiders
        HeadType.builder(EntityType.SPIDER)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SPIDER, ResourceLocationHelper.withDefaultNamespace("entity/spider/spider"))
                .model(ModelType.SPIDER_EYES, ResourceLocationHelper.withDefaultNamespace("entity/spider_eyes"))
                .noteBlockSound(SoundEvents.SPIDER_AMBIENT)
                .build(context, SPIDER);
        HeadType.builder(EntityType.CAVE_SPIDER)
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.SPIDER, ResourceLocationHelper.withDefaultNamespace("entity/spider/cave_spider"))
                .model(ModelType.SPIDER_EYES, ResourceLocationHelper.withDefaultNamespace("entity/spider_eyes"))
                .noteBlockSound(SoundEvents.SPIDER_AMBIENT)
                .build(context, CAVE_SPIDER);

        // Vexes
        HeadType.builder(EntityType.VEX)
                .shape(5.0, 5.0, 5.0)
                .scale(1.6)
                .litModel(ModelType.VEX, ResourceLocationHelper.withDefaultNamespace("entity/illager/vex"), 15)
                .noteBlockSound(SoundEvents.VEX_AMBIENT)
                .build(context, VEX);
        HeadType.builder(EntityType.VEX)
                .shape(5.0, 5.0, 5.0)
                .scale(1.6)
                .litModel(ModelType.VEX, ResourceLocationHelper.withDefaultNamespace("entity/illager/vex_charging"), 15)
                .noteBlockSound(SoundEvents.VEX_CHARGE)
                .build(context, CHARGING_VEX);

        // Illagers
        HeadType.builder(EntityType.VINDICATOR)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/vindicator"))
                .noteBlockSound(SoundEvents.VINDICATOR_AMBIENT)
                .build(context, VINDICATOR);
        HeadType.builder(EntityType.EVOKER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/evoker"))
                .noteBlockSound(SoundEvents.EVOKER_AMBIENT)
                .build(context, EVOKER);
        HeadType.builder(EntityType.PILLAGER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/pillager"))
                .noteBlockSound(SoundEvents.PILLAGER_AMBIENT)
                .build(context, PILLAGER);
        HeadType.builder(EntityType.ILLUSIONER)
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.ILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/illager/illusioner"))
                .noteBlockSound(SoundEvents.ILLUSIONER_AMBIENT)
                .build(context, ILLUSIONER);

        // Squid
        HeadType.builder(EntityType.SQUID)
                .shape(12.0, 16.0, 12.0)
                .scale(2.0 / 3.0)
                .model(ModelType.SQUID, ResourceLocationHelper.withDefaultNamespace("entity/squid/squid"))
                .noteBlockSound(SoundEvents.SQUID_AMBIENT)
                .build(context, SQUID);
        HeadType.builder(EntityType.GLOW_SQUID)
                .shape(12.0, 16.0, 12.0)
                .scale(2.0 / 3.0)
                .litModel(ModelType.SQUID, ResourceLocationHelper.withDefaultNamespace("entity/squid/glow_squid"), 15)
                .noteBlockSound(SoundEvents.GLOW_SQUID_AMBIENT)
                .build(context, GLOW_SQUID);

        // Cows
        bootstrapCow(context,
                CowVariants.TEMPERATE,
                TEMPERATE_COW,
                ModelType.TEMPERATE_COW,
                "entity/cow/temperate_cow");
        bootstrapCow(context, CowVariants.WARM, WARM_COW, ModelType.WARM_COW, "entity/cow/warm_cow");
        bootstrapCow(context, CowVariants.COLD, COLD_COW, ModelType.COLD_COW, "entity/cow/cold_cow");

        // Mooshrooms
        bootstrapMooshroom(context, MushroomCow.Variant.RED, RED_MOOSHROOM, "entity/cow/red_mooshroom");
        bootstrapMooshroom(context, MushroomCow.Variant.BROWN, BROWN_MOOSHROOM, "entity/cow/brown_mooshroom");

        // Chickens
        bootstrapChicken(context,
                ChickenVariants.TEMPERATE,
                TEMPERATE_CHICKEN,
                ModelType.CHICKEN,
                "entity/chicken/temperate_chicken");
        bootstrapChicken(context, ChickenVariants.WARM, WARM_CHICKEN, ModelType.CHICKEN, "entity/chicken/warm_chicken");
        bootstrapChicken(context,
                ChickenVariants.COLD,
                COLD_CHICKEN,
                ModelType.COLD_CHICKEN,
                "entity/chicken/cold_chicken");

        // Pigs
        bootstrapPig(context, PigVariants.TEMPERATE, TEMPERATE_PIG, "entity/pig/temperate_pig");
        bootstrapPig(context, PigVariants.WARM, WARM_PIG, "entity/pig/warm_pig");
        bootstrapPig(context, PigVariants.COLD, COLD_PIG, "entity/pig/cold_pig");

        // Cats
        HeadType.builder(EntityType.OCELOT)
                .shape(5.0, 4.0, 5.0)
                .scale(1.6)
                .model(ModelType.FELINE, ResourceLocationHelper.withDefaultNamespace("entity/cat/ocelot"))
                .noteBlockSound(SoundEvents.OCELOT_AMBIENT)
                .build(context, OCELOT);
        bootstrapCat(context, CatVariants.TABBY, TABBY_CAT, "entity/cat/tabby");
        bootstrapCat(context, CatVariants.BLACK, BLACK_CAT, "entity/cat/black");
        bootstrapCat(context, CatVariants.RED, RED_CAT, "entity/cat/red");
        bootstrapCat(context, CatVariants.SIAMESE, SIAMESE_CAT, "entity/cat/siamese");
        bootstrapCat(context, CatVariants.BRITISH_SHORTHAIR, BRITISH_SHORTHAIR_CAT, "entity/cat/british_shorthair");
        bootstrapCat(context, CatVariants.CALICO, CALICO_CAT, "entity/cat/calico");
        bootstrapCat(context, CatVariants.PERSIAN, PERSIAN_CAT, "entity/cat/persian");
        bootstrapCat(context, CatVariants.RAGDOLL, RAGDOLL_CAT, "entity/cat/ragdoll");
        bootstrapCat(context, CatVariants.WHITE, WHITE_CAT, "entity/cat/white");
        bootstrapCat(context, CatVariants.JELLIE, JELLIE_CAT, "entity/cat/jellie");
        bootstrapCat(context, CatVariants.ALL_BLACK, ALL_BLACK_CAT, "entity/cat/all_black");

        // Axolotls
        bootstrapAxolotl(context, Axolotl.Variant.LUCY, LUCY_AXOLOTL, "entity/axolotl/axolotl_lucy");
        bootstrapAxolotl(context, Axolotl.Variant.WILD, WILD_AXOLOTL, "entity/axolotl/axolotl_wild");
        bootstrapAxolotl(context, Axolotl.Variant.GOLD, GOLD_AXOLOTL, "entity/axolotl/axolotl_gold");
        bootstrapAxolotl(context, Axolotl.Variant.CYAN, CYAN_AXOLOTL, "entity/axolotl/axolotl_cyan");
        bootstrapAxolotl(context, Axolotl.Variant.BLUE, BLUE_AXOLOTL, "entity/axolotl/axolotl_blue");

        // Sheep
        SheepHeadTypes.bootstrap(context);

        // Villagers
        VillagerHeadTypes.bootstrap(context);

        // Villagers
        ZombieVillagerHeadTypes.bootstrap(context);
    }

    private static void bootstrapCow(BootstrapContext<HeadType> context, ResourceKey<CowVariant> variant, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder(EntityType.COW)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.COW_VARIANT,
                                    context.lookup(Registries.COW_VARIANT).getOrThrow(variant)))
                            .build());
                })
                .shape(8.0, 8.0, 6.0)
                .model(modelType, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapMooshroom(BootstrapContext<HeadType> context, MushroomCow.Variant variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.MOOSHROOM)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.MOOSHROOM_VARIANT, variant))
                            .build());
                })
                .shape(8.0, 8.0, 6.0)
                .model(ModelType.TEMPERATE_COW, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.COW_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapChicken(BootstrapContext<HeadType> context, ResourceKey<ChickenVariant> variant, ResourceKey<HeadType> resourceKey, ModelType modelType, String textureLocation) {
        HeadType.builder(EntityType.CHICKEN)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.CHICKEN_VARIANT,
                                    new EitherHolder<>(context.lookup(Registries.CHICKEN_VARIANT).getOrThrow(variant))))
                            .build());
                })
                .shape(4.0, 6.0, 3.0)
                .scale(1.5)
                .model(modelType, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.CHICKEN_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapPig(BootstrapContext<HeadType> context, ResourceKey<PigVariant> variant, ResourceKey<HeadType> resourceKey, String textureLocation) {
        HeadType.builder(EntityType.PIG)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.PIG_VARIANT,
                                    context.lookup(Registries.PIG_VARIANT).getOrThrow(variant)))
                            .build());
                })
                .shape(8.0, 8.0, 8.0)
                .model(ModelType.PIG, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(SoundEvents.PIG_AMBIENT)
                .build(context, resourceKey);
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

    static ResourceKey<HeadType> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY, path);
    }
}