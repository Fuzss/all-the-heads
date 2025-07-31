package fuzs.alltheheads.data;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.ModSkullType;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator gen, String modid) {
        super(gen, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(ModRegistry.MOB_HEAD_BLOCK.get(), "Mob Head");
        this.add(ModRegistry.MOB_WALL_HEAD_BLOCK.get(), "Mob Wall Head");
        this.add(ModSkullType.HEAD_TRANSLATION_KEY, "%s Head");
        this.add(ModSkullType.SKULL_TRANSLATION_KEY, "%s Skull");
        this.add("itemGroup.alltheheads", AllTheHeads.MOD_NAME);
        this.add("itemGroup.alltheheadsvillagers", AllTheHeads.MOD_NAME + " - Villagers");
    }
}
