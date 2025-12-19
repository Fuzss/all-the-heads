package fuzs.alltheheads.client.model;

import net.minecraft.client.model.BoggedModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class BoggedHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(BoggedModel.createBodyLayer(), -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
