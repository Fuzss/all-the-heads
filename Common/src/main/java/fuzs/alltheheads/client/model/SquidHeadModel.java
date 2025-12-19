package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.squid.SquidModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class SquidHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(SquidModel.createBodyLayer(),
                "body",
                -6.0F,
                -8.0F,
                -6.0F,
                12.0F,
                16.0F,
                12.0F,
                0.0F,
                8.0F,
                0.0F);
    }
}
