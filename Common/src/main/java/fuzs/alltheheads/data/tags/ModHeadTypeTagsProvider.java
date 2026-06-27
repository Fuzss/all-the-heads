package fuzs.alltheheads.data.tags;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.puzzleslib.api.data.v3.tags.AbstractTagAppender;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.stream.Stream;

public class ModHeadTypeTagsProvider extends AbstractTagProvider<HeadType> {

    public ModHeadTypeTagsProvider(DataProviderContext context) {
        super(ModRegistry.HEAD_REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        AbstractTagAppender<HeadType> villagerLikeTagAppender = this.tag(ModRegistry.VILLAGER_LIKE_HEAD_TYPE_TAG);
        registries.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .forEach((Holder.Reference<HeadType> headType) -> {
                    if (headType.value().getEntityType() == EntityType.VILLAGER
                            || headType.value().getEntityType() == EntityType.ZOMBIE_VILLAGER) {
                        villagerLikeTagAppender.add(headType);
                    }
                });
        registries.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY).listElements().forEach(headType -> {
            getDefaultLootTables(headType.value().getEntityTypes()).map(ModHeadTypeTagsProvider::getHeadTypeTagKey)
                    .map(this::tag)
                    .forEach((AbstractTagAppender<HeadType> tagAppender) -> {
                        tagAppender.add(headType);
                    });
        });
    }

    public static Stream<ResourceKey<LootTable>> getDefaultLootTables(Stream<EntityType<?>> stream) {
        return stream.map(EntityType::getDefaultLootTable).distinct();
    }

    public static TagKey<HeadType> getHeadTypeTagKey(ResourceKey<LootTable> resourceKey) {
        String path = resourceKey.location().toString().replace(':', '/');
        return TagKey.create(ModRegistry.HEAD_REGISTRY_KEY, AllTheHeads.id(path));
    }
}
