package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.BreezeModel;

public final class BreezeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = new LayerDefinition(BreezeModel.createBodyLayer(32, 32));
        new PartDefinition(layerDefinition.mesh.getRoot()).getChild("body").getChild("head").clearChild("eyes");
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head"),
                -4.0F,
                -8.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F,
                0.0F,
                4.0F,
                0.0F);
    }

    public static LayerDefinition createEyesLayer() {
        return createHeadLayer(new LayerDefinition(BreezeModel.createBodyLayer(32, 32)),
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head").getChild("eyes"),
                -4.0F,
                -8.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }
}
