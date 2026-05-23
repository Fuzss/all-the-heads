package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.ArmadilloModel;

public final class ArmadilloHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(ArmadilloModel.createBodyLayer()),
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -1.5F,
                -1.0F,
                -1.0F,
                3.0F,
                5.0F,
                2.0F,
                0.0F,
                -2.0F,
                -11.0F);
    }
}
