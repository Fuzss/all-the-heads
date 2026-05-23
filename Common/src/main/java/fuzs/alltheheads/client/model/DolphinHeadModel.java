package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.DolphinModel;

public final class DolphinHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(DolphinModel.createBodyLayer()),
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
