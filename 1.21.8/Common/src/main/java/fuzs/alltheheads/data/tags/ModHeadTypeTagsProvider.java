package fuzs.alltheheads.data.tags;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagAppender;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;

public class ModHeadTypeTagsProvider extends AbstractTagProvider<HeadType> {

    public ModHeadTypeTagsProvider(DataProviderContext context) {
        super(ModRegistry.HEAD_REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        AbstractTagAppender<HeadType> tagAppender = this.tag(ModRegistry.VILLAGER_LIKE_HEAD_TYPE_TAG);
        registries.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .forEach((Holder.Reference<HeadType> headType) -> {
                    if (headType.value().getEntityType() == EntityType.VILLAGER
                            || headType.value().getEntityType() == EntityType.ZOMBIE_VILLAGER) {
                        tagAppender.add(headType);
                    }
                });
    }
}
