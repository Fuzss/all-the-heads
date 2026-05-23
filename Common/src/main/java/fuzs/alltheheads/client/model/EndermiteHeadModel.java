package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

import java.util.Set;
import java.util.function.UnaryOperator;

public final class EndermiteHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        int shortenedSize = 2;
        LayerDefinition layerDefinition = new LayerDefinition(EndermiteModel.createBodyLayer());
        PartDefinition partDefinition = new PartDefinition(layerDefinition.mesh.getRoot());
        partDefinition.addOrReplaceChild("segment1",
                CubeListBuilder.create()
                        .texOffs(shortenedSize, 5 + shortenedSize)
                        .addBox(6.0F * -0.5F, 0.0F, 5.0F * -0.5F, 6.0F, 4.0F, 5.0F - shortenedSize),
                PartPose.offset(0.0F, 24 - 4, 0.0F));
        partDefinition.retainExactParts(Set.of("segment0", "segment1"));
        return createHeadLayer(layerDefinition,
                UnaryOperator.identity(),
                6.0F * -0.5F,
                0.0F,
                5.0F * -0.5F,
                6.0F,
                4.0F,
                5.0F - shortenedSize,
                0.0F,
                24 - 4,
                0.0F);
    }
}
