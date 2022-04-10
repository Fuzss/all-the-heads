package fuzs.alltheheads.client.model.geom;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SkullLayerDefinitions {

    public static LayerDefinition createPiglinHeadLayer(boolean zombified) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F).texOffs(31, 1).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 1.0F).texOffs(2, 4).addBox(2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F).texOffs(2, 0).addBox(-3.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(51, 6).addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F), PartPose.offsetAndRotation(4.5F, -6.0F, 0.0F, 0.0F, 0.0F, (-(float)Math.PI / 6F)));
        if (!zombified) {
            partdefinition1.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(39, 6).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F), PartPose.offsetAndRotation(-4.5F, -6.0F, 0.0F, 0.0F, 0.0F, ((float)Math.PI / 6F)));
        }
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createCowHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F).texOffs(22, 0).addBox("right_horn", -5.0F, -9.0F, -1.0F, 1.0F, 3.0F, 1.0F).texOffs(22, 0).addBox("left_horn", 4.0F, -9.0F, -1.0F, 1.0F, 3.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static LayerDefinition createVillagerHeadLayer(boolean witch) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
        PartDefinition partdefinition2 = partdefinition1.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
        partdefinition2.addOrReplaceChild("hat_rim", CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F), PartPose.rotation((-(float)Math.PI / 2F), 0.0F, 0.0F));
        partdefinition1.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F));
        if (witch) {
            PartDefinition partdefinition11 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
            PartDefinition partdefinition21 = partdefinition11.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F), PartPose.offset(-5.0F, -10.03125F, -5.0F));
            PartDefinition partdefinition3 = partdefinition21.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F), PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.05235988F, 0.0F, 0.02617994F));
            PartDefinition partdefinition4 = partdefinition3.addOrReplaceChild("hat3", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F));
            partdefinition4.addOrReplaceChild("hat4", CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, -0.20943952F, 0.0F, 0.10471976F));
            PartDefinition partdefinition5 = partdefinition11.getChild("nose");
            partdefinition5.addOrReplaceChild("mole", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, -2.0F, 0.0F));
            return LayerDefinition.create(meshdefinition, 64, 128);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createZombieVillagerHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", (new CubeListBuilder()).texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F).texOffs(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.ZERO);
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("hat_rim", CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F), PartPose.rotation((-(float)Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createBlazeHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static LayerDefinition createSpiderHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static LayerDefinition createEndermanHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.ZERO);
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static LayerDefinition createAxolotlHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(0.001F);
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 1).addBox(-4.0F, -5.0F, -2.5F, 8.0F, 5.0F, 5.0F, cubedeformation), PartPose.ZERO);
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(3, 37).addBox(-4.0F, -5.0F, 2.5F, 8.0F, 3.0F, 0.0F, cubedeformation);
        CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -7.0F, 2.5F, 3.0F, 7.0F, 0.0F, cubedeformation);
        CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(11, 40).addBox(0.0F, -7.0F, 2.5F, 3.0F, 7.0F, 0.0F, cubedeformation);
        partdefinition2.addOrReplaceChild("top_gills", cubelistbuilder, PartPose.offset(0.0F, -3.0F, -1.0F));
        partdefinition2.addOrReplaceChild("left_gills", cubelistbuilder1, PartPose.offset(-4.0F, 0.0F, -1.0F));
        partdefinition2.addOrReplaceChild("right_gills", cubelistbuilder2, PartPose.offset(4.0F, 0.0F, -1.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createChickenHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -1.5F, 4.0F, 6.0F, 3.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -4.0F, -3.5F, 4.0F, 2.0F, 2.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, -2.0F, -2.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static LayerDefinition createSquidHeadLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 16.0F, 12.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}
