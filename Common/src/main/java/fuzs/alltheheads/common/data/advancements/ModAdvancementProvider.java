package fuzs.alltheheads.common.data.advancements;

import com.google.common.collect.ImmutableMap;
import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.init.headtype.MonsterHeadType;
import fuzs.alltheheads.common.world.item.MobHeadItem;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.data.v3.advancements.AbstractAdvancementProvider;
import fuzs.puzzleslib.common.api.data.v3.advancements.AdvancementToken;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
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

    public ModAdvancementProvider(BootstrapContext<Advancement> output) {
        super(output);
    }

    @Override
    public void generate() {
        HolderGetter<Item> items = this.output.lookup(Registries.ITEM);
        Map<Holder<EntityType<?>>, List<Holder.Reference<HeadType>>> headTypes = this.getHeadTypesByEntityType();
        Map<EntityType<?>, Holder.Reference<Item>> spawnEggs = this.gatherAllSpawnEggs();
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
                    AdvancementHolder advancement = builder.save(this.output, parentId.toString());
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

                builder.save(this.output, baseId.toString());
                rootCriteria.putAll(criteria);
            }
        }

        Advancement.Builder builder = Advancement.Builder.advancement()
                .display(display(MobHeadItem.createTemplate(this.output.lookup(ModRegistry.HEAD_REGISTRY_KEY)
                        .getOrThrow(MonsterHeadType.BLAZE)), ROOT).setBackground(Identifier.withDefaultNamespace(
                        "gui/advancements/backgrounds/stone")).build());
        for (Map.Entry<String, Criterion<?>> criterion : rootCriteria.entrySet()) {
            builder.addCriterion(criterion.getKey(), criterion.getValue());
        }

        builder.save(this.output, ROOT.name());
    }

    private Map<Holder<EntityType<?>>, List<Holder.Reference<HeadType>>> getHeadTypesByEntityType() {
        return this.output.listContextElements(ModRegistry.HEAD_REGISTRY_KEY)
                .sorted(HOLDER_COMPARATOR)
                .mapMulti((Holder.Reference<HeadType> headType, Consumer<Map.Entry<Holder<EntityType<?>>, Holder.Reference<HeadType>>> consumer) -> {
                    Holder<EntityType<?>> holder = headType.value().entityType().orElseThrow();
                    consumer.accept(Map.entry(holder, headType));
                })
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        LinkedHashMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private Map<EntityType<?>, Holder.Reference<Item>> gatherAllSpawnEggs() {
        Map<ResourceKey<Item>, Holder.Reference<Item>> spawnEggsByKey = BuiltInRegistries.ITEM.listElements()
                .filter((Holder.Reference<Item> item) -> {
                    return item.value() instanceof SpawnEggItem;
                })
                .collect(Collectors.toMap(Holder.Reference::key, Function.identity()));
        ImmutableMap.Builder<EntityType<?>, Holder.Reference<Item>> spawnEggs = ImmutableMap.builder();
        List<DataComponentInitializers.InitializerEntry<?>> initializers = BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.initializers;
        for (DataComponentInitializers.InitializerEntry<?> initializer : initializers) {
            Holder.Reference<Item> item = spawnEggsByKey.get(initializer.key());
            if (item != null) {
                DataComponentMap.Builder builder = DataComponentMap.builder();
                // This is more or less safe to do here as spawn egg properties do not use the registry access.
                initializer.run(builder, RegistryAccess.EMPTY);
                TypedEntityData<EntityType<?>> data = builder.build().get(DataComponents.ENTITY_DATA);
                if (data != null) {
                    spawnEggs.put(data.type(), item);
                }
            }
        }

        return spawnEggs.build();
    }
}
