package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.critereon.VillagerDataPredicate;
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

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.register;

public class ZombieVillagerHeadType {
    // Desert Zombie Villagers
    public static final ResourceKey<HeadType> DESERT_ZOMBIE_VILLAGER = register("zombie_villager/desert");
    public static final ResourceKey<HeadType> DESERT_ARMORER_ZOMBIE_VILLAGER = register("zombie_villager/desert/armorer");
    public static final ResourceKey<HeadType> DESERT_BUTCHER_ZOMBIE_VILLAGER = register("zombie_villager/desert/butcher");
    public static final ResourceKey<HeadType> DESERT_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/cartographer");
    public static final ResourceKey<HeadType> DESERT_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/desert/cleric");
    public static final ResourceKey<HeadType> DESERT_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/desert/farmer");
    public static final ResourceKey<HeadType> DESERT_FISHERMAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/fisherman");
    public static final ResourceKey<HeadType> DESERT_FLETCHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/fletcher");
    public static final ResourceKey<HeadType> DESERT_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/leatherworker");
    public static final ResourceKey<HeadType> DESERT_LIBRARIAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/librarian");
    public static final ResourceKey<HeadType> DESERT_MASON_ZOMBIE_VILLAGER = register("zombie_villager/desert/mason");
    public static final ResourceKey<HeadType> DESERT_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/desert/nitwit");
    public static final ResourceKey<HeadType> DESERT_SHEPHERD_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/shepherd");
    public static final ResourceKey<HeadType> DESERT_TOOLSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/toolsmith");
    public static final ResourceKey<HeadType> DESERT_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/desert/weaponsmith");
    // Jungle Zombie Villagers
    public static final ResourceKey<HeadType> JUNGLE_ZOMBIE_VILLAGER = register("zombie_villager/jungle");
    public static final ResourceKey<HeadType> JUNGLE_ARMORER_ZOMBIE_VILLAGER = register("zombie_villager/jungle/armorer");
    public static final ResourceKey<HeadType> JUNGLE_BUTCHER_ZOMBIE_VILLAGER = register("zombie_villager/jungle/butcher");
    public static final ResourceKey<HeadType> JUNGLE_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/cartographer");
    public static final ResourceKey<HeadType> JUNGLE_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/jungle/cleric");
    public static final ResourceKey<HeadType> JUNGLE_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/jungle/farmer");
    public static final ResourceKey<HeadType> JUNGLE_FISHERMAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/fisherman");
    public static final ResourceKey<HeadType> JUNGLE_FLETCHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/fletcher");
    public static final ResourceKey<HeadType> JUNGLE_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/leatherworker");
    public static final ResourceKey<HeadType> JUNGLE_LIBRARIAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/librarian");
    public static final ResourceKey<HeadType> JUNGLE_MASON_ZOMBIE_VILLAGER = register("zombie_villager/jungle/mason");
    public static final ResourceKey<HeadType> JUNGLE_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/jungle/nitwit");
    public static final ResourceKey<HeadType> JUNGLE_SHEPHERD_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/shepherd");
    public static final ResourceKey<HeadType> JUNGLE_TOOLSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/toolsmith");
    public static final ResourceKey<HeadType> JUNGLE_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/jungle/weaponsmith");
    // Plains Zombie Villagers
    public static final ResourceKey<HeadType> PLAINS_ZOMBIE_VILLAGER = register("zombie_villager/plains");
    public static final ResourceKey<HeadType> PLAINS_ARMORER_ZOMBIE_VILLAGER = register("zombie_villager/plains/armorer");
    public static final ResourceKey<HeadType> PLAINS_BUTCHER_ZOMBIE_VILLAGER = register("zombie_villager/plains/butcher");
    public static final ResourceKey<HeadType> PLAINS_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/cartographer");
    public static final ResourceKey<HeadType> PLAINS_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/plains/cleric");
    public static final ResourceKey<HeadType> PLAINS_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/plains/farmer");
    public static final ResourceKey<HeadType> PLAINS_FISHERMAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/fisherman");
    public static final ResourceKey<HeadType> PLAINS_FLETCHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/fletcher");
    public static final ResourceKey<HeadType> PLAINS_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/leatherworker");
    public static final ResourceKey<HeadType> PLAINS_LIBRARIAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/librarian");
    public static final ResourceKey<HeadType> PLAINS_MASON_ZOMBIE_VILLAGER = register("zombie_villager/plains/mason");
    public static final ResourceKey<HeadType> PLAINS_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/plains/nitwit");
    public static final ResourceKey<HeadType> PLAINS_SHEPHERD_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/shepherd");
    public static final ResourceKey<HeadType> PLAINS_TOOLSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/toolsmith");
    public static final ResourceKey<HeadType> PLAINS_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/plains/weaponsmith");
    // Savanna Zombie Villagers
    public static final ResourceKey<HeadType> SAVANNA_ZOMBIE_VILLAGER = register("zombie_villager/savanna");
    public static final ResourceKey<HeadType> SAVANNA_ARMORER_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/armorer");
    public static final ResourceKey<HeadType> SAVANNA_BUTCHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/butcher");
    public static final ResourceKey<HeadType> SAVANNA_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/cartographer");
    public static final ResourceKey<HeadType> SAVANNA_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/savanna/cleric");
    public static final ResourceKey<HeadType> SAVANNA_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/savanna/farmer");
    public static final ResourceKey<HeadType> SAVANNA_FISHERMAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/fisherman");
    public static final ResourceKey<HeadType> SAVANNA_FLETCHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/fletcher");
    public static final ResourceKey<HeadType> SAVANNA_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/leatherworker");
    public static final ResourceKey<HeadType> SAVANNA_LIBRARIAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/librarian");
    public static final ResourceKey<HeadType> SAVANNA_MASON_ZOMBIE_VILLAGER = register("zombie_villager/savanna/mason");
    public static final ResourceKey<HeadType> SAVANNA_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/savanna/nitwit");
    public static final ResourceKey<HeadType> SAVANNA_SHEPHERD_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/shepherd");
    public static final ResourceKey<HeadType> SAVANNA_TOOLSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/toolsmith");
    public static final ResourceKey<HeadType> SAVANNA_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/savanna/weaponsmith");
    // Snow Zombie Villagers
    public static final ResourceKey<HeadType> SNOW_ZOMBIE_VILLAGER = register("zombie_villager/snow");
    public static final ResourceKey<HeadType> SNOW_ARMORER_ZOMBIE_VILLAGER = register("zombie_villager/snow/armorer");
    public static final ResourceKey<HeadType> SNOW_BUTCHER_ZOMBIE_VILLAGER = register("zombie_villager/snow/butcher");
    public static final ResourceKey<HeadType> SNOW_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/snow/cartographer");
    public static final ResourceKey<HeadType> SNOW_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/snow/cleric");
    public static final ResourceKey<HeadType> SNOW_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/snow/farmer");
    public static final ResourceKey<HeadType> SNOW_FISHERMAN_ZOMBIE_VILLAGER = register("zombie_villager/snow/fisherman");
    public static final ResourceKey<HeadType> SNOW_FLETCHER_ZOMBIE_VILLAGER = register("zombie_villager/snow/fletcher");
    public static final ResourceKey<HeadType> SNOW_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/snow/leatherworker");
    public static final ResourceKey<HeadType> SNOW_LIBRARIAN_ZOMBIE_VILLAGER = register("zombie_villager/snow/librarian");
    public static final ResourceKey<HeadType> SNOW_MASON_ZOMBIE_VILLAGER = register("zombie_villager/snow/mason");
    public static final ResourceKey<HeadType> SNOW_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/snow/nitwit");
    public static final ResourceKey<HeadType> SNOW_SHEPHERD_ZOMBIE_VILLAGER = register("zombie_villager/snow/shepherd");
    public static final ResourceKey<HeadType> SNOW_TOOLSMITH_ZOMBIE_VILLAGER = register("zombie_villager/snow/toolsmith");
    public static final ResourceKey<HeadType> SNOW_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/snow/weaponsmith");
    // Swamp Zombie Villagers
    public static final ResourceKey<HeadType> SWAMP_ZOMBIE_VILLAGER = register("zombie_villager/swamp");
    public static final ResourceKey<HeadType> SWAMP_ARMORER_ZOMBIE_VILLAGER = register("zombie_villager/swamp/armorer");
    public static final ResourceKey<HeadType> SWAMP_BUTCHER_ZOMBIE_VILLAGER = register("zombie_villager/swamp/butcher");
    public static final ResourceKey<HeadType> SWAMP_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/swamp/cartographer");
    public static final ResourceKey<HeadType> SWAMP_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/swamp/cleric");
    public static final ResourceKey<HeadType> SWAMP_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/swamp/farmer");
    public static final ResourceKey<HeadType> SWAMP_FISHERMAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/swamp/fisherman");
    public static final ResourceKey<HeadType> SWAMP_FLETCHER_ZOMBIE_VILLAGER = register("zombie_villager/swamp/fletcher");
    public static final ResourceKey<HeadType> SWAMP_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/swamp/leatherworker");
    public static final ResourceKey<HeadType> SWAMP_LIBRARIAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/swamp/librarian");
    public static final ResourceKey<HeadType> SWAMP_MASON_ZOMBIE_VILLAGER = register("zombie_villager/swamp/mason");
    public static final ResourceKey<HeadType> SWAMP_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/swamp/nitwit");
    public static final ResourceKey<HeadType> SWAMP_SHEPHERD_ZOMBIE_VILLAGER = register("zombie_villager/swamp/shepherd");
    public static final ResourceKey<HeadType> SWAMP_TOOLSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/swamp/toolsmith");
    public static final ResourceKey<HeadType> SWAMP_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/swamp/weaponsmith");
    // Taiga Zombie Villagers
    public static final ResourceKey<HeadType> TAIGA_ZOMBIE_VILLAGER = register("zombie_villager/taiga");
    public static final ResourceKey<HeadType> TAIGA_ARMORER_ZOMBIE_VILLAGER = register("zombie_villager/taiga/armorer");
    public static final ResourceKey<HeadType> TAIGA_BUTCHER_ZOMBIE_VILLAGER = register("zombie_villager/taiga/butcher");
    public static final ResourceKey<HeadType> TAIGA_CARTOGRAPHER_ZOMBIE_VILLAGER = register(
            "zombie_villager/taiga/cartographer");
    public static final ResourceKey<HeadType> TAIGA_CLERIC_ZOMBIE_VILLAGER = register("zombie_villager/taiga/cleric");
    public static final ResourceKey<HeadType> TAIGA_FARMER_ZOMBIE_VILLAGER = register("zombie_villager/taiga/farmer");
    public static final ResourceKey<HeadType> TAIGA_FISHERMAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/taiga/fisherman");
    public static final ResourceKey<HeadType> TAIGA_FLETCHER_ZOMBIE_VILLAGER = register("zombie_villager/taiga/fletcher");
    public static final ResourceKey<HeadType> TAIGA_LEATHERWORKER_ZOMBIE_VILLAGER = register(
            "zombie_villager/taiga/leatherworker");
    public static final ResourceKey<HeadType> TAIGA_LIBRARIAN_ZOMBIE_VILLAGER = register(
            "zombie_villager/taiga/librarian");
    public static final ResourceKey<HeadType> TAIGA_MASON_ZOMBIE_VILLAGER = register("zombie_villager/taiga/mason");
    public static final ResourceKey<HeadType> TAIGA_NITWIT_ZOMBIE_VILLAGER = register("zombie_villager/taiga/nitwit");
    public static final ResourceKey<HeadType> TAIGA_SHEPHERD_ZOMBIE_VILLAGER = register("zombie_villager/taiga/shepherd");
    public static final ResourceKey<HeadType> TAIGA_TOOLSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/taiga/toolsmith");
    public static final ResourceKey<HeadType> TAIGA_WEAPONSMITH_ZOMBIE_VILLAGER = register(
            "zombie_villager/taiga/weaponsmith");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        // Desert Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.DESERT, DESERT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.ARMORER,
                DESERT_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.BUTCHER,
                DESERT_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.CARTOGRAPHER,
                DESERT_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.DESERT, VillagerProfession.CLERIC, DESERT_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.DESERT, VillagerProfession.FARMER, DESERT_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.FISHERMAN,
                DESERT_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.FLETCHER,
                DESERT_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.LEATHERWORKER,
                DESERT_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.LIBRARIAN,
                DESERT_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.DESERT, VillagerProfession.MASON, DESERT_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.DESERT, VillagerProfession.NITWIT, DESERT_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.SHEPHERD,
                DESERT_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.TOOLSMITH,
                DESERT_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.DESERT,
                VillagerProfession.WEAPONSMITH,
                DESERT_WEAPONSMITH_ZOMBIE_VILLAGER);

        // Jungle Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.JUNGLE, JUNGLE_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.ARMORER,
                JUNGLE_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.BUTCHER,
                JUNGLE_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.CARTOGRAPHER,
                JUNGLE_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.JUNGLE, VillagerProfession.CLERIC, JUNGLE_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.JUNGLE, VillagerProfession.FARMER, JUNGLE_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.FISHERMAN,
                JUNGLE_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.FLETCHER,
                JUNGLE_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.LEATHERWORKER,
                JUNGLE_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.LIBRARIAN,
                JUNGLE_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.JUNGLE, VillagerProfession.MASON, JUNGLE_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.JUNGLE, VillagerProfession.NITWIT, JUNGLE_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.SHEPHERD,
                JUNGLE_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.TOOLSMITH,
                JUNGLE_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.JUNGLE,
                VillagerProfession.WEAPONSMITH,
                JUNGLE_WEAPONSMITH_ZOMBIE_VILLAGER);

        // Plains Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.PLAINS, PLAINS_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.ARMORER,
                PLAINS_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.BUTCHER,
                PLAINS_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.CARTOGRAPHER,
                PLAINS_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.PLAINS, VillagerProfession.CLERIC, PLAINS_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.PLAINS, VillagerProfession.FARMER, PLAINS_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.FISHERMAN,
                PLAINS_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.FLETCHER,
                PLAINS_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.LEATHERWORKER,
                PLAINS_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.LIBRARIAN,
                PLAINS_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.PLAINS, VillagerProfession.MASON, PLAINS_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.PLAINS, VillagerProfession.NITWIT, PLAINS_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.SHEPHERD,
                PLAINS_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.TOOLSMITH,
                PLAINS_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.PLAINS,
                VillagerProfession.WEAPONSMITH,
                PLAINS_WEAPONSMITH_ZOMBIE_VILLAGER);

        // Savanna Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.SAVANNA, SAVANNA_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.ARMORER,
                SAVANNA_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.BUTCHER,
                SAVANNA_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.CARTOGRAPHER,
                SAVANNA_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.CLERIC,
                SAVANNA_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.FARMER,
                SAVANNA_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.FISHERMAN,
                SAVANNA_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.FLETCHER,
                SAVANNA_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.LEATHERWORKER,
                SAVANNA_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.LIBRARIAN,
                SAVANNA_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SAVANNA, VillagerProfession.MASON, SAVANNA_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.NITWIT,
                SAVANNA_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.SHEPHERD,
                SAVANNA_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.TOOLSMITH,
                SAVANNA_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SAVANNA,
                VillagerProfession.WEAPONSMITH,
                SAVANNA_WEAPONSMITH_ZOMBIE_VILLAGER);

        // Snow Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.SNOW, SNOW_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.ARMORER, SNOW_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.BUTCHER, SNOW_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SNOW,
                VillagerProfession.CARTOGRAPHER,
                SNOW_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.CLERIC, SNOW_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.FARMER, SNOW_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SNOW,
                VillagerProfession.FISHERMAN,
                SNOW_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.FLETCHER, SNOW_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SNOW,
                VillagerProfession.LEATHERWORKER,
                SNOW_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SNOW,
                VillagerProfession.LIBRARIAN,
                SNOW_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.MASON, SNOW_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.NITWIT, SNOW_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SNOW, VillagerProfession.SHEPHERD, SNOW_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SNOW,
                VillagerProfession.TOOLSMITH,
                SNOW_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SNOW,
                VillagerProfession.WEAPONSMITH,
                SNOW_WEAPONSMITH_ZOMBIE_VILLAGER);

        // Swamp Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.SWAMP, SWAMP_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SWAMP, VillagerProfession.ARMORER, SWAMP_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SWAMP, VillagerProfession.BUTCHER, SWAMP_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.CARTOGRAPHER,
                SWAMP_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SWAMP, VillagerProfession.CLERIC, SWAMP_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SWAMP, VillagerProfession.FARMER, SWAMP_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.FISHERMAN,
                SWAMP_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.FLETCHER,
                SWAMP_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.LEATHERWORKER,
                SWAMP_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.LIBRARIAN,
                SWAMP_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SWAMP, VillagerProfession.MASON, SWAMP_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.SWAMP, VillagerProfession.NITWIT, SWAMP_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.SHEPHERD,
                SWAMP_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.TOOLSMITH,
                SWAMP_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.SWAMP,
                VillagerProfession.WEAPONSMITH,
                SWAMP_WEAPONSMITH_ZOMBIE_VILLAGER);

        // Taiga Zombie Villagers
        bootstrapZombieVillager(context, VillagerType.TAIGA, TAIGA_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.TAIGA, VillagerProfession.ARMORER, TAIGA_ARMORER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.TAIGA, VillagerProfession.BUTCHER, TAIGA_BUTCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.CARTOGRAPHER,
                TAIGA_CARTOGRAPHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.TAIGA, VillagerProfession.CLERIC, TAIGA_CLERIC_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.TAIGA, VillagerProfession.FARMER, TAIGA_FARMER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.FISHERMAN,
                TAIGA_FISHERMAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.FLETCHER,
                TAIGA_FLETCHER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.LEATHERWORKER,
                TAIGA_LEATHERWORKER_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.LIBRARIAN,
                TAIGA_LIBRARIAN_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.TAIGA, VillagerProfession.MASON, TAIGA_MASON_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context, VillagerType.TAIGA, VillagerProfession.NITWIT, TAIGA_NITWIT_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.SHEPHERD,
                TAIGA_SHEPHERD_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.TOOLSMITH,
                TAIGA_TOOLSMITH_ZOMBIE_VILLAGER);
        bootstrapZombieVillager(context,
                VillagerType.TAIGA,
                VillagerProfession.WEAPONSMITH,
                TAIGA_WEAPONSMITH_ZOMBIE_VILLAGER);
    }

    private static void bootstrapZombieVillager(BootstrapContext<HeadType> context, ResourceKey<VillagerType> type, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityTypes.ZOMBIE_VILLAGER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.put(VillagerDataPredicate.CODEC,
                            VillagerDataPredicate.hasData(context.lookup(Registries.VILLAGER_TYPE).getOrThrow(type),
                                    context.lookup(Registries.VILLAGER_PROFESSION)
                                            .getOrThrow(VillagerProfession.NONE)));
                })
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.VILLAGER, Identifier.withDefaultNamespace("entity/zombie_villager/zombie_villager"))
                .model(ModelType.VILLAGER, type.identifier().withPrefix("entity/zombie_villager/type/"))
                .noteBlockSound(SoundEvents.ZOMBIE_VILLAGER_AMBIENT)
                .build(context, resourceKey);
    }

    private static void bootstrapZombieVillager(BootstrapContext<HeadType> context, ResourceKey<VillagerType> type, ResourceKey<VillagerProfession> profession, ResourceKey<HeadType> resourceKey) {
        HeadType.builder(EntityTypes.ZOMBIE_VILLAGER)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.put(VillagerDataPredicate.CODEC,
                            VillagerDataPredicate.hasData(context.lookup(Registries.VILLAGER_TYPE).getOrThrow(type),
                                    context.lookup(Registries.VILLAGER_PROFESSION).getOrThrow(profession)));
                })
                .shape(8.0, 10.0, 8.0)
                .scale(0.9375)
                .model(ModelType.VILLAGER, Identifier.withDefaultNamespace("entity/zombie_villager/zombie_villager"))
                .model(ModelType.VILLAGER, type.identifier().withPrefix("entity/zombie_villager/type/"))
                .model(ModelType.VILLAGER, profession.identifier().withPrefix("entity/zombie_villager/profession/"))
                .noteBlockSound(SoundEvents.ZOMBIE_VILLAGER_AMBIENT)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        // Desert Zombie Villagers
        translationConsumer.accept(DESERT_ZOMBIE_VILLAGER, "Desert Zombie Villager Head");
        translationConsumer.accept(DESERT_ARMORER_ZOMBIE_VILLAGER, "Desert Armorer Zombie Villager Head");
        translationConsumer.accept(DESERT_BUTCHER_ZOMBIE_VILLAGER, "Desert Butcher Zombie Villager Head");
        translationConsumer.accept(DESERT_CARTOGRAPHER_ZOMBIE_VILLAGER, "Desert Cartographer Zombie Villager Head");
        translationConsumer.accept(DESERT_CLERIC_ZOMBIE_VILLAGER, "Desert Cleric Zombie Villager Head");
        translationConsumer.accept(DESERT_FARMER_ZOMBIE_VILLAGER, "Desert Farmer Zombie Villager Head");
        translationConsumer.accept(DESERT_FISHERMAN_ZOMBIE_VILLAGER, "Desert Fisherman Zombie Villager Head");
        translationConsumer.accept(DESERT_FLETCHER_ZOMBIE_VILLAGER, "Desert Fletcher Zombie Villager Head");
        translationConsumer.accept(DESERT_LEATHERWORKER_ZOMBIE_VILLAGER, "Desert Leatherworker Zombie Villager Head");
        translationConsumer.accept(DESERT_LIBRARIAN_ZOMBIE_VILLAGER, "Desert Librarian Zombie Villager Head");
        translationConsumer.accept(DESERT_MASON_ZOMBIE_VILLAGER, "Desert Mason Zombie Villager Head");
        translationConsumer.accept(DESERT_NITWIT_ZOMBIE_VILLAGER, "Desert Nitwit Zombie Villager Head");
        translationConsumer.accept(DESERT_SHEPHERD_ZOMBIE_VILLAGER, "Desert Shepherd Zombie Villager Head");
        translationConsumer.accept(DESERT_TOOLSMITH_ZOMBIE_VILLAGER, "Desert Toolsmith Zombie Villager Head");
        translationConsumer.accept(DESERT_WEAPONSMITH_ZOMBIE_VILLAGER, "Desert Weaponsmith Zombie Villager Head");

        // Jungle Zombie Villagers
        translationConsumer.accept(JUNGLE_ZOMBIE_VILLAGER, "Jungle Zombie Villager Head");
        translationConsumer.accept(JUNGLE_ARMORER_ZOMBIE_VILLAGER, "Jungle Armorer Zombie Villager Head");
        translationConsumer.accept(JUNGLE_BUTCHER_ZOMBIE_VILLAGER, "Jungle Butcher Zombie Villager Head");
        translationConsumer.accept(JUNGLE_CARTOGRAPHER_ZOMBIE_VILLAGER, "Jungle Cartographer Zombie Villager Head");
        translationConsumer.accept(JUNGLE_CLERIC_ZOMBIE_VILLAGER, "Jungle Cleric Zombie Villager Head");
        translationConsumer.accept(JUNGLE_FARMER_ZOMBIE_VILLAGER, "Jungle Farmer Zombie Villager Head");
        translationConsumer.accept(JUNGLE_FISHERMAN_ZOMBIE_VILLAGER, "Jungle Fisherman Zombie Villager Head");
        translationConsumer.accept(JUNGLE_FLETCHER_ZOMBIE_VILLAGER, "Jungle Fletcher Zombie Villager Head");
        translationConsumer.accept(JUNGLE_LEATHERWORKER_ZOMBIE_VILLAGER, "Jungle Leatherworker Zombie Villager Head");
        translationConsumer.accept(JUNGLE_LIBRARIAN_ZOMBIE_VILLAGER, "Jungle Librarian Zombie Villager Head");
        translationConsumer.accept(JUNGLE_MASON_ZOMBIE_VILLAGER, "Jungle Mason Zombie Villager Head");
        translationConsumer.accept(JUNGLE_NITWIT_ZOMBIE_VILLAGER, "Jungle Nitwit Zombie Villager Head");
        translationConsumer.accept(JUNGLE_SHEPHERD_ZOMBIE_VILLAGER, "Jungle Shepherd Zombie Villager Head");
        translationConsumer.accept(JUNGLE_TOOLSMITH_ZOMBIE_VILLAGER, "Jungle Toolsmith Zombie Villager Head");
        translationConsumer.accept(JUNGLE_WEAPONSMITH_ZOMBIE_VILLAGER, "Jungle Weaponsmith Zombie Villager Head");

        // Plains Zombie Villagers
        translationConsumer.accept(PLAINS_ZOMBIE_VILLAGER, "Plains Zombie Villager Head");
        translationConsumer.accept(PLAINS_ARMORER_ZOMBIE_VILLAGER, "Plains Armorer Zombie Villager Head");
        translationConsumer.accept(PLAINS_BUTCHER_ZOMBIE_VILLAGER, "Plains Butcher Zombie Villager Head");
        translationConsumer.accept(PLAINS_CARTOGRAPHER_ZOMBIE_VILLAGER, "Plains Cartographer Zombie Villager Head");
        translationConsumer.accept(PLAINS_CLERIC_ZOMBIE_VILLAGER, "Plains Cleric Zombie Villager Head");
        translationConsumer.accept(PLAINS_FARMER_ZOMBIE_VILLAGER, "Plains Farmer Zombie Villager Head");
        translationConsumer.accept(PLAINS_FISHERMAN_ZOMBIE_VILLAGER, "Plains Fisherman Zombie Villager Head");
        translationConsumer.accept(PLAINS_FLETCHER_ZOMBIE_VILLAGER, "Plains Fletcher Zombie Villager Head");
        translationConsumer.accept(PLAINS_LEATHERWORKER_ZOMBIE_VILLAGER, "Plains Leatherworker Zombie Villager Head");
        translationConsumer.accept(PLAINS_LIBRARIAN_ZOMBIE_VILLAGER, "Plains Librarian Zombie Villager Head");
        translationConsumer.accept(PLAINS_MASON_ZOMBIE_VILLAGER, "Plains Mason Zombie Villager Head");
        translationConsumer.accept(PLAINS_NITWIT_ZOMBIE_VILLAGER, "Plains Nitwit Zombie Villager Head");
        translationConsumer.accept(PLAINS_SHEPHERD_ZOMBIE_VILLAGER, "Plains Shepherd Zombie Villager Head");
        translationConsumer.accept(PLAINS_TOOLSMITH_ZOMBIE_VILLAGER, "Plains Toolsmith Zombie Villager Head");
        translationConsumer.accept(PLAINS_WEAPONSMITH_ZOMBIE_VILLAGER, "Plains Weaponsmith Zombie Villager Head");

        // Savanna Zombie Villagers
        translationConsumer.accept(SAVANNA_ZOMBIE_VILLAGER, "Savanna Zombie Villager Head");
        translationConsumer.accept(SAVANNA_ARMORER_ZOMBIE_VILLAGER, "Savanna Armorer Zombie Villager Head");
        translationConsumer.accept(SAVANNA_BUTCHER_ZOMBIE_VILLAGER, "Savanna Butcher Zombie Villager Head");
        translationConsumer.accept(SAVANNA_CARTOGRAPHER_ZOMBIE_VILLAGER, "Savanna Cartographer Zombie Villager Head");
        translationConsumer.accept(SAVANNA_CLERIC_ZOMBIE_VILLAGER, "Savanna Cleric Zombie Villager Head");
        translationConsumer.accept(SAVANNA_FARMER_ZOMBIE_VILLAGER, "Savanna Farmer Zombie Villager Head");
        translationConsumer.accept(SAVANNA_FISHERMAN_ZOMBIE_VILLAGER, "Savanna Fisherman Zombie Villager Head");
        translationConsumer.accept(SAVANNA_FLETCHER_ZOMBIE_VILLAGER, "Savanna Fletcher Zombie Villager Head");
        translationConsumer.accept(SAVANNA_LEATHERWORKER_ZOMBIE_VILLAGER, "Savanna Leatherworker Zombie Villager Head");
        translationConsumer.accept(SAVANNA_LIBRARIAN_ZOMBIE_VILLAGER, "Savanna Librarian Zombie Villager Head");
        translationConsumer.accept(SAVANNA_MASON_ZOMBIE_VILLAGER, "Savanna Mason Zombie Villager Head");
        translationConsumer.accept(SAVANNA_NITWIT_ZOMBIE_VILLAGER, "Savanna Nitwit Zombie Villager Head");
        translationConsumer.accept(SAVANNA_SHEPHERD_ZOMBIE_VILLAGER, "Savanna Shepherd Zombie Villager Head");
        translationConsumer.accept(SAVANNA_TOOLSMITH_ZOMBIE_VILLAGER, "Savanna Toolsmith Zombie Villager Head");
        translationConsumer.accept(SAVANNA_WEAPONSMITH_ZOMBIE_VILLAGER, "Savanna Weaponsmith Zombie Villager Head");

        // Snow Zombie Villagers
        translationConsumer.accept(SNOW_ZOMBIE_VILLAGER, "Snow Zombie Villager Head");
        translationConsumer.accept(SNOW_ARMORER_ZOMBIE_VILLAGER, "Snow Armorer Zombie Villager Head");
        translationConsumer.accept(SNOW_BUTCHER_ZOMBIE_VILLAGER, "Snow Butcher Zombie Villager Head");
        translationConsumer.accept(SNOW_CARTOGRAPHER_ZOMBIE_VILLAGER, "Snow Cartographer Zombie Villager Head");
        translationConsumer.accept(SNOW_CLERIC_ZOMBIE_VILLAGER, "Snow Cleric Zombie Villager Head");
        translationConsumer.accept(SNOW_FARMER_ZOMBIE_VILLAGER, "Snow Farmer Zombie Villager Head");
        translationConsumer.accept(SNOW_FISHERMAN_ZOMBIE_VILLAGER, "Snow Fisherman Zombie Villager Head");
        translationConsumer.accept(SNOW_FLETCHER_ZOMBIE_VILLAGER, "Snow Fletcher Zombie Villager Head");
        translationConsumer.accept(SNOW_LEATHERWORKER_ZOMBIE_VILLAGER, "Snow Leatherworker Zombie Villager Head");
        translationConsumer.accept(SNOW_LIBRARIAN_ZOMBIE_VILLAGER, "Snow Librarian Zombie Villager Head");
        translationConsumer.accept(SNOW_MASON_ZOMBIE_VILLAGER, "Snow Mason Zombie Villager Head");
        translationConsumer.accept(SNOW_NITWIT_ZOMBIE_VILLAGER, "Snow Nitwit Zombie Villager Head");
        translationConsumer.accept(SNOW_SHEPHERD_ZOMBIE_VILLAGER, "Snow Shepherd Zombie Villager Head");
        translationConsumer.accept(SNOW_TOOLSMITH_ZOMBIE_VILLAGER, "Snow Toolsmith Zombie Villager Head");
        translationConsumer.accept(SNOW_WEAPONSMITH_ZOMBIE_VILLAGER, "Snow Weaponsmith Zombie Villager Head");

        // Swamp Zombie Villagers
        translationConsumer.accept(SWAMP_ZOMBIE_VILLAGER, "Swamp Zombie Villager Head");
        translationConsumer.accept(SWAMP_ARMORER_ZOMBIE_VILLAGER, "Swamp Armorer Zombie Villager Head");
        translationConsumer.accept(SWAMP_BUTCHER_ZOMBIE_VILLAGER, "Swamp Butcher Zombie Villager Head");
        translationConsumer.accept(SWAMP_CARTOGRAPHER_ZOMBIE_VILLAGER, "Swamp Cartographer Zombie Villager Head");
        translationConsumer.accept(SWAMP_CLERIC_ZOMBIE_VILLAGER, "Swamp Cleric Zombie Villager Head");
        translationConsumer.accept(SWAMP_FARMER_ZOMBIE_VILLAGER, "Swamp Farmer Zombie Villager Head");
        translationConsumer.accept(SWAMP_FISHERMAN_ZOMBIE_VILLAGER, "Swamp Fisherman Zombie Villager Head");
        translationConsumer.accept(SWAMP_FLETCHER_ZOMBIE_VILLAGER, "Swamp Fletcher Zombie Villager Head");
        translationConsumer.accept(SWAMP_LEATHERWORKER_ZOMBIE_VILLAGER, "Swamp Leatherworker Zombie Villager Head");
        translationConsumer.accept(SWAMP_LIBRARIAN_ZOMBIE_VILLAGER, "Swamp Librarian Zombie Villager Head");
        translationConsumer.accept(SWAMP_MASON_ZOMBIE_VILLAGER, "Swamp Mason Zombie Villager Head");
        translationConsumer.accept(SWAMP_NITWIT_ZOMBIE_VILLAGER, "Swamp Nitwit Zombie Villager Head");
        translationConsumer.accept(SWAMP_SHEPHERD_ZOMBIE_VILLAGER, "Swamp Shepherd Zombie Villager Head");
        translationConsumer.accept(SWAMP_TOOLSMITH_ZOMBIE_VILLAGER, "Swamp Toolsmith Zombie Villager Head");
        translationConsumer.accept(SWAMP_WEAPONSMITH_ZOMBIE_VILLAGER, "Swamp Weaponsmith Zombie Villager Head");

        // Taiga Zombie Villagers
        translationConsumer.accept(TAIGA_ZOMBIE_VILLAGER, "Taiga Zombie Villager Head");
        translationConsumer.accept(TAIGA_ARMORER_ZOMBIE_VILLAGER, "Taiga Armorer Zombie Villager Head");
        translationConsumer.accept(TAIGA_BUTCHER_ZOMBIE_VILLAGER, "Taiga Butcher Zombie Villager Head");
        translationConsumer.accept(TAIGA_CARTOGRAPHER_ZOMBIE_VILLAGER, "Taiga Cartographer Zombie Villager Head");
        translationConsumer.accept(TAIGA_CLERIC_ZOMBIE_VILLAGER, "Taiga Cleric Zombie Villager Head");
        translationConsumer.accept(TAIGA_FARMER_ZOMBIE_VILLAGER, "Taiga Farmer Zombie Villager Head");
        translationConsumer.accept(TAIGA_FISHERMAN_ZOMBIE_VILLAGER, "Taiga Fisherman Zombie Villager Head");
        translationConsumer.accept(TAIGA_FLETCHER_ZOMBIE_VILLAGER, "Taiga Fletcher Zombie Villager Head");
        translationConsumer.accept(TAIGA_LEATHERWORKER_ZOMBIE_VILLAGER, "Taiga Leatherworker Zombie Villager Head");
        translationConsumer.accept(TAIGA_LIBRARIAN_ZOMBIE_VILLAGER, "Taiga Librarian Zombie Villager Head");
        translationConsumer.accept(TAIGA_MASON_ZOMBIE_VILLAGER, "Taiga Mason Zombie Villager Head");
        translationConsumer.accept(TAIGA_NITWIT_ZOMBIE_VILLAGER, "Taiga Nitwit Zombie Villager Head");
        translationConsumer.accept(TAIGA_SHEPHERD_ZOMBIE_VILLAGER, "Taiga Shepherd Zombie Villager Head");
        translationConsumer.accept(TAIGA_TOOLSMITH_ZOMBIE_VILLAGER, "Taiga Toolsmith Zombie Villager Head");
        translationConsumer.accept(TAIGA_WEAPONSMITH_ZOMBIE_VILLAGER, "Taiga Weaponsmith Zombie Villager Head");
    }
}
