package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.PandaModel;

public final class PandaHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(PandaModel.createBodyLayer()),
                -6.5F,
                -5.0F,
                -4.0F,
                13.0F,
                10.0F,
                9.0F,
                0.0F,
                11.5F,
                -17.0F);
    }
}
