package fuzs.alltheheads.data.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.init.headtype.*;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.resources.ResourceKey;

import java.util.function.BiConsumer;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModRegistry.CREATIVE_MODE_TAB.value(), AllTheHeads.MOD_NAME);
        translationBuilder.add(ModRegistry.VILLAGER_CREATIVE_MODE_TAB.value(), "All The Villagers");
        translationBuilder.add(ModRegistry.MOB_HEAD_BLOCK.value(), "Mob Head");

        BiConsumer<ResourceKey<HeadType>, String> translationConsumer = (ResourceKey<HeadType> resourceKey, String value) -> {
            String translationKey = HeadType.customName(resourceKey)
                    .toLanguageKey(ModRegistry.MOB_HEAD_BLOCK.value().getDescriptionId());
            translationBuilder.add(translationKey, value);
        };

        AnimalHeadType.registerTranslations(translationConsumer);
        AquaticHeadType.registerTranslations(translationConsumer);
        AxolotlHeadType.registerTranslations(translationConsumer);
        BeeHeadType.registerTranslations(translationConsumer);
        CatHeadType.registerTranslations(translationConsumer);
        ChickenHeadType.registerTranslations(translationConsumer);
        CopperGolemHeadType.registerTranslations(translationConsumer);
        CowHeadType.registerTranslations(translationConsumer);
        FoxHeadType.registerTranslations(translationConsumer);
        FrogHeadType.registerTranslations(translationConsumer);
        GhastHeadType.registerTranslations(translationConsumer);
        GuardianHeadType.registerTranslations(translationConsumer);
        HoglinHeadType.registerTranslations(translationConsumer);
        HorseHeadType.registerTranslations(translationConsumer);
        IllagerHeadType.registerTranslations(translationConsumer);
        LlamaHeadType.registerTranslations(translationConsumer);
        MonsterHeadType.registerTranslations(translationConsumer);
        PandaHeadType.registerTranslations(translationConsumer);
        ParrotHeadType.registerTranslations(translationConsumer);
        PigHeadType.registerTranslations(translationConsumer);
        PiglinHeadType.registerTranslations(translationConsumer);
        RabbitHeadType.registerTranslations(translationConsumer);
        SheepHeadType.registerTranslations(translationConsumer);
        SpiderHeadType.registerTranslations(translationConsumer);
        StriderHeadType.registerTranslations(translationConsumer);
        TropicalFishHeadType.registerTranslations(translationConsumer);
        VexHeadType.registerTranslations(translationConsumer);
        VillagerHeadType.registerTranslations(translationConsumer);
        WitherHeadType.registerTranslations(translationConsumer);
        WolfHeadType.registerTranslations(translationConsumer);
        ZombieVillagerHeadType.registerTranslations(translationConsumer);
    }
}
