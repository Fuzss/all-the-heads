package fuzs.alltheheads.init;

import fuzs.alltheheads.init.headtype.*;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class HeadTypes {

    public static void bootstrap(BootstrapContext<HeadType> context) {
        AnimalHeadType.bootstrap(context);
        AquaticHeadType.bootstrap(context);
        AxolotlHeadType.bootstrap(context);
        BeeHeadType.bootstrap(context);
        CatHeadType.bootstrap(context);
        ChickenHeadType.bootstrap(context);
        CopperGolemHeadType.bootstrap(context);
        CowHeadType.bootstrap(context);
        FoxHeadType.bootstrap(context);
        FrogHeadType.bootstrap(context);
        GhastHeadType.bootstrap(context);
        GuardianHeadType.bootstrap(context);
        HoglinHeadType.bootstrap(context);
        HorseHeadType.bootstrap(context);
        IllagerHeadType.bootstrap(context);
        LlamaHeadType.bootstrap(context);
        MonsterHeadType.bootstrap(context);
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
        return register(ResourceLocationHelper.withDefaultNamespace(path));
    }

    public static ResourceKey<HeadType> register(ResourceLocation resourceLocation) {
        return ModRegistry.REGISTRIES.makeResourceKey(ModRegistry.HEAD_REGISTRY_KEY,
                resourceLocation.toString().replace(':', '/'));
    }
}
