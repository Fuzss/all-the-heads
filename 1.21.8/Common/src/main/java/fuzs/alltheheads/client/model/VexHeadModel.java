package fuzs.alltheheads.client.model;

import net.minecraft.client.model.VexModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class VexHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(VexModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("root").getChild("head"),
                -2.5F,
                -5.0F,
                -2.5F,
                5.0F,
                5.0F,
                5.0F,
                0.0F,
                20.0F,
                0.0F);
    }
}
