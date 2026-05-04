package fuzs.alltheheads.fabric;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class AllTheHeadsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(AllTheHeads.MOD_ID, AllTheHeads::new);
    }
}
