package fuzs.alltheheads.client.model;

import net.minecraft.client.model.LavaSlimeModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.UnaryOperator;

public final class MagmaCubeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(LavaSlimeModel.createBodyLayer(),
                UnaryOperator.identity(),
                -4.0F,
                16.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }
}
