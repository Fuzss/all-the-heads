package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.RabbitModel;

public final class RabbitHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(RabbitModel.createBodyLayer()),
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
