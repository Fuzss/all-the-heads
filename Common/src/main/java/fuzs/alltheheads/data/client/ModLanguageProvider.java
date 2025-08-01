package fuzs.alltheheads.data.client;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.init.HeadTypes;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.entity.EntityType;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModRegistry.CREATIVE_MODE_TAB.value(), AllTheHeads.MOD_NAME);
        translationBuilder.add(ModRegistry.VILLAGER_CREATIVE_MODE_TAB.value(), "All The Villagers");
        translationBuilder.add(ModRegistry.MOB_HEAD_BLOCK.value(), "Mob Head");
        translationBuilder.add(HeadType.createDescriptionId(EntityType.ENDERMAN, HeadTypes.ENDERMAN), "Enderman Head");
        translationBuilder.add(HeadType.createDescriptionId(EntityType.BLAZE, HeadTypes.BLAZE), "Blaze Head");
        translationBuilder.add(HeadType.createDescriptionId(EntityType.COW, HeadTypes.TEMPERATE_COW),
                "Temperate Cow Head");
        translationBuilder.add(HeadType.createDescriptionId(EntityType.COW, HeadTypes.WARM_COW), "Warm Cow Head");
        translationBuilder.add(HeadType.createDescriptionId(EntityType.COW, HeadTypes.COLD_COW), "Cold Cow Head");
        translationBuilder.add(HeadType.createDescriptionId(EntityType.OCELOT, HeadTypes.OCELOT), "Ocelot Head");
    }
}
