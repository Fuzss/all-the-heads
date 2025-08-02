package fuzs.alltheheads;

import fuzs.alltheheads.handler.HeadBehaviorHandler;
import fuzs.alltheheads.handler.HeadLootHandler;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.DataPackRegistriesContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingDropsCallback;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingVisibilityCallback;
import fuzs.puzzleslib.api.event.v1.server.LootTableLoadCallback;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllTheHeads implements ModConstructor {
    public static final String MOD_ID = "alltheheads";
    public static final String MOD_NAME = "All The Heads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        LootTableLoadCallback.EVENT.register(HeadLootHandler::onLootTableLoad);
        LivingDropsCallback.EVENT.register(HeadLootHandler::onLivingDrops);
        LivingVisibilityCallback.EVENT.register(HeadBehaviorHandler::onLivingVisibility);
    }

    @Override
    public void onRegisterDataPackRegistries(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(ModRegistry.HEAD_REGISTRY_KEY,
                HeadType.DIRECT_CODEC,
                HeadType.DIRECT_NETWORK_CODEC);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
