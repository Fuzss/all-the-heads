package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.TurtleModel;

public final class TurtleHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(TurtleModel.createBodyLayer()),
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
