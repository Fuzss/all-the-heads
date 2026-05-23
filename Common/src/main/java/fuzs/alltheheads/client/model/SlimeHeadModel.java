package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.SlimeModel;

import java.util.function.UnaryOperator;

public final class SlimeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(SlimeModel.createInnerBodyLayer()),
                UnaryOperator.identity(),
                -4.0F,
                16.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }

    public static LayerDefinition createGelHeadLayer() {
        return createHeadLayer(new LayerDefinition(SlimeModel.createOuterBodyLayer()),
                "cube",
                -4.0F,
                16.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }
}
