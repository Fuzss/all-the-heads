package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.PolarBearModel;

public final class PolarBearHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(PolarBearModel.createBodyLayer()),
                -3.5F,
                -3.0F,
                -3.0F,
                7.0F,
                7.0F,
                7.0F,
                0.0F,
                10.0F,
                -16.0F);
    }
}
