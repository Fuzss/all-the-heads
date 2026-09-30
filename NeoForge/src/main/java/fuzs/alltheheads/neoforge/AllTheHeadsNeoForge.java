package fuzs.alltheheads.neoforge;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.data.HeadTypesValidator;
import fuzs.alltheheads.common.data.advancements.ModAdvancementProvider;
import fuzs.alltheheads.common.data.loot.ModBlockLootProvider;
import fuzs.alltheheads.common.data.loot.ModEntityLootProvider;
import fuzs.alltheheads.common.data.tags.ModBlockTagsProvider;
import fuzs.alltheheads.common.data.tags.ModHeadTypeTagsProvider;
import fuzs.alltheheads.common.data.tags.ModItemTagsProvider;
import fuzs.alltheheads.common.init.HeadTypes;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(AllTheHeads.MOD_ID)
public class AllTheHeadsNeoForge {

    public AllTheHeadsNeoForge() {
        ModConstructor.construct(AllTheHeads.MOD_ID, AllTheHeads::new);
        DataProviderBuilder.of(AllTheHeads.MOD_ID)
                .addWorldBootstrap(ModRegistry.HEAD_REGISTRY_KEY, HeadTypes::bootstrapHeadTypes)
                .addReloadableBootstrap(Registries.PREDICATE, HeadTypes::bootstrapLootItemConditions)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addLootProvider(ModEntityLootProvider::new, LootContextParamSets.ENTITY)
                .addProvider(ModBlockTagsProvider::new,
                        ModItemTagsProvider::new,
                        ModHeadTypeTagsProvider::new,
                        HeadTypesValidator::new)
                .addAdvancementProvider(ModAdvancementProvider::new);
    }
}
