package fuzs.alltheheads.world.item.component.headtype;

import com.mojang.serialization.Codec;
import fuzs.alltheheads.AllTheHeads;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

import java.util.Objects;

/**
 * Basically a server-side implementation of {@code ModelLayerLocation}.
 */
public record ModelType(ResourceLocation model, String layer) {
    static final ExtraCodecs.LateBoundIdMapper<String, ModelType> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<ModelType> CODEC = ID_MAPPER.codec(Codec.STRING);
    public static final ModelType MOB = register("mob_head");
    public static final ModelType MOB_OVERLAY = register("mob_head", "overlay");
    public static final ModelType HUMANOID = register("humanoid_head");
    public static final ModelType HUMANOID_OVERLAY = register("humanoid_head", "overlay");
    public static final ModelType ENDERMAN = register("enderman_head");
    public static final ModelType ENDERMAN_EYES = register("enderman_head", "eyes");
    public static final ModelType BOGGED = register("bogged_head");
    public static final ModelType SPIDER = register("spider_head");
    public static final ModelType SPIDER_EYES = register("spider_head", "eyes");
    public static final ModelType TEMPERATE_COW = register("temperate_cow_head");
    public static final ModelType WARM_COW = register("warm_cow_head");
    public static final ModelType COLD_COW = register("cold_cow_head");
    public static final ModelType FELINE = register("feline_head");
    public static final ModelType SHEEP = register("sheep_head");
    public static final ModelType SHEEP_WOOL = register("sheep_head", "wool");
    public static final ModelType VILLAGER = register("villager_head");
    public static final ModelType ILLAGER = register("illager_head");
    public static final ModelType WITCH = register("witch_head");
    public static final ModelType VEX = register("vex_head");
    public static final ModelType RAVAGER = register("ravager_head");
    public static final ModelType SQUID = register("squid_head");
    public static final ModelType CHICKEN = register("chicken_head");
    public static final ModelType COLD_CHICKEN = register("cold_chicken_head");
    public static final ModelType PIG = register("pig_head");
    public static final ModelType AXOLOTL = register("axolotl_head");
    public static final ModelType DOLPHIN = register("dolphin_head");
    public static final ModelType SLIME = register("slime_head");
    public static final ModelType SLIME_GEL = register("slime_head", "gel");
    public static final ModelType MAGMA_CUBE = register("magma_cube_head");
    public static final ModelType GOAT = register("goat_head");
    public static final ModelType LLAMA = register("llama_head");
    public static final ModelType LLAMA_DECOR = register("llama_head", "decor");
    public static final ModelType TURTLE = register("turtle_head");
    public static final ModelType BAT = register("bat_head");
    public static final ModelType ALLAY = register("allay_head");
    public static final ModelType WOLF = register("wolf_head");
    public static final ModelType PHANTOM = register("phantom_head");
    public static final ModelType PHANTOM_EYES = register("phantom_head", "eyes");
    public static final ModelType IRON_GOLEM = register("iron_golem_head");
    public static final ModelType FOX = register("fox_head");
    public static final ModelType BEE = register("bee_head");
    public static final ModelType HORSE = register("horse_head");
    public static final ModelType HORSE_MARKINGS = register("horse_head", "markings");
    public static final ModelType CAMEL = register("camel_head");
    public static final ModelType GHAST = register("ghast_head");
    public static final ModelType HAPPY_GHAST = register("happy_ghast_head");
    public static final ModelType CREAKING = register("creaking_head");
    public static final ModelType CREAKING_EYES = register("creaking_head", "eyes");
    public static final ModelType FROG = register("frog_head");
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
