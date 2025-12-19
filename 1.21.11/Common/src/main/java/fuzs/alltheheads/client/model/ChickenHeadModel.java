package fuzs.alltheheads.client.model;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.ColdChickenModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class ChickenHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(ChickenModel.createBodyLayer(),
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

    public static LayerDefinition createColdHeadLayer() {
        return createHeadLayer(ColdChickenModel.createBodyLayer(),
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
