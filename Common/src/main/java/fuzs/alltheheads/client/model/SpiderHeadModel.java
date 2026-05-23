package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.SpiderModel;

public final class SpiderHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(SpiderModel.createSpiderBodyLayer()),
                -4.0F,
                -4.0F,
                -8.0F,
                8.0F,
                8.0F,
                8.0F,
                0.0F,
                15.0F,
                -3.0F);
    }
}
