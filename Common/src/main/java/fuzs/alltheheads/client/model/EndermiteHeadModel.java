package fuzs.alltheheads.client.model;

import fuzs.alltheheads.client.util.PartDefinitionHelper;
import net.minecraft.client.model.EndermiteModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Set;
import java.util.function.UnaryOperator;

public final class EndermiteHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        int shortenedSize = 2;
        LayerDefinition layerDefinition = EndermiteModel.createBodyLayer();
        PartDefinition partDefinition = layerDefinition.mesh.getRoot();
        partDefinition.addOrReplaceChild("segment1",
                CubeListBuilder.create()
                        .texOffs(shortenedSize, 5 + shortenedSize)
                        .addBox(6.0F * -0.5F, 0.0F, 5.0F * -0.5F, 6.0F, 4.0F, 5.0F - shortenedSize),
                PartPose.offset(0.0F, 24 - 4, 0.0F));
        PartDefinitionHelper.retainExactParts(partDefinition, Set.of("segment0", "segment1"));
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
