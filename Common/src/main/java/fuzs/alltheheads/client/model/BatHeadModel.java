package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.BatModel;

public final class BatHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(BatModel.createBodyLayer()),
                -2.0F,
                -3.0F,
                -1.0F,
                4.0F,
                3.0F,
                2.0F,
                0.0F,
                17.0F,
                0.0F);
    }
}
