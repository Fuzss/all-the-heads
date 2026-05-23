package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.ChickenModel;

public final class ChickenHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(ChickenModel.createBodyLayer()),
                -2.0F,
                -6.0F,
                -2.0F,
                4.0F,
                6.0F,
                3.0F,
                0.0F,
                15.0F,
                -4.0F);
    }
}
