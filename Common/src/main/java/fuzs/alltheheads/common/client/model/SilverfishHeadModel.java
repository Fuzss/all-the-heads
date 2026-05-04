package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.silverfish.SilverfishModel;

import java.util.Set;
import java.util.function.UnaryOperator;

public final class SilverfishHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = SilverfishModel.createBodyLayer();
        PartDefinition partDefinition = layerDefinition.mesh.getRoot();
        partDefinition.retainExactParts(Set.of("segment0", "segment1", "segment2", "layer0", "layer2"));
        return createHeadLayer(layerDefinition,
                UnaryOperator.identity(),
                -3.0F,
                0.0F,
                -1.5F,
                6.0F,
                4.0F,
                3.0F,
                0.0F,
                20.0F,
                1.0F);
    }
}
