package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.illager.IllagerModel;

public final class IllagerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(IllagerModel.createBodyLayer(), -4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F);
    }
}
