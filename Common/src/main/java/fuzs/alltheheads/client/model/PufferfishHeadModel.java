package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.PufferfishBigModel;

import java.util.function.UnaryOperator;

public final class PufferfishHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(PufferfishBigModel.createBodyLayer()),
                UnaryOperator.identity(),
                -4.0F,
                -8.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F,
                0.0F,
                22.0F,
                0.0F);
    }
}
