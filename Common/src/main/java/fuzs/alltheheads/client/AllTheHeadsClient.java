package fuzs.alltheheads.client;

import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.client.model.*;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.client.renderer.special.MobHeadSpecialRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.api.client.core.v1.context.BuiltinModelItemRendererContext;
import fuzs.puzzleslib.api.client.core.v1.context.LayerDefinitionsContext;
import fuzs.puzzleslib.api.client.core.v1.context.SkullRenderersContext;
import fuzs.puzzleslib.api.client.event.v1.renderer.AddLivingEntityRenderLayersCallback;
import fuzs.puzzleslib.api.client.event.v1.renderer.RenderLivingEvents;
import fuzs.puzzleslib.api.core.v1.ContentRegistrationFlags;
import net.minecraft.client.model.PiglinHeadModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.resources.DefaultPlayerSkin;

public class AllTheHeadsClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
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
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ALLAY),
                AllayHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ARMADILLO),
                ArmadilloHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.AXOLOTL),
                AxolotlHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BAT),
                BatHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BEE),
                BeeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BOGGED),
                BoggedHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BREEZE),
                BreezeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.BREEZE_EYES),
                BreezeHeadModel::createEyesLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.CAMEL),
                CamelHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.CHICKEN),
                ChickenHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.COD),
                CodHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.CREEPER_CHARGE),
                () -> MobHeadModel.createHeadLayer(new CubeDeformation(1.0F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.DOLPHIN),
                DolphinHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ENDERMAN),
                EndermanHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ENDERMAN_EYES),
                EndermanHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ENDERMITE),
                EndermiteHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.FELINE),
                FelineHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.FOX),
                FoxHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.FROG),
                FrogHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.GHAST),
                GhastHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.GOAT),
                GoatHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.GUARDIAN),
                GuardianHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HOGLIN),
                HoglinHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HORSE),
                HorseHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HORSE_MARKINGS),
                HorseHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HUMANOID),
                () -> HumanoidHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.HUMANOID_OVERLAY),
                () -> HumanoidHeadModel.createHeadLayer(new CubeDeformation(0.25F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.ILLAGER),
                IllagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.IRON_GOLEM),
                IronGolemHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.LLAMA),
                () -> LlamaHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.LLAMA_DECOR),
                () -> LlamaHeadModel.createHeadLayer(new CubeDeformation(0.5F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.MAGMA_CUBE),
                MagmaCubeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.MOB),
                () -> MobHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.MOB_OVERLAY),
                () -> MobHeadModel.createHeadLayer(new CubeDeformation(0.25F)));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PANDA),
                PandaHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PARROT),
                ParrotHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PHANTOM),
                PhantomHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PHANTOM_EYES),
                PhantomHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PIG),
                PigHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PIGLIN),
                () -> LayerDefinition.create(PiglinHeadModel.createHeadModel(), 64, 64));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.POLAR_BEAR),
                PolarBearHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.PUFFERFISH),
                PufferfishHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.RABBIT),
                RabbitHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.RAVAGER),
                RavagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SALMON),
                SalmonHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SHEEP),
                SheepHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SHEEP_WOOL),
                SheepHeadModel::createWoolHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SHULKER),
                ShulkerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SILVERFISH),
                SilverfishHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SLIME),
                SlimeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SLIME_GEL),
                SlimeHeadModel::createGelHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SNIFFER),
                SnifferHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SPIDER),
                SpiderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SPIDER_EYES),
                SpiderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.SQUID),
                SquidHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.STRIDER),
                StriderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TADPOLE),
                TadpoleHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TEMPERATE_COW),
                CowHeadModel::createTemperateHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TROPICAL_FISH_LARGE),
                TropicalFishHeadModel::createLargeHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TROPICAL_FISH_SMALL),
                TropicalFishHeadModel::createSmallHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.TURTLE),
                TurtleHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.VEX),
                VexHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.VILLAGER),
                VillagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.WARDEN),
                WardenHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.WITCH),
                WitchHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.WITHER_SHIELD),
                () -> HumanoidHeadModel.createHeadLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION));
        context.registerLayerDefinition(MobHeadBlockRenderer.createModelLayer(ModelType.WOLF),
                WolfHeadModel::createHeadLayer);
    }

    @Override
    public void onRegisterSkullRenderers(SkullRenderersContext context) {
        context.registerSkullRenderer(ModRegistry.MOB_SKULL_BLOCK_TYPE,
                DefaultPlayerSkin.getDefaultTexture(),
                (EntityModelSet entityModelSet) -> new SkullModel(entityModelSet.bakeLayer(ModelLayers.PLAYER_HEAD)));
    }

    @Override
    public void onRegisterBuiltinModelItemRenderers(BuiltinModelItemRendererContext context) {
        context.registerItemRenderer(ModRegistry.MOB_HEAD_ITEM.value(), new MobHeadSpecialRenderer.Unbaked());
    }

    @Override
    public ContentRegistrationFlags[] getContentRegistrationFlags() {
        return new ContentRegistrationFlags[]{ContentRegistrationFlags.DYNAMIC_RENDERERS};
    }
}
