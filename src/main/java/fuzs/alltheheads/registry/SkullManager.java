package fuzs.alltheheads.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import fuzs.alltheheads.world.item.ModStandingAndWallBlockItem;
import fuzs.alltheheads.world.level.block.ModSkullBlock;
import fuzs.alltheheads.world.level.block.ModWallSkullBlock;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();

    private Set<ModSkullType> skullTypes;
    private List<Pair<RegistryObject<Block>, RegistryObject<Block>>> skullBlocks;
    private Map<EntityType<?>, ModSkullType> skullTypeByEntity;
    private Map<ResourceLocation, ModSkullType> skullTypeByLootTable;
    private Map<ResourceLocation, byte[]> resourceDataByLocation;

    public void load() {
        ImmutableSet.Builder<ModSkullType> builder = ImmutableSet.builder();
        builder.add(new ModSkullType.Builder().mobType("piglin").textureLocation("textures/entity/piglin/piglin.png").skullSize(10.0F, 8.0F, 8.0F).build());
        builder.add(new ModSkullType.Builder().mobType("zombified_piglin").textureLocation("textures/entity/piglin/zombified_piglin.png").skullSize(10.0F, 8.0F, 8.0F).build());
        builder.add(new ModSkullType.Builder().mobType("piglin_brute").textureLocation("textures/entity/piglin/piglin_brute.png").skullSize(10.0F, 8.0F, 8.0F).build());
        builder.add(new ModSkullType.Builder().mobType("cow").textureLocation("textures/entity/cow/cow.png").skullSize(8.0F, 8.0F, 6.0F).modelOffsets(0.0F, -8.0F, 11.0F).build());
        builder.add(new ModSkullType.Builder().mobType("villager").textureLocation("textures/entity/villager/villager.png").skullSize(8.0F, 10.0F, 8.0F).build());
        builder.add(new ModSkullType.Builder().mobType("enderman").textureLocation("textures/entity/enderman/enderman.png").modelOffsets(0.0F, 13.0F, 0.0F).build());
        builder.add(new ModSkullType.Builder().mobType("blaze").textureLocation("textures/entity/blaze.png").modelOffsets(0.0F, -4.0F, 0.0F).build());
        this.skullTypes = builder.build();
    }

    public Set<ModSkullType> getAllSkullTypes() {
        return this.skullTypes.stream()
                .filter(ModSkullType::isValid)
                .collect(Collectors.toUnmodifiableSet());
    }

    public void register(RegistryManager registry) {
        this.skullBlocks = Lists.newArrayList();
        for (ModSkullType skullType : this.getAllSkullTypes()) {
            Pair<RegistryObject<Block>, RegistryObject<Block>> pair = this.registerBlocks(registry, skullType);
            this.skullBlocks.add(pair);
            this.registerItem(registry, skullType, pair.left(), pair.right());
        }
    }

    public Optional<ModSkullType> getSkullTypeByEntity(EntityType<?> entityType) {
        if (this.skullTypeByEntity == null) {
            this.skullTypeByEntity = this.getAllSkullTypes().stream().collect(ImmutableMap.toImmutableMap(skullType -> skullType.entityType.get(), Function.identity()));
        }
        return Optional.ofNullable(this.skullTypeByEntity.get(entityType));
    }

    public Optional<ModSkullType> getSkullTypeByLootTable(ResourceLocation lootTable) {
        if (this.skullTypeByLootTable == null) {
            this.skullTypeByLootTable = this.getAllSkullTypes().stream().collect(ImmutableMap.toImmutableMap(ModSkullType::getMobLootTableId, Function.identity()));
        }
        return Optional.ofNullable(this.skullTypeByLootTable.get(lootTable));
    }

    public Map<ResourceLocation, byte[]> getBuiltInResourceData() {
        if (this.resourceDataByLocation == null) {
            ImmutableMap.Builder<ResourceLocation, byte[]> builder = new ImmutableMap.Builder<>();
            for (ModSkullType skullType : this.getAllSkullTypes()) {
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

    private Pair<RegistryObject<Block>, RegistryObject<Block>> registerBlocks(RegistryManager registry, ModSkullType skullType) {
        RegistryObject<Block> headBlock = registry.registerBlock(skullType.getId(), () -> new ModSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        RegistryObject<Block> wallHeadBlock = registry.registerBlock(skullType.getWallId(), () -> new ModWallSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        return Pair.of(headBlock, wallHeadBlock);
    }

    private void registerItem(RegistryManager registry, ModSkullType skullType, RegistryObject<Block> headBlock, RegistryObject<Block> wallHeadBlock) {
        registry.registerItem(skullType.getId(), () -> new ModStandingAndWallBlockItem(headBlock.get(), wallHeadBlock.get(), new Item.Properties().tab(ModRegistry.ALL_THE_HEADS_CREATIVE_TAB).rarity(Rarity.UNCOMMON)));
    }
}
