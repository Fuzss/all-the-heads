package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.creaking.CreakingModel;

public final class CreakingHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(CreakingModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("root")
                        .getChild("upper_body")
                        .getChild("head"),
                -3.0F,
                -10.0F,
                -3.0F,
                6.0F,
                10.0F,
                6.0F,
                -3.0F,
                -11.0F,
                0.0F);
    }
}
