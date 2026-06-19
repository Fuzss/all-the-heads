package fuzs.alltheheads.common.data.tags;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagAppender;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;
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
                    if (headType.value().getEntityType() == EntityTypes.VILLAGER
                            || headType.value().getEntityType() == EntityTypes.ZOMBIE_VILLAGER) {
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
        return stream.map(EntityType::getDefaultLootTable).mapMulti(Optional::ifPresent);
    }

    public static TagKey<HeadType> getHeadTypeTagKey(ResourceKey<LootTable> resourceKey) {
        String path = resourceKey.identifier().toString().replace(':', '/');
        return TagKey.create(ModRegistry.HEAD_REGISTRY_KEY, AllTheHeads.id(path));
    }
}
