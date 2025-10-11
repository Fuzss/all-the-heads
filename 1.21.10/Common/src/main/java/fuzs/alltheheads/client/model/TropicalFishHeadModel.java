package fuzs.alltheheads.client.model;

import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.TropicalFishModelB;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Set;
import java.util.function.UnaryOperator;

public final class TropicalFishHeadModel extends HeadModelBase {
    public static LayerDefinition createSmallHeadLayer() {
        int shortenedSize = 2;
        LayerDefinition layerDefinition = TropicalFishModelA.createBodyLayer(CubeDeformation.NONE);
        PartDefinition partDefinition = layerDefinition.mesh.getRoot();
        partDefinition.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(shortenedSize, shortenedSize)
                        .addBox(-1.0F, -1.5F, -3.0F, 2.0F, 3.0F, 6.0F - shortenedSize),
                PartPose.offset(0.0F, 22.0F, 0.0F));
        partDefinition.retainExactParts(Set.of("body", "right_fin", "left_fin"));
        return createHeadLayer(layerDefinition,
                UnaryOperator.identity(),
                -1.0F,
                -1.5F,
                -3.0F,
                2.0F,
                3.0F,
                6.0F - shortenedSize,
                0.0F,
                22.0F,
                0.0F);
    }

    public static LayerDefinition createLargeHeadLayer() {
        int shortenedSize = 2;
        LayerDefinition layerDefinition = TropicalFishModelB.createBodyLayer(CubeDeformation.NONE);
        PartDefinition partDefinition = layerDefinition.mesh.getRoot();
        partDefinition.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(shortenedSize, 20 + shortenedSize)
                        .addBox(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F - shortenedSize),
                PartPose.offset(0.0F, 19.0F, 0.0F));
        partDefinition.retainExactParts(Set.of("body", "right_fin", "left_fin"));
        return createHeadLayer(layerDefinition,
                UnaryOperator.identity(),
                -1.0F,
                -3.0F,
                -3.0F,
                2.0F,
                6.0F,
                6.0F - shortenedSize,
                0.0F,
                19.0F,
                0.0F);
    }
}
