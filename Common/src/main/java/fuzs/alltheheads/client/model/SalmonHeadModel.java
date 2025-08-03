package fuzs.alltheheads.client.model;

import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class SalmonHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(SalmonModel.createBodyLayer(),
                -1.0F,
                -2.0F,
                -3.0F,
                2.0F,
                4.0F,
                3.0F,
                0.0F,
                20.0F,
                -7.2F);
    }
}
