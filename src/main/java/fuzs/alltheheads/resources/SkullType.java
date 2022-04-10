package fuzs.alltheheads.resources;

import com.google.common.base.Suppliers;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Vector3f;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.util.BlockLootUtil;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class SkullType implements SkullBlock.Type {
    private static final String HEAD_SUFFIX = "_head";
    private static final String SKULL_SUFFIX = "_skull";
    private static final String WALL_SUFFIX = "_wall";
    private static final String HEAD_TRANSLATION_KEY = "block.alltheheads.head";
    private static final String SKULL_TRANSLATION_KEY = "block.alltheheads.skull";
    private static final String WALL_TRANSLATION_KEY = "block.alltheheads.wall";

    private final ResourceLocation mobType;
    private final boolean skull;
    private final float dropRate;
    private final float lootingBonus;
    private final boolean fromChargedCreepers;
    private final boolean mobDisguise;
    private final Vector3f skullSize;
    private final String variant;
    private final String nbtPredicate;

    public final Supplier<Block> block;
    public final Supplier<Block> wallBlock;
    public final Supplier<Item> item;
    public final Supplier<EntityType<?>> entityType;
    public final Supplier<LootTable> lootTable;

    private BooleanSupplier configSupplier;

    private SkullType(ResourceLocation mobType, boolean skull, float dropRate, float lootingBonus, boolean fromChargedCreepers, boolean mobDisguise, Vector3f skullSize, String variant, String nbtPredicate) {
        this.mobType = mobType;
        this.skull = skull;
        this.dropRate = dropRate;
        this.lootingBonus = lootingBonus;
        this.fromChargedCreepers = fromChargedCreepers;
        this.mobDisguise = mobDisguise;
        this.skullSize = skullSize;
        this.variant = variant;
        this.nbtPredicate = nbtPredicate;
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

    public ResourceLocation getMobType() {
        return this.mobType;
    }

    public ResourceLocation getMobLootTableId() {
        return new ResourceLocation(this.mobType.getNamespace(), "entities/".concat(this.mobType.getPath()));
    }

    public String getMappingKey() {
        return this.mobType.toString() + (this.variant.isEmpty() ? "" : "#" + this.variant);
    }

    public String getId() {
        return this.getBaseId() + this.getSuffixId();
    }

    public String getWallId() {
        return this.getBaseId() + WALL_SUFFIX + this.getSuffixId();
    }

    private String getBaseId() {
        if (this.variant.isEmpty()) {
            return this.mobType.getPath();
        }
        return this.variant + "_" + this.mobType.getPath();
    }

    public String getSuffixId() {
        return this.skull ? SKULL_SUFFIX : HEAD_SUFFIX;
    }

    public boolean obtainableFromNormalDrops() {
        return this.configSupplier.getAsBoolean() && (this.getDropRate() > 0.0F || this.getLootingBonus() > 0.0F);
    }

    public float getDropRate() {
        return this.dropRate;
    }

    public float getLootingBonus() {
        return this.lootingBonus;
    }

    public boolean dropsFromChargedCreepers() {
        return this.configSupplier.getAsBoolean() && this.fromChargedCreepers;
    }

    public boolean worksAsMobDisguise() {
        return this.mobDisguise;
    }

    public Vector3f getSkullSize() {
        return this.skullSize;
    }

    public String getVariant() {
        return this.variant;
    }

    public boolean matchesNbtVariant(Entity entity) {
        return this.nbtPredicate.isEmpty() || NbtUtils.compareNbt(this.getNbtPredicate(), NbtPredicate.getEntityTagToCompare(entity), true);
    }

    public CompoundTag getNbtPredicate() {
        if (this.nbtPredicate.isEmpty()) throw new RuntimeException("Nbt predicate missing for variant " + this.variant);
        try {
            return TagParser.parseTag(this.nbtPredicate);
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public MutableComponent getName() {
        return this.getName(this.entityType.get().getDescription());
    }

    public MutableComponent getWallName() {
        return this.getName(new TranslatableComponent(WALL_TRANSLATION_KEY, this.entityType.get().getDescription()));
    }

    public void setConfigSupplier(BooleanSupplier supplier) {
        if (this.configSupplier != null) throw new IllegalStateException("Config supplier already set!");
        this.configSupplier = supplier;
    }

    private MutableComponent getName(Component description) {
        return new TranslatableComponent(this.skull ? SKULL_TRANSLATION_KEY : HEAD_TRANSLATION_KEY, description);
    }

    @Override
    public String toString() {
        return "SkullType{" + "mobType=" + this.mobType + ", skull=" + this.skull + ", dropRate=" + this.dropRate + ", lootingBonus=" + this.lootingBonus + ", fromChargedCreepers=" + this.fromChargedCreepers + ", mobDisguise=" + this.mobDisguise + ", skullSize=" + this.skullSize + ", variant='" + this.variant + '\'' + ", nbtPredicate='" + this.nbtPredicate + '\'' + '}';
    }

    public static class Builder {
        private final ResourceLocation mobType;
        private boolean skull;
        private float dropRate = 0.025F;
        private float lootingBonus = 0.01F;
        private boolean fromChargedCreepers = true;
        private boolean mobDisguise = true;
        private Vector3f skullSize = new Vector3f(8.0F, 8.0F, 8.0F);
        private String variant = "";
        private String nbtPredicate = "";

        public Builder(String path) {
            this(new ResourceLocation(path));
        }

        public Builder(ResourceLocation mobType) {
            this.mobType = mobType;
        }

        public Builder skull() {
            this.skull = true;
            return this;
        }

        public Builder dropRate(float dropRate) {
            this.dropRate = Math.max(0.0F, dropRate);
            return this;
        }

        public Builder lootingBonus(float lootingBonus) {
            this.lootingBonus = Math.max(0.0F, lootingBonus);
            return this;
        }

        public Builder disableChargedCreepers() {
            this.fromChargedCreepers = false;
            return this;
        }

        public Builder disableMobDisguise() {
            this.mobDisguise = false;
            return this;
        }

        public Builder skullSize(float width, float height, float depth) {
            this.skullSize = new Vector3f(width, height, depth);
            return this;
        }

        public Builder variant(String variant, String nbtPredicate) {
            this.variant = variant;
            this.nbtPredicate = nbtPredicate;
            return this;
        }

        public SkullType build() {
            return new SkullType(this.mobType, this.skull, this.dropRate, this.lootingBonus, this.fromChargedCreepers, this.mobDisguise, this.skullSize, this.variant, this.nbtPredicate);
        }
    }
}
