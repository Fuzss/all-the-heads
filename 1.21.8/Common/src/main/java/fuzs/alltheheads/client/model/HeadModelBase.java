package fuzs.alltheheads.client.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.function.UnaryOperator;

public abstract class HeadModelBase {
    protected static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ) {
        return createHeadLayer(layerDefinition, "head", originX, originY, originZ, dimensionX, dimensionY, dimensionZ);
    }

    protected static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, String partName, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ) {
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild(partName),
                originX,
                originY,
                originZ,
                dimensionX,
                dimensionY,
                dimensionZ);
    }

    protected static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, UnaryOperator<PartDefinition> headPartGetter, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ) {
        return createHeadLayer(layerDefinition,
                headPartGetter,
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

    protected static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ, float offsetX, float offsetY, float offsetZ) {
        return createHeadLayer(layerDefinition,
                "head",
                originX,
                originY,
                originZ,
                dimensionX,
                dimensionY,
                dimensionZ,
                offsetX,
                offsetY,
                offsetZ);
    }

    protected static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, String partName, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ, float offsetX, float offsetY, float offsetZ) {
        return createHeadLayer(layerDefinition,
                (PartDefinition partDefinition) -> partDefinition.getChild(partName),
                originX,
                originY,
                originZ,
                dimensionX,
                dimensionY,
                dimensionZ,
                offsetX,
                offsetY,
                offsetZ);
    }

    protected static LayerDefinition createHeadLayer(LayerDefinition layerDefinition, UnaryOperator<PartDefinition> headPartGetter, float originX, float originY, float originZ, float dimensionX, float dimensionY, float dimensionZ, float offsetX, float offsetY, float offsetZ) {
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
        partDefinition3.addOrReplaceChild("head", headPartGetter.apply(layerDefinition.mesh.getRoot()));
        return new LayerDefinition(meshDefinition, layerDefinition.material);
    }
}
