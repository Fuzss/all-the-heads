package fuzs.alltheheads.world.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.init.ModRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public record HeadType(EntityPredicate entityPredicate,
                       Shape shape,
                       Loot loot,
                       Optional<String> customName,
                       boolean mobDisguise,
                       Optional<Holder<SoundEvent>> noteBlockSound,
                       List<Model> models) {
    public static final Codec<HeadType> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    EntityPredicate.CODEC.fieldOf("entity_predicate").forGetter(HeadType::entityPredicate),
                    Shape.CODEC.fieldOf("shape").forGetter(HeadType::shape),
                    Loot.CODEC.forGetter(HeadType::loot),
                    Codec.STRING.optionalFieldOf("custom_name").forGetter(HeadType::customName),
                    Codec.BOOL.optionalFieldOf("mob_disguise", true).forGetter(HeadType::mobDisguise),
                    BuiltInRegistries.SOUND_EVENT.holderByNameCodec()
                            .optionalFieldOf("note_block_sound")
                            .forGetter(HeadType::noteBlockSound),
                    Model.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("models").forGetter(HeadType::models))
            .apply(instance, HeadType::new));
    public static final Codec<HeadType> DIRECT_NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Shape.CODEC.fieldOf("shape").forGetter(HeadType::shape),
                    Codec.STRING.optionalFieldOf("custom_name").forGetter(HeadType::customName),
                    BuiltInRegistries.SOUND_EVENT.holderByNameCodec()
                            .optionalFieldOf("note_block_sound")
                            .forGetter(HeadType::noteBlockSound),
                    Model.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("models").forGetter(HeadType::models))
            .apply(instance, HeadType::new));
    public static final Codec<Holder<HeadType>> CODEC = RegistryFixedCodec.create(ModRegistry.HEAD_REGISTRY_KEY);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<HeadType>> STREAM_CODEC = ByteBufCodecs.holderRegistry(
            ModRegistry.HEAD_REGISTRY_KEY);

    private HeadType(Shape shape, Optional<String> customName, Optional<Holder<SoundEvent>> noteBlockSound, List<Model> models) {
        this(EntityPredicate.Builder.entity().build(),
                shape,
                new Loot(Optional.empty(), true),
                customName,
                false,
                noteBlockSound,
                models);
    }

    public static HeadTypeBuilder builder(EntityType<?> entityType) {
        return new HeadTypeBuilder(entityType);
    }

    public static String customName(EntityType<?> entityType, ResourceKey<HeadType> resourceKey) {
        return customName(entityType, resourceKey, ModRegistry.MOB_HEAD_BLOCK.value().getDescriptionId());
    }

    public static String customName(EntityType<?> entityType, ResourceKey<HeadType> resourceKey, @Nullable String descriptionId) {
        ResourceLocation resourceLocation = HeadTypeBuilder.getNamespacedLocation(entityType, resourceKey)
                .withPath((String path) -> {
                    return path.replace('/', '_');
                });
        return descriptionId != null ? resourceLocation.toLanguageKey(descriptionId) : resourceLocation.toLanguageKey();
    }

    public Component getName(String descriptionId) {
        return Component.translatable(this.customName.map((String s) -> descriptionId + "." + s).orElse(descriptionId));
    }

    public boolean matches(Entity entity) {
        return this.entityPredicate().matches((ServerLevel) entity.level(), entity.position(), entity);
    }

    public record Shape(double width, double height, double depth, double scale, Map<Direction, VoxelShape> shapes) {
        public static final Codec<Shape> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.doubleRange(
                                1.0,
                                16.0).fieldOf("width").forGetter(Shape::width),
                        Codec.doubleRange(1.0, 16.0).fieldOf("height").forGetter(Shape::height),
                        Codec.doubleRange(1.0, 16.0).fieldOf("depth").forGetter(Shape::depth),
                        Codec.doubleRange(0.0, Double.MAX_VALUE).optionalFieldOf("scale", 1.0).forGetter(Shape::scale))
                .apply(instance, Shape::new));
        public static final StreamCodec<ByteBuf, Shape> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.DOUBLE,
                Shape::scaledWidth,
                ByteBufCodecs.DOUBLE,
                Shape::scaledHeight,
                ByteBufCodecs.DOUBLE,
                Shape::scaledDepth,
                Shape::new);

        public Shape(double width, double height, double depth) {
            this(width, height, depth, 1.0);
        }

        private Shape(double width, double height, double depth, double scale) {
            this(width,
                    height,
                    depth,
                    scale,
                    Util.make(Shapes.rotateHorizontal(Block.boxZ(width * scale,
                            8.0 - height * scale / 2.0,
                            8.0 + height * scale / 2.0,
                            16.0 - depth * scale,
                            16.0)), (Map<Direction, VoxelShape> map) -> {
                        map.put(Direction.UP, Block.column(Math.max(width, depth) * scale, 0.0, height * scale));
                    }));
        }

        public Shape scale(double scale) {
            return new Shape(this.width, this.height, this.depth, this.scale * scale);
        }

        public double scaledWidth() {
            return this.width * this.scale;
        }

        public double scaledHeight() {
            return this.height * this.scale;
        }

        public double scaledDepth() {
            return this.depth * this.scale;
        }
    }

    public record Loot(Optional<ResourceKey<LootTable>> lootTable, boolean chargedCreeperDrop) {
        public static final MapCodec<Loot> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(ResourceKey.codec(
                                Registries.LOOT_TABLE).optionalFieldOf("loot_table").forGetter(Loot::lootTable),
                        Codec.BOOL.optionalFieldOf("charged_creeper_drop", true).forGetter(Loot::chargedCreeperDrop))
                .apply(instance, Loot::new));
        public static final StreamCodec<ByteBuf, Loot> STREAM_CODEC = StreamCodec.composite(ResourceKey.streamCodec(
                        Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
                Loot::lootTable,
                ByteBufCodecs.BOOL,
                Loot::chargedCreeperDrop,
                Loot::new);

        public Loot(ResourceKey<LootTable> resourceKey) {
            this(Optional.of(resourceKey), true);
        }
    }

    public record Model(ModelAndTexture<ModelType> model, int color) {
        public static final Codec<Model> CODEC = RecordCodecBuilder.create(instance -> instance.group(ModelAndTexture.codec(
                                ModelType.CODEC,
                                ModelType.MOB).forGetter(Model::model),
                        ExtraCodecs.RGB_COLOR_CODEC.optionalFieldOf("color", -1).forGetter(Model::color))
                .apply(instance, Model::new));

        public Model(ModelType modelType, ResourceLocation assetId) {
            this(new ModelAndTexture<>(modelType, assetId), -1);
        }
    }

    /**
     * Basically a server-side implementation of {@code ModelLayerLocation}.
     */
    public record ModelType(ResourceLocation model, String layer) {
        public static final ExtraCodecs.LateBoundIdMapper<String, ModelType> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
        public static final Codec<ModelType> CODEC = ID_MAPPER.codec(Codec.STRING);
        public static final ModelType MOB = register("mob_head");
        public static final ModelType HUMANOID = register("humanoid_head");
        public static final ModelType ENDERMAN = register("enderman_head");
        public static final ModelType ENDERMAN_EYES = register("enderman_head", "eyes");
        public static final ModelType TEMPERATE_COW = register("temperate_cow_head");
        public static final ModelType WARM_COW = register("warm_cow_head");
        public static final ModelType COLD_COW = register("cold_cow_head");
        public static final ModelType OCELOT = register("ocelot_head");
        public static final ModelType SHEEP = register("sheep_head");
        public static final ModelType SHEEP_WOOL = register("sheep_head", "wool");
        public static final ModelType DEFAULT = HUMANOID;

        private static ModelType register(String model) {
            return register(model, "main");
        }

        private static ModelType register(String model, String layer) {
            ModelType modelType = new ModelType(AllTheHeads.id(model), layer);
            ID_MAPPER.put(modelType.toString(), modelType);
            return modelType;
        }

        @Override
        public String toString() {
            return this.model + (Objects.equals(this.layer, "main") ? "" : "/" + this.layer);
        }
    }
}
