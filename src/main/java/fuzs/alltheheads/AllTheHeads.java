package fuzs.alltheheads;

import fuzs.alltheheads.data.ModLanguageProvider;
import fuzs.alltheheads.handler.MobLootHandler;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.resources.SkullType;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(AllTheHeads.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AllTheHeads {
    public static final String MOD_ID = "alltheheads";
    public static final String MOD_NAME = "All The Heads";
    public static final String MOD_DESCRIPTION = "WIP";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModRegistry.touch();
        registerHandlers();
        setupTemporaryConfig();
    }

    private static void setupTemporaryConfig() {
        // should be replaced with data driven system in the future, only that serializing model generation will be quite the effort
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Allows for disabling individual heads from being obtainable in survival (via mob drops). This will not remove blocks and items, they are still available in-game using cheats.");
        builder.push(MOD_ID);
        for (SkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
            skullType.setConfigSupplier(builder.define(skullType.getMappingKey(), true)::get);
        }
        builder.pop();
        // need to make this a common config instead of server as the value is used for injecting into loot tables, and that happens before server configs are loaded
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());
    }

    private static void registerHandlers() {
        MobLootHandler mobLootHandler = new MobLootHandler();
        MinecraftForge.EVENT_BUS.addListener(mobLootHandler::onLivingDrops);
        MinecraftForge.EVENT_BUS.addListener(mobLootHandler::onLivingVisibility);
        MinecraftForge.EVENT_BUS.addListener(mobLootHandler::onLootTableLoad);
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        final ExistingFileHelper existingFileHelper = evt.getExistingFileHelper();
        generator.addProvider(new ModLanguageProvider(generator, MOD_ID));
    }
}
