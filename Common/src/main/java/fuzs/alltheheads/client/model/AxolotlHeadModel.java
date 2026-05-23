package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.AxolotlModel;

public final class AxolotlHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(AxolotlModel.createBodyLayer()),
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -4.0F,
                -3.0F,
                -5.0F,
                8.0F,
                5.0F,
                5.0F,
                0.0F,
                0.0F,
                -9.0F);
    }
}
