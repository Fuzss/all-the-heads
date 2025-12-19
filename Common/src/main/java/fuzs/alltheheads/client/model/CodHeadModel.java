package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.fish.CodModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class CodHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = CodModel.createBodyLayer();
        PartDefinition partDefinition = layerDefinition.mesh.getRoot().getChild("head");
        partDefinition.addOrReplaceChild("nose",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, -3.0F));
        return createHeadLayer(layerDefinition, -1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, 0.0F, 22.0F, 0.0F);
    }
}
