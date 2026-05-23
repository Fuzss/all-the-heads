package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.MeshDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

public final class FrogHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        // shorten body / head parts by 4 pixels, so we end up with a depth of 5
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition1 = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition1.addOrReplaceChild("root",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition partDefinition3 = partDefinition2.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(3 + 4, 1 + 4)
                        .addBox(-3.5F, -2.0F, -8.0F, 7.0F, 3.0F, 9.0F - 4.0F)
                        .texOffs(23 + 4, 22 + 4)
                        .addBox(-3.5F, -1.0F, -8.0F, 7.0F, 0.0F, 9.0F - 4.0F),
                PartPose.offset(0.0F, -2.0F, 4.0F));
        PartDefinition partDefinition4 = partDefinition3.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(23 + 4, 13 + 4)
                        .addBox(-3.5F, -1.0F, -7.0F, 7.0F, 0.0F, 9.0F - 4.0F)
                        .texOffs(0 + 4, 13 + 4)
                        .addBox(-3.5F, -2.0F, -7.0F, 7.0F, 3.0F, 9.0F - 4.0F),
                PartPose.offset(0.0F, -2.0F, -1.0F));
        PartDefinition partDefinition5 = partDefinition4.addOrReplaceChild("eyes",
                CubeListBuilder.create(),
                PartPose.offset(-0.5F, 0.0F, 2.0F));
        partDefinition5.addOrReplaceChild("right_eye",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F),
                PartPose.offset(-1.5F, -3.0F, -6.5F));
        partDefinition5.addOrReplaceChild("left_eye",
                CubeListBuilder.create().texOffs(0, 5).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F),
                PartPose.offset(2.5F, -3.0F, -6.5F));
        LayerDefinition layerDefinition = LayerDefinition.create(meshDefinition, 48, 48);
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("root").getChild("body"),
                -3.5F,
                -2.0F,
                -8.0F,
                7.0F,
                3.0F + 2.0F,
                9.0F - 4.0F,
                0.0F,
                -2.0F - 2.0F,
                4.0F);
    }
}
