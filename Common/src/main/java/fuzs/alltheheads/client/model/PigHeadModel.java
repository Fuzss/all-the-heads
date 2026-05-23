package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;

public final class PigHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(PigModel.createBodyLayer(CubeDeformation.NONE)),
                -4.0F,
                -4.0F,
                -8.0F,
                8.0F,
                8.0F,
                8.0F,
                0.0F,
                12.0F,
                -6.0F);
    }
}
