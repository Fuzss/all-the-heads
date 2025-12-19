package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.shulker.ShulkerModel;

public final class ShulkerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(ShulkerModel.createBodyLayer(), -3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 12.0F, 0.0F);
    }
}
