package fuzs.alltheheads.client.model;

import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class GhastHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(GhastModel.createBodyLayer(),
                "body",
                -8.0F,
                -8.0F,
                -8.0F,
                16.0F,
                16.0F,
                16.0F,
                0.0F,
                17.6F,
                0.0F);
    }
}
