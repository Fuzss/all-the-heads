package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.SalmonModel;

public final class SalmonHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(SalmonModel.createBodyLayer()),
                -1.0F,
                -2.0F,
                -3.0F,
                2.0F,
                4.0F,
                3.0F,
                0.0F,
                20.0F,
                0.0F);
    }
}
