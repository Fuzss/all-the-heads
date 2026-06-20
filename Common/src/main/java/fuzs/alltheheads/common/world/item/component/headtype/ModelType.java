package fuzs.alltheheads.common.world.item.component.headtype;

import com.mojang.serialization.Codec;
import fuzs.alltheheads.common.AllTheHeads;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

import java.util.Objects;

/**
 * Basically a server-side implementation of {@code ModelLayerLocation}.
 */
public record ModelType(Identifier model, String layer) {
    static final ExtraCodecs.LateBoundIdMapper<String, ModelType> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<ModelType> CODEC = ID_MAPPER.codec(Codec.STRING);
    public static final ModelType ALLAY = register("allay_head");
    public static final ModelType ARMADILLO = register("armadillo_head");
    public static final ModelType AXOLOTL = register("axolotl_head");
    public static final ModelType BAT = register("bat_head");
    public static final ModelType BEE = register("bee_head");
    public static final ModelType BOGGED = register("bogged_head");
    public static final ModelType BREEZE = register("breeze_head");
    public static final ModelType BREEZE_EYES = register("breeze_head", "eyes");
    public static final ModelType CAMEL = register("camel_head");
    public static final ModelType CHICKEN = register("chicken_head");
    public static final ModelType COLD_CHICKEN = register("cold_chicken_head");
    public static final ModelType COLD_COW = register("cold_cow_head");
    public static final ModelType COD = register("cod_head");
    public static final ModelType COPPER_GOLEM = register("copper_golem_head");
    public static final ModelType COPPER_GOLEM_EYES = register("copper_golem_head", "eyes");
    public static final ModelType CREAKING = register("creaking_head");
    public static final ModelType CREAKING_EYES = register("creaking_head", "eyes");
    public static final ModelType CREEPER_CHARGE = register("creeper_head", "charge");
    public static final ModelType DOLPHIN = register("dolphin_head");
    public static final ModelType ENDERMAN = register("enderman_head");
    public static final ModelType ENDERMAN_EYES = register("enderman_head", "eyes");
    public static final ModelType ENDERMITE = register("endermite_head");
    public static final ModelType FELINE = register("feline_head");
    public static final ModelType FOX = register("fox_head");
    public static final ModelType FROG = register("frog_head");
    public static final ModelType GHAST = register("ghast_head");
    public static final ModelType GOAT = register("goat_head");
    public static final ModelType GUARDIAN = register("guardian_head");
    public static final ModelType HAPPY_GHAST = register("happy_ghast_head");
    public static final ModelType HOGLIN = register("hoglin_head");
    public static final ModelType HORSE = register("horse_head");
    public static final ModelType HORSE_MARKINGS = register("horse_head", "markings");
    public static final ModelType HUMANOID = register("humanoid_head");
    public static final ModelType HUMANOID_OVERLAY = register("humanoid_head", "overlay");
    public static final ModelType ILLAGER = register("illager_head");
    public static final ModelType IRON_GOLEM = register("iron_golem_head");
    public static final ModelType LLAMA = register("llama_head");
    public static final ModelType LLAMA_DECOR = register("llama_head", "decor");
    public static final ModelType MAGMA_CUBE = register("magma_cube_head");
    public static final ModelType MOB = register("mob_head");
    public static final ModelType MOB_OVERLAY = register("mob_head", "overlay");
    public static final ModelType NAUTILUS = register("nautilus_head");
    public static final ModelType PANDA = register("panda_head");
    public static final ModelType PARCHED = register("parched_head");
    public static final ModelType PARROT = register("parrot_head");
    public static final ModelType PHANTOM = register("phantom_head");
    public static final ModelType PHANTOM_EYES = register("phantom_head", "eyes");
    public static final ModelType PIG = register("pig_head");
    public static final ModelType PIGLIN = register("piglin_head");
    public static final ModelType POLAR_BEAR = register("polar_bear_head");
    public static final ModelType PUFFERFISH = register("pufferfish_head");
    public static final ModelType RABBIT = register("rabbit_head");
    public static final ModelType RAVAGER = register("ravager_head");
    public static final ModelType SALMON = register("salmon_head");
    public static final ModelType SHEEP = register("sheep_head");
    public static final ModelType SHEEP_WOOL = register("sheep_head", "wool");
    public static final ModelType SHULKER = register("shulker_head");
    public static final ModelType SILVERFISH = register("silverfish_head");
    public static final ModelType SLIME = register("slime_head");
    public static final ModelType SLIME_GEL = register("slime_head", "gel");
    public static final ModelType SNIFFER = register("sniffer_head");
    public static final ModelType SPIDER = register("spider_head");
    public static final ModelType SPIDER_EYES = register("spider_head", "eyes");
    public static final ModelType SQUID = register("squid_head");
    public static final ModelType STRIDER = register("strider_head");
    public static final ModelType SULFUR_CUBE = register("sulfur_cube_head");
    public static final ModelType SULFUR_CUBE_GEL = register("sulfur_cube_head", "gel");
    public static final ModelType SULFUR_CUBE_BOUNCY = register("sulfur_cube_head", "bouncy");
    public static final ModelType SULFUR_CUBE_EXPLOSIVE = register("sulfur_cube_head", "explosive");
    public static final ModelType SULFUR_CUBE_FAST_FLAT = register("sulfur_cube_head", "fast_flat");
    public static final ModelType SULFUR_CUBE_FAST_SLIDING = register("sulfur_cube_head", "fast_sliding");
    public static final ModelType SULFUR_CUBE_HIGH_RESISTANCE = register("sulfur_cube_head", "high_resistance");
    public static final ModelType SULFUR_CUBE_HOT = register("sulfur_cube_head", "hot");
    public static final ModelType SULFUR_CUBE_LIGHT = register("sulfur_cube_head", "light");
    public static final ModelType SULFUR_CUBE_REGULAR = register("sulfur_cube_head", "regular");
    public static final ModelType SULFUR_CUBE_SLOW_BOUNCY = register("sulfur_cube_head", "slow_bouncy");
    public static final ModelType SULFUR_CUBE_SLOW_FLAT = register("sulfur_cube_head", "slow_flat");
    public static final ModelType SULFUR_CUBE_SLOW_SLIDING = register("sulfur_cube_head", "slow_sliding");
    public static final ModelType SULFUR_CUBE_STICKY = register("sulfur_cube_head", "sticky");
    public static final ModelType TADPOLE = register("tadpole_head");
    public static final ModelType TEMPERATE_COW = register("temperate_cow_head");
    public static final ModelType TROPICAL_FISH_LARGE = register("tropical_fish_head", "large");
    public static final ModelType TROPICAL_FISH_SMALL = register("tropical_fish_head", "small");
    public static final ModelType TURTLE = register("turtle_head");
    public static final ModelType VEX = register("vex_head");
    public static final ModelType VILLAGER = register("villager_head");
    public static final ModelType WARDEN = register("warden_head");
    public static final ModelType WARM_COW = register("warm_cow_head");
    public static final ModelType WITCH = register("witch_head");
    public static final ModelType WITHER_SHIELD = register("wither_head", "shield");
    public static final ModelType WOLF = register("wolf_head");
    public static final ModelType DEFAULT = HUMANOID;

    private static ModelType register(String model) {
        return register(model, "main");
    }

    private static ModelType register(String model, String layer) {
        ModelType modelType = new ModelType(AllTheHeads.id(model), layer);
        ID_MAPPER.put(modelType.toString(), modelType);
        return modelType;
    }

    @Override
    public String toString() {
        return this.model + (Objects.equals(this.layer, "main") ? "" : "/" + this.layer);
    }
}
