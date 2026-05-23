package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.RabbitModel;

import java.util.Set;
import java.util.function.UnaryOperator;

public final class RabbitHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = new LayerDefinition(RabbitModel.createBodyLayer());
        layerDefinition.mesh.getRoot().retainExactParts(Set.of("head", "right_ear", "left_ear", "nose"));
        return createHeadLayer(layerDefinition,
                UnaryOperator.identity(),
                -2.5F,
                -4.0F,
                -5.0F,
                5.0F,
                4.0F,
                5.0F,
                0.0F,
                16.0F,
                -1.0F);
    }
}
