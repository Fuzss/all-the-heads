package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.ravager.RavagerModel;

public final class RavagerHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(RavagerModel.createBodyLayer(),
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
