package fuzs.alltheheads.client.resources;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.BuiltInSkullJsonData;
import fuzs.alltheheads.resources.SkullType;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ClientSkullType {
    private static final ModelLayerRegistry REGISTRY = ModelLayerRegistry.of(AllTheHeads.MOD_ID);

    private final SkullType baseSkullType;
    private final ResourceLocation textureLocation;
    private final Supplier<LayerDefinition> layerDefinition;
    private final float modelScale;

    private ClientSkullType(SkullType baseSkullType, ResourceLocation textureLocation, Supplier<LayerDefinition> layerDefinition, float modelScale) {
        this.baseSkullType = baseSkullType;
        this.textureLocation = textureLocation;
        this.layerDefinition = layerDefinition;
        this.modelScale = modelScale;
    }

    public SkullType getBaseSkullType() {
        return this.baseSkullType;
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public ModelLayerLocation getModelLayerLocationId() {
        return REGISTRY.register(this.baseSkullType.getMobType().getPath() + this.baseSkullType.getSuffixId());
    }

    public LayerDefinition getLayerDefinition() {
        return this.layerDefinition.get();
    }

    public float getModelScale() {
        return this.modelScale;
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
        private Supplier<LayerDefinition> layerDefinition;
        private float modelScale = 1.0F;
        
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

        public Builder layerDefinition(Supplier<LayerDefinition> layerDefinition) {
            this.layerDefinition = layerDefinition;
            return this;
        }

        public Builder modelScale(float modelScale) {
            this.modelScale = modelScale;
            return this;
        }

        public ClientSkullType build(Function<String, SkullType> skullTypeGetter) {
            Objects.requireNonNull(this.textureLocation);
            Objects.requireNonNull(this.layerDefinition);
            return new ClientSkullType(skullTypeGetter.apply(this.baseSkullTypeKey), this.textureLocation, this.layerDefinition, this.modelScale);
        }
    }
}
