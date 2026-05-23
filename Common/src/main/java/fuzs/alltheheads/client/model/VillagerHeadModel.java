package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.VillagerModel;

public final class VillagerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64);
        return createHeadLayer(layerDefinition, -4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F);
    }
}
