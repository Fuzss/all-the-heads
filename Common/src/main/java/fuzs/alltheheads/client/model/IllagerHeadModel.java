package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.IllagerModel;

public final class IllagerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(IllagerModel.createBodyLayer()),
                -4.0F,
                -10.0F,
                -4.0F,
                8.0F,
                10.0F,
                8.0F);
    }
}
