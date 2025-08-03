package fuzs.alltheheads.init;

import fuzs.alltheheads.world.item.component.headtype.HeadType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

public class HeadTypes {

    public static void bootstrap(BootstrapContext<HeadType> context) {
        MonsterHeadTypes.bootstrap(context);
        AnimalHeadTypes.bootstrap(context);
        HorseHeadTypes.bootstrap(context);
        SheepHeadTypes.bootstrap(context);
        WolfHeadTypes.bootstrap(context);
        VillagerHeadTypes.bootstrap(context);
        ZombieVillagerHeadTypes.bootstrap(context);
    }

    static ResourceKey<HeadType> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY, path);
    }
}
