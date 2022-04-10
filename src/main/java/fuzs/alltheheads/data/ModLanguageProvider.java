package fuzs.alltheheads.data;

import fuzs.alltheheads.AllTheHeads;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator gen, String modid) {
        super(gen, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("block.alltheheads.mob_head", "Mob Head");
        this.add("block.alltheheads.mob_wall_head", "Mob Wall Head");
        this.add("block.alltheheads.wall", "%s Wall");
        this.add("block.alltheheads.head", "%s Head");
        this.add("block.alltheheads.skull", "%s Skull");
        this.add("itemGroup.alltheheads", AllTheHeads.MOD_NAME);
        this.add("itemGroup.alltheheadsvillagers", AllTheHeads.MOD_NAME + " - Villagers");
    }
}
