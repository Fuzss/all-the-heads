package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.RavagerModel;

public final class RavagerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(RavagerModel.createBodyLayer()),
                (PartDefinition partDefinition) -> partDefinition.getChild("neck").getChild("head"),
                -8.0F,
                -20.0F,
                -14.0F,
                16.0F,
                20.0F,
                16.0F,
                0.0F,
                16.0F,
                -17.0F);
    }
}
