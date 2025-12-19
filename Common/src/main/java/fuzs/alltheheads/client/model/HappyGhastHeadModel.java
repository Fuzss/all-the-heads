package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Map;

public final class HappyGhastHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = HappyGhastModel.createBodyLayer(false, CubeDeformation.NONE);
        PartDefinition partDefinition = layerDefinition.mesh.getRoot().getChild("body");
        partDefinition.getChildren().stream().map(Map.Entry::getKey).forEach(partDefinition::clearChild);
        return createHeadLayer(layerDefinition, "body", -8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, 16.0F, 0.0F);
    }
}
