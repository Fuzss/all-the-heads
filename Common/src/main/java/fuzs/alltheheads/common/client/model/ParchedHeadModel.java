package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;

public final class ParchedHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(SkeletonModel.createSingleModelDualBodyLayer(), -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
