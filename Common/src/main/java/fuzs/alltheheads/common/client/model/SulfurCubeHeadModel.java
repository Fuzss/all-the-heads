package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.monster.slime.SulfurCubeModel;

import java.util.function.UnaryOperator;

public final class SulfurCubeHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        // The y-offset is needed to make the cube centered for some reason.
        return createHeadLayer(SulfurCubeModel.createInnerBodyLayer(),
                UnaryOperator.identity(),
                -8.0F,
                -7.0F,
                -8.0F,
                16.0F,
                16.0F,
                16.0F);
    }

    public static LayerDefinition createGelHeadLayer() {
        return createHeadLayer(SulfurCubeModel.createOuterBodyLayer(),
                UnaryOperator.identity(),
                -9.0F,
                -9.0F,
                -9.0F,
                18.0F,
                18.0F,
                18.0F);
    }
}
