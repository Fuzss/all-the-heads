package fuzs.alltheheads.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;

public class CommonConfig implements ConfigCore {
    @Config(description = "Drop vanilla mob heads with the same rate as wither skeleton skulls.")
    public boolean vanillaHeadDrops = true;
}
