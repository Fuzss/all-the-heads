package fuzs.alltheheads.common;

import com.google.common.collect.Sets;
import fuzs.alltheheads.common.config.CommonConfig;
import fuzs.alltheheads.common.handler.HeadBehaviorHandler;
import fuzs.alltheheads.common.handler.HeadLootHandler;
import fuzs.alltheheads.common.init.ModLootTables;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.ModLoaderEnvironment;
import fuzs.puzzleslib.common.api.core.v1.context.DataPackRegistriesContext;
import fuzs.puzzleslib.common.api.event.v1.entity.living.CalculateLivingVisibilityCallback;
import fuzs.puzzleslib.common.api.event.v1.entity.living.LivingDropsCallback;
import fuzs.puzzleslib.common.api.event.v1.server.LootTableLoadCallback;
import fuzs.puzzleslib.common.api.event.v1.server.TagsUpdatedCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AllTheHeads implements ModConstructor {
    public static final String MOD_ID = "alltheheads";
    public static final String MOD_NAME = "All The Heads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).common(CommonConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
        setupDevelopmentEnvironment();
    }

    private static void registerEventHandlers() {
        LootTableLoadCallback.EVENT.register(ModLootTables::onLootTableLoad);
        LootTableLoadCallback.EVENT.register(HeadLootHandler::onLootTableLoad);
        LivingDropsCallback.EVENT.register(HeadLootHandler::onLivingDrops);
        CalculateLivingVisibilityCallback.EVENT.register(HeadBehaviorHandler::onCalculateLivingVisibility);
    }

    private static void setupDevelopmentEnvironment() {
        if (!ModLoaderEnvironment.INSTANCE.isDevelopmentEnvironment(MOD_ID)) {
            return;
        }

        TagsUpdatedCallback.EVENT.register((HolderLookup.Provider registries, boolean isClientUpdate) -> {
            if (!isClientUpdate) {
                Set<ResourceKey<EntityType<?>>> mobEntities = BuiltInRegistries.ENTITY_TYPE.stream()
                        .filter((EntityType<?> entityType) -> entityType.getCategory() != MobCategory.MISC)
                        .map(BuiltInRegistries.ENTITY_TYPE::getResourceKey)
                        .<ResourceKey<EntityType<?>>>mapMulti(Optional::ifPresent)
                        .collect(Collectors.toSet());
                Set<ResourceKey<EntityType<?>>> headTypeEntities = registries.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                        .listElements()
                        .map(Holder.Reference::value)
                        .map(HeadType::getEntityType)
                        .distinct()
                        .map(BuiltInRegistries.ENTITY_TYPE::getResourceKey)
                        .<ResourceKey<EntityType<?>>>mapMulti(Optional::ifPresent)
                        .collect(Collectors.toSet());
                Sets.difference(mobEntities, headTypeEntities).forEach((ResourceKey<EntityType<?>> resourceKey) -> {
                    LOGGER.warn("Missing head type for {}", resourceKey);
                });
            }
        });
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
