package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.FoxModel;

public final class FoxHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(FoxModel.createBodyLayer()),
                -3.0F,
                -2.0F,
                -5.0F,
                8.0F,
                6.0F,
                6.0F,
                -1.0F,
                16.5F,
                -3.0F);
    }
}
