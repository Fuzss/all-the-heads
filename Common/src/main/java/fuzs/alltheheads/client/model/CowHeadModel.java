package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class CowHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer(LayerDefinition layerDefinition) {
        return createHeadLayer(layerDefinition, -4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, 0.0F, 4.0F, -8.0F);
    }
}
