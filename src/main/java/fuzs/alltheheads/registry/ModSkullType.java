package fuzs.alltheheads.registry;

import com.google.common.base.Suppliers;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.util.BlockLootUtil;
import fuzs.puzzleslib.core.ModLoaderEnvironment;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;
import java.util.function.Supplier;

public class ModSkullType implements SkullBlock.Type {
    private static final String HEAD_SUFFIX = "_head";
    private static final String SKULL_SUFFIX = "_skull";
    private static final String WALL_SUFFIX = "_wall";
    private static final String HEAD_TRANSLATION_KEY = "block.alltheheads.head";
    private static final String SKULL_TRANSLATION_KEY = "block.alltheheads.skull";
    private static final String WALL_TRANSLATION_KEY = "block.alltheheads.wall";

    private final ResourceLocation mobType;
    private final ResourceLocation textureLocation;
    private final ResourceLocation modelLocation;
    private final String layerLocation;
    private final String headKey;
    private final boolean skull;
    private final float dropRate;
    private final float lootingBonus;
    private final boolean fromChargedCreepers;
    public final Supplier<Block> block;
    public final Supplier<Block> wallBlock;
    public final Supplier<Item> item;
    public final Supplier<EntityType<?>> entityType;
    public final Supplier<LootTable> lootTable;

    private ModSkullType(ResourceLocation mobType, ResourceLocation textureLocation, ResourceLocation modelLocation, String layerLocation, String headKey, boolean skull, float dropRate, float lootingBonus, boolean fromChargedCreepers) {
        this.mobType = mobType;
        this.textureLocation = textureLocation;
        this.modelLocation = modelLocation;
        this.layerLocation = layerLocation;
        this.headKey = headKey;
        this.skull = skull;
        this.dropRate = dropRate;
        this.lootingBonus = lootingBonus;
        this.fromChargedCreepers = fromChargedCreepers;
        this.entityType = Suppliers.memoize(() -> getRegistryEntry(ForgeRegistries.ENTITIES, this.mobType));
        this.wallBlock = Suppliers.memoize(() -> getRegistryEntry(ForgeRegistries.BLOCKS, new ResourceLocation(AllTheHeads.MOD_ID, this.getWallId())));
        this.block = Suppliers.memoize(() -> getRegistryEntry(ForgeRegistries.BLOCKS, new ResourceLocation(AllTheHeads.MOD_ID, this.getId())));
        this.item = Suppliers.memoize(() -> getRegistryEntry(ForgeRegistries.ITEMS, new ResourceLocation(AllTheHeads.MOD_ID, this.getId())));
        this.lootTable = Suppliers.memoize(() -> BlockLootUtil.createSingleItemTable(this.item.get()).build());
    }

    private static <T extends ForgeRegistryEntry<T>> T getRegistryEntry(IForgeRegistry<T> registry, ResourceLocation location) {
        T value = registry.getValue(location);
        Objects.requireNonNull(value, "Registry entry was resolved too early!");
        return value;
    }

    public boolean isValid() {
        return ModLoaderEnvironment.isModLoaded(this.mobType.getNamespace());
    }

    public ResourceLocation getLootTableId() {
        return new ResourceLocation(this.mobType.getNamespace(), "entities/".concat(this.mobType.getPath()));
    }

    public String getHeadKey() {
        return this.headKey;
    }

    public String getId() {
        return this.mobType.getPath().concat(this.skull ? SKULL_SUFFIX : HEAD_SUFFIX);
    }

    public String getWallId() {
        return this.mobType.getPath().concat(WALL_SUFFIX).concat(this.skull ? SKULL_SUFFIX : HEAD_SUFFIX);
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public Pair<ResourceLocation, String> getModelLayerLocation() {
        return Pair.of(this.modelLocation, this.layerLocation);
    }

    public float getDropRate() {
        return this.dropRate;
    }

    public float getLootingBonus() {
        return this.lootingBonus;
    }

    public boolean dropsFromChargedCreepers() {
        return this.fromChargedCreepers;
    }

    public MutableComponent getName() {
        return this.getName(this.entityType.get().getDescription());
    }

    public MutableComponent getWallName() {
        return this.getName(new TranslatableComponent(WALL_TRANSLATION_KEY, this.entityType.get().getDescription()));
    }

    private MutableComponent getName(Component description) {
        return new TranslatableComponent(this.skull ? SKULL_TRANSLATION_KEY : HEAD_TRANSLATION_KEY, description);
    }

    public static class Builder {
        private ResourceLocation mobType;
        private ResourceLocation textureLocation;
        private ResourceLocation modelLocation;
        private String layerLocation = "main";
        private String headKey = "head";
        private boolean skull;
        private float dropRate = 0.025F;
        private float lootingBonus = 0.01F;
        private boolean fromChargedCreepers = true;

        public Builder mobType(String path) {
            return this.mobType(new ResourceLocation(path));
        }

        public Builder mobType(ResourceLocation mobType) {
            this.mobType = mobType;
            return this;
        }

        public Builder textureLocation(String path) {
            return this.textureLocation(new ResourceLocation(path));
        }

        public Builder textureLocation(ResourceLocation textureLocation) {
            this.textureLocation = textureLocation;
            return this;
        }

        public Builder modelLayerLocation(String path) {
            return this.modelLayerLocation(new ResourceLocation(path));
        }

        public Builder modelLayerLocation(ResourceLocation model) {
            this.modelLocation = model;
            return this;
        }

        public Builder modelLayerLocation(String path, String layer) {
            return this.modelLayerLocation(new ResourceLocation(path), layer);
        }

        public Builder modelLayerLocation(ResourceLocation model, String layer) {
            this.modelLocation = model;
            this.layerLocation = layer;
            return this;
        }

        public Builder customHeadKey(String headKey) {
            this.headKey = headKey;
            return this;
        }

        public Builder skull() {
            this.skull = true;
            return this;
        }

        public Builder dropRate(float dropRate) {
            this.dropRate = dropRate;
            return this;
        }

        public Builder lootingBonus(float lootingBonus) {
            this.lootingBonus = lootingBonus;
            return this;
        }

        public Builder disableChargedCreepers() {
            this.fromChargedCreepers = false;
            return this;
        }

        public ModSkullType build() {
            Objects.requireNonNull(this.mobType);
            Objects.requireNonNull(this.textureLocation);
            return new ModSkullType(this.mobType, this.textureLocation, this.modelLocation == null ? this.mobType : this.modelLocation, this.layerLocation, this.headKey, this.skull, this.dropRate, this.lootingBonus, this.fromChargedCreepers);
        }
    }
}
