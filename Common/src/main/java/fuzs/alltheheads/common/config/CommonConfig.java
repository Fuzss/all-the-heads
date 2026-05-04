package fuzs.alltheheads.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;

public class CommonConfig implements ConfigCore {
    @Config(description = "Drop vanilla mob heads with the same rate as wither skeleton skulls.")
    public boolean vanillaHeadDrops = true;
}
