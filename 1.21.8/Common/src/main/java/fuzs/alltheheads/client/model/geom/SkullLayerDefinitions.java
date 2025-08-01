package fuzs.alltheheads.client.model.geom;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

/**
 * How to convert to head models from vanilla definitions:
 * <ul>
 * <li>{@link CubeListBuilder#addBox} -originX (negative) needs to be half of sizeX</li>
 * <li>{@link CubeListBuilder#addBox} -originY (negative) needs to be same as sizeY</li>
 * <li>{@link CubeListBuilder#addBox} -originZ (negative) needs to be half of sizeZ</li>
 * <li>Replace any {@link PartPose} on the main head model with {@link PartPose#ZERO}</li>
 * </ul>
 */
public class SkullLayerDefinitions {

    public static LayerDefinition createPiglinHeadLayer(boolean isZombified) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition1 = partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F)
                        .texOffs(31, 1)
                        .addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 1.0F)
                        .texOffs(2, 4)
                        .addBox(2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F)
                        .texOffs(2, 0)
                        .addBox(-3.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F),
                PartPose.ZERO);
        partDefinition1.addOrReplaceChild("left_ear",
                CubeListBuilder.create().texOffs(51, 6).addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F),
                PartPose.offsetAndRotation(4.5F, -6.0F, 0.0F, 0.0F, 0.0F, (-(float) Math.PI / 6F)));

        if (!isZombified) {
            partDefinition1.addOrReplaceChild("right_ear",
                    CubeListBuilder.create().texOffs(39, 6).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F),
                    PartPose.offsetAndRotation(-4.5F, -6.0F, 0.0F, 0.0F, 0.0F, ((float) Math.PI / 6F)));
        }

        partDefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createVillagerHeadLayer(boolean isWitch) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition1 = partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F),
                PartPose.ZERO);
        PartDefinition partDefinition2 = partDefinition1.addOrReplaceChild("hat",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO);
        partDefinition2.addOrReplaceChild("hat_rim",
                CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F),
                PartPose.rotation((-(float) Math.PI / 2F), 0.0F, 0.0F));
        partDefinition1.addOrReplaceChild("nose",
                CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offset(0.0F, -2.0F, 0.0F));

        if (isWitch) {
            PartDefinition partDefinition11 = partDefinition.addOrReplaceChild("head",
                    CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F),
                    PartPose.ZERO);
            PartDefinition partDefinition21 = partDefinition11.addOrReplaceChild("hat",
                    CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F),
                    PartPose.offset(-5.0F, -10.03125F, -5.0F));
            PartDefinition partDefinition3 = partDefinition21.addOrReplaceChild("hat2",
                    CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F),
                    PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.05235988F, 0.0F, 0.02617994F));
            PartDefinition partDefinition4 = partDefinition3.addOrReplaceChild("hat3",
                    CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F),
                    PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F));
            partDefinition4.addOrReplaceChild("hat4",
                    CubeListBuilder.create()
                            .texOffs(0, 95)
                            .addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
                    PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, -0.20943952F, 0.0F, 0.10471976F));
            PartDefinition partDefinition5 = partDefinition11.getChild("nose");
            partDefinition5.addOrReplaceChild("mole",
                    CubeListBuilder.create()
                            .texOffs(0, 0)
                            .addBox(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)),
                    PartPose.offset(0.0F, -2.0F, 0.0F));
            return LayerDefinition.create(meshDefinition, 64, 128);
        }

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createZombieVillagerHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                (new CubeListBuilder()).texOffs(0, 0)
                        .addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F)
                        .texOffs(24, 0)
                        .addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F),
                PartPose.ZERO);
        PartDefinition partDefinition1 = partDefinition.addOrReplaceChild("hat",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO);
        partDefinition1.addOrReplaceChild("hat_rim",
                CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F),
                PartPose.rotation((-(float) Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createBlazeHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createSpiderHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createEndermanHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("hat",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)),
                PartPose.ZERO);
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createAxolotlHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(0.001F);
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 1).addBox(-4.0F, -5.0F, -2.5F, 8.0F, 5.0F, 5.0F, cubedeformation),
                PartPose.ZERO);
        CubeListBuilder cubelistbuilder = CubeListBuilder.create()
                .texOffs(3, 37)
                .addBox(-4.0F, -5.0F, 2.5F, 8.0F, 3.0F, 0.0F, cubedeformation);
        CubeListBuilder cubelistbuilder1 = CubeListBuilder.create()
                .texOffs(0, 40)
                .addBox(-3.0F, -7.0F, 2.5F, 3.0F, 7.0F, 0.0F, cubedeformation);
        CubeListBuilder cubelistbuilder2 = CubeListBuilder.create()
                .texOffs(11, 40)
                .addBox(0.0F, -7.0F, 2.5F, 3.0F, 7.0F, 0.0F, cubedeformation);
        partDefinition2.addOrReplaceChild("top_gills", cubelistbuilder, PartPose.offset(0.0F, -3.0F, -1.0F));
        partDefinition2.addOrReplaceChild("left_gills", cubelistbuilder1, PartPose.offset(-4.0F, 0.0F, -1.0F));
        partDefinition2.addOrReplaceChild("right_gills", cubelistbuilder2, PartPose.offset(4.0F, 0.0F, -1.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createChickenHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -1.5F, 4.0F, 6.0F, 3.0F),
                PartPose.ZERO);
        partDefinition.addOrReplaceChild("beak",
                CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -4.0F, -3.5F, 4.0F, 2.0F, 2.0F),
                PartPose.ZERO);
        partDefinition.addOrReplaceChild("red_thing",
                CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, -2.0F, -2.5F, 2.0F, 2.0F, 2.0F),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createSquidHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 16.0F, 12.0F),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createSheepHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 6.0F, 8.0F),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition createSheepFurHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.6F)),
                PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }
}
