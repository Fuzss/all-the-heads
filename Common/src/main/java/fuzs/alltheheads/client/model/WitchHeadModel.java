package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.WitchModel;

public final class WitchHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(WitchModel.createBodyLayer()),
                -4.0F,
                -10.0F,
                -4.0F,
                8.0F,
                10.0F,
                8.0F);
    }
}
