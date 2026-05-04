package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.phantom.PhantomModel;

public final class PhantomHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = PhantomModel.createBodyLayer();
        PartDefinition body = layerDefinition.mesh.getRoot().getChild("body");
        // Replace head to get rid of rotation.
        body.addOrReplaceChild("head", body.getChild("head").transformed((PartPose partPose) -> {
            return PartPose.offset(0.0F, 1.0F, -7.0F);
        }));
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
