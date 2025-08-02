package fuzs.alltheheads.neoforge;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.data.loot.ModBlockLootProvider;
import fuzs.alltheheads.data.loot.ModEntityLootProvider;
import fuzs.alltheheads.data.tags.ModBlockTagsProvider;
import fuzs.alltheheads.data.tags.ModHeadTypeTagsProvider;
import fuzs.alltheheads.data.tags.ModItemTagsProvider;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
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
