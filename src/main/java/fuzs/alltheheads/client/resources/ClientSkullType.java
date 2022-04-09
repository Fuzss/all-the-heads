package fuzs.alltheheads.client.resources;

import com.mojang.math.Vector3f;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.BuiltInSkullJsonData;
import fuzs.alltheheads.resources.SkullType;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ClientSkullType {
    private final SkullType baseSkullType;
    private final ResourceLocation textureLocation;
    private final ResourceLocation modelLocation;
    private final String layerLocation;
    private final String[] headKey;
    private final Vector3f modelOffsets;

    private ClientSkullType(SkullType baseSkullType, ResourceLocation textureLocation, ResourceLocation modelLocation, String layerLocation, String[] headKey, Vector3f modelOffsets) {
        this.baseSkullType = baseSkullType;
        this.textureLocation = textureLocation;
        this.modelLocation = modelLocation;
        this.layerLocation = layerLocation;
        this.headKey = headKey;
        this.modelOffsets = modelOffsets;
    }

    public SkullType getBaseSkullType() {
        return this.baseSkullType;
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public ModelLayerLocation getModelLayerLocation() {
        return new ModelLayerLocation(this.modelLocation, this.layerLocation);
    }

    public String[] getModelPartHeadKey() {
        return this.headKey;
    }

    public Vector3f getModelOffsets() {
        return this.modelOffsets;
    }

    public void buildResourceMap(BiConsumer<ResourceLocation, byte[]> consumer) {
        consumer.accept(new ResourceLocation(AllTheHeads.MOD_ID, "blockstates/" + this.baseSkullType.getId() + ".json"), this.getBuiltInBlockstateVariants().getBytes(StandardCharsets.UTF_8));
        consumer.accept(new ResourceLocation(AllTheHeads.MOD_ID, "blockstates/" + this.baseSkullType.getWallId() + ".json"), this.getBuiltInWallBlockstateVariants().getBytes(StandardCharsets.UTF_8));
        consumer.accept(new ResourceLocation(AllTheHeads.MOD_ID, "models/item/" + this.baseSkullType.getId() + ".json"), this.getBuiltInItemModel().getBytes(StandardCharsets.UTF_8));
    }

    String getBuiltInBlockstateVariants() {
        return BuiltInSkullJsonData.SKULL_BLOCKSTATE_VARIANTS;
    }

    String getBuiltInWallBlockstateVariants() {
        return BuiltInSkullJsonData.SKULL_WALL_BLOCKSTATE_VARIANTS;
    }

    String getBuiltInItemModel() {
        return BuiltInSkullJsonData.SKULL_ITEM_MODEL;
    }

    public static class Builder {
        private final String baseSkullTypeKey;
        private ResourceLocation textureLocation;
        private ResourceLocation modelLocation;
        private String layerLocation = "main";
        private String[] headKey = new String[]{"head"};
        private Vector3f modelOffsets = Vector3f.ZERO;
        
        public Builder(String baseSkullTypeKey) {
            this.baseSkullTypeKey = baseSkullTypeKey;
        }

        public Builder textureLocation(String path) {
            return this.textureLocation(new ResourceLocation(path));
        }

        public Builder textureLocation(ResourceLocation textureLocation) {
            this.textureLocation = textureLocation;
            return this;
        }

        public Builder modelLayerLocation(String path) {
            return this.modelLayerLocation(new ResourceLocation(path));
        }

        public Builder modelLayerLocation(ResourceLocation model) {
            this.modelLocation = model;
            return this;
        }

        public Builder modelLayerLocation(String path, String layer) {
            return this.modelLayerLocation(new ResourceLocation(path), layer);
        }

        public Builder modelLayerLocation(ResourceLocation model, String layer) {
            this.modelLocation = model;
            this.layerLocation = layer;
            return this;
        }

        public Builder customHeadKey(String... headKey) {
            this.headKey = headKey;
            return this;
        }

        public Builder modelOffsets(float x, float y, float z) {
            this.modelOffsets = new Vector3f(x, y, z);
            return this;
        }

        public ClientSkullType build(Function<String, SkullType> skullTypeGetter) {
            Objects.requireNonNull(this.textureLocation);
            SkullType baseSkullType = skullTypeGetter.apply(this.baseSkullTypeKey);
            return new ClientSkullType(baseSkullType, this.textureLocation, this.modelLocation == null ? baseSkullType.getMobType() : this.modelLocation, this.layerLocation, this.headKey, this.modelOffsets);
        }
    }
}
