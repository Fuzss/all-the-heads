package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.SquidModel;

public final class SquidHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(SquidModel.createBodyLayer()),
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
