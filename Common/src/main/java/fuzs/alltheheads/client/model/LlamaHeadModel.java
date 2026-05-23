package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.MeshDefinition;
import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;

public final class LlamaHeadModel extends HeadModelBase {
    /**
     * The llama neck is very long, so shorten it by quite a bit.
     *
     * @see net.minecraft.client.model.LlamaModel#createBodyLayer(CubeDeformation)
     */
    public static LayerDefinition createHeadLayer(CubeDeformation cubeDeformation) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, cubeDeformation)
                        .texOffs(0, 14)
                        .addBox("neck", -4.0F, -16.0F, -6.0F, 8.0F, 10.0F, 6.0F, cubeDeformation)
                        .texOffs(17, 0)
                        .addBox("ear", -4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, cubeDeformation)
                        .texOffs(17, 0)
                        .addBox("ear", 1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, cubeDeformation),
                PartPose.offset(0.0F, 7.0F, -6.0F));
        LayerDefinition layerDefinition = LayerDefinition.create(meshDefinition, 128, 64);
        return createHeadLayer(layerDefinition, -4.0F, -16.0F, -6.0F, 8.0F, 10.0F, 6.0F, 0.0F, 7.0F, -6.0F);
    }
}
