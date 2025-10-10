package fuzs.alltheheads.client.model;

import net.minecraft.client.model.ColdCowModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.WarmCowModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class CowHeadModel extends HeadModelBase {
    public static LayerDefinition createTemperateHeadLayer() {
        return createHeadLayer(CowModel.createBodyLayer(), -4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, 0.0F, 4.0F, -8.0F);
    }

    public static LayerDefinition createWarmHeadLayer() {
        return createHeadLayer(WarmCowModel.createBodyLayer(),
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

    public static LayerDefinition createColdHeadLayer() {
        return createHeadLayer(ColdCowModel.createBodyLayer(),
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
