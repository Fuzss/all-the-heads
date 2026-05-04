package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.axolotl.AdultAxolotlModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class AxolotlHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(AdultAxolotlModel.createBodyLayer(),
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
