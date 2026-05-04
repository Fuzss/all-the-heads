package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.client.model.*;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadRenderer;
import fuzs.alltheheads.client.renderer.special.MobHeadSpecialRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.*;
import fuzs.puzzleslib.common.api.client.event.v1.renderer.AddLivingEntityRenderLayersCallback;
import fuzs.puzzleslib.common.api.client.event.v1.renderer.ExtractEntityRenderStateCallback;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.skull.PiglinHeadModel;
import net.minecraft.client.model.object.skull.SkullModel;
import net.minecraft.client.resources.DefaultPlayerSkin;

public class AllTheHeadsClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ExtractEntityRenderStateCallback.EVENT.register(CustomHeadLayerHandler::onExtractEntityRenderState);
        AddLivingEntityRenderLayersCallback.EVENT.register(CustomHeadLayerHandler::addLivingEntityRenderLayers);
    }

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.value(), MobHeadRenderer::new);
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.ALLAY),
                AllayHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.ARMADILLO),
                ArmadilloHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.AXOLOTL),
                AxolotlHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.BAT), BatHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.BEE), BeeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.BOGGED),
                BoggedHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.BREEZE),
                BreezeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.BREEZE_EYES),
                BreezeHeadModel::createEyesLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.CAMEL),
                CamelHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.CHICKEN),
                ChickenHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.COLD_CHICKEN),
                ChickenHeadModel::createColdHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.COLD_COW),
                CowHeadModel::createColdHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.COD), CodHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.COPPER_GOLEM),
                CopperGolemHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.COPPER_GOLEM_EYES),
                CopperGolemHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.CREAKING),
                CreakingHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.CREAKING_EYES),
                CreakingHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.CREEPER_CHARGE),
                () -> MobHeadModel.createHeadLayer(new CubeDeformation(1.0F)));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.DOLPHIN),
                DolphinHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.ENDERMAN),
                EndermanHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.ENDERMAN_EYES),
                EndermanHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.ENDERMITE),
                EndermiteHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.FELINE),
                FelineHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.FOX), FoxHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.FROG),
                FrogHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.GHAST),
                GhastHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.GOAT),
                GoatHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.GUARDIAN),
                GuardianHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.HAPPY_GHAST),
                HappyGhastHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.HOGLIN),
                HoglinHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.HORSE),
                HorseHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.HORSE_MARKINGS),
                HorseHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.HUMANOID),
                () -> HumanoidHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.HUMANOID_OVERLAY),
                () -> HumanoidHeadModel.createHeadLayer(new CubeDeformation(0.25F)));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.ILLAGER),
                IllagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.IRON_GOLEM),
                IronGolemHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.LLAMA),
                () -> LlamaHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.LLAMA_DECOR),
                () -> LlamaHeadModel.createHeadLayer(new CubeDeformation(0.5F)));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.MAGMA_CUBE),
                MagmaCubeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.MOB),
                () -> MobHeadModel.createHeadLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.MOB_OVERLAY),
                () -> MobHeadModel.createHeadLayer(new CubeDeformation(0.25F)));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.NAUTILUS),
                NautilusHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PANDA),
                PandaHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PARCHED),
                ParchedHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PARROT),
                ParrotHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PHANTOM),
                PhantomHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PHANTOM_EYES),
                PhantomHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PIG), PigHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PIGLIN),
                () -> LayerDefinition.create(PiglinHeadModel.createHeadModel(), 64, 64));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.POLAR_BEAR),
                PolarBearHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.PUFFERFISH),
                PufferfishHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.RABBIT),
                RabbitHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.RAVAGER),
                RavagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SALMON),
                SalmonHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SHEEP),
                SheepHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SHEEP_WOOL),
                SheepHeadModel::createWoolHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SHULKER),
                ShulkerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SILVERFISH),
                SilverfishHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SLIME),
                SlimeHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SLIME_GEL),
                SlimeHeadModel::createGelHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SNIFFER),
                SnifferHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SPIDER),
                SpiderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SPIDER_EYES),
                SpiderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.SQUID),
                SquidHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.STRIDER),
                StriderHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.TADPOLE),
                TadpoleHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.TEMPERATE_COW),
                CowHeadModel::createTemperateHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.TROPICAL_FISH_LARGE),
                TropicalFishHeadModel::createLargeHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.TROPICAL_FISH_SMALL),
                TropicalFishHeadModel::createSmallHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.TURTLE),
                TurtleHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.VEX), VexHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.VILLAGER),
                VillagerHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.WARDEN),
                WardenHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.WARM_COW),
                CowHeadModel::createWarmHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.WITCH),
                WitchHeadModel::createHeadLayer);
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.WITHER_SHIELD),
                () -> HumanoidHeadModel.createHeadLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION));
        context.registerLayerDefinition(MobHeadRenderer.createModelLayer(ModelType.WOLF),
                WolfHeadModel::createHeadLayer);
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
    public void onRegisterBuiltInBlockModels(BuiltInBlockModelsContext context) {
        context.registerModelFactory(ModRegistry.MOB_HEAD_BLOCK.value(),
                MobHeadSpecialRenderer.Unbaked.createMobHead());
        context.registerModelFactory(ModRegistry.MOB_WALL_HEAD_BLOCK.value(),
                MobHeadSpecialRenderer.Unbaked.createMobWallHead());
    }
}
