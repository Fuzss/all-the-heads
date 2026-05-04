package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.rabbit.AdultRabbitModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class RabbitHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = AdultRabbitModel.createBodyLayer();
        PartDefinition body = layerDefinition.mesh.getRoot().getChild("body");
        // Replace head to get rid of rotation.
        body.addOrReplaceChild("head", body.getChild("head").transformed((PartPose partPose) -> {
            return PartPose.offset(0.0F, -5.2929F, -8.1213F);
        }));
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -2.5F,
                -3.0F,
                -4.0F,
                5.0F,
                5.0F,
                5.0F,
                0.0F,
                -5.2929F,
                -8.1213F);
    }
}
