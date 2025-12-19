package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.bee.BeeModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class BeeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        int shortenedSize = 4;
        LayerDefinition layerDefinition = BeeModel.createBodyLayer();
        PartDefinition partDefinition1 = layerDefinition.mesh.getRoot().getChild("bone");
        PartDefinition partDefinition2 = partDefinition1.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(shortenedSize, shortenedSize)
                        .addBox(-3.5F, -4.0F, -5.0F - 1.0F, 7.0F, 7.0F, 10.0F - shortenedSize),
                PartPose.ZERO);
        partDefinition2.clearChild("stinger");
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("bone").getChild("body"),
                -3.5F,
                -4.0F,
                -5.0F,
                7.0F,
                7.0F,
                10.0F - shortenedSize);
    }
}
