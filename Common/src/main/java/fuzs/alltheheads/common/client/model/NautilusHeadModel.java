package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public final class NautilusHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(NautilusModel.createBodyLayer(),
                (PartDefinition partDefinition) -> partDefinition.getChild("root").getChild("body"),
                -5.0F,
                -4.51F,
                -3.0F - 33.6F,
                10.0F,
                8.0F,
                14.0F,
                0.0F,
                -8.5F,
                12.3F,
                0.0F,
                Mth.PI,
                0.0F);
    }
}
