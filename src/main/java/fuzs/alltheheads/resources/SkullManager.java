package fuzs.alltheheads.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.world.item.ModStandingAndWallBlockItem;
import fuzs.alltheheads.world.level.block.ModSkullBlock;
import fuzs.alltheheads.world.level.block.ModWallSkullBlock;
import fuzs.puzzleslib.core.ModLoaderEnvironment;
import fuzs.puzzleslib.registry.RegistryManager;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();

    private Map<String, SkullType> skullTypesByKey;
    private List<Pair<RegistryObject<Block>, RegistryObject<Block>>> skullBlocks;
    private Map<EntityType<?>, List<SkullType>> skullTypesByEntity;
    private Map<ResourceLocation, List<SkullType>> skullTypesByLootTable;

    public Collection<SkullType> getAllSkullTypes() {
        this.dissolve();
        return this.skullTypesByKey.values().stream()
                .sorted(Comparator.<SkullType, String>comparing(skullType -> skullType.getMobType().getNamespace())
                        .thenComparing(skullType -> skullType.getMobType().getPath())
                        .thenComparing(SkullType::getVariant))
                .toList();
    }

    public SkullType getSkullType(String key) {
        return this.skullTypesByKey.get(key.indexOf(':') >= 0 ? key : "minecraft:" + key);
    }

    public void register(RegistryManager registry) {
        this.skullBlocks = Lists.newArrayList();
        for (SkullType skullType : this.getAllSkullTypes()) {
            Pair<RegistryObject<Block>, RegistryObject<Block>> pair = this.registerBlocks(registry, skullType);
            this.skullBlocks.add(pair);
            this.registerItem(registry, skullType, pair.left(), pair.right());
        }
    }

    public Block[] getAllSkullBlocks() {
        return this.skullBlocks.stream().mapMulti((Pair<RegistryObject<Block>, RegistryObject<Block>> pair, Consumer<RegistryObject<Block>> consumer) -> {
                    consumer.accept(pair.left());
                    consumer.accept(pair.right());
                }).map(RegistryObject::get)
                .toArray(Block[]::new);
    }

    public Optional<List<SkullType>> getSkullTypeByEntity(EntityType<?> entityType) {
        if (this.skullTypesByEntity == null) {
            this.skullTypesByEntity = this.getAllSkullTypes().stream().collect(Collectors.groupingBy(skullType -> skullType.entityType.get()));
        }
        return Optional.ofNullable(this.skullTypesByEntity.get(entityType));
    }

    public Optional<List<SkullType>> getSkullTypeByLootTable(ResourceLocation lootTable) {
        if (this.skullTypesByLootTable == null) {
            this.skullTypesByLootTable = this.getAllSkullTypes().stream()
                    .filter(SkullType::obtainableFromNormalDrops)
                    .collect(Collectors.groupingBy(SkullType::getMobLootTableId));
        }
        return Optional.ofNullable(this.skullTypesByLootTable.get(lootTable));
    }

    private void dissolve() {
        if (this.skullTypesByKey == null) {
            List<SkullType.Builder> builders = this.load();
            this.skullTypesByKey = builders.stream().map(SkullType.Builder::build)
                    .filter(skullType -> ModLoaderEnvironment.isModLoaded(skullType.getMobType().getNamespace()))
                    .collect(ImmutableMap.toImmutableMap(SkullType::getMappingKey, Function.identity()));
        }
    }

    private List<SkullType.Builder> load() {
        List<SkullType.Builder> builders = Lists.newArrayList();
        builders.add(new SkullType.Builder("piglin").skullSize(10.0F, 8.0F, 8.0F));
        builders.add(new SkullType.Builder("zombified_piglin").skullSize(10.0F, 8.0F, 8.0F));
        builders.add(new SkullType.Builder("piglin_brute").skullSize(10.0F, 8.0F, 8.0F));
        builders.add(new SkullType.Builder("cow").skullSize(8.0F, 8.0F, 6.0F));
        builders.add(new SkullType.Builder("villager").skullSize(8.0F, 10.0F, 8.0F));
        builders.add(new SkullType.Builder("enderman"));
        builders.add(new SkullType.Builder("blaze"));
        builders.add(new SkullType.Builder("spider"));
        builders.add(new SkullType.Builder("cave_spider"));
        builders.add(new SkullType.Builder("witch").skullSize(8.0F, 10.0F, 8.0F));
        builders.add(new SkullType.Builder("squid").skullSize(8.0F, 10.6667F, 8.0F));
        for (Axolotl.Variant variant : Axolotl.Variant.values()) {
            builders.add(new SkullType.Builder("axolotl").variant(variant.getName(), "{Variant:" + variant.getId() + "}").skullSize(8.0F, 5.0F, 5.0F));
        }
        builders.add(new SkullType.Builder("chicken").skullSize(8.0F, 12.0F, 6.0F));
        return builders;
    }

    private Pair<RegistryObject<Block>, RegistryObject<Block>> registerBlocks(RegistryManager registry, SkullType skullType) {
        RegistryObject<Block> headBlock = registry.registerBlock(skullType.getId(), () -> new ModSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        RegistryObject<Block> wallHeadBlock = registry.registerBlock(skullType.getWallId(), () -> new ModWallSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        return Pair.of(headBlock, wallHeadBlock);
    }

    private void registerItem(RegistryManager registry, SkullType skullType, RegistryObject<Block> headBlock, RegistryObject<Block> wallHeadBlock) {
        // we use a mixin for all skulls to add them to our custom creative tab, but still set this here so the correct tab shows on creative search tab tooltip
        registry.registerItem(skullType.getId(), () -> new ModStandingAndWallBlockItem(headBlock.get(), wallHeadBlock.get(), new Item.Properties().tab(ModRegistry.ALL_THE_HEADS_CREATIVE_TAB).rarity(Rarity.UNCOMMON)));
    }
}
