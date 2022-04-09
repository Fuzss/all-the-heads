package fuzs.alltheheads.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();

    private List<SkullType> skullTypes;
    private List<Pair<RegistryObject<Block>, RegistryObject<Block>>> skullBlocks;
    private Map<EntityType<?>, SkullType> skullTypeByEntity;
    private Map<ResourceLocation, SkullType> skullTypeByLootTable;
    private Map<ResourceLocation, byte[]> resourceDataByLocation;

    public void load() {
        ImmutableList.Builder<SkullType> builder = ImmutableList.builder();
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
        this.skullTypes = builder.build();
    }

    public List<SkullType> getAllSkullTypes() {
        return this.skullTypes.stream()
                .filter(skullType -> ModLoaderEnvironment.isModLoaded(skullType.getMobType().getNamespace()))
                .sorted(Comparator.<SkullType, String>comparing(skullType -> skullType.getMobType().getNamespace()).thenComparing(skullType -> skullType.getMobType().getPath()))
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

    public Optional<SkullType> getSkullTypeByEntity(EntityType<?> entityType) {
        if (this.skullTypeByEntity == null) {
            this.skullTypeByEntity = this.getAllSkullTypes().stream().collect(ImmutableMap.toImmutableMap(skullType -> skullType.entityType.get(), Function.identity()));
        }
        return Optional.ofNullable(this.skullTypeByEntity.get(entityType));
    }

    public Optional<SkullType> getSkullTypeByLootTable(ResourceLocation lootTable) {
        if (this.skullTypeByLootTable == null) {
            this.skullTypeByLootTable = this.getAllSkullTypes().stream().collect(ImmutableMap.toImmutableMap(SkullType::getMobLootTableId, Function.identity()));
        }
        return Optional.ofNullable(this.skullTypeByLootTable.get(lootTable));
    }

    public Map<ResourceLocation, byte[]> getBuiltInResourceData() {
        if (this.resourceDataByLocation == null) {
            ImmutableMap.Builder<ResourceLocation, byte[]> builder = new ImmutableMap.Builder<>();
            for (SkullType skullType : this.getAllSkullTypes()) {
                skullType.buildResourceMap(builder::put);
            }
            this.resourceDataByLocation = builder.build();
        }
        return this.resourceDataByLocation;
    }

    public Block[] getAllSkullBlocks() {
        return this.skullBlocks.stream().mapMulti((Pair<RegistryObject<Block>, RegistryObject<Block>> pair, Consumer<RegistryObject<Block>> consumer) -> {
            consumer.accept(pair.left());
            consumer.accept(pair.right());
        }).map(RegistryObject::get)
                .toArray(Block[]::new);
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
