package fuzs.alltheheads.init;

import fuzs.alltheheads.advancements.critereon.VillagerPredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SheepPredicate;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.animal.CatVariants;
import net.minecraft.world.entity.animal.CowVariant;
import net.minecraft.world.entity.animal.CowVariants;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HeadTypes {
    private static final Object2IntMap<DyeColor> SHEEP_COLORS = (Arrays.stream(DyeColor.values())
            .collect(Collectors.<DyeColor, DyeColor, Integer, Object2IntArrayMap<DyeColor>>toMap(Function.identity(),
                    (DyeColor dyeColor) -> getModifiedColor(dyeColor, 0.75F),
                    (Integer o1, Integer o2) -> o2,
                    Object2IntArrayMap::new)));
    public static final ResourceKey<HeadType> ENDERMAN = register("enderman");
    public static final ResourceKey<HeadType> BLAZE = register("blaze");
    public static final ResourceKey<HeadType> WITCH = register("witch");
    // Spider
    public static final ResourceKey<HeadType> SPIDER = register("spider");
    public static final ResourceKey<HeadType> CAVE_SPIDER = register("cave_spider");
    // Squid
    public static final ResourceKey<HeadType> SQUID = register("squid");
    public static final ResourceKey<HeadType> GLOW_SQUID = register("glow_squid");
    // Cow
    public static final ResourceKey<HeadType> TEMPERATE_COW = register("cow/temperate");
    public static final ResourceKey<HeadType> WARM_COW = register("cow/warm");
    public static final ResourceKey<HeadType> COLD_COW = register("cow/cold");
    // Cat
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
    // Sheep
    public static final ResourceKey<HeadType> WHITE_SHEEP = register("sheep/white");
    public static final ResourceKey<HeadType> WHITE_WOOLLY_SHEEP = register("sheep/woolly/white");
    public static final ResourceKey<HeadType> ORANGE_SHEEP = register("sheep/orange");
    public static final ResourceKey<HeadType> ORANGE_WOOLLY_SHEEP = register("sheep/woolly/orange");
    public static final ResourceKey<HeadType> MAGENTA_SHEEP = register("sheep/magenta");
    public static final ResourceKey<HeadType> MAGENTA_WOOLLY_SHEEP = register("sheep/woolly/magenta");
    public static final ResourceKey<HeadType> LIGHT_BLUE_SHEEP = register("sheep/light_blue");
    public static final ResourceKey<HeadType> LIGHT_BLUE_WOOLLY_SHEEP = register("sheep/woolly/light_blue");
    public static final ResourceKey<HeadType> YELLOW_SHEEP = register("sheep/yellow");
    public static final ResourceKey<HeadType> YELLOW_WOOLLY_SHEEP = register("sheep/woolly/yellow");
    public static final ResourceKey<HeadType> LIME_SHEEP = register("sheep/lime");
    public static final ResourceKey<HeadType> LIME_WOOLLY_SHEEP = register("sheep/woolly/lime");
    public static final ResourceKey<HeadType> PINK_SHEEP = register("sheep/pink");
    public static final ResourceKey<HeadType> PINK_WOOLLY_SHEEP = register("sheep/woolly/pink");
    public static final ResourceKey<HeadType> GRAY_SHEEP = register("sheep/gray");
    public static final ResourceKey<HeadType> GRAY_WOOLLY_SHEEP = register("sheep/woolly/gray");
    public static final ResourceKey<HeadType> LIGHT_GRAY_SHEEP = register("sheep/light_gray");
    public static final ResourceKey<HeadType> LIGHT_GRAY_WOOLLY_SHEEP = register("sheep/woolly/light_gray");
    public static final ResourceKey<HeadType> CYAN_SHEEP = register("sheep/cyan");
    public static final ResourceKey<HeadType> CYAN_WOOLLY_SHEEP = register("sheep/woolly/cyan");
    public static final ResourceKey<HeadType> PURPLE_SHEEP = register("sheep/purple");
    public static final ResourceKey<HeadType> PURPLE_WOOLLY_SHEEP = register("sheep/woolly/purple");
    public static final ResourceKey<HeadType> BLUE_SHEEP = register("sheep/blue");
    public static final ResourceKey<HeadType> BLUE_WOOLLY_SHEEP = register("sheep/woolly/blue");
    public static final ResourceKey<HeadType> BROWN_SHEEP = register("sheep/brown");
    public static final ResourceKey<HeadType> BROWN_WOOLLY_SHEEP = register("sheep/woolly/brown");
    public static final ResourceKey<HeadType> GREEN_SHEEP = register("sheep/green");
    public static final ResourceKey<HeadType> GREEN_WOOLLY_SHEEP = register("sheep/woolly/green");
    public static final ResourceKey<HeadType> RED_SHEEP = register("sheep/red");
    public static final ResourceKey<HeadType> RED_WOOLLY_SHEEP = register("sheep/woolly/red");
    public static final ResourceKey<HeadType> BLACK_SHEEP = register("sheep/black");
    public static final ResourceKey<HeadType> BLACK_WOOLLY_SHEEP = register("sheep/woolly/black");
    // Desert Villager
    public static final ResourceKey<HeadType> DESERT_VILLAGER = register("villager/desert");
    public static final ResourceKey<HeadType> DESERT_ARMORER_VILLAGER = register("villager/desert/armorer");
    public static final ResourceKey<HeadType> DESERT_BUTCHER_VILLAGER = register("villager/desert/butcher");
    public static final ResourceKey<HeadType> DESERT_CARTOGRAPHER_VILLAGER = register("villager/desert/cartographer");
    public static final ResourceKey<HeadType> DESERT_CLERIC_VILLAGER = register("villager/desert/cleric");
    public static final ResourceKey<HeadType> DESERT_FARMER_VILLAGER = register("villager/desert/farmer");
    public static final ResourceKey<HeadType> DESERT_FISHERMAN_VILLAGER = register("villager/desert/fisherman");
    public static final ResourceKey<HeadType> DESERT_FLETCHER_VILLAGER = register("villager/desert/fletcher");
    public static final ResourceKey<HeadType> DESERT_LEATHERWORKER_VILLAGER = register("villager/desert/leatherworker");
    public static final ResourceKey<HeadType> DESERT_LIBRARIAN_VILLAGER = register("villager/desert/librarian");
    public static final ResourceKey<HeadType> DESERT_MASON_VILLAGER = register("villager/desert/mason");
    public static final ResourceKey<HeadType> DESERT_NITWIT_VILLAGER = register("villager/desert/nitwit");
    public static final ResourceKey<HeadType> DESERT_SHEPHERD_VILLAGER = register("villager/desert/shepherd");
    public static final ResourceKey<HeadType> DESERT_TOOLSMITH_VILLAGER = register("villager/desert/toolsmith");
    public static final ResourceKey<HeadType> DESERT_WEAPONSMITH_VILLAGER = register("villager/desert/weaponsmith");
    // Jungle Villager
    public static final ResourceKey<HeadType> JUNGLE_VILLAGER = register("villager/jungle");
    public static final ResourceKey<HeadType> JUNGLE_ARMORER_VILLAGER = register("villager/jungle/armorer");
    public static final ResourceKey<HeadType> JUNGLE_BUTCHER_VILLAGER = register("villager/jungle/butcher");
    public static final ResourceKey<HeadType> JUNGLE_CARTOGRAPHER_VILLAGER = register("villager/jungle/cartographer");
    public static final ResourceKey<HeadType> JUNGLE_CLERIC_VILLAGER = register("villager/jungle/cleric");
    public static final ResourceKey<HeadType> JUNGLE_FARMER_VILLAGER = register("villager/jungle/farmer");
    public static final ResourceKey<HeadType> JUNGLE_FISHERMAN_VILLAGER = register("villager/jungle/fisherman");
    public static final ResourceKey<HeadType> JUNGLE_FLETCHER_VILLAGER = register("villager/jungle/fletcher");
    public static final ResourceKey<HeadType> JUNGLE_LEATHERWORKER_VILLAGER = register("villager/jungle/leatherworker");
    public static final ResourceKey<HeadType> JUNGLE_LIBRARIAN_VILLAGER = register("villager/jungle/librarian");
    public static final ResourceKey<HeadType> JUNGLE_MASON_VILLAGER = register("villager/jungle/mason");
    public static final ResourceKey<HeadType> JUNGLE_NITWIT_VILLAGER = register("villager/jungle/nitwit");
    public static final ResourceKey<HeadType> JUNGLE_SHEPHERD_VILLAGER = register("villager/jungle/shepherd");
    public static final ResourceKey<HeadType> JUNGLE_TOOLSMITH_VILLAGER = register("villager/jungle/toolsmith");
    public static final ResourceKey<HeadType> JUNGLE_WEAPONSMITH_VILLAGER = register("villager/jungle/weaponsmith");
    // Plains Villager
    public static final ResourceKey<HeadType> PLAINS_VILLAGER = register("villager/plains");
    public static final ResourceKey<HeadType> PLAINS_ARMORER_VILLAGER = register("villager/plains/armorer");
    public static final ResourceKey<HeadType> PLAINS_BUTCHER_VILLAGER = register("villager/plains/butcher");
    public static final ResourceKey<HeadType> PLAINS_CARTOGRAPHER_VILLAGER = register("villager/plains/cartographer");
    public static final ResourceKey<HeadType> PLAINS_CLERIC_VILLAGER = register("villager/plains/cleric");
    public static final ResourceKey<HeadType> PLAINS_FARMER_VILLAGER = register("villager/plains/farmer");
    public static final ResourceKey<HeadType> PLAINS_FISHERMAN_VILLAGER = register("villager/plains/fisherman");
    public static final ResourceKey<HeadType> PLAINS_FLETCHER_VILLAGER = register("villager/plains/fletcher");
    public static final ResourceKey<HeadType> PLAINS_LEATHERWORKER_VILLAGER = register("villager/plains/leatherworker");
    public static final ResourceKey<HeadType> PLAINS_LIBRARIAN_VILLAGER = register("villager/plains/librarian");
    public static final ResourceKey<HeadType> PLAINS_MASON_VILLAGER = register("villager/plains/mason");
    public static final ResourceKey<HeadType> PLAINS_NITWIT_VILLAGER = register("villager/plains/nitwit");
    public static final ResourceKey<HeadType> PLAINS_SHEPHERD_VILLAGER = register("villager/plains/shepherd");
    public static final ResourceKey<HeadType> PLAINS_TOOLSMITH_VILLAGER = register("villager/plains/toolsmith");
    public static final ResourceKey<HeadType> PLAINS_WEAPONSMITH_VILLAGER = register("villager/plains/weaponsmith");
    // Savanna Villager
    public static final ResourceKey<HeadType> SAVANNA_VILLAGER = register("villager/savanna");
    public static final ResourceKey<HeadType> SAVANNA_ARMORER_VILLAGER = register("villager/savanna/armorer");
    public static final ResourceKey<HeadType> SAVANNA_BUTCHER_VILLAGER = register("villager/savanna/butcher");
    public static final ResourceKey<HeadType> SAVANNA_CARTOGRAPHER_VILLAGER = register("villager/savanna/cartographer");
    public static final ResourceKey<HeadType> SAVANNA_CLERIC_VILLAGER = register("villager/savanna/cleric");
    public static final ResourceKey<HeadType> SAVANNA_FARMER_VILLAGER = register("villager/savanna/farmer");
    public static final ResourceKey<HeadType> SAVANNA_FISHERMAN_VILLAGER = register("villager/savanna/fisherman");
    public static final ResourceKey<HeadType> SAVANNA_FLETCHER_VILLAGER = register("villager/savanna/fletcher");
    public static final ResourceKey<HeadType> SAVANNA_LEATHERWORKER_VILLAGER = register("villager/savanna/leatherworker");
    public static final ResourceKey<HeadType> SAVANNA_LIBRARIAN_VILLAGER = register("villager/savanna/librarian");
    public static final ResourceKey<HeadType> SAVANNA_MASON_VILLAGER = register("villager/savanna/mason");
    public static final ResourceKey<HeadType> SAVANNA_NITWIT_VILLAGER = register("villager/savanna/nitwit");
    public static final ResourceKey<HeadType> SAVANNA_SHEPHERD_VILLAGER = register("villager/savanna/shepherd");
    public static final ResourceKey<HeadType> SAVANNA_TOOLSMITH_VILLAGER = register("villager/savanna/toolsmith");
    public static final ResourceKey<HeadType> SAVANNA_WEAPONSMITH_VILLAGER = register("villager/savanna/weaponsmith");
    // Snow Villager
    public static final ResourceKey<HeadType> SNOW_VILLAGER = register("villager/snow");
    public static final ResourceKey<HeadType> SNOW_ARMORER_VILLAGER = register("villager/snow/armorer");
    public static final ResourceKey<HeadType> SNOW_BUTCHER_VILLAGER = register("villager/snow/butcher");
    public static final ResourceKey<HeadType> SNOW_CARTOGRAPHER_VILLAGER = register("villager/snow/cartographer");
    public static final ResourceKey<HeadType> SNOW_CLERIC_VILLAGER = register("villager/snow/cleric");
    public static final ResourceKey<HeadType> SNOW_FARMER_VILLAGER = register("villager/snow/farmer");
    public static final ResourceKey<HeadType> SNOW_FISHERMAN_VILLAGER = register("villager/snow/fisherman");
    public static final ResourceKey<HeadType> SNOW_FLETCHER_VILLAGER = register("villager/snow/fletcher");
    public static final ResourceKey<HeadType> SNOW_LEATHERWORKER_VILLAGER = register("villager/snow/leatherworker");
    public static final ResourceKey<HeadType> SNOW_LIBRARIAN_VILLAGER = register("villager/snow/librarian");
    public static final ResourceKey<HeadType> SNOW_MASON_VILLAGER = register("villager/snow/mason");
    public static final ResourceKey<HeadType> SNOW_NITWIT_VILLAGER = register("villager/snow/nitwit");
    public static final ResourceKey<HeadType> SNOW_SHEPHERD_VILLAGER = register("villager/snow/shepherd");
    public static final ResourceKey<HeadType> SNOW_TOOLSMITH_VILLAGER = register("villager/snow/toolsmith");
    public static final ResourceKey<HeadType> SNOW_WEAPONSMITH_VILLAGER = register("villager/snow/weaponsmith");
    // Swamp Villager
    public static final ResourceKey<HeadType> SWAMP_VILLAGER = register("villager/swamp");
    public static final ResourceKey<HeadType> SWAMP_ARMORER_VILLAGER = register("villager/swamp/armorer");
    public static final ResourceKey<HeadType> SWAMP_BUTCHER_VILLAGER = register("villager/swamp/butcher");
    public static final ResourceKey<HeadType> SWAMP_CARTOGRAPHER_VILLAGER = register("villager/swamp/cartographer");
    public static final ResourceKey<HeadType> SWAMP_CLERIC_VILLAGER = register("villager/swamp/cleric");
    public static final ResourceKey<HeadType> SWAMP_FARMER_VILLAGER = register("villager/swamp/farmer");
    public static final ResourceKey<HeadType> SWAMP_FISHERMAN_VILLAGER = register("villager/swamp/fisherman");
    public static final ResourceKey<HeadType> SWAMP_FLETCHER_VILLAGER = register("villager/swamp/fletcher");
    public static final ResourceKey<HeadType> SWAMP_LEATHERWORKER_VILLAGER = register("villager/swamp/leatherworker");
    public static final ResourceKey<HeadType> SWAMP_LIBRARIAN_VILLAGER = register("villager/swamp/librarian");
    public static final ResourceKey<HeadType> SWAMP_MASON_VILLAGER = register("villager/swamp/mason");
    public static final ResourceKey<HeadType> SWAMP_NITWIT_VILLAGER = register("villager/swamp/nitwit");
    public static final ResourceKey<HeadType> SWAMP_SHEPHERD_VILLAGER = register("villager/swamp/shepherd");
    public static final ResourceKey<HeadType> SWAMP_TOOLSMITH_VILLAGER = register("villager/swamp/toolsmith");
    public static final ResourceKey<HeadType> SWAMP_WEAPONSMITH_VILLAGER = register("villager/swamp/weaponsmith");
    // Taiga Villager
    public static final ResourceKey<HeadType> TAIGA_VILLAGER = register("villager/taiga");
    public static final ResourceKey<HeadType> TAIGA_ARMORER_VILLAGER = register("villager/taiga/armorer");
    public static final ResourceKey<HeadType> TAIGA_BUTCHER_VILLAGER = register("villager/taiga/butcher");
    public static final ResourceKey<HeadType> TAIGA_CARTOGRAPHER_VILLAGER = register("villager/taiga/cartographer");
    public static final ResourceKey<HeadType> TAIGA_CLERIC_VILLAGER = register("villager/taiga/cleric");
    public static final ResourceKey<HeadType> TAIGA_FARMER_VILLAGER = register("villager/taiga/farmer");
    public static final ResourceKey<HeadType> TAIGA_FISHERMAN_VILLAGER = register("villager/taiga/fisherman");
    public static final ResourceKey<HeadType> TAIGA_FLETCHER_VILLAGER = register("villager/taiga/fletcher");
    public static final ResourceKey<HeadType> TAIGA_LEATHERWORKER_VILLAGER = register("villager/taiga/leatherworker");
    public static final ResourceKey<HeadType> TAIGA_LIBRARIAN_VILLAGER = register("villager/taiga/librarian");
    public static final ResourceKey<HeadType> TAIGA_MASON_VILLAGER = register("villager/taiga/mason");
    public static final ResourceKey<HeadType> TAIGA_NITWIT_VILLAGER = register("villager/taiga/nitwit");
    public static final ResourceKey<HeadType> TAIGA_SHEPHERD_VILLAGER = register("villager/taiga/shepherd");
    public static final ResourceKey<HeadType> TAIGA_TOOLSMITH_VILLAGER = register("villager/taiga/toolsmith");
    public static final ResourceKey<HeadType> TAIGA_WEAPONSMITH_VILLAGER = register("villager/taiga/weaponsmith");

    public static void bootstrap(BootstrapContext<HeadType> context) {
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

        // Sheep
        bootstrapSheep(context, DyeColor.WHITE, WHITE_SHEEP, WHITE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.ORANGE, ORANGE_SHEEP, ORANGE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.MAGENTA, MAGENTA_SHEEP, MAGENTA_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.LIGHT_BLUE, LIGHT_BLUE_SHEEP, LIGHT_BLUE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.YELLOW, YELLOW_SHEEP, YELLOW_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.LIME, LIME_SHEEP, LIME_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.PINK, PINK_SHEEP, PINK_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.GRAY, GRAY_SHEEP, GRAY_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.LIGHT_GRAY, LIGHT_GRAY_SHEEP, LIGHT_GRAY_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.CYAN, CYAN_SHEEP, CYAN_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.PURPLE, PURPLE_SHEEP, PURPLE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.BLUE, BLUE_SHEEP, BLUE_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.BROWN, BROWN_SHEEP, BROWN_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.GREEN, GREEN_SHEEP, GREEN_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.RED, RED_SHEEP, RED_WOOLLY_SHEEP);
        bootstrapSheep(context, DyeColor.BLACK, BLACK_SHEEP, BLACK_WOOLLY_SHEEP);

        // Desert Villagers
        bootstrapVillager(context, VillagerType.DESERT, DESERT_VILLAGER, "entity/villager/type/desert");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.ARMORER,
                DESERT_ARMORER_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.BUTCHER,
                DESERT_BUTCHER_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.CARTOGRAPHER,
                DESERT_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.CLERIC,
                DESERT_CLERIC_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.FARMER,
                DESERT_FARMER_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.FISHERMAN,
                DESERT_FISHERMAN_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.FLETCHER,
                DESERT_FLETCHER_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.LEATHERWORKER,
                DESERT_LEATHERWORKER_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.LIBRARIAN,
                DESERT_LIBRARIAN_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.MASON,
                DESERT_MASON_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.NITWIT,
                DESERT_NITWIT_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.SHEPHERD,
                DESERT_SHEPHERD_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.TOOLSMITH,
                DESERT_TOOLSMITH_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.WEAPONSMITH,
                DESERT_WEAPONSMITH_VILLAGER,
                "entity/villager/type/desert",
                "entity/villager/profession/weaponsmith");

        // Jungle Villagers
        bootstrapVillager(context, VillagerType.JUNGLE, JUNGLE_VILLAGER, "entity/villager/type/jungle");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.ARMORER,
                JUNGLE_ARMORER_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.BUTCHER,
                JUNGLE_BUTCHER_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.CARTOGRAPHER,
                JUNGLE_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.CLERIC,
                JUNGLE_CLERIC_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.FARMER,
                JUNGLE_FARMER_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.FISHERMAN,
                JUNGLE_FISHERMAN_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.FLETCHER,
                JUNGLE_FLETCHER_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.LEATHERWORKER,
                JUNGLE_LEATHERWORKER_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.LIBRARIAN,
                JUNGLE_LIBRARIAN_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.MASON,
                JUNGLE_MASON_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.NITWIT,
                JUNGLE_NITWIT_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.SHEPHERD,
                JUNGLE_SHEPHERD_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.TOOLSMITH,
                JUNGLE_TOOLSMITH_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.WEAPONSMITH,
                JUNGLE_WEAPONSMITH_VILLAGER,
                "entity/villager/type/jungle",
                "entity/villager/profession/weaponsmith");

        // Plains Villagers
        bootstrapVillager(context, VillagerType.PLAINS, PLAINS_VILLAGER, "entity/villager/type/plains");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.ARMORER,
                PLAINS_ARMORER_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.BUTCHER,
                PLAINS_BUTCHER_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.CARTOGRAPHER,
                PLAINS_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.CLERIC,
                PLAINS_CLERIC_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.FARMER,
                PLAINS_FARMER_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.FISHERMAN,
                PLAINS_FISHERMAN_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.FLETCHER,
                PLAINS_FLETCHER_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.LEATHERWORKER,
                PLAINS_LEATHERWORKER_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.LIBRARIAN,
                PLAINS_LIBRARIAN_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.MASON,
                PLAINS_MASON_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.NITWIT,
                PLAINS_NITWIT_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.SHEPHERD,
                PLAINS_SHEPHERD_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.TOOLSMITH,
                PLAINS_TOOLSMITH_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.WEAPONSMITH,
                PLAINS_WEAPONSMITH_VILLAGER,
                "entity/villager/type/plains",
                "entity/villager/profession/weaponsmith");

        // Savanna Villagers
        bootstrapVillager(context, VillagerType.SAVANNA, SAVANNA_VILLAGER, "entity/villager/type/savanna");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.ARMORER,
                SAVANNA_ARMORER_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.BUTCHER,
                SAVANNA_BUTCHER_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.CARTOGRAPHER,
                SAVANNA_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.CLERIC,
                SAVANNA_CLERIC_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.FARMER,
                SAVANNA_FARMER_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.FISHERMAN,
                SAVANNA_FISHERMAN_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.FLETCHER,
                SAVANNA_FLETCHER_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.LEATHERWORKER,
                SAVANNA_LEATHERWORKER_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.LIBRARIAN,
                SAVANNA_LIBRARIAN_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.MASON,
                SAVANNA_MASON_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.NITWIT,
                SAVANNA_NITWIT_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.SHEPHERD,
                SAVANNA_SHEPHERD_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.TOOLSMITH,
                SAVANNA_TOOLSMITH_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.WEAPONSMITH,
                SAVANNA_WEAPONSMITH_VILLAGER,
                "entity/villager/type/savanna",
                "entity/villager/profession/weaponsmith");

        // Snow Villagers
        bootstrapVillager(context, VillagerType.SNOW, SNOW_VILLAGER, "entity/villager/type/snow");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.ARMORER,
                SNOW_ARMORER_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.BUTCHER,
                SNOW_BUTCHER_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.CARTOGRAPHER,
                SNOW_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.CLERIC,
                SNOW_CLERIC_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.FARMER,
                SNOW_FARMER_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.FISHERMAN,
                SNOW_FISHERMAN_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.FLETCHER,
                SNOW_FLETCHER_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.LEATHERWORKER,
                SNOW_LEATHERWORKER_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.LIBRARIAN,
                SNOW_LIBRARIAN_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.MASON,
                SNOW_MASON_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.NITWIT,
                SNOW_NITWIT_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.SHEPHERD,
                SNOW_SHEPHERD_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.TOOLSMITH,
                SNOW_TOOLSMITH_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.SNOW,
                VillagerProfession.WEAPONSMITH,
                SNOW_WEAPONSMITH_VILLAGER,
                "entity/villager/type/snow",
                "entity/villager/profession/weaponsmith");

        // Swamp Villagers
        bootstrapVillager(context, VillagerType.SWAMP, SWAMP_VILLAGER, "entity/villager/type/swamp");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.ARMORER,
                SWAMP_ARMORER_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.BUTCHER,
                SWAMP_BUTCHER_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.CARTOGRAPHER,
                SWAMP_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.CLERIC,
                SWAMP_CLERIC_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.FARMER,
                SWAMP_FARMER_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.FISHERMAN,
                SWAMP_FISHERMAN_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.FLETCHER,
                SWAMP_FLETCHER_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.LEATHERWORKER,
                SWAMP_LEATHERWORKER_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.LIBRARIAN,
                SWAMP_LIBRARIAN_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.MASON,
                SWAMP_MASON_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.NITWIT,
                SWAMP_NITWIT_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.SHEPHERD,
                SWAMP_SHEPHERD_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.TOOLSMITH,
                SWAMP_TOOLSMITH_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.WEAPONSMITH,
                SWAMP_WEAPONSMITH_VILLAGER,
                "entity/villager/type/swamp",
                "entity/villager/profession/weaponsmith");

        // Taiga Villagers
        bootstrapVillager(context, VillagerType.TAIGA, TAIGA_VILLAGER, "entity/villager/type/taiga");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.ARMORER,
                TAIGA_ARMORER_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/armorer");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.BUTCHER,
                TAIGA_BUTCHER_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/butcher");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.CARTOGRAPHER,
                TAIGA_CARTOGRAPHER_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/cartographer");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.CLERIC,
                TAIGA_CLERIC_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/cleric");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.FARMER,
                TAIGA_FARMER_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/farmer");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.FISHERMAN,
                TAIGA_FISHERMAN_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/fisherman");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.FLETCHER,
                TAIGA_FLETCHER_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/fletcher");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.LEATHERWORKER,
                TAIGA_LEATHERWORKER_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/leatherworker");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.LIBRARIAN,
                TAIGA_LIBRARIAN_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/librarian");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.MASON,
                TAIGA_MASON_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/mason");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.NITWIT,
                TAIGA_NITWIT_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/nitwit");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.SHEPHERD,
                TAIGA_SHEPHERD_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/shepherd");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.TOOLSMITH,
                TAIGA_TOOLSMITH_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/toolsmith");
        bootstrapVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.WEAPONSMITH,
                TAIGA_WEAPONSMITH_VILLAGER,
                "entity/villager/type/taiga",
                "entity/villager/profession/weaponsmith");
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

    private static void bootstrapSheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> sheep, ResourceKey<HeadType> woollySheep) {
        bootstrapSheep(context, dyeColor, sheep);
        bootstrapWoollySheep(context, dyeColor, woollySheep);
    }

    private static void bootstrapSheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.SHEEP_COLOR, dyeColor))
                            .build()).subPredicate(new SheepPredicate(Optional.of(true)));
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .dyedModel(ModelType.SHEEP,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_wool_undercoat"),
                        SHEEP_COLORS.getInt(dyeColor))
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapWoollySheep(BootstrapContext<HeadType> context, DyeColor dyeColor, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityType.SHEEP)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.components(DataComponentMatchers.Builder.components()
                            .exact(DataComponentExactPredicate.expect(DataComponents.SHEEP_COLOR, dyeColor))
                            .build()).subPredicate(SheepPredicate.hasWool());
                })
                .shape(6.0, 6.0, 8.0)
                .model(ModelType.SHEEP, ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep"))
                .dyedModel(ModelType.SHEEP,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_wool_undercoat"),
                        SHEEP_COLORS.getInt(dyeColor))
                .dyedModel(ModelType.SHEEP_WOOL,
                        ResourceLocationHelper.withDefaultNamespace("entity/sheep/sheep_wool"),
                        SHEEP_COLORS.getInt(dyeColor))
                .noteBlockSound(SoundEvents.SHEEP_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapVillager(BootstrapContext<HeadType> context, ResourceKey<VillagerType> type, ResourceKey<HeadType> resourceKey, String typeLocation) {
        HeadType.builder(EntityType.VILLAGER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(VillagerPredicate.hasData(context.lookup(Registries.VILLAGER_TYPE)
                                    .getOrThrow(type),
                            context.lookup(Registries.VILLAGER_PROFESSION).getOrThrow(VillagerProfession.NONE)));
                })
                .shape(8.0, 10.0, 8.0)
                .model(ModelType.VILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/villager/villager"))
                .model(ModelType.VILLAGER, ResourceLocationHelper.withDefaultNamespace(typeLocation))
                .noteBlockSound(SoundEvents.VILLAGER_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapVillager(BootstrapContext<HeadType> context, ResourceKey<VillagerType> type, ResourceKey<VillagerProfession> profession, ResourceKey<HeadType> resourceKey, String typeLocation, String professionLocation) {
        HeadType.builder(EntityType.VILLAGER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(VillagerPredicate.hasData(context.lookup(Registries.VILLAGER_TYPE)
                                    .getOrThrow(type),
                            context.lookup(Registries.VILLAGER_PROFESSION).getOrThrow(profession)));
                })
                .shape(8.0, 10.0, 8.0)
                .model(ModelType.VILLAGER, ResourceLocationHelper.withDefaultNamespace("entity/villager/villager"))
                .model(ModelType.VILLAGER, ResourceLocationHelper.withDefaultNamespace(typeLocation))
                .model(ModelType.VILLAGER, ResourceLocationHelper.withDefaultNamespace(professionLocation))
                .noteBlockSound(SoundEvents.VILLAGER_AMBIENT)
                .build(context, resourceKey);
    }

    private static ResourceKey<HeadType> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY, path);
    }

    /**
     * Copied from client-only class.
     *
     * @see net.minecraft.client.color.ColorLerper#getModifiedColor(DyeColor, float)
     */
    private static int getModifiedColor(DyeColor color, float brightness) {
        if (color == DyeColor.WHITE) {
            return -1644826;
        } else {
            int textureDiffuseColor = color.getTextureDiffuseColor();
            return ARGB.color(255,
                    Mth.floor(ARGB.red(textureDiffuseColor) * brightness),
                    Mth.floor(ARGB.green(textureDiffuseColor) * brightness),
                    Mth.floor(ARGB.blue(textureDiffuseColor) * brightness));
        }
    }
}