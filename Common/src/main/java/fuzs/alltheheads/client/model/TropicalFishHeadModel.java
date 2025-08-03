package fuzs.alltheheads.client.model;

import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class TropicalFishHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(TropicalFishModelA.createBodyLayer(CubeDeformation.NONE),
                -1.0F,
                -2.0F,
                -3.0F,
                2.0F,
                4.0F,
                3.0F,
                0.0F,
                20.0F,
                -7.2F);
    }
}
