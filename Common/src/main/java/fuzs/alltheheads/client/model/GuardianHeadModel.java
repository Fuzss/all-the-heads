package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.GuardianModel;

public final class GuardianHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = new LayerDefinition(GuardianModel.createBodyLayer());
        new PartDefinition(layerDefinition.mesh.getRoot()).getChild("head")
                .clearChild("tail0")
                .clearChild("tail1")
                .clearChild("tail2");
        return createHeadLayer(layerDefinition, -8.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F);
    }
}
