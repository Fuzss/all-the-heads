package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.VillagerDataPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class VillagerHeadType {
    // Desert Villagers
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
    // Jungle Villagers
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
    // Plains Villagers
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
    // Savanna Villagers
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
    // Snow Villagers
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
    // Swamp Villagers
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
    // Taiga Villagers
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

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        // Desert Villagers
        bootstrapVillager(context, VillagerType.DESERT, DESERT_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.ARMORER, DESERT_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.BUTCHER, DESERT_BUTCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.CARTOGRAPHER, DESERT_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.CLERIC, DESERT_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.FARMER, DESERT_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.FISHERMAN, DESERT_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.FLETCHER, DESERT_FLETCHER_VILLAGER);
        bootstrapVillager(context,
                VillagerType.DESERT,
                VillagerProfession.LEATHERWORKER,
                DESERT_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.LIBRARIAN, DESERT_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.MASON, DESERT_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.NITWIT, DESERT_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.SHEPHERD, DESERT_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.TOOLSMITH, DESERT_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.DESERT, VillagerProfession.WEAPONSMITH, DESERT_WEAPONSMITH_VILLAGER);

        // Jungle Villagers
        bootstrapVillager(context, VillagerType.JUNGLE, JUNGLE_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.ARMORER, JUNGLE_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.BUTCHER, JUNGLE_BUTCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.CARTOGRAPHER, JUNGLE_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.CLERIC, JUNGLE_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.FARMER, JUNGLE_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.FISHERMAN, JUNGLE_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.FLETCHER, JUNGLE_FLETCHER_VILLAGER);
        bootstrapVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.LEATHERWORKER,
                JUNGLE_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.LIBRARIAN, JUNGLE_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.MASON, JUNGLE_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.NITWIT, JUNGLE_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.SHEPHERD, JUNGLE_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.TOOLSMITH, JUNGLE_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.JUNGLE, VillagerProfession.WEAPONSMITH, JUNGLE_WEAPONSMITH_VILLAGER);

        // Plains Villagers
        bootstrapVillager(context, VillagerType.PLAINS, PLAINS_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.ARMORER, PLAINS_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.BUTCHER, PLAINS_BUTCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.CARTOGRAPHER, PLAINS_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.CLERIC, PLAINS_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.FARMER, PLAINS_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.FISHERMAN, PLAINS_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.FLETCHER, PLAINS_FLETCHER_VILLAGER);
        bootstrapVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.LEATHERWORKER,
                PLAINS_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.LIBRARIAN, PLAINS_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.MASON, PLAINS_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.NITWIT, PLAINS_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.SHEPHERD, PLAINS_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.TOOLSMITH, PLAINS_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.PLAINS, VillagerProfession.WEAPONSMITH, PLAINS_WEAPONSMITH_VILLAGER);

        // Savanna Villagers
        bootstrapVillager(context, VillagerType.SAVANNA, SAVANNA_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.ARMORER, SAVANNA_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.BUTCHER, SAVANNA_BUTCHER_VILLAGER);
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.CARTOGRAPHER,
                SAVANNA_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.CLERIC, SAVANNA_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.FARMER, SAVANNA_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.FISHERMAN, SAVANNA_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.FLETCHER, SAVANNA_FLETCHER_VILLAGER);
        bootstrapVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.LEATHERWORKER,
                SAVANNA_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.LIBRARIAN, SAVANNA_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.MASON, SAVANNA_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.NITWIT, SAVANNA_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.SHEPHERD, SAVANNA_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.TOOLSMITH, SAVANNA_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.SAVANNA, VillagerProfession.WEAPONSMITH, SAVANNA_WEAPONSMITH_VILLAGER);

        // Snow Villagers
        bootstrapVillager(context, VillagerType.SNOW, SNOW_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.ARMORER, SNOW_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.BUTCHER, SNOW_BUTCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.CARTOGRAPHER, SNOW_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.CLERIC, SNOW_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.FARMER, SNOW_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.FISHERMAN, SNOW_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.FLETCHER, SNOW_FLETCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.LEATHERWORKER, SNOW_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.LIBRARIAN, SNOW_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.MASON, SNOW_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.NITWIT, SNOW_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.SHEPHERD, SNOW_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.TOOLSMITH, SNOW_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.SNOW, VillagerProfession.WEAPONSMITH, SNOW_WEAPONSMITH_VILLAGER);

        // Swamp Villagers
        bootstrapVillager(context, VillagerType.SWAMP, SWAMP_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.ARMORER, SWAMP_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.BUTCHER, SWAMP_BUTCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.CARTOGRAPHER, SWAMP_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.CLERIC, SWAMP_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.FARMER, SWAMP_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.FISHERMAN, SWAMP_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.FLETCHER, SWAMP_FLETCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.LEATHERWORKER, SWAMP_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.LIBRARIAN, SWAMP_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.MASON, SWAMP_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.NITWIT, SWAMP_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.SHEPHERD, SWAMP_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.TOOLSMITH, SWAMP_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.SWAMP, VillagerProfession.WEAPONSMITH, SWAMP_WEAPONSMITH_VILLAGER);

        // Taiga Villagers
        bootstrapVillager(context, VillagerType.TAIGA, TAIGA_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.ARMORER, TAIGA_ARMORER_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.BUTCHER, TAIGA_BUTCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.CARTOGRAPHER, TAIGA_CARTOGRAPHER_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.CLERIC, TAIGA_CLERIC_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.FARMER, TAIGA_FARMER_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.FISHERMAN, TAIGA_FISHERMAN_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.FLETCHER, TAIGA_FLETCHER_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.LEATHERWORKER, TAIGA_LEATHERWORKER_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.LIBRARIAN, TAIGA_LIBRARIAN_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.MASON, TAIGA_MASON_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.NITWIT, TAIGA_NITWIT_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.SHEPHERD, TAIGA_SHEPHERD_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.TOOLSMITH, TAIGA_TOOLSMITH_VILLAGER);
        bootstrapVillager(context, VillagerType.TAIGA, VillagerProfession.WEAPONSMITH, TAIGA_WEAPONSMITH_VILLAGER);
    }

    private static void bootstrapVillager(BootstrapContext<HeadType> context, ResourceKey<VillagerType> type, ResourceKey<HeadType> resourceKey) {
        HeadType.builder()
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.VILLAGER, Identifier.withDefaultNamespace("entity/villager/villager"))
                .model(ModelType.VILLAGER, type.identifier().withPrefix("entity/villager/type/"))
                .noteBlockSound(SoundEvents.VILLAGER_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapVillager(BootstrapContext<HeadType> context, ResourceKey<VillagerType> type, ResourceKey<VillagerProfession> profession, ResourceKey<HeadType> resourceKey) {
        HeadType.builder()
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.VILLAGER, Identifier.withDefaultNamespace("entity/villager/villager"))
                .model(ModelType.VILLAGER, type.identifier().withPrefix("entity/villager/type/"))
                .model(ModelType.VILLAGER, profession.identifier().withPrefix("entity/villager/profession/"))
                .noteBlockSound(SoundEvents.VILLAGER_AMBIENT)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        // Desert Villagers
        bootstrapLootItemCondition(context, VillagerType.DESERT, DESERT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.ARMORER, DESERT_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.BUTCHER, DESERT_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.DESERT,
                VillagerProfession.CARTOGRAPHER,
                DESERT_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.CLERIC, DESERT_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.FARMER, DESERT_FARMER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.DESERT,
                VillagerProfession.FISHERMAN,
                DESERT_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.FLETCHER, DESERT_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.DESERT,
                VillagerProfession.LEATHERWORKER,
                DESERT_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.DESERT,
                VillagerProfession.LIBRARIAN,
                DESERT_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.MASON, DESERT_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.NITWIT, DESERT_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.DESERT, VillagerProfession.SHEPHERD, DESERT_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.DESERT,
                VillagerProfession.TOOLSMITH,
                DESERT_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.DESERT,
                VillagerProfession.WEAPONSMITH,
                DESERT_WEAPONSMITH_VILLAGER);

        // Jungle Villagers
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, JUNGLE_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.ARMORER, JUNGLE_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.BUTCHER, JUNGLE_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.JUNGLE,
                VillagerProfession.CARTOGRAPHER,
                JUNGLE_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.CLERIC, JUNGLE_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.FARMER, JUNGLE_FARMER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.JUNGLE,
                VillagerProfession.FISHERMAN,
                JUNGLE_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.FLETCHER, JUNGLE_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.JUNGLE,
                VillagerProfession.LEATHERWORKER,
                JUNGLE_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.JUNGLE,
                VillagerProfession.LIBRARIAN,
                JUNGLE_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.MASON, JUNGLE_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.NITWIT, JUNGLE_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.JUNGLE, VillagerProfession.SHEPHERD, JUNGLE_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.JUNGLE,
                VillagerProfession.TOOLSMITH,
                JUNGLE_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.JUNGLE,
                VillagerProfession.WEAPONSMITH,
                JUNGLE_WEAPONSMITH_VILLAGER);

        // Plains Villagers
        bootstrapLootItemCondition(context, VillagerType.PLAINS, PLAINS_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.ARMORER, PLAINS_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.BUTCHER, PLAINS_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.PLAINS,
                VillagerProfession.CARTOGRAPHER,
                PLAINS_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.CLERIC, PLAINS_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.FARMER, PLAINS_FARMER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.PLAINS,
                VillagerProfession.FISHERMAN,
                PLAINS_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.FLETCHER, PLAINS_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.PLAINS,
                VillagerProfession.LEATHERWORKER,
                PLAINS_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.PLAINS,
                VillagerProfession.LIBRARIAN,
                PLAINS_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.MASON, PLAINS_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.NITWIT, PLAINS_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.PLAINS, VillagerProfession.SHEPHERD, PLAINS_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.PLAINS,
                VillagerProfession.TOOLSMITH,
                PLAINS_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.PLAINS,
                VillagerProfession.WEAPONSMITH,
                PLAINS_WEAPONSMITH_VILLAGER);

        // Savanna Villagers
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, SAVANNA_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, VillagerProfession.ARMORER, SAVANNA_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, VillagerProfession.BUTCHER, SAVANNA_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.CARTOGRAPHER,
                SAVANNA_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, VillagerProfession.CLERIC, SAVANNA_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, VillagerProfession.FARMER, SAVANNA_FARMER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.FISHERMAN,
                SAVANNA_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.FLETCHER,
                SAVANNA_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.LEATHERWORKER,
                SAVANNA_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.LIBRARIAN,
                SAVANNA_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, VillagerProfession.MASON, SAVANNA_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SAVANNA, VillagerProfession.NITWIT, SAVANNA_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.SHEPHERD,
                SAVANNA_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.TOOLSMITH,
                SAVANNA_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SAVANNA,
                VillagerProfession.WEAPONSMITH,
                SAVANNA_WEAPONSMITH_VILLAGER);

        // Snow Villagers
        bootstrapLootItemCondition(context, VillagerType.SNOW, SNOW_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.ARMORER, SNOW_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.BUTCHER, SNOW_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SNOW,
                VillagerProfession.CARTOGRAPHER,
                SNOW_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.CLERIC, SNOW_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.FARMER, SNOW_FARMER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.FISHERMAN, SNOW_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.FLETCHER, SNOW_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SNOW,
                VillagerProfession.LEATHERWORKER,
                SNOW_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.LIBRARIAN, SNOW_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.MASON, SNOW_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.NITWIT, SNOW_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.SHEPHERD, SNOW_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SNOW, VillagerProfession.TOOLSMITH, SNOW_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SNOW,
                VillagerProfession.WEAPONSMITH,
                SNOW_WEAPONSMITH_VILLAGER);

        // Swamp Villagers
        bootstrapLootItemCondition(context, VillagerType.SWAMP, SWAMP_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.ARMORER, SWAMP_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.BUTCHER, SWAMP_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SWAMP,
                VillagerProfession.CARTOGRAPHER,
                SWAMP_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.CLERIC, SWAMP_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.FARMER, SWAMP_FARMER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.FISHERMAN, SWAMP_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.FLETCHER, SWAMP_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SWAMP,
                VillagerProfession.LEATHERWORKER,
                SWAMP_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.LIBRARIAN, SWAMP_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.MASON, SWAMP_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.NITWIT, SWAMP_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.SHEPHERD, SWAMP_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.SWAMP, VillagerProfession.TOOLSMITH, SWAMP_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.SWAMP,
                VillagerProfession.WEAPONSMITH,
                SWAMP_WEAPONSMITH_VILLAGER);

        // Taiga Villagers
        bootstrapLootItemCondition(context, VillagerType.TAIGA, TAIGA_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.ARMORER, TAIGA_ARMORER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.BUTCHER, TAIGA_BUTCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.TAIGA,
                VillagerProfession.CARTOGRAPHER,
                TAIGA_CARTOGRAPHER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.CLERIC, TAIGA_CLERIC_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.FARMER, TAIGA_FARMER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.FISHERMAN, TAIGA_FISHERMAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.FLETCHER, TAIGA_FLETCHER_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.TAIGA,
                VillagerProfession.LEATHERWORKER,
                TAIGA_LEATHERWORKER_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.LIBRARIAN, TAIGA_LIBRARIAN_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.MASON, TAIGA_MASON_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.NITWIT, TAIGA_NITWIT_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.SHEPHERD, TAIGA_SHEPHERD_VILLAGER);
        bootstrapLootItemCondition(context, VillagerType.TAIGA, VillagerProfession.TOOLSMITH, TAIGA_TOOLSMITH_VILLAGER);
        bootstrapLootItemCondition(context,
                VillagerType.TAIGA,
                VillagerProfession.WEAPONSMITH,
                TAIGA_WEAPONSMITH_VILLAGER);
    }

    private static void bootstrapLootItemCondition(BootstrapContext<LootItemCondition> context, ResourceKey<VillagerType> type, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.VILLAGER, (EntityPredicate.Builder builder) -> {
            builder.put(VillagerDataPredicate.CODEC,
                    VillagerDataPredicate.hasData(context.lookup(Registries.VILLAGER_TYPE).getOrThrow(type),
                            context.lookup(Registries.VILLAGER_PROFESSION).getOrThrow(VillagerProfession.NONE)));
        });
    }

    private static void bootstrapLootItemCondition(BootstrapContext<LootItemCondition> context, ResourceKey<VillagerType> type, ResourceKey<VillagerProfession> profession, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.VILLAGER, (EntityPredicate.Builder builder) -> {
            builder.put(VillagerDataPredicate.CODEC,
                    VillagerDataPredicate.hasData(context.lookup(Registries.VILLAGER_TYPE).getOrThrow(type),
                            context.lookup(Registries.VILLAGER_PROFESSION).getOrThrow(profession)));
        });
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        // Desert Villagers
        translationConsumer.accept(DESERT_VILLAGER, "Desert Villager Head");
        translationConsumer.accept(DESERT_ARMORER_VILLAGER, "Desert Armorer Villager Head");
        translationConsumer.accept(DESERT_BUTCHER_VILLAGER, "Desert Butcher Villager Head");
        translationConsumer.accept(DESERT_CARTOGRAPHER_VILLAGER, "Desert Cartographer Villager Head");
        translationConsumer.accept(DESERT_CLERIC_VILLAGER, "Desert Cleric Villager Head");
        translationConsumer.accept(DESERT_FARMER_VILLAGER, "Desert Farmer Villager Head");
        translationConsumer.accept(DESERT_FISHERMAN_VILLAGER, "Desert Fisherman Villager Head");
        translationConsumer.accept(DESERT_FLETCHER_VILLAGER, "Desert Fletcher Villager Head");
        translationConsumer.accept(DESERT_LEATHERWORKER_VILLAGER, "Desert Leatherworker Villager Head");
        translationConsumer.accept(DESERT_LIBRARIAN_VILLAGER, "Desert Librarian Villager Head");
        translationConsumer.accept(DESERT_MASON_VILLAGER, "Desert Mason Villager Head");
        translationConsumer.accept(DESERT_NITWIT_VILLAGER, "Desert Nitwit Villager Head");
        translationConsumer.accept(DESERT_SHEPHERD_VILLAGER, "Desert Shepherd Villager Head");
        translationConsumer.accept(DESERT_TOOLSMITH_VILLAGER, "Desert Toolsmith Villager Head");
        translationConsumer.accept(DESERT_WEAPONSMITH_VILLAGER, "Desert Weaponsmith Villager Head");

        // Jungle Villagers
        translationConsumer.accept(JUNGLE_VILLAGER, "Jungle Villager Head");
        translationConsumer.accept(JUNGLE_ARMORER_VILLAGER, "Jungle Armorer Villager Head");
        translationConsumer.accept(JUNGLE_BUTCHER_VILLAGER, "Jungle Butcher Villager Head");
        translationConsumer.accept(JUNGLE_CARTOGRAPHER_VILLAGER, "Jungle Cartographer Villager Head");
        translationConsumer.accept(JUNGLE_CLERIC_VILLAGER, "Jungle Cleric Villager Head");
        translationConsumer.accept(JUNGLE_FARMER_VILLAGER, "Jungle Farmer Villager Head");
        translationConsumer.accept(JUNGLE_FISHERMAN_VILLAGER, "Jungle Fisherman Villager Head");
        translationConsumer.accept(JUNGLE_FLETCHER_VILLAGER, "Jungle Fletcher Villager Head");
        translationConsumer.accept(JUNGLE_LEATHERWORKER_VILLAGER, "Jungle Leatherworker Villager Head");
        translationConsumer.accept(JUNGLE_LIBRARIAN_VILLAGER, "Jungle Librarian Villager Head");
        translationConsumer.accept(JUNGLE_MASON_VILLAGER, "Jungle Mason Villager Head");
        translationConsumer.accept(JUNGLE_NITWIT_VILLAGER, "Jungle Nitwit Villager Head");
        translationConsumer.accept(JUNGLE_SHEPHERD_VILLAGER, "Jungle Shepherd Villager Head");
        translationConsumer.accept(JUNGLE_TOOLSMITH_VILLAGER, "Jungle Toolsmith Villager Head");
        translationConsumer.accept(JUNGLE_WEAPONSMITH_VILLAGER, "Jungle Weaponsmith Villager Head");

        // Plains Villagers
        translationConsumer.accept(PLAINS_VILLAGER, "Plains Villager Head");
        translationConsumer.accept(PLAINS_ARMORER_VILLAGER, "Plains Armorer Villager Head");
        translationConsumer.accept(PLAINS_BUTCHER_VILLAGER, "Plains Butcher Villager Head");
        translationConsumer.accept(PLAINS_CARTOGRAPHER_VILLAGER, "Plains Cartographer Villager Head");
        translationConsumer.accept(PLAINS_CLERIC_VILLAGER, "Plains Cleric Villager Head");
        translationConsumer.accept(PLAINS_FARMER_VILLAGER, "Plains Farmer Villager Head");
        translationConsumer.accept(PLAINS_FISHERMAN_VILLAGER, "Plains Fisherman Villager Head");
        translationConsumer.accept(PLAINS_FLETCHER_VILLAGER, "Plains Fletcher Villager Head");
        translationConsumer.accept(PLAINS_LEATHERWORKER_VILLAGER, "Plains Leatherworker Villager Head");
        translationConsumer.accept(PLAINS_LIBRARIAN_VILLAGER, "Plains Librarian Villager Head");
        translationConsumer.accept(PLAINS_MASON_VILLAGER, "Plains Mason Villager Head");
        translationConsumer.accept(PLAINS_NITWIT_VILLAGER, "Plains Nitwit Villager Head");
        translationConsumer.accept(PLAINS_SHEPHERD_VILLAGER, "Plains Shepherd Villager Head");
        translationConsumer.accept(PLAINS_TOOLSMITH_VILLAGER, "Plains Toolsmith Villager Head");
        translationConsumer.accept(PLAINS_WEAPONSMITH_VILLAGER, "Plains Weaponsmith Villager Head");

        // Savanna Villagers
        translationConsumer.accept(SAVANNA_VILLAGER, "Savanna Villager Head");
        translationConsumer.accept(SAVANNA_ARMORER_VILLAGER, "Savanna Armorer Villager Head");
        translationConsumer.accept(SAVANNA_BUTCHER_VILLAGER, "Savanna Butcher Villager Head");
        translationConsumer.accept(SAVANNA_CARTOGRAPHER_VILLAGER, "Savanna Cartographer Villager Head");
        translationConsumer.accept(SAVANNA_CLERIC_VILLAGER, "Savanna Cleric Villager Head");
        translationConsumer.accept(SAVANNA_FARMER_VILLAGER, "Savanna Farmer Villager Head");
        translationConsumer.accept(SAVANNA_FISHERMAN_VILLAGER, "Savanna Fisherman Villager Head");
        translationConsumer.accept(SAVANNA_FLETCHER_VILLAGER, "Savanna Fletcher Villager Head");
        translationConsumer.accept(SAVANNA_LEATHERWORKER_VILLAGER, "Savanna Leatherworker Villager Head");
        translationConsumer.accept(SAVANNA_LIBRARIAN_VILLAGER, "Savanna Librarian Villager Head");
        translationConsumer.accept(SAVANNA_MASON_VILLAGER, "Savanna Mason Villager Head");
        translationConsumer.accept(SAVANNA_NITWIT_VILLAGER, "Savanna Nitwit Villager Head");
        translationConsumer.accept(SAVANNA_SHEPHERD_VILLAGER, "Savanna Shepherd Villager Head");
        translationConsumer.accept(SAVANNA_TOOLSMITH_VILLAGER, "Savanna Toolsmith Villager Head");
        translationConsumer.accept(SAVANNA_WEAPONSMITH_VILLAGER, "Savanna Weaponsmith Villager Head");

        // Snow Villagers
        translationConsumer.accept(SNOW_VILLAGER, "Snow Villager Head");
        translationConsumer.accept(SNOW_ARMORER_VILLAGER, "Snow Armorer Villager Head");
        translationConsumer.accept(SNOW_BUTCHER_VILLAGER, "Snow Butcher Villager Head");
        translationConsumer.accept(SNOW_CARTOGRAPHER_VILLAGER, "Snow Cartographer Villager Head");
        translationConsumer.accept(SNOW_CLERIC_VILLAGER, "Snow Cleric Villager Head");
        translationConsumer.accept(SNOW_FARMER_VILLAGER, "Snow Farmer Villager Head");
        translationConsumer.accept(SNOW_FISHERMAN_VILLAGER, "Snow Fisherman Villager Head");
        translationConsumer.accept(SNOW_FLETCHER_VILLAGER, "Snow Fletcher Villager Head");
        translationConsumer.accept(SNOW_LEATHERWORKER_VILLAGER, "Snow Leatherworker Villager Head");
        translationConsumer.accept(SNOW_LIBRARIAN_VILLAGER, "Snow Librarian Villager Head");
        translationConsumer.accept(SNOW_MASON_VILLAGER, "Snow Mason Villager Head");
        translationConsumer.accept(SNOW_NITWIT_VILLAGER, "Snow Nitwit Villager Head");
        translationConsumer.accept(SNOW_SHEPHERD_VILLAGER, "Snow Shepherd Villager Head");
        translationConsumer.accept(SNOW_TOOLSMITH_VILLAGER, "Snow Toolsmith Villager Head");
        translationConsumer.accept(SNOW_WEAPONSMITH_VILLAGER, "Snow Weaponsmith Villager Head");

        // Swamp Villagers
        translationConsumer.accept(SWAMP_VILLAGER, "Swamp Villager Head");
        translationConsumer.accept(SWAMP_ARMORER_VILLAGER, "Swamp Armorer Villager Head");
        translationConsumer.accept(SWAMP_BUTCHER_VILLAGER, "Swamp Butcher Villager Head");
        translationConsumer.accept(SWAMP_CARTOGRAPHER_VILLAGER, "Swamp Cartographer Villager Head");
        translationConsumer.accept(SWAMP_CLERIC_VILLAGER, "Swamp Cleric Villager Head");
        translationConsumer.accept(SWAMP_FARMER_VILLAGER, "Swamp Farmer Villager Head");
        translationConsumer.accept(SWAMP_FISHERMAN_VILLAGER, "Swamp Fisherman Villager Head");
        translationConsumer.accept(SWAMP_FLETCHER_VILLAGER, "Swamp Fletcher Villager Head");
        translationConsumer.accept(SWAMP_LEATHERWORKER_VILLAGER, "Swamp Leatherworker Villager Head");
        translationConsumer.accept(SWAMP_LIBRARIAN_VILLAGER, "Swamp Librarian Villager Head");
        translationConsumer.accept(SWAMP_MASON_VILLAGER, "Swamp Mason Villager Head");
        translationConsumer.accept(SWAMP_NITWIT_VILLAGER, "Swamp Nitwit Villager Head");
        translationConsumer.accept(SWAMP_SHEPHERD_VILLAGER, "Swamp Shepherd Villager Head");
        translationConsumer.accept(SWAMP_TOOLSMITH_VILLAGER, "Swamp Toolsmith Villager Head");
        translationConsumer.accept(SWAMP_WEAPONSMITH_VILLAGER, "Swamp Weaponsmith Villager Head");

        // Taiga Villagers
        translationConsumer.accept(TAIGA_VILLAGER, "Taiga Villager Head");
        translationConsumer.accept(TAIGA_ARMORER_VILLAGER, "Taiga Armorer Villager Head");
        translationConsumer.accept(TAIGA_BUTCHER_VILLAGER, "Taiga Butcher Villager Head");
        translationConsumer.accept(TAIGA_CARTOGRAPHER_VILLAGER, "Taiga Cartographer Villager Head");
        translationConsumer.accept(TAIGA_CLERIC_VILLAGER, "Taiga Cleric Villager Head");
        translationConsumer.accept(TAIGA_FARMER_VILLAGER, "Taiga Farmer Villager Head");
        translationConsumer.accept(TAIGA_FISHERMAN_VILLAGER, "Taiga Fisherman Villager Head");
        translationConsumer.accept(TAIGA_FLETCHER_VILLAGER, "Taiga Fletcher Villager Head");
        translationConsumer.accept(TAIGA_LEATHERWORKER_VILLAGER, "Taiga Leatherworker Villager Head");
        translationConsumer.accept(TAIGA_LIBRARIAN_VILLAGER, "Taiga Librarian Villager Head");
        translationConsumer.accept(TAIGA_MASON_VILLAGER, "Taiga Mason Villager Head");
        translationConsumer.accept(TAIGA_NITWIT_VILLAGER, "Taiga Nitwit Villager Head");
        translationConsumer.accept(TAIGA_SHEPHERD_VILLAGER, "Taiga Shepherd Villager Head");
        translationConsumer.accept(TAIGA_TOOLSMITH_VILLAGER, "Taiga Toolsmith Villager Head");
        translationConsumer.accept(TAIGA_WEAPONSMITH_VILLAGER, "Taiga Weaponsmith Villager Head");
    }
}
