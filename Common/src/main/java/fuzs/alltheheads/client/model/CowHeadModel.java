package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.CowModel;

public final class CowHeadModel extends HeadModelBase {
    public static LayerDefinition createTemperateHeadLayer() {
        return createHeadLayer(new LayerDefinition(CowModel.createBodyLayer()),
                -4.0F,
                -4.0F,
                -6.0F,
                8.0F,
                8.0F,
                6.0F,
                0.0F,
                4.0F,
                -8.0F);
    }
}
