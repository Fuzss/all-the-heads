package fuzs.alltheheads.client.model;

import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class RabbitHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(RabbitModel.createBodyLayer(false),
                -2.5F,
                -4.0F,
                -5.0F,
                5.0F,
                4.0F,
                5.0F,
                0.0F,
                16.0F,
                -1.0F);
    }
}
