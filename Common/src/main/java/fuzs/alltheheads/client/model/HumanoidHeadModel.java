package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;

public final class HumanoidHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer(CubeDeformation cubeDeformation) {
        LayerDefinition layerDefinition = LayerDefinition.create(PlayerModel.createMesh(cubeDeformation, 0.0F), 64, 64);
        new PartDefinition(layerDefinition.mesh.getRoot()).getChild("head").clearChild("hat");
        return createHeadLayer(layerDefinition, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
