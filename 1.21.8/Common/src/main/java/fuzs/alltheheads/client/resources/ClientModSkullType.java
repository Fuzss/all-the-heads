package fuzs.alltheheads.client.resources;

import com.google.common.collect.Lists;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@Deprecated
public class ClientModSkullType {
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

    public LayerDefinition getLayerDefinition() {
        return this.layerDefinition.get();
    }

    public float getModelScale() {
        return this.modelScale;
    }

    public List<SkullRenderLayer> getRenderLayers() {
        return this.renderLayers;
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
            return this.textureLocation(ResourceLocationHelper.withDefaultNamespace(path));
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
            List<SkullRenderLayer> renderLayers = this.layers.stream().map(layer -> {
                if (layer.indexOf('#') >= 0) {
                    return layer;
                }
                return skullType.getMobType().getPath() + "#" + layer;
            }).map(SkullRenderLayer::findRenderLayer).toList();
            return new ClientModSkullType(skullType,
                    this.textureLocation,
                    this.layerDefinition,
                    this.modelScale,
                    renderLayers);
        }
    }
}
