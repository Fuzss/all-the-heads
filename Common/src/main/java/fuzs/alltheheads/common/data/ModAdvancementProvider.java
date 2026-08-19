package fuzs.alltheheads.common.data;

import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.init.headtype.MonsterHeadType;
import fuzs.alltheheads.common.world.item.MobHeadItem;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.SharedConstants;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModAdvancementProvider extends AbstractAdvancementProvider {
    private static final Comparator<Holder<?>> HOLDER_COMPARATOR = Comparator.comparing((Holder<?> holder) -> holder.unwrapKey()
            .orElseThrow()
            .registry()).thenComparing((Holder<?> holder) -> holder.unwrapKey().orElseThrow().identifier());
    public static final AdvancementToken ROOT = new AdvancementToken(AllTheHeads.id("root"));
    public static final String KILL_DESCRIPTION_KEY = AllTheHeads.id("kill")
            .toLanguageKey("advancements", "description");
    public static final String OBTAIN_DESCRIPTION_KEY = AllTheHeads.id("obtain")
            .toLanguageKey("advancements", "description");

    public ModAdvancementProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addAdvancements(HolderLookup.Provider context, Consumer<AdvancementHolder> writer) {
        HolderLookup.RegistryLookup<Item> items = context.lookupOrThrow(Registries.ITEM);
        Map<Holder<EntityType<?>>, List<Holder.Reference<HeadType>>> headTypes = this.getHeadTypesByEntityType(context);
        Map<EntityType<?>, Holder.Reference<Item>> spawnEggs = this.gatherAllSpawnEggs(context);
        Map<String, Criterion<?>> rootCriteria = new LinkedHashMap<>();
        for (Map.Entry<Holder<EntityType<?>>, List<Holder.Reference<HeadType>>> entry : headTypes.entrySet()) {
            EntityType<?> entityType = entry.getKey().value();
            Holder.Reference<Item> item = spawnEggs.get(entityType);
            if (item != null) {
                Identifier entityId = entry.getKey().unwrapKey().orElseThrow().identifier();
                Identifier baseId = AllTheHeads.id("root/" + entityId.getNamespace() + "/" + entityId.getPath());
                Identifier parentId = baseId;
                Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
                for (Holder.Reference<HeadType> holder : entry.getValue()) {
                    Component displayName = holder.value()
                            .getName(ModRegistry.MOB_HEAD_ITEM.value().getDescriptionId());
                    Advancement.Builder builder = Advancement.Builder.advancement()
                            .display(display(MobHeadItem.createTemplate(holder),
                                    displayName,
                                    Component.translatable(OBTAIN_DESCRIPTION_KEY, displayName)).build());
                    builder.parent = Optional.of(parentId);
                    builder.addCriterion(holder.key().identifier().getPath(),
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                    .of(items, ModRegistry.MOB_HEAD_ITEM.value())
                                    .withComponents(DataComponentMatchers.Builder.components()
                                            .exact(DataComponentExactPredicate.expect(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(),
                                                    holder))
                                            .build())));
                    parentId = holder.key().identifier();
                    AdvancementHolder advancement = builder.save(writer, parentId.toString());
                    criteria.putAll(advancement.value().criteria());
                }

                Component entityName = entityType.getDescription();
                Advancement.Builder builder = Advancement.Builder.advancement()
                        .display(display(item.value(),
                                entityName,
                                Component.translatable(KILL_DESCRIPTION_KEY, entityName)).build());
                builder.parent = Optional.of(ROOT.id());
                for (Map.Entry<String, Criterion<?>> criterion : criteria.entrySet()) {
                    builder.addCriterion(criterion.getKey(), criterion.getValue());
                }

                builder.save(writer, baseId.toString());
                rootCriteria.putAll(criteria);
            }
        }

        Advancement.Builder builder = Advancement.Builder.advancement()
                .display(display(MobHeadItem.createTemplate(context, MonsterHeadType.BLAZE), ROOT).setBackground(
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/stone")).build());
        for (Map.Entry<String, Criterion<?>> criterion : rootCriteria.entrySet()) {
            builder.addCriterion(criterion.getKey(), criterion.getValue());
        }

        builder.save(writer, ROOT.name());
    }

    private Map<Holder<EntityType<?>>, List<Holder.Reference<HeadType>>> getHeadTypesByEntityType(HolderLookup.Provider context) {
        return context.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY)
                .listElements()
                .sorted(HOLDER_COMPARATOR)
                .mapMulti((Holder.Reference<HeadType> headType, Consumer<Map.Entry<Holder<EntityType<?>>, Holder.Reference<HeadType>>> consumer) -> {
                    headType.value().getEntityTypes().forEach((EntityType<?> entityType) -> {
                        consumer.accept(Map.entry(entityType.builtInRegistryHolder(), headType));
                    });
                })
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        LinkedHashMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private Map<EntityType<?>, Holder.Reference<Item>> gatherAllSpawnEggs(HolderLookup.Provider context) {
        List<DataComponentInitializers.PendingComponents<?>> initializers = this.buildInitializers(context);
        Map<EntityType<?>, Holder.Reference<Item>> spawnEggs = new HashMap<>();
        for (DataComponentInitializers.PendingComponents<?> initializer : initializers) {
            if (initializer.key() == Registries.ITEM) {
                initializer.forEach((holder, components) -> {
                    if (components.has(DataComponents.ENTITY_DATA)) {
                        spawnEggs.put(components.get(DataComponents.ENTITY_DATA).type(),
                                (Holder.Reference<Item>) holder);
                    }
                });
            }
        }

        return spawnEggs;
    }

    private List<DataComponentInitializers.PendingComponents<?>> buildInitializers(HolderLookup.Provider context) {
        boolean isRunningInIde = SharedConstants.IS_RUNNING_IN_IDE;
        SharedConstants.IS_RUNNING_IN_IDE = false;
        List<DataComponentInitializers.PendingComponents<?>> initializers = BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.build(
                context);
        SharedConstants.IS_RUNNING_IN_IDE = isRunningInIde;
        return initializers;
    }
}
