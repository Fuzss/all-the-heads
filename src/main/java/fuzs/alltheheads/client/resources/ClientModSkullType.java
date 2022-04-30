package fuzs.alltheheads.client.resources;

import com.google.common.collect.Lists;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.BuiltInSkullJsonData;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ClientModSkullType {
    private static final ModelLayerRegistry REGISTRY = ModelLayerRegistry.of(AllTheHeads.MOD_ID);

    private final ModSkullType baseSkullType;
    private final ResourceLocation textureLocation;
    private final Supplier<LayerDefinition> layerDefinition;
    private final float modelScale;
    private final List<SkullRenderLayer> renderLayers;

    private ClientModSkullType(ModSkullType baseSkullType, ResourceLocation textureLocation, Supplier<LayerDefinition> layerDefinition, float modelScale, List<SkullRenderLayer> renderLayers) {
        this.baseSkullType = baseSkullType;
        this.textureLocation = textureLocation;
        this.layerDefinition = layerDefinition;
        this.modelScale = modelScale;
        this.renderLayers = renderLayers;
    }

    public ModSkullType getBaseSkullType() {
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

    public List<SkullRenderLayer> getRenderLayers() {
        return this.renderLayers;
    }

    public void buildResourceMap(BiConsumer<ResourceLocation, byte[]> consumer) {
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
        private final List<String> layers = Lists.newArrayList();
        
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

        public Builder layer(String layer) {
            this.layers.add(layer);
            return this;
        }

        public ClientModSkullType build(Function<String, ModSkullType> skullTypeGetter) {
            ModSkullType skullType = skullTypeGetter.apply(this.baseSkullTypeKey);
            Objects.requireNonNull(skullType);
            Objects.requireNonNull(this.textureLocation);
            Objects.requireNonNull(this.layerDefinition);
            List<SkullRenderLayer> renderLayers = this.layers.stream()
                    .map(layer -> {
                        if (layer.indexOf('#') >= 0) {
                            return layer;
                        }
                        return skullType.getMobType().getPath() + "#" + layer;
                    })
                    .map(SkullRenderLayer::findRenderLayer)
                    .toList();
            return new ClientModSkullType(skullType, this.textureLocation, this.layerDefinition, this.modelScale, renderLayers);
        }
    }
}
