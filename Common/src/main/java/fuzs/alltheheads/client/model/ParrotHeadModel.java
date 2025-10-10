package fuzs.alltheheads.client.model;

import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class ParrotHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(ParrotModel.createBodyLayer(),
                -1.0F,
                -1.5F,
                -1.0F,
                2.0F,
                3.0F,
                2.0F,
                0.0F,
                15.69F,
                -2.76F);
    }
}
