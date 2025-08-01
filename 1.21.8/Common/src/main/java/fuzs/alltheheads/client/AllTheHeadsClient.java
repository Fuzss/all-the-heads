package fuzs.alltheheads.client;

import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.client.model.geom.SkullLayerDefinitions;
import fuzs.alltheheads.client.renderer.blockentity.ModSkullBlockRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.api.client.core.v1.context.SkullRenderersContext;
import fuzs.puzzleslib.api.client.event.v1.renderer.ExtractRenderStateCallback;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.resources.DefaultPlayerSkin;

public class AllTheHeadsClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ExtractRenderStateCallback.EVENT.register(CustomHeadLayerHandler::onExtractRenderState);
    }

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.value(), ModSkullBlockRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.DEFAULT),
                SkullModel::createHumanoidHeadLayer);
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.ENDERMAN),
                SkullLayerDefinitions::createEndermanHeadLayer);
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.ENDERMAN_EYES),
                SkullLayerDefinitions::createEndermanHeadLayer);
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.BLAZE),
                SkullModel::createMobHeadLayer);
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.TEMPERATE_COW),
                SkullLayerDefinitions::createCowHeadLayer);
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.WARM_COW),
                SkullLayerDefinitions::createCowHeadLayer);
        context.registerLayerDefinition(ModSkullBlockRenderer.createModelLayer(HeadType.ModelType.COLD_COW),
                SkullLayerDefinitions::createCowHeadLayer);
    }

    @Override
    public void onRegisterSkullRenderers(SkullRenderersContext context) {
        context.registerSkullRenderer(ModRegistry.MOB_SKULL_BLOCK_TYPE,
                DefaultPlayerSkin.getDefaultTexture(),
                (EntityModelSet entityModelSet) -> new SkullModel(entityModelSet.bakeLayer(ModelLayers.PLAYER_HEAD)));
    }
}
