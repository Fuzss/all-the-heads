package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.guardian.GuardianModel;

public final class GuardianHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = GuardianModel.createBodyLayer();
        layerDefinition.mesh.getRoot().getChild("head").clearChild("tail0").clearChild("tail1").clearChild("tail2");
        return createHeadLayer(layerDefinition, -8.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F);
    }
}
