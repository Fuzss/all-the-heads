package fuzs.alltheheads.client.resources;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.ModSkullModel;
import fuzs.alltheheads.resources.SkullManager;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.stream.Stream;

public abstract class SkullRenderLayer {
    private static final Map<ModelResourceLocation, SkullRenderLayer> SKULL_RENDER_LAYERS = Maps.newHashMap();

    static {
        register("enderman#eyes", createEyesRenderLayer("textures/entity/enderman/enderman_eyes.png"));
        register("spider#eyes", createEyesRenderLayer("textures/entity/spider_eyes.png"));
        register("cave_spider#eyes", createEyesRenderLayer("textures/entity/spider_eyes.png"));
        register("phantom#eyes", createEyesRenderLayer("textures/entity/phantom_eyes.png"));
        for (ResourceLocation type : SkullManager.VILLAGER_BIOME_TYPES) {
            for (ResourceLocation profession : SkullManager.VILLAGER_WORKER_PROFESSIONS) {
                String villager = String.format("villager#%s_%s", type.getPath(), profession.getPath());
                register(villager, createVillagerRenderLayer(type.getPath(), profession.getPath()));
                String zombieVillager = String.format("zombie_villager#%s_%s", type.getPath(), profession.getPath());
                register(zombieVillager, createZombieVillagerRenderLayer(type.getPath(), profession.getPath()));
            }
        }
    }

    private static SkullRenderLayer createEyesRenderLayer(String textureLocation) {
        final RenderType eyesRenderType = RenderType.eyes(new ResourceLocation(textureLocation));
        return new SkullRenderLayer() {

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, ModSkullModel parentModel) {
                VertexConsumer vertexconsumer = bufferSource.getBuffer(eyesRenderType);
                parentModel.renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        };
    }

    private static SkullRenderLayer createVillagerRenderLayer(String villagerType, String villagerProfession) {
        return createVillagerRenderLayer("villager", villagerType, villagerProfession);
    }

    private static SkullRenderLayer createZombieVillagerRenderLayer(String villagerType, String villagerProfession) {
        return createVillagerRenderLayer("zombie_villager", villagerType, villagerProfession);
    }

    private static SkullRenderLayer createVillagerRenderLayer(String entity, String villagerType, String villagerProfession) {
        final RenderType villagerTypeRenderType = RenderType.entityCutoutNoCull(new ResourceLocation(String.format("textures/entity/%s/type/%s.png", entity, villagerType)));
        final RenderType villagerProfessionRenderType = RenderType.entityCutoutNoCull(new ResourceLocation(String.format("textures/entity/%s/profession/%s.png", entity, villagerProfession)));
        return new SkullRenderLayer() {

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, ModSkullModel parentModel) {
                Stream.of(villagerTypeRenderType, villagerProfessionRenderType).forEach(renderType -> {
                    VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
                    parentModel.renderToBuffer(poseStack, vertexconsumer, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                });
            }
        };
    }

    public static SkullRenderLayer findRenderLayer(String identifier) {
        int splitterIndex = identifier.indexOf('#');
        if (splitterIndex >= 0) {
            return findRenderLayer(new ModelResourceLocation(new ResourceLocation(AllTheHeads.MOD_ID, identifier.substring(0, splitterIndex)), identifier.substring(splitterIndex)));
        } else {
            throw new IllegalArgumentException("Skull render layer identifier " + identifier + " is not valid,  must contain '#'!");
        }
    }

    public static SkullRenderLayer findRenderLayer(ModelResourceLocation location) {
        return SKULL_RENDER_LAYERS.get(location);
    }

    private static void register(String identifier, SkullRenderLayer renderLayer) {
        int splitterIndex = identifier.indexOf('#');
        if (splitterIndex >= 0) {
            SKULL_RENDER_LAYERS.put(new ModelResourceLocation(new ResourceLocation(AllTheHeads.MOD_ID, identifier.substring(0, splitterIndex)), identifier.substring(splitterIndex)), renderLayer);
        } else {
            throw new IllegalArgumentException("Skull render layer identifier " + identifier + " is not valid,  must contain '#'!");
        }
    }

    public abstract void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, ModSkullModel parentModel);
}
