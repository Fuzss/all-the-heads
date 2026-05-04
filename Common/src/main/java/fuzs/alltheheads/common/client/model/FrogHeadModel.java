package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.frog.FrogModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Set;

public final class FrogHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        // Shorten body and head parts to reduce depth.
        int smallerSize = 4;
        LayerDefinition layerDefinition = FrogModel.createBodyLayer();
        PartDefinition root = layerDefinition.mesh.getRoot();
        root.retainPartsAndChildren(Set.of("head"));
        PartDefinition body = root.getChild("root")
                .addOrReplaceChild("body",
                        CubeListBuilder.create()
                                .texOffs(3 + smallerSize, 1 + smallerSize)
                                .addBox(-3.5F, -2.0F, -8.0F, 7.0F, 3.0F, 9.0F - smallerSize)
                                .texOffs(23 + smallerSize, 22 + smallerSize)
                                .addBox(-3.5F, -1.0F, -8.0F, 7.0F, 0.0F, 9.0F - smallerSize),
                        PartPose.offset(0.0F, -2.0F, 4.0F));
        body.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(23 + smallerSize, 13 + smallerSize)
                        .addBox(-3.5F, -1.0F, -7.0F, 7.0F, 0.0F, 9.0F - smallerSize)
                        .texOffs(smallerSize, 13 + smallerSize)
                        .addBox(-3.5F, -2.0F, -7.0F, 7.0F, 3.0F, 9.0F - smallerSize),
                PartPose.offset(0.0F, -2.0F, -1.0F));
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("root").getChild("body"),
                -3.5F,
                -2.0F,
                -8.0F,
                7.0F,
                3.0F + 2.0F,
                9.0F - smallerSize,
                0.0F,
                -2.0F - 2.0F,
                4.0F);
    }
}
