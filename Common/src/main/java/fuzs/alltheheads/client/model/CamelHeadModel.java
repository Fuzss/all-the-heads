package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.CamelModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

import java.util.Set;

public final class CamelHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = new LayerDefinition(CamelModel.createBodyLayer());
        PartDefinition body = layerDefinition.mesh.getRoot().getChild("body");
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
//                        .texOffs(60, 24)
//                        .addBox(-3.5F, -7.0F, -15.0F, 7.0F, 8.0F, 19.0F)
                .texOffs(21, 0)
                .addBox(-3.5F, -21.0F, -15.0F, 7.0F, 14.0F, 7.0F)
                .texOffs(50, 0)
                .addBox(-2.5F, -21.0F, -21.0F, 5.0F, 5.0F, 6.0F), PartPose.offset(0.0F, -3.0F, -19.5F));
        head.retainExactParts(Set.of("left_ear", "right_ear"));
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -3.5F,
                -21.0F,
                -15.0F,
                7.0F,
                14.0F,
                7.0F,
                0.0F,
                -3.0F,
                -19.5F);
    }
}
