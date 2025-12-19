package fuzs.alltheheads.client.model;

import net.minecraft.client.model.animal.sheep.SheepFurModel;
import net.minecraft.client.model.animal.sheep.SheepModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class SheepHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        return createHeadLayer(SheepModel.createBodyLayer(), -3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F, 6.0F, -8.0F);
    }

    public static LayerDefinition createWoolHeadLayer() {
        return createHeadLayer(SheepFurModel.createFurLayer(),
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
