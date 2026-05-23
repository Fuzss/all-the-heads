package fuzs.alltheheads.client.model;

import fuzs.puzzleslib.api.client.renderer.v1.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;

import java.util.Set;

public final class HorseHeadModel extends HeadModelBase {
    public static LayerDefinition createHeadLayer() {
        LayerDefinition layerDefinition = LayerDefinition.create(HorseModel.createBodyMesh(CubeDeformation.NONE),
                64,
                64);
        layerDefinition.mesh.getRoot()
                .getChild("head_parts")
                .retainPartsAndChildren(Set.of("head", "mane", "upper_mouth"));
        return createHeadLayer(layerDefinition,
                "head_parts",
                -2.05F,
                -6.0F,
                -2.0F,
                4.0F,
                12.0F,
                7.0F,
                0.0F,
                4.0F - 1.0F,
                -12.0F - 1.0F);
    }
}
