package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.model.ModSkullModel;
import fuzs.alltheheads.client.resources.ClientModSkullType;
import fuzs.alltheheads.client.resources.ClientSkullManager;
import fuzs.alltheheads.client.resources.SkullRenderLayer;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.server.packs.VirtualPackResources;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = AllTheHeads.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AllTheHeadsClient {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent evt) {
        BlockEntityRenderers.register(ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.get(), SkullBlockRenderer::new);
        SkullBlockRenderer.SKIN_BY_TYPE.put(ModRegistry.MOB_SKULL_BLOCK_TYPE, DefaultPlayerSkin.getDefaultSkin());
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        for (ClientModSkullType skullType : ClientSkullManager.INSTANCE.getSkullTypeClientData().values()) {
            evt.registerLayerDefinition(skullType.getModelLayerLocationId(), skullType::getLayerDefinition);
        }
        SkullRenderLayer.registerSheepFurLayerDefinitions(evt::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void onAddLayers(final EntityRenderersEvent.AddLayers evt) {
        SkullRenderLayer.createSheepFurHeadModels(evt.getEntityModels());
    }

    @SubscribeEvent
    public static void onCreateSkullModels(final EntityRenderersEvent.CreateSkullModels evt) {
        evt.registerSkullModel(ModRegistry.MOB_SKULL_BLOCK_TYPE, new SkullModel(evt.getEntityModelSet().bakeLayer(ModelLayers.PLAYER_HEAD)));
        for (Map.Entry<ModSkullType, ClientModSkullType> entry : ClientSkullManager.INSTANCE.getSkullTypeClientData().entrySet()) {
            ClientModSkullType skullType = entry.getValue();
            evt.registerSkullModel(entry.getKey(), new ModSkullModel(evt.getEntityModelSet().bakeLayer(skullType.getModelLayerLocationId()), skullType));
        }
    }

    @SubscribeEvent
    public static void onAddPackFinders(final AddPackFindersEvent evt) {
        // TODO allow for individual json model files per skull type
        if (false && evt.getPackType() == PackType.CLIENT_RESOURCES) {
            evt.addRepositorySource((Consumer<Pack> consumer, Pack.PackConstructor factory) -> {
                Path packIconPath = ModList.get().getModFileById(AllTheHeads.MOD_ID).getFile().findResource("mod_logo.png");
                Pack packInfo = Pack.create(AllTheHeads.MOD_ID, true, () -> new VirtualPackResources(AllTheHeads.MOD_NAME + " Resources", packIconPath, new TextComponent(AllTheHeads.MOD_DESCRIPTION), ClientSkullManager.INSTANCE.getBuiltInResourceData()), factory, Pack.Position.BOTTOM, PackSource.BUILT_IN);
                consumer.accept(packInfo);
            });
        }
    }
}
