package fuzs.alltheheads.common.client.model;

import net.minecraft.client.model.animal.llama.LlamaModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public final class LlamaHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer(CubeDeformation cubeDeformation) {
        LayerDefinition layerDefinition = LlamaModel.createBodyLayer(cubeDeformation);
        PartDefinition root = layerDefinition.mesh.getRoot();
        root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, cubeDeformation)
                        .texOffs(0, 14)
                        // Shorten the neck quite a bit, as it's very long.
                        .addBox("neck", -4.0F, -16.0F, -6.0F, 8.0F, 10.0F, 6.0F, cubeDeformation)
                        .texOffs(17, 0)
                        .addBox("ear", -4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, cubeDeformation)
                        .texOffs(17, 0)
                        .addBox("ear", 1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, cubeDeformation),
                PartPose.offset(0.0F, 7.0F, -6.0F));
        return createHeadLayer(layerDefinition, -4.0F, -16.0F, -6.0F, 8.0F, 10.0F, 6.0F, 0.0F, 7.0F, -6.0F);
    }
}
