package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.rabbit.AdultRabbitModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class RabbitHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(AdultRabbitModel.createBodyLayer(),
                -2.5F,
                -3.0F,
                -4.0F,
                5.0F,
                5.0F,
                5.0F,
                0.0F,
                -5.2929F,
                -8.1213F);
    }
}
