package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.ModSkullModel;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.registry.SkullManager;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
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
    public static void onCreateSkullModels(final EntityRenderersEvent.CreateSkullModels evt) {
        for (ModSkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
            Pair<ResourceLocation, String> modelLayerLocation = skullType.getModelLayerLocation();
            evt.registerSkullModel(skullType, new ModSkullModel(evt.getEntityModelSet().bakeLayer(new ModelLayerLocation(modelLayerLocation.left(), modelLayerLocation.right())), skullType.getHeadKey()));
        }
    }
}
