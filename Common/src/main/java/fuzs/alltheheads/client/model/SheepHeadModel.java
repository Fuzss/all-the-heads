package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;

public final class SheepHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(new LayerDefinition(SheepModel.createBodyLayer()),
                -3.0F,
                -4.0F,
                -6.0F,
                6.0F,
                6.0F,
                8.0F,
                0.0F,
                6.0F,
                -8.0F);
    }

    public static LayerDefinition createWoolHeadLayer() {
        return createHeadLayer(new LayerDefinition(SheepFurModel.createFurLayer()),
                -3.0F,
                -4.0F,
                -6.0F,
                6.0F,
                6.0F,
                8.0F,
                0.0F,
                6.0F,
                -8.0F);
    }
}
