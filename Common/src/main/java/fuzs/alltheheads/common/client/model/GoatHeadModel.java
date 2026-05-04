package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.goat.GoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class GoatHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(GoatModel.createBodyLayer(),
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
