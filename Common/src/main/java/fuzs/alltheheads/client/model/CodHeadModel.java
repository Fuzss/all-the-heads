package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

public final class CodHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = new LayerDefinition(CodModel.createBodyLayer());
        PartDefinition partDefinition = new PartDefinition(layerDefinition.mesh.getRoot()).getChild("head");
        partDefinition.addOrReplaceChild("nose",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, -3.0F));
        return createHeadLayer(layerDefinition, -1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, 0.0F, 22.0F, 0.0F);
    }
}
