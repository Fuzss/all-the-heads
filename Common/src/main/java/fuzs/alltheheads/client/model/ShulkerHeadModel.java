package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.ShulkerModel;

public final class ShulkerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(ShulkerModel.createBodyLayer()),
                -3.0F,
                0.0F,
                -3.0F,
                6.0F,
                6.0F,
                6.0F,
                0.0F,
                12.0F,
                0.0F);
    }
}
