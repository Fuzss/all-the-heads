package fuzs.alltheheads.data.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.init.*;
import fuzs.alltheheads.init.headtype.*;
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

        // Monsters
        translationBuilder.add(HeadType.customName(EntityType.ENDERMAN, MonsterHeadType.ENDERMAN), "Enderman Head");
        translationBuilder.add(HeadType.customName(EntityType.BLAZE, MonsterHeadType.BLAZE), "Blaze Head");
        translationBuilder.add(HeadType.customName(EntityType.WITCH, MonsterHeadType.WITCH), "Witch Head");
        translationBuilder.add(HeadType.customName(EntityType.RAVAGER, MonsterHeadType.RAVAGER), "Ravager Head");
        translationBuilder.add(HeadType.customName(EntityType.PHANTOM, MonsterHeadType.PHANTOM), "Phantom Head");
        translationBuilder.add(HeadType.customName(EntityType.CREAKING, MonsterHeadType.CREAKING), "Creaking Head");

        // Piglins
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIFIED_PIGLIN, MonsterHeadType.ZOMBIFIED_PIGLIN),
                "Zombified Piglin Head");
        translationBuilder.add(HeadType.customName(EntityType.PIGLIN_BRUTE, MonsterHeadType.PIGLIN_BRUTE),
                "Piglin Brute Head");

        // Ghasts
        translationBuilder.add(HeadType.customName(EntityType.GHAST, MonsterHeadType.GHAST), "Ghast Head");
        translationBuilder.add(HeadType.customName(EntityType.GHAST, MonsterHeadType.CHARGING_GHAST),
                "Charging Ghast Head");

        // Zombies
        translationBuilder.add(HeadType.customName(EntityType.HUSK, MonsterHeadType.HUSK), "Husk Head");
        translationBuilder.add(HeadType.customName(EntityType.DROWNED, MonsterHeadType.DROWNED), "Drowned Head");

        // Skeletons
        translationBuilder.add(HeadType.customName(EntityType.STRAY, MonsterHeadType.STRAY), "Stray Head");
        translationBuilder.add(HeadType.customName(EntityType.BOGGED, MonsterHeadType.BOGGED), "Bogged Head");

        // Slimes
        translationBuilder.add(HeadType.customName(EntityType.SLIME, MonsterHeadType.SLIME), "Slime Head");
        translationBuilder.add(HeadType.customName(EntityType.MAGMA_CUBE, MonsterHeadType.MAGMA_CUBE),
                "Magma Cube Head");

        // Animals
        translationBuilder.add(HeadType.customName(EntityType.DOLPHIN, AnimalHeadType.DOLPHIN), "Dolphin Head");
        translationBuilder.add(HeadType.customName(EntityType.GOAT, AnimalHeadType.GOAT), "Goat Head");
        translationBuilder.add(HeadType.customName(EntityType.TURTLE, AnimalHeadType.TURTLE), "Turtle Head");
        translationBuilder.add(HeadType.customName(EntityType.BAT, AnimalHeadType.BAT), "Bat Head");
        translationBuilder.add(HeadType.customName(EntityType.ALLAY, AnimalHeadType.ALLAY), "Allay Head");
        translationBuilder.add(HeadType.customName(EntityType.IRON_GOLEM, AnimalHeadType.IRON_GOLEM),
                "Iron Golem Head");
        translationBuilder.add(HeadType.customName(EntityType.CAMEL, AnimalHeadType.CAMEL), "Camel Head");
        translationBuilder.add(HeadType.customName(EntityType.HAPPY_GHAST, AnimalHeadType.HAPPY_GHAST),
                "Happy Ghast Head");
        translationBuilder.add(HeadType.customName(EntityType.SNOW_GOLEM, AnimalHeadType.SNOW_GOLEM),
                "Snow Golem Head");
        translationBuilder.add(HeadType.customName(EntityType.WANDERING_TRADER, AnimalHeadType.WANDERING_TRADER),
                "Wandering Trader Head");

        // Frogs
        translationBuilder.add(HeadType.customName(EntityType.FROG, AnimalHeadType.TEMPERATE_FROG),
                "Temperate Frog Head");
        translationBuilder.add(HeadType.customName(EntityType.FROG, AnimalHeadType.WARM_FROG), "Warm Frog Head");
        translationBuilder.add(HeadType.customName(EntityType.FROG, AnimalHeadType.COLD_FROG), "Cold Frog Head");

        // Horses
        translationBuilder.add(HeadType.customName(EntityType.SKELETON_HORSE, HorseHeadType.SKELETON_HORSE),
                "Skeleton Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_HORSE, HorseHeadType.ZOMBIE_HORSE),
                "Zombie Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.DONKEY, HorseHeadType.DONKEY), "Donkey Head");
        translationBuilder.add(HeadType.customName(EntityType.MULE, HorseHeadType.MULE), "Mule Head");

        // White Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_HORSE), "White Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_WHITE_HORSE),
                "White Blaze White Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_WHITE_HORSE),
                "White Field White Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_WHITE_HORSE),
                "White Spots White Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_WHITE_HORSE),
                "Black Dots White Horse Head");

        // Creamy Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.CREAMY_HORSE), "Creamy Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_CREAMY_HORSE),
                "White Blaze Creamy Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_CREAMY_HORSE),
                "White Field Creamy Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_CREAMY_HORSE),
                "White Spots Creamy Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_CREAMY_HORSE),
                "Black Dots Creamy Horse Head");

        // Chestnut Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.CHESTNUT_HORSE),
                "Chestnut Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_CHESTNUT_HORSE),
                "White Blaze Chestnut Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_CHESTNUT_HORSE),
                "White Field Chestnut Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_CHESTNUT_HORSE),
                "White Spots Chestnut Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_CHESTNUT_HORSE),
                "Black Dots Chestnut Horse Head");

        // Brown Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BROWN_HORSE), "Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_BROWN_HORSE),
                "White Blaze Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_BROWN_HORSE),
                "White Field Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_BROWN_HORSE),
                "White Spots Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_BROWN_HORSE),
                "Black Dots Brown Horse Head");

        // Black Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_HORSE), "Black Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_BLACK_HORSE),
                "White Blaze Black Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_BLACK_HORSE),
                "White Field Black Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_BLACK_HORSE),
                "White Spots Black Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_BLACK_HORSE),
                "Black Dots Black Horse Head");

        // Gray Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.GRAY_HORSE), "Gray Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_GRAY_HORSE),
                "White Blaze Gray Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_GRAY_HORSE),
                "White Field Gray Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_GRAY_HORSE),
                "White Spots Gray Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_GRAY_HORSE),
                "Black Dots Gray Horse Head");

        // Dark Brown Horses
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.DARK_BROWN_HORSE),
                "Dark Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DARK_BROWN_HORSE),
                "White Blaze Dark Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_FIELD_DARK_BROWN_HORSE),
                "White Field Dark Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.WHITE_DOTS_DARK_BROWN_HORSE),
                "White Spots Dark Brown Horse Head");
        translationBuilder.add(HeadType.customName(EntityType.HORSE, HorseHeadType.BLACK_DOTS_DARK_BROWN_HORSE),
                "Black Dots Dark Brown Horse Head");

        // Foxes
        translationBuilder.add(HeadType.customName(EntityType.FOX, AnimalHeadType.FOX), "Fox Head");
        translationBuilder.add(HeadType.customName(EntityType.FOX, AnimalHeadType.SNOW_FOX), "Snow Fox Head");

        // Bees
        translationBuilder.add(HeadType.customName(EntityType.BEE, AnimalHeadType.BEE), "Bee Head");
        translationBuilder.add(HeadType.customName(EntityType.BEE, AnimalHeadType.POLLINATED_BEE),
                "Pollinated Bee Head");
        translationBuilder.add(HeadType.customName(EntityType.BEE, AnimalHeadType.ANGRY_BEE), "Angry Bee Head");
        translationBuilder.add(HeadType.customName(EntityType.BEE, AnimalHeadType.POLLINATED_ANGRY_BEE),
                "Pollinated Angry Bee Head");

        // Llamas
        translationBuilder.add(HeadType.customName(EntityType.LLAMA, AnimalHeadType.CREAMY_LLAMA),
                "Creamy Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.LLAMA, AnimalHeadType.WHITE_LLAMA), "White Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.LLAMA, AnimalHeadType.BROWN_LLAMA), "Brown Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.LLAMA, AnimalHeadType.GRAY_LLAMA), "Gray Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.TRADER_LLAMA, AnimalHeadType.CREAMY_TRADER_LLAMA),
                "Creamy Trader Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.TRADER_LLAMA, AnimalHeadType.WHITE_TRADER_LLAMA),
                "White Trader Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.TRADER_LLAMA, AnimalHeadType.BROWN_TRADER_LLAMA),
                "Brown Trader Llama Head");
        translationBuilder.add(HeadType.customName(EntityType.TRADER_LLAMA, AnimalHeadType.GRAY_TRADER_LLAMA),
                "Gray Trader Llama Head");

        // Spiders
        translationBuilder.add(HeadType.customName(EntityType.SPIDER, MonsterHeadType.SPIDER), "Spider Head");
        translationBuilder.add(HeadType.customName(EntityType.CAVE_SPIDER, MonsterHeadType.CAVE_SPIDER),
                "Cave Spider Head");

        // Vexes
        translationBuilder.add(HeadType.customName(EntityType.VEX, MonsterHeadType.VEX), "Vex Head");
        translationBuilder.add(HeadType.customName(EntityType.VEX, MonsterHeadType.CHARGING_VEX), "Charging Vex Head");

        // Illagers
        translationBuilder.add(HeadType.customName(EntityType.VINDICATOR, MonsterHeadType.VINDICATOR),
                "Vindicator Head");
        translationBuilder.add(HeadType.customName(EntityType.EVOKER, MonsterHeadType.EVOKER), "Evoker Head");
        translationBuilder.add(HeadType.customName(EntityType.PILLAGER, MonsterHeadType.PILLAGER), "Pillager Head");
        translationBuilder.add(HeadType.customName(EntityType.ILLUSIONER, MonsterHeadType.ILLUSIONER),
                "Illusioner Head");

        // Squid
        translationBuilder.add(HeadType.customName(EntityType.SQUID, AnimalHeadType.SQUID), "Squid Head");
        translationBuilder.add(HeadType.customName(EntityType.GLOW_SQUID, AnimalHeadType.GLOW_SQUID),
                "Glow Squid Head");

        // Cows
        translationBuilder.add(HeadType.customName(EntityType.COW, AnimalHeadType.TEMPERATE_COW),
                "Temperate Cow Head");
        translationBuilder.add(HeadType.customName(EntityType.COW, AnimalHeadType.WARM_COW), "Warm Cow Head");
        translationBuilder.add(HeadType.customName(EntityType.COW, AnimalHeadType.COLD_COW), "Cold Cow Head");

        // Mooshrooms
        translationBuilder.add(HeadType.customName(EntityType.MOOSHROOM, AnimalHeadType.RED_MOOSHROOM),
                "Red Mooshroom Head");
        translationBuilder.add(HeadType.customName(EntityType.MOOSHROOM, AnimalHeadType.BROWN_MOOSHROOM),
                "Brown Mooshroom Head");

        // Chickens
        translationBuilder.add(HeadType.customName(EntityType.CHICKEN, AnimalHeadType.TEMPERATE_CHICKEN),
                "Temperate Chicken Head");
        translationBuilder.add(HeadType.customName(EntityType.CHICKEN, AnimalHeadType.WARM_CHICKEN),
                "Warm Chicken Head");
        translationBuilder.add(HeadType.customName(EntityType.CHICKEN, AnimalHeadType.COLD_CHICKEN),
                "Cold Chicken Head");

        // Pigs
        translationBuilder.add(HeadType.customName(EntityType.PIG, AnimalHeadType.TEMPERATE_PIG),
                "Temperate Pig Head");
        translationBuilder.add(HeadType.customName(EntityType.PIG, AnimalHeadType.WARM_PIG), "Warm Pig Head");
        translationBuilder.add(HeadType.customName(EntityType.PIG, AnimalHeadType.COLD_PIG), "Cold Pig Head");

        // Cats
        translationBuilder.add(HeadType.customName(EntityType.OCELOT, AnimalHeadType.OCELOT), "Ocelot Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.TABBY_CAT), "Tabby Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.BLACK_CAT), "Black Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.RED_CAT), "Red Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.SIAMESE_CAT), "Siamese Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.BRITISH_SHORTHAIR_CAT),
                "British Shorthair Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.CALICO_CAT), "Calico Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.PERSIAN_CAT), "Persian Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.RAGDOLL_CAT), "Ragdoll Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.WHITE_CAT), "White Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.JELLIE_CAT), "Jellie Cat Head");
        translationBuilder.add(HeadType.customName(EntityType.CAT, AnimalHeadType.ALL_BLACK_CAT),
                "All Black Cat Head");

        // Axolotls
        translationBuilder.add(HeadType.customName(EntityType.AXOLOTL, AnimalHeadType.LUCY_AXOLOTL),
                "Lucy Axolotl Head");
        translationBuilder.add(HeadType.customName(EntityType.AXOLOTL, AnimalHeadType.WILD_AXOLOTL),
                "Wild Axolotl Head");
        translationBuilder.add(HeadType.customName(EntityType.AXOLOTL, AnimalHeadType.GOLD_AXOLOTL),
                "Gold Axolotl Head");
        translationBuilder.add(HeadType.customName(EntityType.AXOLOTL, AnimalHeadType.CYAN_AXOLOTL),
                "Cyan Axolotl Head");
        translationBuilder.add(HeadType.customName(EntityType.AXOLOTL, AnimalHeadType.BLUE_AXOLOTL),
                "Blue Axolotl Head");

        // Sheep
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.WHITE_SHEEP), "White Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.WHITE_WOOLLY_SHEEP),
                "White Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.ORANGE_SHEEP), "Orange Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.ORANGE_WOOLLY_SHEEP),
                "Orange Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.MAGENTA_SHEEP),
                "Magenta Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.MAGENTA_WOOLLY_SHEEP),
                "Magenta Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.LIGHT_BLUE_SHEEP),
                "Light Blue Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.LIGHT_BLUE_WOOLLY_SHEEP),
                "Light Blue Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.YELLOW_SHEEP), "Yellow Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.YELLOW_WOOLLY_SHEEP),
                "Yellow Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.LIME_SHEEP), "Lime Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.LIME_WOOLLY_SHEEP),
                "Lime Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.PINK_SHEEP), "Pink Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.PINK_WOOLLY_SHEEP),
                "Pink Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.GRAY_SHEEP), "Gray Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.GRAY_WOOLLY_SHEEP),
                "Gray Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.LIGHT_GRAY_SHEEP),
                "Light Gray Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.LIGHT_GRAY_WOOLLY_SHEEP),
                "Light Gray Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.CYAN_SHEEP), "Cyan Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.CYAN_WOOLLY_SHEEP),
                "Cyan Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.PURPLE_SHEEP), "Purple Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.PURPLE_WOOLLY_SHEEP),
                "Purple Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.BLUE_SHEEP), "Blue Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.BLUE_WOOLLY_SHEEP),
                "Blue Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.BROWN_SHEEP), "Brown Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.BROWN_WOOLLY_SHEEP),
                "Brown Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.GREEN_SHEEP), "Green Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.GREEN_WOOLLY_SHEEP),
                "Green Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.RED_SHEEP), "Red Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.RED_WOOLLY_SHEEP),
                "Red Woolly Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.BLACK_SHEEP), "Black Sheep Head");
        translationBuilder.add(HeadType.customName(EntityType.SHEEP, SheepHeadType.BLACK_WOOLLY_SHEEP),
                "Black Woolly Sheep Head");

        // Wolves
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ASHEN_WOLF), "Ashen Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_ASHEN_WOLF),
                "Angry Ashen Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_ASHEN_WOLF),
                "Tame Ashen Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.BLACK_WOLF), "Black Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_BLACK_WOLF),
                "Angry Black Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_BLACK_WOLF),
                "Tame Black Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.CHESTNUT_WOLF), "Chestnut Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_CHESTNUT_WOLF),
                "Angry Chestnut Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_CHESTNUT_WOLF),
                "Tame Chestnut Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.PALE_WOLF), "Pale Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_PALE_WOLF),
                "Angry Pale Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_PALE_WOLF),
                "Tame Pale Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.RUSTY_WOLF), "Rusty Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_RUSTY_WOLF),
                "Angry Rusty Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_RUSTY_WOLF),
                "Tame Rusty Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.SPOTTED_WOLF), "Spotted Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_SPOTTED_WOLF),
                "Angry Spotted Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_SPOTTED_WOLF),
                "Tame Spotted Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.SNOWY_WOLF), "Snowy Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_SNOWY_WOLF),
                "Angry Snowy Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_SNOWY_WOLF),
                "Tame Snowy Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.STRIPED_WOLF), "Striped Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_STRIPED_WOLF),
                "Angry Striped Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_STRIPED_WOLF),
                "Tame Striped Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.WOODS_WOLF), "Woods Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.ANGRY_WOODS_WOLF),
                "Angry Woods Wolf Head");
        translationBuilder.add(HeadType.customName(EntityType.WOLF, WolfHeadType.TAME_WOODS_WOLF),
                "Tame Woods Wolf Head");

        // Desert Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_VILLAGER),
                "Desert Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_ARMORER_VILLAGER),
                "Desert Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_BUTCHER_VILLAGER),
                "Desert Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_CARTOGRAPHER_VILLAGER),
                "Desert Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_CLERIC_VILLAGER),
                "Desert Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_FARMER_VILLAGER),
                "Desert Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_FISHERMAN_VILLAGER),
                "Desert Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_FLETCHER_VILLAGER),
                "Desert Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER,
                VillagerHeadType.DESERT_LEATHERWORKER_VILLAGER), "Desert Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_LIBRARIAN_VILLAGER),
                "Desert Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_MASON_VILLAGER),
                "Desert Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_NITWIT_VILLAGER),
                "Desert Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_SHEPHERD_VILLAGER),
                "Desert Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_TOOLSMITH_VILLAGER),
                "Desert Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.DESERT_WEAPONSMITH_VILLAGER),
                "Desert Weaponsmith Villager Head");

        // Jungle Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_VILLAGER),
                "Jungle Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_ARMORER_VILLAGER),
                "Jungle Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_BUTCHER_VILLAGER),
                "Jungle Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_CARTOGRAPHER_VILLAGER),
                "Jungle Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_CLERIC_VILLAGER),
                "Jungle Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_FARMER_VILLAGER),
                "Jungle Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_FISHERMAN_VILLAGER),
                "Jungle Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_FLETCHER_VILLAGER),
                "Jungle Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER,
                VillagerHeadType.JUNGLE_LEATHERWORKER_VILLAGER), "Jungle Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_LIBRARIAN_VILLAGER),
                "Jungle Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_MASON_VILLAGER),
                "Jungle Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_NITWIT_VILLAGER),
                "Jungle Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_SHEPHERD_VILLAGER),
                "Jungle Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_TOOLSMITH_VILLAGER),
                "Jungle Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.JUNGLE_WEAPONSMITH_VILLAGER),
                "Jungle Weaponsmith Villager Head");

        // Plains Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_VILLAGER),
                "Plains Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_ARMORER_VILLAGER),
                "Plains Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_BUTCHER_VILLAGER),
                "Plains Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_CARTOGRAPHER_VILLAGER),
                "Plains Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_CLERIC_VILLAGER),
                "Plains Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_FARMER_VILLAGER),
                "Plains Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_FISHERMAN_VILLAGER),
                "Plains Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_FLETCHER_VILLAGER),
                "Plains Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER,
                VillagerHeadType.PLAINS_LEATHERWORKER_VILLAGER), "Plains Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_LIBRARIAN_VILLAGER),
                "Plains Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_MASON_VILLAGER),
                "Plains Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_NITWIT_VILLAGER),
                "Plains Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_SHEPHERD_VILLAGER),
                "Plains Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_TOOLSMITH_VILLAGER),
                "Plains Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.PLAINS_WEAPONSMITH_VILLAGER),
                "Plains Weaponsmith Villager Head");

        // Savanna Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_VILLAGER),
                "Savanna Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_ARMORER_VILLAGER),
                "Savanna Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_BUTCHER_VILLAGER),
                "Savanna Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER,
                VillagerHeadType.SAVANNA_CARTOGRAPHER_VILLAGER), "Savanna Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_CLERIC_VILLAGER),
                "Savanna Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_FARMER_VILLAGER),
                "Savanna Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_FISHERMAN_VILLAGER),
                "Savanna Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_FLETCHER_VILLAGER),
                "Savanna Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER,
                VillagerHeadType.SAVANNA_LEATHERWORKER_VILLAGER), "Savanna Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_LIBRARIAN_VILLAGER),
                "Savanna Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_MASON_VILLAGER),
                "Savanna Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_NITWIT_VILLAGER),
                "Savanna Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_SHEPHERD_VILLAGER),
                "Savanna Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_TOOLSMITH_VILLAGER),
                "Savanna Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SAVANNA_WEAPONSMITH_VILLAGER),
                "Savanna Weaponsmith Villager Head");

        // Snow Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_VILLAGER),
                "Snow Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_ARMORER_VILLAGER),
                "Snow Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_BUTCHER_VILLAGER),
                "Snow Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_CARTOGRAPHER_VILLAGER),
                "Snow Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_CLERIC_VILLAGER),
                "Snow Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_FARMER_VILLAGER),
                "Snow Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_FISHERMAN_VILLAGER),
                "Snow Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_FLETCHER_VILLAGER),
                "Snow Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_LEATHERWORKER_VILLAGER),
                "Snow Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_LIBRARIAN_VILLAGER),
                "Snow Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_MASON_VILLAGER),
                "Snow Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_NITWIT_VILLAGER),
                "Snow Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_SHEPHERD_VILLAGER),
                "Snow Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_TOOLSMITH_VILLAGER),
                "Snow Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SNOW_WEAPONSMITH_VILLAGER),
                "Snow Weaponsmith Villager Head");

        // Swamp Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_VILLAGER),
                "Swamp Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_ARMORER_VILLAGER),
                "Swamp Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_BUTCHER_VILLAGER),
                "Swamp Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_CARTOGRAPHER_VILLAGER),
                "Swamp Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_CLERIC_VILLAGER),
                "Swamp Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_FARMER_VILLAGER),
                "Swamp Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_FISHERMAN_VILLAGER),
                "Swamp Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_FLETCHER_VILLAGER),
                "Swamp Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_LEATHERWORKER_VILLAGER),
                "Swamp Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_LIBRARIAN_VILLAGER),
                "Swamp Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_MASON_VILLAGER),
                "Swamp Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_NITWIT_VILLAGER),
                "Swamp Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_SHEPHERD_VILLAGER),
                "Swamp Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_TOOLSMITH_VILLAGER),
                "Swamp Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.SWAMP_WEAPONSMITH_VILLAGER),
                "Swamp Weaponsmith Villager Head");

        // Taiga Villagers
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_VILLAGER),
                "Taiga Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_ARMORER_VILLAGER),
                "Taiga Armorer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_BUTCHER_VILLAGER),
                "Taiga Butcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_CARTOGRAPHER_VILLAGER),
                "Taiga Cartographer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_CLERIC_VILLAGER),
                "Taiga Cleric Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_FARMER_VILLAGER),
                "Taiga Farmer Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_FISHERMAN_VILLAGER),
                "Taiga Fisherman Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_FLETCHER_VILLAGER),
                "Taiga Fletcher Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_LEATHERWORKER_VILLAGER),
                "Taiga Leatherworker Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_LIBRARIAN_VILLAGER),
                "Taiga Librarian Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_MASON_VILLAGER),
                "Taiga Mason Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_NITWIT_VILLAGER),
                "Taiga Nitwit Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_SHEPHERD_VILLAGER),
                "Taiga Shepherd Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_TOOLSMITH_VILLAGER),
                "Taiga Toolsmith Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.VILLAGER, VillagerHeadType.TAIGA_WEAPONSMITH_VILLAGER),
                "Taiga Weaponsmith Villager Head");

        // Desert Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_ZOMBIE_VILLAGER), "Desert Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_ARMORER_ZOMBIE_VILLAGER), "Desert Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_BUTCHER_ZOMBIE_VILLAGER), "Desert Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.DESERT_CARTOGRAPHER_ZOMBIE_VILLAGER),
                "Desert Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_CLERIC_ZOMBIE_VILLAGER), "Desert Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_FARMER_ZOMBIE_VILLAGER), "Desert Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_FISHERMAN_ZOMBIE_VILLAGER), "Desert Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_FLETCHER_ZOMBIE_VILLAGER), "Desert Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.DESERT_LEATHERWORKER_ZOMBIE_VILLAGER),
                "Desert Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_LIBRARIAN_ZOMBIE_VILLAGER), "Desert Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_MASON_ZOMBIE_VILLAGER), "Desert Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_NITWIT_ZOMBIE_VILLAGER), "Desert Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_SHEPHERD_ZOMBIE_VILLAGER), "Desert Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_TOOLSMITH_ZOMBIE_VILLAGER), "Desert Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.DESERT_WEAPONSMITH_ZOMBIE_VILLAGER), "Desert Weaponsmith Zombie Villager Head");

        // Jungle Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_ZOMBIE_VILLAGER), "Jungle Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_ARMORER_ZOMBIE_VILLAGER), "Jungle Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_BUTCHER_ZOMBIE_VILLAGER), "Jungle Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.JUNGLE_CARTOGRAPHER_ZOMBIE_VILLAGER),
                "Jungle Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_CLERIC_ZOMBIE_VILLAGER), "Jungle Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_FARMER_ZOMBIE_VILLAGER), "Jungle Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_FISHERMAN_ZOMBIE_VILLAGER), "Jungle Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_FLETCHER_ZOMBIE_VILLAGER), "Jungle Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.JUNGLE_LEATHERWORKER_ZOMBIE_VILLAGER),
                "Jungle Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_LIBRARIAN_ZOMBIE_VILLAGER), "Jungle Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_MASON_ZOMBIE_VILLAGER), "Jungle Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_NITWIT_ZOMBIE_VILLAGER), "Jungle Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_SHEPHERD_ZOMBIE_VILLAGER), "Jungle Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_TOOLSMITH_ZOMBIE_VILLAGER), "Jungle Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.JUNGLE_WEAPONSMITH_ZOMBIE_VILLAGER), "Jungle Weaponsmith Zombie Villager Head");

        // Plains Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_ZOMBIE_VILLAGER), "Plains Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_ARMORER_ZOMBIE_VILLAGER), "Plains Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_BUTCHER_ZOMBIE_VILLAGER), "Plains Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.PLAINS_CARTOGRAPHER_ZOMBIE_VILLAGER),
                "Plains Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_CLERIC_ZOMBIE_VILLAGER), "Plains Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_FARMER_ZOMBIE_VILLAGER), "Plains Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_FISHERMAN_ZOMBIE_VILLAGER), "Plains Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_FLETCHER_ZOMBIE_VILLAGER), "Plains Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.PLAINS_LEATHERWORKER_ZOMBIE_VILLAGER),
                "Plains Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_LIBRARIAN_ZOMBIE_VILLAGER), "Plains Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_MASON_ZOMBIE_VILLAGER), "Plains Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_NITWIT_ZOMBIE_VILLAGER), "Plains Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_SHEPHERD_ZOMBIE_VILLAGER), "Plains Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_TOOLSMITH_ZOMBIE_VILLAGER), "Plains Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.PLAINS_WEAPONSMITH_ZOMBIE_VILLAGER), "Plains Weaponsmith Zombie Villager Head");

        // Savanna Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_ZOMBIE_VILLAGER), "Savanna Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_ARMORER_ZOMBIE_VILLAGER), "Savanna Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_BUTCHER_ZOMBIE_VILLAGER), "Savanna Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.SAVANNA_CARTOGRAPHER_ZOMBIE_VILLAGER),
                "Savanna Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_CLERIC_ZOMBIE_VILLAGER), "Savanna Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_FARMER_ZOMBIE_VILLAGER), "Savanna Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_FISHERMAN_ZOMBIE_VILLAGER), "Savanna Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_FLETCHER_ZOMBIE_VILLAGER), "Savanna Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.SAVANNA_LEATHERWORKER_ZOMBIE_VILLAGER),
                "Savanna Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_LIBRARIAN_ZOMBIE_VILLAGER), "Savanna Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_MASON_ZOMBIE_VILLAGER), "Savanna Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_NITWIT_ZOMBIE_VILLAGER), "Savanna Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_SHEPHERD_ZOMBIE_VILLAGER), "Savanna Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SAVANNA_TOOLSMITH_ZOMBIE_VILLAGER), "Savanna Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.SAVANNA_WEAPONSMITH_ZOMBIE_VILLAGER),
                "Savanna Weaponsmith Zombie Villager Head");

        // Snow Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_ZOMBIE_VILLAGER), "Snow Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_ARMORER_ZOMBIE_VILLAGER), "Snow Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_BUTCHER_ZOMBIE_VILLAGER), "Snow Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_CARTOGRAPHER_ZOMBIE_VILLAGER), "Snow Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_CLERIC_ZOMBIE_VILLAGER), "Snow Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_FARMER_ZOMBIE_VILLAGER), "Snow Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_FISHERMAN_ZOMBIE_VILLAGER), "Snow Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_FLETCHER_ZOMBIE_VILLAGER), "Snow Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_LEATHERWORKER_ZOMBIE_VILLAGER), "Snow Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_LIBRARIAN_ZOMBIE_VILLAGER), "Snow Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_MASON_ZOMBIE_VILLAGER), "Snow Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_NITWIT_ZOMBIE_VILLAGER), "Snow Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_SHEPHERD_ZOMBIE_VILLAGER), "Snow Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_TOOLSMITH_ZOMBIE_VILLAGER), "Snow Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SNOW_WEAPONSMITH_ZOMBIE_VILLAGER), "Snow Weaponsmith Zombie Villager Head");

        // Swamp Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_ZOMBIE_VILLAGER), "Swamp Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_ARMORER_ZOMBIE_VILLAGER), "Swamp Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_BUTCHER_ZOMBIE_VILLAGER), "Swamp Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_CARTOGRAPHER_ZOMBIE_VILLAGER), "Swamp Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_CLERIC_ZOMBIE_VILLAGER), "Swamp Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_FARMER_ZOMBIE_VILLAGER), "Swamp Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_FISHERMAN_ZOMBIE_VILLAGER), "Swamp Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_FLETCHER_ZOMBIE_VILLAGER), "Swamp Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.SWAMP_LEATHERWORKER_ZOMBIE_VILLAGER),
                "Swamp Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_LIBRARIAN_ZOMBIE_VILLAGER), "Swamp Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_MASON_ZOMBIE_VILLAGER), "Swamp Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_NITWIT_ZOMBIE_VILLAGER), "Swamp Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_SHEPHERD_ZOMBIE_VILLAGER), "Swamp Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_TOOLSMITH_ZOMBIE_VILLAGER), "Swamp Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.SWAMP_WEAPONSMITH_ZOMBIE_VILLAGER), "Swamp Weaponsmith Zombie Villager Head");

        // Taiga Zombie Villagers
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_ZOMBIE_VILLAGER), "Taiga Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_ARMORER_ZOMBIE_VILLAGER), "Taiga Armorer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_BUTCHER_ZOMBIE_VILLAGER), "Taiga Butcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_CARTOGRAPHER_ZOMBIE_VILLAGER), "Taiga Cartographer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_CLERIC_ZOMBIE_VILLAGER), "Taiga Cleric Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_FARMER_ZOMBIE_VILLAGER), "Taiga Farmer Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_FISHERMAN_ZOMBIE_VILLAGER), "Taiga Fisherman Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_FLETCHER_ZOMBIE_VILLAGER), "Taiga Fletcher Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                        ZombieVillagerHeadType.TAIGA_LEATHERWORKER_ZOMBIE_VILLAGER),
                "Taiga Leatherworker Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_LIBRARIAN_ZOMBIE_VILLAGER), "Taiga Librarian Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_MASON_ZOMBIE_VILLAGER), "Taiga Mason Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_NITWIT_ZOMBIE_VILLAGER), "Taiga Nitwit Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_SHEPHERD_ZOMBIE_VILLAGER), "Taiga Shepherd Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_TOOLSMITH_ZOMBIE_VILLAGER), "Taiga Toolsmith Zombie Villager Head");
        translationBuilder.add(HeadType.customName(EntityType.ZOMBIE_VILLAGER,
                ZombieVillagerHeadType.TAIGA_WEAPONSMITH_ZOMBIE_VILLAGER), "Taiga Weaponsmith Zombie Villager Head");
    }
}
