package fuzs.alltheheads.resources;

import com.google.common.collect.ImmutableSet;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();

    private Set<SkullType> skullTypes;
    private List<Pair<RegistryObject<Block>, RegistryObject<Block>>> skullBlocks;
    private Map<EntityType<?>, List<SkullType>> skullTypesByEntity;
    private Map<ResourceLocation, List<SkullType>> skullTypesByLootTable;

    public Collection<SkullType> getAllSkullTypes() {
        this.dissolve();
        return this.skullTypes.stream()
                .filter(skullType -> ModLoaderEnvironment.isModLoaded(skullType.getMobType().getNamespace()))
                .sorted(Comparator.<SkullType, String>comparing(skullType -> skullType.getMobType().getNamespace())
                        .thenComparing(skullType -> skullType.getMobType().getPath())
                        .thenComparing(SkullType::getVariant))
                .toList();
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
                    .filter(skullType -> skullType.getDropRate() > 0.0F || skullType.getLootingBonus() > 0.0F)
                    .collect(Collectors.groupingBy(SkullType::getMobLootTableId));
        }
        return Optional.ofNullable(this.skullTypesByLootTable.get(lootTable));
    }

    private void dissolve() {
        if (this.skullTypes == null) {
            ImmutableSet.Builder<SkullType> builder = ImmutableSet.builder();
            builder.add(ModRegistry.PIGLIN_SKULL_TYPE);
            builder.add(ModRegistry.ZOMBIFIED_PIGLIN_SKULL_TYPE);
            builder.add(ModRegistry.PIGLIN_BRUTE_SKULL_TYPE);
            builder.add(ModRegistry.COW_SKULL_TYPE);
            builder.add(ModRegistry.VILLAGER_SKULL_TYPE);
            builder.add(ModRegistry.ENDERMAN_SKULL_TYPE);
            builder.add(ModRegistry.BLAZE_SKULL_TYPE);
            builder.add(ModRegistry.SPIDER_SKULL_TYPE);
            builder.add(ModRegistry.CAVE_SPIDER_SKULL_TYPE);
            builder.add(ModRegistry.WITCH_SKULL_TYPE);
            builder.add(ModRegistry.SQUID_SKULL_TYPE);
            builder.add(ModRegistry.LUCY_AXOLOTL_SKULL_TYPE);
            builder.add(ModRegistry.WILD_AXOLOTL_SKULL_TYPE);
            builder.add(ModRegistry.GOLD_AXOLOTL_SKULL_TYPE);
            this.skullTypes = builder.build();
        }
    }

    private Pair<RegistryObject<Block>, RegistryObject<Block>> registerBlocks(RegistryManager registry, SkullType skullType) {
        RegistryObject<Block> headBlock = registry.registerBlock(skullType.getId(), () -> new ModSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        RegistryObject<Block> wallHeadBlock = registry.registerBlock(skullType.getWallId(), () -> new ModWallSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        return Pair.of(headBlock, wallHeadBlock);
    }

    private void registerItem(RegistryManager registry, SkullType skullType, RegistryObject<Block> headBlock, RegistryObject<Block> wallHeadBlock) {
        registry.registerItem(skullType.getId(), () -> new ModStandingAndWallBlockItem(headBlock.get(), wallHeadBlock.get(), new Item.Properties().tab(ModRegistry.ALL_THE_HEADS_CREATIVE_TAB).rarity(Rarity.UNCOMMON)));
    }
}
