package fuzs.alltheheads.neoforge.client;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.client.AllTheHeadsClient;
import fuzs.alltheheads.common.data.client.ModLanguageProvider;
import fuzs.alltheheads.common.data.client.ModModelProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = AllTheHeads.MOD_ID, dist = Dist.CLIENT)
public class AllTheHeadsNeoForgeClient {

    public AllTheHeadsNeoForgeClient() {
        ClientModConstructor.construct(AllTheHeads.MOD_ID, AllTheHeadsClient::new);
        DataProviderHelper.registerDataProviders(AllTheHeads.MOD_ID, ModLanguageProvider::new, ModModelProvider::new);
    }
}
