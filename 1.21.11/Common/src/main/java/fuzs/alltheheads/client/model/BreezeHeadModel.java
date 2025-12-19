package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.breeze.BreezeModel;

public final class BreezeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = BreezeModel.createBodyLayer();
        layerDefinition.mesh.getRoot().getChild("body").getChild("head").clearChild("eyes");
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
        return createHeadLayer(BreezeModel.createEyesLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("body").getChild("head").getChild("eyes"),
                -4.0F,
                -8.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }
}
