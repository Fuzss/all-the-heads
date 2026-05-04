package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.golem.CopperGolemModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class CopperGolemHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(CopperGolemModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -4.0F,
                -5.0F,
                -5.0F,
                8.0F,
                5.0F,
                10.0F,
                0.0F,
                -6.0F,
                0.0F);
    }
}
