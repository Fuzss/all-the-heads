package fuzs.alltheheads.client.model;

import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class PhantomHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = PhantomModel.createBodyLayer();
        PartDefinition partDefinition2 = layerDefinition.mesh.getRoot().getChild("body");
        // replace head to get rid of rotation
        partDefinition2.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -5.0F, 7.0F, 3.0F, 5.0F),
                PartPose.offset(0.0F, 1.0F, -7.0F));
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -4.0F,
                -2.0F,
                -5.0F,
                7.0F,
                3.0F,
                5.0F,
                0.0F,
                1.0F,
                -7.0F);
    }
}
