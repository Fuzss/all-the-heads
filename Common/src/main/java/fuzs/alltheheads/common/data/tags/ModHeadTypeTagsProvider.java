package fuzs.alltheheads.common.data.tags;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagAppender;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;
import java.util.stream.Stream;

public class ModHeadTypeTagsProvider extends AbstractTagsProvider<HeadType> {

    public ModHeadTypeTagsProvider(DataProviderContext context) {
        super(ModRegistry.HEAD_REGISTRY_KEY, context);
    }

    @Override
    public void addTags(HolderLookup.Provider context) {
        HolderGetter<LootItemCondition> predicates = context.lookupOrThrow(Registries.PREDICATE);
        AbstractTagAppender<HeadType> villagerLikeTagAppender = this.tag(ModRegistry.VILLAGER_LIKE_HEAD_TYPE_TAG);
        context.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .forEach((Holder.Reference<HeadType> headType) -> {
                    if (headType.value().getEntityType(predicates).is(EntityTypeIds.VILLAGER) || headType.value()
                            .getEntityType(predicates)
                            .is(EntityTypeIds.ZOMBIE_VILLAGER)) {
                        villagerLikeTagAppender.add(headType);
                    }
                });
        context.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .forEach((Holder.Reference<HeadType> headType) -> {
                    getDefaultLootTables(headType.value().getEntityTypes(predicates).map(Holder::value)).map(
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
