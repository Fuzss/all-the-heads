package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.slime.SlimeModel;

import java.util.function.UnaryOperator;

public final class SlimeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(SlimeModel.createInnerBodyLayer(),
                UnaryOperator.identity(),
                -4.0F,
                16.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }

    public static LayerDefinition createGelHeadLayer() {
        return createHeadLayer(SlimeModel.createOuterBodyLayer(), "cube", -4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }
}
