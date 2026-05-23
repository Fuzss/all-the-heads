package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.GoatModel;

public final class GoatHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(GoatModel.createBodyLayer()),
                -3.0F,
                -4.0F,
                -8.0F,
                5.0F,
                7.0F,
                10.0F,
                1.0F,
                6.0F + 4.0F,
                -8.0F + 1.0F);
    }
}
