package fuzs.alltheheads.client.model;

import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class SnifferHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(SnifferModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("bone").getChild("body").getChild("head"),
                -6.5F,
                -7.5F,
                -11.5F,
                13.0F,
                18.0F,
                11.0F,
                0.0F,
                6.5F - 3.0F,
                -19.48F - 4.52F);
    }
}
