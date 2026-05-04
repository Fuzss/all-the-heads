package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.hoglin.HoglinModel;

public final class HoglinHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(HoglinModel.createBodyLayer(),
                -7.0F,
                -3.0F,
                -19.0F,
                14.0F,
                6.0F,
                19.0F,
                0.0F,
                2.0F + 13.0F,
                -12.0F + 4.0F);
    }
}
