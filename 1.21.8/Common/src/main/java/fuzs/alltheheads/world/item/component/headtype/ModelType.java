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
    public static final ExtraCodecs.LateBoundIdMapper<String, ModelType> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<ModelType> CODEC = ID_MAPPER.codec(Codec.STRING);
    public static final ModelType MOB = register("mob_head");
    public static final ModelType HUMANOID = register("humanoid_head");
    public static final ModelType ENDERMAN = register("enderman_head");
    public static final ModelType ENDERMAN_EYES = register("enderman_head", "eyes");
    public static final ModelType SPIDER = register("spider_head");
    public static final ModelType SPIDER_EYES = register("spider_head", "eyes");
    public static final ModelType TEMPERATE_COW = register("temperate_cow_head");
    public static final ModelType WARM_COW = register("warm_cow_head");
    public static final ModelType COLD_COW = register("cold_cow_head");
    public static final ModelType FELINE = register("feline_head");
    public static final ModelType SHEEP = register("sheep_head");
    public static final ModelType SHEEP_WOOL = register("sheep_head", "wool");
    public static final ModelType VILLAGER = register("villager_head");
    public static final ModelType WITCH = register("witch_head");
    public static final ModelType SQUID = register("squid_head");
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
