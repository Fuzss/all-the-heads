package fuzs.alltheheads.client.model;

import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CowHeadModel {

    public static MeshTransformer scaling(float scale) {
        return (MeshDefinition meshDefinition) -> {
            return meshDefinition.transformed(pose -> pose.scaled(scale));
        };
    }

    public static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ) {
        return createHeadLayer(layerDefinition,
                originX,
                originY,
                originZ,
                dimensionX,
                dimensionY,
                dimensionZ,
                0.0F,
                0.0F,
                0.0F);
    }

    public static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ, float offsetX, float offsetY, float offsetZ) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create(),
                PartPose.ZERO);
        PartDefinition partDefinition3 = partDefinition2.addOrReplaceChild("head",
                CubeListBuilder.create(),
                PartPose.offset(-originX - offsetX - dimensionX / 2.0F,
                        -originY - offsetY - dimensionY,
                        -originZ - offsetZ - dimensionZ / 2.0F));
        partDefinition3.addOrReplaceChild("head", layerDefinition.mesh.getRoot().getChild("head"));
        return new LayerDefinition(meshDefinition, layerDefinition.material);
    }

    public static LayerDefinition createCowHeadLayer(LayerDefinition layerDefinition) {
        return createHeadLayer(layerDefinition, -4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, 0.0F, 4.0F, -8.0F);
    }

    public static LayerDefinition createOcelotHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(OcelotModel.createBodyMesh(CubeDeformation.NONE),
                64,
                32);
        return createHeadLayer(layerDefinition, -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, 0.0F, 15.0F, -9.0F);
    }
}
