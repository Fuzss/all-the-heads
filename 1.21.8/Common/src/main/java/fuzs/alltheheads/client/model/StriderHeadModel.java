package fuzs.alltheheads.client.model;

import net.minecraft.client.model.StriderModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class StriderHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(StriderModel.createBodyLayer(),
                "body",
                -8.0F,
                -6.0F,
                -8.0F,
                16.0F,
                14.0F,
                16.0F,
                0.0F,
                1.0F,
                0.0F);
    }
}
