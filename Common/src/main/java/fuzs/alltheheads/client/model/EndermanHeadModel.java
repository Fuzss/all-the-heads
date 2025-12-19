package fuzs.alltheheads.client.model;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class EndermanHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(EndermanModel.createBodyLayer(),
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
