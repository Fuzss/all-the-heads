package fuzs.alltheheads.client.model;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class HumanoidHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer(CubeDeformation cubeDeformation) {
        LayerDefinition layerDefinition = LayerDefinition.create(PlayerModel.createMesh(cubeDeformation, 0.0F), 64, 64);
        return createHeadLayer(layerDefinition, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
