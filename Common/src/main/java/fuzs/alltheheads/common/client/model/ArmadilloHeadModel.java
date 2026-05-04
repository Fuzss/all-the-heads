package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.armadillo.AdultArmadilloModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class ArmadilloHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(AdultArmadilloModel.createBodyLayer(),
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
