package fuzs.alltheheads.init;

import fuzs.alltheheads.init.headtype.*;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

public class HeadTypes {

    public static void bootstrap(BootstrapContext<HeadType> context) {
        MonsterHeadType.bootstrap(context);
        AnimalHeadType.bootstrap(context);
        HorseHeadType.bootstrap(context);
        SheepHeadType.bootstrap(context);
        WolfHeadType.bootstrap(context);
        VillagerHeadType.bootstrap(context);
        ZombieVillagerHeadType.bootstrap(context);
    }

    public static ResourceKey<HeadType> register(String path) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY, path);
    }
}
