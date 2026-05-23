package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.GhastModel;

public final class GhastHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(GhastModel.createBodyLayer()),
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
