package fuzs.alltheheads.neoforge;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.data.loot.ModBlockLootProvider;
import fuzs.alltheheads.common.data.loot.ModEntityLootProvider;
import fuzs.alltheheads.common.data.tags.ModBlockTagsProvider;
import fuzs.alltheheads.common.data.tags.ModHeadTypeTagsProvider;
import fuzs.alltheheads.common.data.tags.ModItemTagsProvider;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(AllTheHeads.MOD_ID)
public class AllTheHeadsNeoForge {

    public AllTheHeadsNeoForge() {
        ModConstructor.construct(AllTheHeads.MOD_ID, AllTheHeads::new);
        DataProviderHelper.registerDataProviders(AllTheHeads.MOD_ID,
                ModRegistry.REGISTRY_SET_BUILDER,
                ModBlockLootProvider::new,
                ModEntityLootProvider::new,
                ModBlockTagsProvider::new,
                ModItemTagsProvider::new,
                ModHeadTypeTagsProvider::new);
    }
}
