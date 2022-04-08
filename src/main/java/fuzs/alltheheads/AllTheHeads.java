package fuzs.alltheheads;

import fuzs.alltheheads.config.ClientConfig;
import fuzs.alltheheads.config.ServerConfig;
import fuzs.alltheheads.data.ModLanguageProvider;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.config.ConfigHolderImpl;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(AllTheHeads.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AllTheHeads {
    public static final String MOD_ID = "alltheheads";
    public static final String MOD_NAME = "All The Heads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder<ClientConfig, ServerConfig> CONFIG = ConfigHolder.of(() -> new ClientConfig(), () -> new ServerConfig());

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ((ConfigHolderImpl<?, ?>) CONFIG).addConfigs(MOD_ID);
        ModRegistry.touch();
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        final ExistingFileHelper existingFileHelper = evt.getExistingFileHelper();
        generator.addProvider(new ModLanguageProvider(generator, MOD_ID));
    }
}
