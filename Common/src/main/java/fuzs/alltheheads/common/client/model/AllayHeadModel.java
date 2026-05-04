package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.allay.AllayModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class AllayHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(AllayModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("root").getChild("head"),
                -2.5F,
                -5.0F,
                -2.5F,
                5.0F,
                5.0F,
                5.0F,
                0.0F,
                -3.99F,
                0.0F);
    }
}
