package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.chicken.AdultChickenModel;
import net.minecraft.client.model.animal.chicken.ColdChickenModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class ChickenHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(AdultChickenModel.createBodyLayer(),
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
