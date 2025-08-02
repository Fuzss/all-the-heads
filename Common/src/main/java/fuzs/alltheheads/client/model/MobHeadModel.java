package fuzs.alltheheads.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class MobHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer(CubeDeformation cubeDeformation) {
        LayerDefinition layerDefinition = LayerDefinition.create(HumanoidModel.createMesh(cubeDeformation, 0.0F),
                64,
                32);
        return createHeadLayer(layerDefinition, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
