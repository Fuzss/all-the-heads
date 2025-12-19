package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class PigHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(PigModel.createBodyLayer(CubeDeformation.NONE),
                -4.0F,
                -4.0F,
                -8.0F,
                8.0F,
                8.0F,
                8.0F,
                0.0F,
                12.0F,
                -6.0F);
    }
}
