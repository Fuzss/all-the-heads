package fuzs.alltheheads.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkullManager {
    public static final SkullManager INSTANCE = new SkullManager();
    public static final List<ResourceLocation> VILLAGER_BIOME_TYPES = Stream.of("desert", "jungle", "plains", "savanna", "snow", "swamp", "taiga").map(
            ResourceLocationHelper::withDefaultNamespace).toList();
    public static final List<ResourceLocation> VILLAGER_WORKER_PROFESSIONS = Stream.of("armorer", "butcher", "cartographer", "cleric", "farmer", "fisherman", "fletcher", "leatherworker", "librarian", "mason", "nitwit", "shepherd", "toolsmith", "weaponsmith").map(ResourceLocationHelper::withDefaultNamespace).toList();

    private Map<String, ModSkullType> skullTypesByKey;
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

    @Nullable
    public ModSkullType getSkullType(String key) {
        return this.skullTypesByKey.get(key.indexOf(':') >= 0 ? key : "minecraft:" + key);
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
}
