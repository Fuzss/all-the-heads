package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.equine.AbstractEquineModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class HorseHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(AbstractEquineModel.createBodyMesh(CubeDeformation.NONE),
                64,
                64);
        return createHeadLayer(layerDefinition,
                "head_parts",
                -2.05F,
                -6.0F,
                -2.0F,
                4.0F,
                12.0F,
                7.0F,
                0.0F,
                4.0F - 1.0F,
                -12.0F - 1.0F);
    }
}
