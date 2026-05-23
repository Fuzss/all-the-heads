package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.ChickenModel;

import java.util.Set;
import java.util.function.UnaryOperator;

public final class ChickenHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = new LayerDefinition(ChickenModel.createBodyLayer());
        layerDefinition.mesh.getRoot().retainExactParts(Set.of("head", "beak", "red_thing"));
        return createHeadLayer(layerDefinition,
                UnaryOperator.identity(),
                -2.0F,
                -6.0F,
                -2.0F,
                4.0F,
                6.0F,
                3.0F,
                0.0F,
                15.0F,
                -4.0F);
    }
}
