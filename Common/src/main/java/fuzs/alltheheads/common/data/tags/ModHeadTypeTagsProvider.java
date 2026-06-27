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
import net.minecraft.world.entity.EntityTypeIds;
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
                    if (headType.value().getEntityType().is(EntityTypeIds.VILLAGER) || headType.value()
                            .getEntityType()
                            .is(EntityTypeIds.ZOMBIE_VILLAGER)) {
                        villagerLikeTagAppender.add(headType);
                    }
                });
        registries.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .forEach((Holder.Reference<HeadType> headType) -> {
                    getDefaultLootTables(headType.value().getEntityTypes().map(Holder::value)).map(
                                    ModHeadTypeTagsProvider::getHeadTypeTagKey)
                            .map(this::tag)
                            .forEach((AbstractTagAppender<HeadType> tagAppender) -> {
                                tagAppender.add(headType);
                            });
                });
    }

    public static Stream<ResourceKey<LootTable>> getDefaultLootTables(Stream<EntityType<?>> stream) {
        return stream.map(EntityType::getDefaultLootTable)
                .<ResourceKey<LootTable>>mapMulti(Optional::ifPresent)
                .distinct();
    }

    public static TagKey<HeadType> getHeadTypeTagKey(ResourceKey<LootTable> resourceKey) {
        String path = resourceKey.identifier().toString().replace(':', '/');
        return TagKey.create(ModRegistry.HEAD_REGISTRY_KEY, AllTheHeads.id(path));
    }
}
