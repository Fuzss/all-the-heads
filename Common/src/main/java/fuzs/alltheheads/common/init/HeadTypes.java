package fuzs.alltheheads.common.init;

import fuzs.alltheheads.common.init.headtype.*;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class HeadTypes {

    public static void bootstrap(BootstrapContext<HeadType> context) {
        AnimalHeadType.bootstrap(context);
        AquaticHeadType.bootstrap(context);
        AxolotlHeadType.bootstrap(context);
        BeeHeadType.bootstrap(context);
        CamelHeadType.bootstrap(context);
        FelineHeadType.bootstrap(context);
        ChickenHeadType.bootstrap(context);
        CopperGolemHeadType.bootstrap(context);
        CowHeadType.bootstrap(context);
        CubeMobHeadType.bootstrap(context);
        FoxHeadType.bootstrap(context);
        FrogHeadType.bootstrap(context);
        GhastHeadType.bootstrap(context);
        GuardianHeadType.bootstrap(context);
        HoglinHeadType.bootstrap(context);
        EquineHeadType.bootstrap(context);
        IllagerHeadType.bootstrap(context);
        LlamaHeadType.bootstrap(context);
        MonsterHeadType.bootstrap(context);
        NautilusHeadType.bootstrap(context);
        PandaHeadType.bootstrap(context);
        ParrotHeadType.bootstrap(context);
        PigHeadType.bootstrap(context);
        PiglinHeadType.bootstrap(context);
        RabbitHeadType.bootstrap(context);
        SheepHeadType.bootstrap(context);
        SpiderHeadType.bootstrap(context);
        StriderHeadType.bootstrap(context);
        TropicalFishHeadType.bootstrap(context);
        VexHeadType.bootstrap(context);
        VillagerHeadType.bootstrap(context);
        WitherHeadType.bootstrap(context);
        WolfHeadType.bootstrap(context);
        ZombieVillagerHeadType.bootstrap(context);
    }

    public static ResourceKey<HeadType> register(String path) {
        return register(Identifier.withDefaultNamespace(path));
    }

    public static ResourceKey<HeadType> register(Identifier identifier) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY,
                identifier.toString().replace(':', '/'));
    }
}
