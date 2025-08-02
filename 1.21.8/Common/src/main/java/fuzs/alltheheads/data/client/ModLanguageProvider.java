package fuzs.alltheheads.data.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.init.HeadTypes;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.entity.EntityType;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModRegistry.CREATIVE_MODE_TAB.value(), AllTheHeads.MOD_NAME);
        translationBuilder.add(ModRegistry.VILLAGER_CREATIVE_MODE_TAB.value(), "All The Villagers");
        translationBuilder.add(ModRegistry.MOB_HEAD_BLOCK.value(), "Mob Head");

        // Mobs
        translationBuilder.add(HeadType.customName(EntityType.ENDERMAN, HeadTypes.ENDERMAN), "Enderman Head");
        translationBuilder.add(HeadType.customName(EntityType.BLAZE, HeadTypes.BLAZE), "Blaze Head");

        // Spiders
        translationBuilder.add(HeadType.customName(EntityType.SPIDER, HeadTypes.SPIDER), "Spider Head");
        translationBuilder.add(HeadType.customName(EntityType.CAVE_SPIDER, HeadTypes.CAVE_SPIDER), "Cave Spider Head");

        // Squid
        translationBuilder.add(HeadType.customName(EntityType.SQUID, HeadTypes.SQUID), "Squid Head");
        translationBuilder.add(HeadType.customName(EntityType.GLOW_SQUID, HeadTypes.GLOW_SQUID), "Glow Squid Head");

        // Cows
        translationBuilder.add(HeadType.customName(EntityType.COW, HeadTypes.TEMPERATE_COW), "Temperate Cow Head");
        translationBuilder.add(HeadType.customName(EntityType.COW, HeadTypes.WARM_COW), "Warm Cow Head");
        translationBuilder.add(HeadType.customName(EntityType.COW, HeadTypes.COLD_COW), "Cold Cow Head");

        // Cats
        translationBuilder.add(HeadType.customName(EntityType.OCELOT, HeadTypes.OCELOT), "Ocelot Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.TABBY_CAT), "Tabby Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.BLACK_CAT), "Black Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.RED_CAT), "Red Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.SIAMESE_CAT), "Siamese Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.BRITISH_SHORTHAIR_CAT),
                "British Shorthair Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.CALICO_CAT), "Calico Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.PERSIAN_CAT), "Persian Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.RAGDOLL_CAT), "Ragdoll Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.WHITE_CAT), "White Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.JELLIE_CAT), "Jellie Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, HeadTypes.ALL_BLACK_CAT), "All Black Cat Head");

        // Sheep
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.WHITE_SHEEP), "White Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.WHITE_WOOLLY_SHEEP),
                "White Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.ORANGE_SHEEP), "Orange Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.ORANGE_WOOLLY_SHEEP),
                "Orange Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.MAGENTA_SHEEP), "Magenta Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.MAGENTA_WOOLLY_SHEEP),
                "Magenta Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.LIGHT_BLUE_SHEEP),
                "Light Blue Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.LIGHT_BLUE_WOOLLY_SHEEP),
                "Light Blue Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.YELLOW_SHEEP), "Yellow Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.YELLOW_WOOLLY_SHEEP),
                "Yellow Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.LIME_SHEEP), "Lime Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.LIME_WOOLLY_SHEEP),
                "Lime Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.PINK_SHEEP), "Pink Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.PINK_WOOLLY_SHEEP),
                "Pink Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.GRAY_SHEEP), "Gray Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.GRAY_WOOLLY_SHEEP),
                "Gray Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.LIGHT_GRAY_SHEEP),
                "Light Gray Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.LIGHT_GRAY_WOOLLY_SHEEP),
                "Light Gray Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.CYAN_SHEEP), "Cyan Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.CYAN_WOOLLY_SHEEP),
                "Cyan Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.PURPLE_SHEEP), "Purple Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.PURPLE_WOOLLY_SHEEP),
                "Purple Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.BLUE_SHEEP), "Blue Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.BLUE_WOOLLY_SHEEP),
                "Blue Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.BROWN_SHEEP), "Brown Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.BROWN_WOOLLY_SHEEP),
                "Brown Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.GREEN_SHEEP), "Green Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.GREEN_WOOLLY_SHEEP),
                "Green Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.RED_SHEEP), "Red Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.RED_WOOLLY_SHEEP),
                "Red Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.BLACK_SHEEP), "Black Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, HeadTypes.BLACK_WOOLLY_SHEEP),
                "Black Woolly Sheep Head");

        // Desert Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_VILLAGER),
                "Desert Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_ARMORER_VILLAGER),
                "Desert Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_BUTCHER_VILLAGER),
                "Desert Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_CARTOGRAPHER_VILLAGER),
                "Desert Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_CLERIC_VILLAGER),
                "Desert Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_FARMER_VILLAGER),
                "Desert Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_FISHERMAN_VILLAGER),
                "Desert Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_FLETCHER_VILLAGER),
                "Desert Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_LEATHERWORKER_VILLAGER),
                "Desert Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_LIBRARIAN_VILLAGER),
                "Desert Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_MASON_VILLAGER),
                "Desert Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_NITWIT_VILLAGER),
                "Desert Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_SHEPHERD_VILLAGER),
                "Desert Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_TOOLSMITH_VILLAGER),
                "Desert Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.DESERT_WEAPONSMITH_VILLAGER),
                "Desert Weaponsmith Villager Head");

        // Jungle Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_VILLAGER),
                "Jungle Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_ARMORER_VILLAGER),
                "Jungle Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_BUTCHER_VILLAGER),
                "Jungle Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_CARTOGRAPHER_VILLAGER),
                "Jungle Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_CLERIC_VILLAGER),
                "Jungle Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_FARMER_VILLAGER),
                "Jungle Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_FISHERMAN_VILLAGER),
                "Jungle Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_FLETCHER_VILLAGER),
                "Jungle Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_LEATHERWORKER_VILLAGER),
                "Jungle Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_LIBRARIAN_VILLAGER),
                "Jungle Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_MASON_VILLAGER),
                "Jungle Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_NITWIT_VILLAGER),
                "Jungle Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_SHEPHERD_VILLAGER),
                "Jungle Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_TOOLSMITH_VILLAGER),
                "Jungle Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.JUNGLE_WEAPONSMITH_VILLAGER),
                "Jungle Weaponsmith Villager Head");

        // Plains Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_VILLAGER),
                "Plains Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_ARMORER_VILLAGER),
                "Plains Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_BUTCHER_VILLAGER),
                "Plains Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_CARTOGRAPHER_VILLAGER),
                "Plains Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_CLERIC_VILLAGER),
                "Plains Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_FARMER_VILLAGER),
                "Plains Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_FISHERMAN_VILLAGER),
                "Plains Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_FLETCHER_VILLAGER),
                "Plains Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_LEATHERWORKER_VILLAGER),
                "Plains Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_LIBRARIAN_VILLAGER),
                "Plains Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_MASON_VILLAGER),
                "Plains Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_NITWIT_VILLAGER),
                "Plains Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_SHEPHERD_VILLAGER),
                "Plains Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_TOOLSMITH_VILLAGER),
                "Plains Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.PLAINS_WEAPONSMITH_VILLAGER),
                "Plains Weaponsmith Villager Head");

        // Savanna Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_VILLAGER),
                "Savanna Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_ARMORER_VILLAGER),
                "Savanna Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_BUTCHER_VILLAGER),
                "Savanna Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_CARTOGRAPHER_VILLAGER),
                "Savanna Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_CLERIC_VILLAGER),
                "Savanna Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_FARMER_VILLAGER),
                "Savanna Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_FISHERMAN_VILLAGER),
                "Savanna Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_FLETCHER_VILLAGER),
                "Savanna Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_LEATHERWORKER_VILLAGER),
                "Savanna Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_LIBRARIAN_VILLAGER),
                "Savanna Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_MASON_VILLAGER),
                "Savanna Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_NITWIT_VILLAGER),
                "Savanna Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_SHEPHERD_VILLAGER),
                "Savanna Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_TOOLSMITH_VILLAGER),
                "Savanna Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SAVANNA_WEAPONSMITH_VILLAGER),
                "Savanna Weaponsmith Villager Head");

        // Snow Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_VILLAGER), "Snow Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_ARMORER_VILLAGER),
                "Snow Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_BUTCHER_VILLAGER),
                "Snow Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_CARTOGRAPHER_VILLAGER),
                "Snow Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_CLERIC_VILLAGER),
                "Snow Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_FARMER_VILLAGER),
                "Snow Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_FISHERMAN_VILLAGER),
                "Snow Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_FLETCHER_VILLAGER),
                "Snow Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_LEATHERWORKER_VILLAGER),
                "Snow Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_LIBRARIAN_VILLAGER),
                "Snow Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_MASON_VILLAGER),
                "Snow Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_NITWIT_VILLAGER),
                "Snow Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_SHEPHERD_VILLAGER),
                "Snow Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_TOOLSMITH_VILLAGER),
                "Snow Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SNOW_WEAPONSMITH_VILLAGER),
                "Snow Weaponsmith Villager Head");

        // Swamp Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_VILLAGER),
                "Swamp Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_ARMORER_VILLAGER),
                "Swamp Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_BUTCHER_VILLAGER),
                "Swamp Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_CARTOGRAPHER_VILLAGER),
                "Swamp Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_CLERIC_VILLAGER),
                "Swamp Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_FARMER_VILLAGER),
                "Swamp Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_FISHERMAN_VILLAGER),
                "Swamp Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_FLETCHER_VILLAGER),
                "Swamp Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_LEATHERWORKER_VILLAGER),
                "Swamp Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_LIBRARIAN_VILLAGER),
                "Swamp Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_MASON_VILLAGER),
                "Swamp Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_NITWIT_VILLAGER),
                "Swamp Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_SHEPHERD_VILLAGER),
                "Swamp Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_TOOLSMITH_VILLAGER),
                "Swamp Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.SWAMP_WEAPONSMITH_VILLAGER),
                "Swamp Weaponsmith Villager Head");

        // Taiga Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_VILLAGER),
                "Taiga Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_ARMORER_VILLAGER),
                "Taiga Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_BUTCHER_VILLAGER),
                "Taiga Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_CARTOGRAPHER_VILLAGER),
                "Taiga Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_CLERIC_VILLAGER),
                "Taiga Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_FARMER_VILLAGER),
                "Taiga Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_FISHERMAN_VILLAGER),
                "Taiga Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_FLETCHER_VILLAGER),
                "Taiga Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_LEATHERWORKER_VILLAGER),
                "Taiga Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_LIBRARIAN_VILLAGER),
                "Taiga Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_MASON_VILLAGER),
                "Taiga Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_NITWIT_VILLAGER),
                "Taiga Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_SHEPHERD_VILLAGER),
                "Taiga Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_TOOLSMITH_VILLAGER),
                "Taiga Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, HeadTypes.TAIGA_WEAPONSMITH_VILLAGER),
                "Taiga Weaponsmith Villager Head");

    }
}
