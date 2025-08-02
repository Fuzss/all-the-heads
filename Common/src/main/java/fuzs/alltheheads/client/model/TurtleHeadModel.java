package fuzs.alltheheads.client.model;

import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class TurtleHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(TurtleModel.createBodyLayer(),
                -3.0F,
                -1.0F,
                -3.0F,
                6.0F,
                5.0F,
                6.0F,
                0.0F,
                19.0F,
                -10.0F);
    }
}
