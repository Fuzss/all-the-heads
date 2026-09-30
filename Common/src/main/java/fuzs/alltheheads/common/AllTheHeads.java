package fuzs.alltheheads.common;

import fuzs.alltheheads.common.config.CommonConfig;
import fuzs.alltheheads.common.handler.HeadBehaviorHandler;
import fuzs.alltheheads.common.handler.HeadLootHandler;
import fuzs.alltheheads.common.init.ModLootTables;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.DataPackRegistriesContext;
import fuzs.puzzleslib.common.api.event.v1.entity.living.LivingDropsCallback;
import fuzs.puzzleslib.common.api.event.v1.server.LootTableLoadCallback;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllTheHeads implements ModConstructor {
    public static final String MOD_ID = "alltheheads";
    public static final String MOD_NAME = "All The Heads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).common(CommonConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        LootTableLoadCallback.EVENT.register(ModLootTables::onLootTableLoad);
        LootTableLoadCallback.EVENT.register(HeadLootHandler::onLootTableLoad);
        LivingDropsCallback.EVENT.register(HeadLootHandler::onLivingDrops);
    }

    @Override
    public void onRegisterDataPackRegistries(DataPackRegistriesContext context) {
        context.registerSyncedRegistry(ModRegistry.HEAD_REGISTRY_KEY,
                HeadType.DIRECT_CODEC,
                HeadType.DIRECT_NETWORK_CODEC);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
