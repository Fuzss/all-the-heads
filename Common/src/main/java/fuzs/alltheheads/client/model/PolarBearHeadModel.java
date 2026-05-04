package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.polarbear.PolarBearModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class PolarBearHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(PolarBearModel.createBodyLayer(),
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
