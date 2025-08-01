package fuzs.alltheheads.world.item.component;

import com.mojang.serialization.Codec;
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
import java.util.Optional;

public record HeadType(Holder<EntityType<?>> entityType,
                       Shape shape,
                       Loot loot,
                       Optional<EntityPredicate> entityPredicate,
                       Optional<String> customName,
                       boolean mobDisguise,
                       Optional<Holder<SoundEvent>> noteBlockSound,
                       List<ModelAndTexture<ModelType>> modelAndTextures) {
    public static final Codec<HeadType> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ENTITY_TYPE.holderByNameCodec().fieldOf("entity_type").forGetter(HeadType::entityType),
            Shape.CODEC.fieldOf("shape").forGetter(HeadType::shape),
            Loot.CODEC.optionalFieldOf("loot", Loot.DEFAULT).forGetter(HeadType::loot),
            EntityPredicate.CODEC.optionalFieldOf("entity_predicate").forGetter(HeadType::entityPredicate),
            Codec.STRING.optionalFieldOf("custom_name").forGetter(HeadType::customName),
            Codec.BOOL.optionalFieldOf("mob_disguise", true).forGetter(HeadType::mobDisguise),
            BuiltInRegistries.SOUND_EVENT.holderByNameCodec()
                    .optionalFieldOf("note_block_sound")
                    .forGetter(HeadType::noteBlockSound),
            ModelAndTexture.codec(ModelType.CODEC, ModelType.DEFAULT)
                    .codec()
                    .listOf(1, Integer.MAX_VALUE)
                    .fieldOf("models_and_textures")
                    .forGetter(HeadType::modelAndTextures)).apply(instance, HeadType::new));
    public static final Codec<HeadType> DIRECT_NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ENTITY_TYPE.holderByNameCodec().fieldOf("entity_type").forGetter(HeadType::entityType),
            Shape.CODEC.fieldOf("shape").forGetter(HeadType::shape),
            Codec.STRING.optionalFieldOf("custom_name").forGetter(HeadType::customName),
            BuiltInRegistries.SOUND_EVENT.holderByNameCodec()
                    .optionalFieldOf("note_block_sound")
                    .forGetter(HeadType::noteBlockSound),
            ModelAndTexture.codec(ModelType.CODEC, ModelType.DEFAULT)
                    .codec()
                    .listOf(1, Integer.MAX_VALUE)
                    .fieldOf("models_and_textures")
                    .forGetter(HeadType::modelAndTextures)).apply(instance, HeadType::new));
    public static final Codec<Holder<HeadType>> CODEC = RegistryFixedCodec.create(ModRegistry.HEAD_REGISTRY_KEY);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<HeadType>> STREAM_CODEC = ByteBufCodecs.holderRegistry(
            ModRegistry.HEAD_REGISTRY_KEY);

    private HeadType(Holder<EntityType<?>> entityType, Shape shape, Optional<String> customName, Optional<Holder<SoundEvent>> noteBlockSound, List<ModelAndTexture<ModelType>> modelAndTexture) {
        this(entityType, shape, Loot.DEFAULT, Optional.empty(), customName, false, noteBlockSound, modelAndTexture);
    }

    public HeadType(EntityType<?> entityType, Shape shape, List<ModelAndTexture<ModelType>> modelAndTexture, Optional<Holder<SoundEvent>> noteBlockSound) {
        this(entityType.builtInRegistryHolder(),
                shape,
                modelAndTexture,
                noteBlockSound,
                Optional.empty(),
                entityType.builtInRegistryHolder().key().location());
    }

    public HeadType(EntityType<?> entityType, Shape shape, ModelAndTexture<ModelType> modelAndTexture, Optional<Holder<SoundEvent>> noteBlockSound, EntityPredicate entityPredicate, @Nullable ResourceKey<HeadType> resourceKey) {
        this(entityType.builtInRegistryHolder(),
                shape,
                List.of(modelAndTexture),
                noteBlockSound,
                Optional.of(entityPredicate),
                resourceKey != null ? resourceKey.location().getPath() : null);
    }

    private HeadType(Holder.Reference<EntityType<?>> entityType, Shape shape, List<ModelAndTexture<ModelType>> modelAndTexture, Optional<Holder<SoundEvent>> noteBlockSound, Optional<EntityPredicate> entityPredicate, @Nullable String path) {
        this(entityType,
                shape,
                modelAndTexture,
                noteBlockSound,
                entityPredicate,
                path != null ? entityType.key().location().withPath(path) : entityType.key().location());
    }

    private HeadType(Holder<EntityType<?>> entityType, Shape shape, List<ModelAndTexture<ModelType>> modelAndTexture, Optional<Holder<SoundEvent>> noteBlockSound, Optional<EntityPredicate> entityPredicate, ResourceLocation resourceLocation) {
        this(entityType,
                shape,
                new Loot(resourceLocation),
                entityPredicate,
                Optional.of(resourceLocation.toLanguageKey().replace('/', '.')),
                true,
                noteBlockSound,
                modelAndTexture);
    }

    public static ResourceKey<LootTable> createLootTable(ResourceKey<HeadType> resourceKey) {
        return createLootTable(resourceKey.location());
    }

    public static ResourceKey<LootTable> createLootTable(ResourceLocation resourceLocation) {
        return ResourceKey.create(Registries.LOOT_TABLE, resourceLocation.withPrefix("entities/heads/"));
    }

    public static String createDescriptionId(EntityType<?> entityType, ResourceKey<HeadType> resourceKey) {
        ResourceLocation resourceLocation = BuiltInRegistries.ENTITY_TYPE.getKey(entityType)
                .withPath(resourceKey.location().getPath());
        return resourceLocation.toLanguageKey(ModRegistry.MOB_HEAD_BLOCK.value().getDescriptionId()).replace('/', '.');
    }

    public Component getName(String descriptionId) {
        return Component.translatable(this.customName.map((String s) -> descriptionId + "." + s).orElse(descriptionId));
    }

    public boolean matches(Entity entity) {
        // TODO possible turn into either for entity type or predicate and include the type in the predicate
        if (this.entityType().value() != entity.getType()) {
            return false;
        } else {
            return this.entityPredicate().isEmpty() || this.entityPredicate()
                    .get()
                    .matches((ServerLevel) entity.level(), entity.position(), entity);
        }
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

        public Shape(double size) {
            this(size, size);
        }

        public Shape(double width, double height) {
            this(width, height, width);
        }

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

        public Shape scale(float scale) {
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
        public static final Loot DEFAULT = new Loot(Optional.empty(), true);
        public static final Codec<Loot> CODEC = RecordCodecBuilder.create(instance -> instance.group(ResourceKey.codec(
                                Registries.LOOT_TABLE).optionalFieldOf("loot_table").forGetter(Loot::lootTable),
                        Codec.BOOL.optionalFieldOf("charged_creeper_drop", true).forGetter(Loot::chargedCreeperDrop))
                .apply(instance, Loot::new));
        public static final StreamCodec<ByteBuf, Loot> STREAM_CODEC = StreamCodec.composite(ResourceKey.streamCodec(
                        Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
                Loot::lootTable,
                ByteBufCodecs.BOOL,
                Loot::chargedCreeperDrop,
                Loot::new);

        public Loot(ResourceLocation resourceLocation) {
            this(Optional.of(createLootTable(resourceLocation)), true);
        }
    }

    /**
     * Basically a server-side implementation of {@code ModelLayerLocation}.
     */
    public record ModelType(ResourceLocation model) {
        public static final ExtraCodecs.LateBoundIdMapper<ResourceLocation, ModelType> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
        public static final Codec<ModelType> CODEC = ID_MAPPER.codec(ResourceLocation.CODEC);
        public static final ModelType DEFAULT = register("player_head");
        public static final ModelType ENDERMAN = register("enderman_head");
        public static final ModelType ENDERMAN_EYES = register("enderman_head", "eyes");
        public static final ModelType BLAZE = register("blaze_head");
        public static final ModelType TEMPERATE_COW = register("temperate_cow_head");
        public static final ModelType WARM_COW = register("warm_cow_head");
        public static final ModelType COLD_COW = register("cold_cow_head");
        public static final ModelType OCELOT = register("ocelot_head");

        private static ModelType register(String model) {
            return register(model, null);
        }

        private static ModelType register(String model, @Nullable String layer) {
            ResourceLocation resourceLocation = AllTheHeads.id(model).withSuffix(layer != null ? "/" + layer : "");
            ModelType modelType = new ModelType(resourceLocation);
            ID_MAPPER.put(resourceLocation, modelType);
            return modelType;
        }
    }
}
