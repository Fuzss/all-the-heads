package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.skeleton.BoggedModel;

public final class BoggedHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(BoggedModel.createBodyLayer(), -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
