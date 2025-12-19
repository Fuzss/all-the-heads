package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.dolphin.DolphinModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class DolphinHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(DolphinModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -4.0F,
                -3.0F,
                -3.0F,
                8.0F,
                7.0F,
                6.0F,
                0.0F,
                -4.0F,
                -3.0F);
    }
}
