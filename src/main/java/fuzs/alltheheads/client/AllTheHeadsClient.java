package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.ModSkullModel;
import fuzs.alltheheads.client.registry.ModClientRegistry;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.registry.SkullManager;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AllTheHeads.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AllTheHeadsClient {
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent evt) {
        BlockEntityRenderers.register(ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.get(), SkullBlockRenderer::new);
        for (ModSkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
            SkullBlockRenderer.SKIN_BY_TYPE.put(skullType, skullType.getTextureLocation());
        }
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(ModClientRegistry.PIGLIN_HEAD_MODEL_LAYER_LOCATION, () -> LayerDefinition.create(PiglinModel.createMesh(CubeDeformation.NONE), 64, 64));
    }

    @SubscribeEvent
    public static void onCreateSkullModels(final EntityRenderersEvent.CreateSkullModels evt) {
        evt.registerSkullModel(ModRegistry.PIGLIN_SKULL_BLOCK_TYPE, new ModSkullModel(evt.getEntityModelSet().bakeLayer(ModClientRegistry.PIGLIN_HEAD_MODEL_LAYER_LOCATION)));
    }
}
