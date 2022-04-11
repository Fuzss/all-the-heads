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
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
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
import java.util.stream.Stream;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();
    public static final List<ResourceLocation> VILLAGER_BIOME_TYPES = Stream.of("desert", "jungle", "plains", "savanna", "snow", "swamp", "taiga").map(ResourceLocation::new).toList();
    public static final List<ResourceLocation> VILLAGER_WORKER_PROFESSIONS = Stream.of("armorer", "butcher", "cartographer", "cleric", "farmer", "fisherman", "fletcher", "leatherworker", "librarian", "mason", "nitwit", "shepherd", "toolsmith", "weaponsmith").map(ResourceLocation::new).toList();

    private Map<String, ModSkullType> skullTypesByKey;
    private List<Pair<RegistryObject<Block>, RegistryObject<Block>>> skullBlocks;
    private Map<EntityType<?>, List<ModSkullType>> skullTypesByEntity;
    private Map<ResourceLocation, List<ModSkullType>> skullTypesByLootTable;

    public Collection<ModSkullType> getAllSkullTypes() {
        this.dissolve();
        return this.skullTypesByKey.values().stream()
                .sorted(Comparator.<ModSkullType, String>comparing(skullType -> skullType.getMobType().getNamespace())
                        .thenComparing(skullType -> skullType.getMobType().getPath())
                        .thenComparing(ModSkullType::getVariantForComparison))
                .toList();
    }

    public ModSkullType getSkullType(String key) {
        return this.skullTypesByKey.get(key.indexOf(':') >= 0 ? key : "minecraft:" + key);
    }

    public void register(RegistryManager registry) {
        this.skullBlocks = Lists.newArrayList();
        for (ModSkullType skullType : this.getAllSkullTypes()) {
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

    public Optional<List<ModSkullType>> getSkullTypeByEntity(EntityType<?> entityType) {
        if (this.skullTypesByEntity == null) {
            this.skullTypesByEntity = this.getAllSkullTypes().stream().collect(Collectors.groupingBy(skullType -> skullType.entityType.get()));
        }
        return Optional.ofNullable(this.skullTypesByEntity.get(entityType));
    }

    public Optional<List<ModSkullType>> getSkullTypeByLootTable(ResourceLocation lootTable) {
        if (this.skullTypesByLootTable == null) {
            this.skullTypesByLootTable = this.getAllSkullTypes().stream()
                    .filter(ModSkullType::obtainableFromNormalDrops)
                    .collect(Collectors.groupingBy(ModSkullType::getMobLootTableId));
        }
        return Optional.ofNullable(this.skullTypesByLootTable.get(lootTable));
    }

    private void dissolve() {
        if (this.skullTypesByKey == null) {
            List<ModSkullType.Builder> builders = this.load();
            this.skullTypesByKey = builders.stream().map(ModSkullType.Builder::build)
                    .filter(skullType -> ModLoaderEnvironment.isModLoaded(skullType.getMobType().getNamespace()))
                    .collect(ImmutableMap.toImmutableMap(ModSkullType::getMappingKey, Function.identity()));
        }
    }

    private List<ModSkullType.Builder> load() {
        List<ModSkullType.Builder> builders = Lists.newArrayList();
        builders.add(new ModSkullType.Builder("piglin").skullSize(10.0F, 8.0F, 8.0F));
        builders.add(new ModSkullType.Builder("zombified_piglin").skullSize(10.0F, 8.0F, 8.0F));
        builders.add(new ModSkullType.Builder("piglin_brute").skullSize(10.0F, 8.0F, 8.0F));
        builders.add(new ModSkullType.Builder("cow").skullSize(8.0F, 8.0F, 6.0F));
        for (ResourceLocation villagerBiomeType : VILLAGER_BIOME_TYPES) {
            for (ResourceLocation villagerWorkerProfession : VILLAGER_WORKER_PROFESSIONS) {
                String variant = String.format("%s_%s", villagerBiomeType.getPath(), villagerWorkerProfession.getPath());
                String nbtPredicate = String.format("{VillagerData:{type:\"%s\",profession:\"%s\"}}", villagerBiomeType, villagerWorkerProfession);
                builders.add(new ModSkullType.Builder("villager").variant(variant, nbtPredicate).skullSize(8.0F, 10.0F, 8.0F));
                builders.add(new ModSkullType.Builder("zombie_villager").variant(variant, nbtPredicate).skullSize(8.0F, 10.0F, 8.0F));
            }
        }
        builders.add(new ModSkullType.Builder("enderman"));
        builders.add(new ModSkullType.Builder("blaze"));
        builders.add(new ModSkullType.Builder("spider"));
        builders.add(new ModSkullType.Builder("cave_spider"));
        builders.add(new ModSkullType.Builder("witch").skullSize(8.0F, 10.0F, 8.0F));
        builders.add(new ModSkullType.Builder("squid").skullSize(8.0F, 10.6667F, 8.0F));
        for (Axolotl.Variant variant : Axolotl.Variant.values()) {
            builders.add(new ModSkullType.Builder("axolotl").variant(variant.getName(), "{Variant:" + variant.getId() + "}").skullSize(8.0F, 5.0F, 5.0F));
        }
        builders.add(new ModSkullType.Builder("chicken").skullSize(8.0F, 12.0F, 6.0F));
        for (DyeColor dyeColor : DyeColor.values()) {
            builders.add(new ModSkullType.Builder("sheep").variant(dyeColor.getName(), "{Color:" + dyeColor.getId() + "}").skullSize(8.0F, 8.0F, 10.6667F).lootTableOverride("entities/sheep/" + dyeColor.getName()));
        }
        return builders;
    }

    private Pair<RegistryObject<Block>, RegistryObject<Block>> registerBlocks(RegistryManager registry, ModSkullType skullType) {
        RegistryObject<Block> headBlock = registry.registerBlock(skullType.getId(), () -> new ModSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        RegistryObject<Block> wallHeadBlock = registry.registerBlock(skullType.getWallId(), () -> new ModWallSkullBlock(skullType, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
        return Pair.of(headBlock, wallHeadBlock);
    }

    private void registerItem(RegistryManager registry, ModSkullType skullType, RegistryObject<Block> headBlock, RegistryObject<Block> wallHeadBlock) {
        registry.registerItem(skullType.getId(), () -> {
            CreativeModeTab tab;
            if (skullType.getMobType().equals(new ResourceLocation("villager")) || skullType.getMobType().equals(new ResourceLocation("zombie_villager"))) {
                tab = ModRegistry.VILLAGERS_CREATIVE_TAB;
            } else {
                tab = ModRegistry.DEFAULT_CREATIVE_TAB;
            }
            return new ModStandingAndWallBlockItem(headBlock.get(), wallHeadBlock.get(), new Item.Properties().tab(tab).rarity(Rarity.UNCOMMON));
        });
    }
}
