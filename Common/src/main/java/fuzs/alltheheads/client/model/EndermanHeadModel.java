package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.EndermanModel;

public final class EndermanHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(EndermanModel.createBodyLayer()),
                -4.0F,
                -8.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F,
                0.0F,
                -13.0F,
                0.0F);
    }
}
