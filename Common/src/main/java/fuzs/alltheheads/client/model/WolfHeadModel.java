package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class WolfHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(WolfModel.createMeshDefinition(CubeDeformation.NONE),
                64,
                32);
        return createHeadLayer(layerDefinition, -2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, -1.0F, 13.5F, -7.0F);
    }
}
