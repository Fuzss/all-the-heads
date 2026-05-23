package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.LavaSlimeModel;

import java.util.function.UnaryOperator;

public final class MagmaCubeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(LavaSlimeModel.createBodyLayer()),
                UnaryOperator.identity(),
                -4.0F,
                16.0F,
                -4.0F,
                8.0F,
                8.0F,
                8.0F);
    }
}
