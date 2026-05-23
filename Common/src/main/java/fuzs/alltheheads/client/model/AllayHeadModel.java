package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.AllayModel;

public final class AllayHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(AllayModel.createBodyLayer()),
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
