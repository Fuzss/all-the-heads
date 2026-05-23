package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.TadpoleModel;

public final class TadpoleHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(TadpoleModel.createBodyLayer()),
                "body",
                -1.5F,
                -1.0F,
                0.0F,
                3.0F,
                2.0F,
                3.0F,
                0.0F,
                22.0F,
                -3.0F);
    }
}
