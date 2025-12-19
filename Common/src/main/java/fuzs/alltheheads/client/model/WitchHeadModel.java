package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.witch.WitchModel;

public final class WitchHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(WitchModel.createBodyLayer(), -4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F);
    }
}
