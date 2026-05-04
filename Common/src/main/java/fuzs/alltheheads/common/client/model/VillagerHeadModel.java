package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.npc.VillagerModel;

public final class VillagerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64);
        return createHeadLayer(layerDefinition, -4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F);
    }
}
