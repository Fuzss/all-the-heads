package fuzs.alltheheads.client.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.geom.SkullLayerDefinitions;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.client.color.ColorLerper;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Deprecated
public abstract class SkullRenderLayer {
    private static final Map<ModelLayerLocation, SkullRenderLayer> SKULL_RENDER_LAYERS = Maps.newHashMap();
    private static final Map<DyeColor, ModelLayerLocation> SHEEP_FUR_LAYER_DEFINITIONS;
    private static Map<DyeColor, SkullModel> sheepFurHeadModels;

    static {
        ImmutableMap.Builder<DyeColor, ModelLayerLocation> builder = new ImmutableMap.Builder<>();
        for (DyeColor dyeColor : DyeColor.values()) {
            builder.put(dyeColor,
                    new ModelLayerLocation(AllTheHeads.id(dyeColor.getName() + "_fur_sheep_head"), "main"));
        }
        SHEEP_FUR_LAYER_DEFINITIONS = builder.build();
        registerSkullLayers();
    }

    public static void registerSheepFurLayerDefinitions(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        for (ModelLayerLocation layerLocation : SHEEP_FUR_LAYER_DEFINITIONS.values()) {
            consumer.accept(layerLocation, SkullLayerDefinitions::createSheepFurHeadLayer);
        }
    }

    public static void createSheepFurHeadModels(EntityModelSet entityModelSet) {
        ImmutableMap.Builder<DyeColor, SkullModel> builder = new ImmutableMap.Builder<>();
        for (Map.Entry<DyeColor, ModelLayerLocation> entry : SHEEP_FUR_LAYER_DEFINITIONS.entrySet()) {
            builder.put(entry.getKey(), new SkullModel(entityModelSet.bakeLayer(entry.getValue())));
        }
        sheepFurHeadModels = builder.build();
    }

    private static void registerSkullLayers() {
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
        for (DyeColor dyeColor : DyeColor.values()) {
            register("sheep#" + dyeColor.getName() + "_fur", createSheepFurRenderLayer(dyeColor));
        }
    }

    private static SkullRenderLayer createEyesRenderLayer(String textureLocation) {
        final RenderType eyesRenderType = RenderType.eyes(ResourceLocationHelper.withDefaultNamespace(textureLocation));
        return new SkullRenderLayer() {

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, SkullModel parentModel) {
                VertexConsumer vertexconsumer = bufferSource.getBuffer(eyesRenderType);
                parentModel.renderToBuffer(poseStack, vertexconsumer, 0XF00000, OverlayTexture.NO_OVERLAY);
            }
        };
    }

    private static SkullRenderLayer createSheepFurRenderLayer(DyeColor dyeColor) {
        final RenderType renderType = RenderType.entityCutoutNoCull(ResourceLocationHelper.withDefaultNamespace(
                "textures/entity/sheep/sheep_fur.png"));
        return new SkullRenderLayer() {

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, SkullModel parentModel) {
                SkullModel model = sheepFurHeadModels.get(dyeColor);
//                parentModel.copyPropertiesTo(model);
                VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
                model.renderToBuffer(poseStack,
                        vertexconsumer,
                        combinedLight,
                        OverlayTexture.NO_OVERLAY,
                        ColorLerper.Type.SHEEP.getColor(dyeColor));
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
        final RenderType villagerTypeRenderType = RenderType.entityCutoutNoCull(ResourceLocationHelper.withDefaultNamespace(
                String.format("textures/entity/%s/type/%s.png", entity, villagerType)));
        final RenderType villagerProfessionRenderType = RenderType.entityCutoutNoCull(ResourceLocationHelper.withDefaultNamespace(
                String.format("textures/entity/%s/profession/%s.png", entity, villagerProfession)));
        return new SkullRenderLayer() {

            @Override
            public void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, SkullModel parentModel) {
                Stream.of(villagerTypeRenderType, villagerProfessionRenderType).forEach(renderType -> {
                    VertexConsumer vertexconsumer = bufferSource.getBuffer(renderType);
                    parentModel.renderToBuffer(poseStack, vertexconsumer, combinedLight, OverlayTexture.NO_OVERLAY);
                });
            }
        };
    }

    public static SkullRenderLayer findRenderLayer(String identifier) {
        int splitterIndex = identifier.indexOf('#');
        if (splitterIndex >= 0) {
            return findRenderLayer(new ModelLayerLocation(ResourceLocationHelper.fromNamespaceAndPath(AllTheHeads.MOD_ID,
                    identifier.substring(0, splitterIndex)), identifier.substring(splitterIndex)));
        } else {
            throw new IllegalArgumentException(
                    "Skull render layer identifier " + identifier + " is not valid,  must contain '#'!");
        }
    }

    public static SkullRenderLayer findRenderLayer(ModelLayerLocation location) {
        return SKULL_RENDER_LAYERS.get(location);
    }

    private static void register(String identifier, SkullRenderLayer renderLayer) {
        int splitterIndex = identifier.indexOf('#');
        if (splitterIndex >= 0) {
            SKULL_RENDER_LAYERS.put(new ModelLayerLocation(ResourceLocationHelper.fromNamespaceAndPath(AllTheHeads.MOD_ID,
                    identifier.substring(0, splitterIndex)), identifier.substring(splitterIndex)), renderLayer);
        } else {
            throw new IllegalArgumentException(
                    "Skull render layer identifier " + identifier + " is not valid,  must contain '#'!");
        }
    }

    public abstract void render(PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int overlayTexture, SkullModel parentModel);
}
