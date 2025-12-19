package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.fox.FoxModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class FoxHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(FoxModel.createBodyLayer(), -3.0F, -2.0F, -5.0F, 8.0F, 6.0F, 6.0F, -1.0F, 16.5F, -3.0F);
    }
}
