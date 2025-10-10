package fuzs.alltheheads.client.model;

import net.minecraft.client.model.WardenModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class WardenHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(WardenModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("bone").getChild("body").getChild("head"),
                -8.0F,
                -16.0F,
                -5.0F,
                16.0F,
                16.0F,
                10.0F,
                0.0F,
                -13.0F,
                0.0F);
    }
}
