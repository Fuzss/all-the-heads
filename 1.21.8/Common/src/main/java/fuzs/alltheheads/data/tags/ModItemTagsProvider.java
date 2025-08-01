package fuzs.alltheheads.data.tags;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

public class ModItemTagsProvider extends AbstractTagProvider<Item> {

    public ModItemTagsProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(ItemTags.SKULLS).add(ModRegistry.MOB_HEAD_ITEM.value());
        this.tag(ItemTags.NOTE_BLOCK_TOP_INSTRUMENTS).add(ModRegistry.MOB_HEAD_ITEM.value());
    }
}
