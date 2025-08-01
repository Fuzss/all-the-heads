package fuzs.alltheheads.resources;

import com.google.common.base.Suppliers;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class ModSkullType implements SkullBlock.Type {
    private static final String HEAD_SUFFIX = "_head";
    private static final String SKULL_SUFFIX = "_skull";
    private static final String WALL_SUFFIX = "_wall";
    public static final String HEAD_TRANSLATION_KEY = "block.alltheheads.head";
    public static final String SKULL_TRANSLATION_KEY = "block.alltheheads.skull";

    public final Supplier<EntityType<?>> entityType;
    private final ResourceLocation mobType;
    private final boolean skull;
    private final double dropRate;
    private final double lootingBonus;
    private final boolean fromChargedCreepers;
    private final boolean mobDisguise;
    private final Vector3f skullSize;
    @Nullable
    private final String lootTableOverride;
    @Nullable
    private final String variant;
    @Nullable
    private final String nbtPredicate;

    public Supplier<Block> block;
//    public Supplier<Block> wallBlock;
//    public Supplier<Item> item;
//    public Supplier<LootTable> lootTable;

    private DoubleSupplier dropRateSupplier;
    private DoubleSupplier lootingBonusSupplier;
    private BooleanSupplier fromChargedCreepersSupplier;
    private BooleanSupplier mobDisguiseSupplier;

    public final Map<Integer, VoxelShape> shapes;
    public final Map<Direction, VoxelShape> wallShapes;

    private ModSkullType(ResourceLocation mobType, boolean skull, double dropRate, double lootingBonus, boolean fromChargedCreepers, boolean mobDisguise, Vector3f skullSize, @Nullable String lootTableOverride, @Nullable String variant, @Nullable String nbtPredicate) {
        this.entityType = Suppliers.memoize(() -> BuiltInRegistries.ENTITY_TYPE.getValue(mobType));
        this.mobType = mobType;
        this.skull = skull;
        this.dropRate = dropRate;
        this.lootingBonus = lootingBonus;
        this.fromChargedCreepers = fromChargedCreepers;
        this.mobDisguise = mobDisguise;
        this.skullSize = skullSize;
        this.lootTableOverride = lootTableOverride;
        this.variant = variant;
        this.nbtPredicate = nbtPredicate;
        this.shapes = Collections.emptyMap();
        this.wallShapes = Collections.emptyMap();
    }

    public ResourceLocation getMobType() {
        return this.mobType;
    }

    public ResourceLocation getMobLootTableId() {
        return ResourceLocationHelper.fromNamespaceAndPath(this.mobType.getNamespace(), Objects.requireNonNullElseGet(this.lootTableOverride, () -> "entities/" + this.mobType.getPath()));
    }

    public String getMappingKey() {
        return this.mobType.toString() + (this.variant == null ? "" : "#" + this.variant);
    }

    public String getId() {
        return this.getBaseId() + this.getSuffixId();
    }

    public String getWallId() {
        return this.getBaseId() + WALL_SUFFIX + this.getSuffixId();
    }

    private String getBaseId() {
        if (this.variant == null) {
            return this.mobType.getPath();
        }
        return this.variant + "_" + this.mobType.getPath();
    }

    public String getSuffixId() {
        return this.skull ? SKULL_SUFFIX : HEAD_SUFFIX;
    }

    public boolean obtainableFromNormalDrops() {
        return this.getDropRate() > 0.0F || this.getLootingBonus() > 0.0F;
    }

    public float getDropRate() {
        return (float) this.dropRateSupplier.getAsDouble();
    }

    public float getLootingBonus() {
        return (float) this.lootingBonusSupplier.getAsDouble();
    }

    public boolean dropsFromChargedCreepers() {
        return this.fromChargedCreepersSupplier.getAsBoolean();
    }

    public boolean worksAsMobDisguise() {
        return this.mobDisguiseSupplier.getAsBoolean();
    }

    public double getDropRateDefault() {
        return this.dropRate;
    }

    public double getLootingBonusDefault() {
        return this.lootingBonus;
    }

    public boolean dropsFromChargedCreepersDefault() {
        return this.fromChargedCreepers;
    }

    public boolean worksAsMobDisguiseDefault() {
        return this.mobDisguise;
    }

    public Vector3f getSkullSize() {
        return this.skullSize;
    }

    public String getVariantForComparison() {
        return this.variant == null ? "" : this.variant;
    }

    public boolean matchesNbtVariant(Entity entity) {
        return this.nbtPredicate == null || NbtUtils.compareNbt(this.getNbtPredicate(), NbtPredicate.getEntityTagToCompare(entity), true);
    }

    public CompoundTag getNbtPredicate() {
        return new CompoundTag();
//        if (this.nbtPredicate == null) throw new RuntimeException("Nbt predicate missing for variant " + this.variant);
//        try {
//            return TagParser.parseTag(this.nbtPredicate);
//        } catch (CommandSyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }

    public MutableComponent getName() {
        return this.getName(this.entityType.get().getDescription());
    }

    public void setConfigSuppliers(DoubleSupplier dropRate, DoubleSupplier lootingBonus, BooleanSupplier fromChargedCreepers, BooleanSupplier mobDisguise) {
        if (this.dropRateSupplier != null || this.lootingBonusSupplier != null || this.fromChargedCreepersSupplier != null || this.mobDisguiseSupplier != null) {
            throw new IllegalStateException("Config supplier already set!");
        }
        this.dropRateSupplier = dropRate;
        this.lootingBonusSupplier = lootingBonus;
        this.fromChargedCreepersSupplier = fromChargedCreepers;
        this.mobDisguiseSupplier = mobDisguise;
    }

    private MutableComponent getName(Component description) {
        return Component.translatable(this.skull ? SKULL_TRANSLATION_KEY : HEAD_TRANSLATION_KEY, description);
    }

    @Override
    public String toString() {
        return this.mobType.toString();
    }

    @Override
    public String getSerializedName() {
        return this.toString();
    }

    public static class Builder {
        private final ResourceLocation mobType;
        private boolean skull;
        private double dropRate = 0.025;
        private double lootingBonus = 0.01;
        private boolean fromChargedCreepers = true;
        private boolean mobDisguise = true;
        private Vector3f skullSize = new Vector3f(8.0F, 8.0F, 8.0F);
        private String lootTableOverride;
        private String variant;
        private String nbtPredicate;

        public Builder(String path) {
            this(ResourceLocationHelper.withDefaultNamespace(path));
        }

        public Builder(ResourceLocation mobType) {
            this.mobType = mobType;
        }

        public Builder skull() {
            this.skull = true;
            return this;
        }

        public Builder dropRate(float dropRate) {
            this.dropRate = Mth.clamp(dropRate, 0.0, 1.0);
            return this;
        }

        public Builder lootingBonus(float lootingBonus) {
            this.lootingBonus = Mth.clamp(lootingBonus, 0.0, 1.0);
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

        public Builder lootTableOverride(String lootTableOverride) {
            this.lootTableOverride = lootTableOverride;
            return this;
        }

        public Builder variant(String variant, String nbtPredicate) {
            this.variant = variant;
            this.nbtPredicate = nbtPredicate;
            return this;
        }

        public ModSkullType build() {
            return new ModSkullType(this.mobType, this.skull, this.dropRate, this.lootingBonus, this.fromChargedCreepers, this.mobDisguise, this.skullSize, this.lootTableOverride, this.variant, this.nbtPredicate);
        }
    }
}
