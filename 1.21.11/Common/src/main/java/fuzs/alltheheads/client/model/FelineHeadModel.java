package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.feline.FelineModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class FelineHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(FelineModel.createBodyMesh(CubeDeformation.NONE),
                64,
                32);
        return createHeadLayer(layerDefinition, -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, 0.0F, 15.0F, -9.0F);
    }
}
