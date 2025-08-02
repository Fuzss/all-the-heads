package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.client.model.HeadLayerDefinitions;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.client.renderer.special.MobHeadSpecialRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.*;
import fuzs.puzzleslib.api.client.event.v1.renderer.AddLivingEntityRenderLayersCallback;
import fuzs.puzzleslib.api.client.event.v1.renderer.ExtractRenderStateCallback;
import fuzs.puzzleslib.api.client.event.v1.renderer.RenderLivingEvents;
import net.minecraft.client.model.ColdCowModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.WarmCowModel;
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
        AddLivingEntityRenderLayersCallback.EVENT.register(CustomHeadLayerHandler::addLivingEntityRenderLayers);
        RenderLivingEvents.BEFORE.register(CustomHeadLayerHandler::onBeforeRenderEntity);
        RenderLivingEvents.AFTER.register(CustomHeadLayerHandler::onAfterRenderEntity);
    }

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.value(), MobHeadBlockRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.MOB),
                SkullModel::createMobHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.HUMANOID),
                SkullModel::createHumanoidHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.ENDERMAN),
                HeadLayerDefinitions::createEndermanHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.ENDERMAN_EYES),
                HeadLayerDefinitions::createEndermanHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.TEMPERATE_COW),
                () -> HeadLayerDefinitions.createCowHeadLayer(CowModel.createBodyLayer()));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.WARM_COW),
                () -> HeadLayerDefinitions.createCowHeadLayer(WarmCowModel.createBodyLayer()));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.COLD_COW),
                () -> HeadLayerDefinitions.createCowHeadLayer(ColdCowModel.createBodyLayer()));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.OCELOT),
                () -> HeadLayerDefinitions.createOcelotHeadLayer().apply(HeadLayerDefinitions.scaling(1.6F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.SHEEP),
                HeadLayerDefinitions::createSheepHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(HeadType.ModelType.SHEEP_WOOL),
                HeadLayerDefinitions::createSheepWoolHeadLayer);
    }

    @Override
    public void onRegisterSkullRenderers(SkullRenderersContext context) {
        context.registerSkullRenderer(ModRegistry.MOB_SKULL_BLOCK_TYPE,
                DefaultPlayerSkin.getDefaultTexture(),
                (EntityModelSet entityModelSet) -> new SkullModel(entityModelSet.bakeLayer(ModelLayers.PLAYER_HEAD)));
    }

    @Override
    public void onRegisterItemModels(ItemModelsContext context) {
        context.registerSpecialModelRenderer(AllTheHeads.id("mob_head"), MobHeadSpecialRenderer.Unbaked.MAP_CODEC);
    }

    @Override
    public void onRegisterSpecialBlockModelRenderers(SpecialBlockModelRenderersContext context) {
        context.registerSpecialBlockModelRenderer(ModRegistry.MOB_HEAD_BLOCK.value(),
                new MobHeadSpecialRenderer.Unbaked());
        context.registerSpecialBlockModelRenderer(ModRegistry.MOB_WALL_HEAD_BLOCK.value(),
                new MobHeadSpecialRenderer.Unbaked());
    }
}
