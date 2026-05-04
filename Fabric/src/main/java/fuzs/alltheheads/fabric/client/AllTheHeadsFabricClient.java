package fuzs.alltheheads.fabric.client;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.client.AllTheHeadsClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class AllTheHeadsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(AllTheHeads.MOD_ID, AllTheHeadsClient::new);
    }
}
