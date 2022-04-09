package fuzs.alltheheads.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.handler.ModelBakeHandler;
import fuzs.alltheheads.client.model.ModSkullModel;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.registry.SkullManager;
import fuzs.alltheheads.server.packs.VirtualPackResources;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import java.nio.file.Path;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = AllTheHeads.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AllTheHeadsClient {
    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        registerHandlers();
    }

    private static void registerHandlers() {
        ModelBakeHandler modelBakeHandler = new ModelBakeHandler();
        MinecraftForge.EVENT_BUS.addListener(modelBakeHandler::onModelBake);
    }

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
            evt.registerSkullModel(skullType, new ModSkullModel(evt.getEntityModelSet().bakeLayer(new ModelLayerLocation(modelLayerLocation.left(), modelLayerLocation.right())), skullType.getModelPartHeadKey()));
        }
    }

//    @SubscribeEvent
//    public static void onModelBake(final ModelBakeEvent evt) {
//        for (Map.Entry<ResourceLocation, BakedModel> entry : evt.getModelRegistry().entrySet()) {
//            if (entry.getKey().getNamespace().equals(AllTheHeads.MOD_ID) && entry.getKey() instanceof ModelResourceLocation location && location.getVariant().equals("inventory")) {
//                BlockModel blockModel = new BlockModel(new ResourceLocation("item/template_skull"), Lists.newArrayList(), Maps.newHashMap(), true, null, ItemTransforms.NO_TRANSFORMS, Lists.newArrayList());
//                blockModel.name = location.toString();
//                BakedModel bakedModel = blockModel.bake(evt.getModelLoader(), evt.getModelLoader().getSpriteMap()::getSprite, BlockModelRotation.X0_Y0, location);
////                entry.setValue(bakedModel);
//                BakedModel value = evt.getModelRegistry().get(new ModelResourceLocation(new ResourceLocation("zombie_head"), "inventory"));
//                entry.setValue(value);
//            }
//
//
////            if (entry.getKey().toString().contains("piglin_head#inventory")) {
////                AllTheHeads.LOGGER.info("{}", entry);
////            }
//        }
//    }

    @SubscribeEvent
    public static void onAddPackFinders(final AddPackFindersEvent evt) {
        if (evt.getPackType() == PackType.CLIENT_RESOURCES) {
            evt.addRepositorySource((Consumer<Pack> consumer, Pack.PackConstructor factory) -> {
                Path packIconPath = ModList.get().getModFileById(AllTheHeads.MOD_ID).getFile().findResource("mod_logo.png");
                final Pack packInfo = Pack.create(AllTheHeads.MOD_ID, true, () -> new VirtualPackResources(AllTheHeads.MOD_NAME + " Resources", packIconPath, new TextComponent("This is my very awesome built-in resource pack!"), SkullManager.INSTANCE.getBuiltInResourceData()), factory, Pack.Position.BOTTOM, PackSource.BUILT_IN);
                consumer.accept(packInfo);
            });
        }
    }
}
