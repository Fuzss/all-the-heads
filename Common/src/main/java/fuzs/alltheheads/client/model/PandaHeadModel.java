package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.panda.PandaModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class PandaHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(PandaModel.createBodyLayer(),
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
