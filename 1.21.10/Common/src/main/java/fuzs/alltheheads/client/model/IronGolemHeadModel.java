package fuzs.alltheheads.client.model;

import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class IronGolemHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(IronGolemModel.createBodyLayer(),
                -4.0F,
                -12.0F,
                -5.5F,
                8.0F,
                10.0F,
                8.0F,
                0.0F,
                -7.0F,
                -2.0F);
    }
}
