package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.client.model.*;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.client.renderer.special.MobHeadSpecialRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.*;
import fuzs.puzzleslib.api.client.event.v1.renderer.AddLivingEntityRenderLayersCallback;
import fuzs.puzzleslib.api.client.event.v1.renderer.ExtractRenderStateCallback;
import fuzs.puzzleslib.api.client.event.v1.renderer.RenderLivingEvents;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
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
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.MOB),
                () -> MobHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.MOB_OVERLAY),
                () -> MobHeadModel.createHeadLayer(new CubeDeformation(0.25F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HUMANOID),
                () -> HumanoidHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HUMANOID_OVERLAY),
                () -> HumanoidHeadModel.createHeadLayer(new CubeDeformation(0.25F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ENDERMAN),
                EndermanHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ENDERMAN_EYES),
                EndermanHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BOGGED),
                BoggedHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SPIDER),
                SpiderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SPIDER_EYES),
                SpiderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TEMPERATE_COW),
                CowHeadModel::createTemperateHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.WARM_COW),
                CowHeadModel::createWarmHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.COLD_COW),
                CowHeadModel::createColdHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.FELINE),
                FelineHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SHEEP),
                SheepHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SHEEP_WOOL),
                SheepHeadModel::createWoolHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.VILLAGER),
                VillagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ILLAGER),
                IllagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.WITCH),
                WitchHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.VEX),
                VexHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.RAVAGER),
                RavagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SQUID),
                SquidHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.CHICKEN),
                ChickenHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.COLD_CHICKEN),
                ChickenHeadModel::createColdHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PIG),
                PigHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.AXOLOTL),
                AxolotlHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.DOLPHIN),
                DolphinHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SLIME),
                SlimeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SLIME_GEL),
                SlimeHeadModel::createGelHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.MAGMA_CUBE),
                MagmaCubeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.GOAT),
                GoatHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.LLAMA),
                () -> LlamaHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.LLAMA_DECOR),
                () -> LlamaHeadModel.createHeadLayer(new CubeDeformation(0.5F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TURTLE),
                TurtleHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BAT),
                BatHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ALLAY),
                AllayHeadModel::createHeadLayer);
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
