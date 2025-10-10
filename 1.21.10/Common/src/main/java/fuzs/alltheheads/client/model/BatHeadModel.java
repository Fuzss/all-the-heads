package fuzs.alltheheads.client.model;

import net.minecraft.client.model.BatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class BatHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(BatModel.createBodyLayer(), -2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, 17.0F, 0.0F);
    }
}
