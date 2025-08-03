package fuzs.alltheheads.client.model;

import net.minecraft.client.model.TadpoleModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class TadpoleHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(TadpoleModel.createBodyLayer(),
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
