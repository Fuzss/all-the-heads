package fuzs.alltheheads.common.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.common.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryFixedCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Optional;

public sealed interface HeadType permits HeadType.Local, HeadType.Shared {
    Codec<Holder<HeadType>> CODEC = RegistryFixedCodec.create(ModRegistry.HEAD_REGISTRY_KEY);
    StreamCodec<RegistryFriendlyByteBuf, Holder<HeadType>> STREAM_CODEC = ByteBufCodecs.holderRegistry(ModRegistry.HEAD_REGISTRY_KEY);
    Codec<HeadType> DIRECT_CODEC = RecordCodecBuilder.<HeadType>create(instance -> instance.group(ResourceKey.codec(
                                    Registries.PREDICATE)
                            .xmap(Optional::of, Optional::orElseThrow)
                            .fieldOf("entity_predicate")
                            .forGetter(HeadType::entityPredicate),
                    Shape.CODEC.fieldOf("shape").forGetter(HeadType::shape),
                    Loot.CODEC.forGetter(HeadType::loot),
                    Codec.STRING.optionalFieldOf("custom_name").forGetter(HeadType::customName),
                    Codec.BOOL.optionalFieldOf("mob_disguise", true).forGetter(HeadType::mobDisguise),
                    BuiltInRegistries.SOUND_EVENT.holderByNameCodec()
                            .optionalFieldOf("note_block_sound")
                            .forGetter(HeadType::noteBlockSound),
                    Model.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("models").forGetter(HeadType::models))
            .apply(instance, Server::new)).validate(HeadType::requireData);
    Codec<HeadType> DIRECT_NETWORK_CODEC = RecordCodecBuilder.<HeadType>create(instance -> instance.group(Shape.CODEC.fieldOf(
                            "shape").forGetter(HeadType::shape),
                    Codec.STRING.optionalFieldOf("custom_name").forGetter(HeadType::customName),
                    BuiltInRegistries.SOUND_EVENT.holderByNameCodec()
                            .optionalFieldOf("note_block_sound")
                            .forGetter(HeadType::noteBlockSound),
                    Model.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("models").forGetter(HeadType::models))
            .apply(instance, Local::new));

    private static DataResult<HeadType> requireData(HeadType headType) {
        return headType instanceof Shared ? DataResult.success(headType) :
                DataResult.error(() -> "Cannot serialize local head type: " + headType);
    }

    static Builder builder(EntityType<?> entityType) {
        return new Builder(entityType);
    }

    static Identifier customName(ResourceKey<HeadType> resourceKey) {
        String joinedPath = String.join(":", resourceKey.identifier().getPath().split("/", 2));
        return Optional.ofNullable(Identifier.tryParse(joinedPath))
                .orElse(resourceKey.identifier())
                .withPath((String path) -> {
                    return path.replace('/', '_');
                });
    }

    Shape shape();

    Optional<String> customName();

    Optional<Holder<SoundEvent>> noteBlockSound();

    List<Model> models();

    default Component getName(String descriptionId) {
        return Component.translatable(this.customName()
                .map((String name) -> descriptionId + "." + name)
                .orElse(descriptionId));
    }

    default Optional<ResourceKey<LootItemCondition>> entityPredicate() {
        return Optional.empty();
    }

    default Loot loot() {
        return Loot.EMPTY;
    }

    default boolean mobDisguise() {
        return false;
    }

    default boolean matches(ServerLevel serverLevel, Entity entity) {
        return false;
    }

    default Optional<Holder<EntityType<?>>> entityType() {
        return Optional.empty();
    }

    record Local(Shape shape,
                 Optional<String> customName,
                 Optional<Holder<SoundEvent>> noteBlockSound,
                 List<Model> models) implements HeadType {
    }

    abstract sealed class Shared implements HeadType permits HeadType.Server, HeadType.Data {
        private final Optional<ResourceKey<LootItemCondition>> entityPredicate;
        private final Shape shape;
        private final Loot loot;
        private final Optional<String> customName;
        private final boolean mobDisguise;
        private final Optional<Holder<SoundEvent>> noteBlockSound;
        private final List<Model> models;

        protected Shared(Optional<ResourceKey<LootItemCondition>> entityPredicate, Shape shape, Loot loot, Optional<String> customName, boolean mobDisguise, Optional<Holder<SoundEvent>> noteBlockSound, List<Model> models) {
            this.entityPredicate = entityPredicate;
            this.shape = shape;
            this.loot = loot;
            this.customName = customName;
            this.mobDisguise = mobDisguise;
            this.noteBlockSound = noteBlockSound;
            this.models = models;
        }

        @Override
        public Optional<ResourceKey<LootItemCondition>> entityPredicate() {
            return this.entityPredicate;
        }

        @Override
        public Shape shape() {
            return this.shape;
        }

        @Override
        public Loot loot() {
            return this.loot;
        }

        @Override
        public Optional<String> customName() {
            return this.customName;
        }

        @Override
        public boolean mobDisguise() {
            return this.mobDisguise;
        }

        @Override
        public Optional<Holder<SoundEvent>> noteBlockSound() {
            return this.noteBlockSound;
        }

        @Override
        public List<Model> models() {
            return this.models;
        }

        @Override
        public boolean matches(ServerLevel serverLevel, Entity entity) {
            LootParams lootParams = new LootParams.Builder(serverLevel).withParameter(LootContextParams.THIS_ENTITY,
                    entity).create(ModRegistry.HEAD_CONTEXT_KEY_SET.value());
            LootContext context = new LootContext.Builder(lootParams).create(Optional.empty());
            return serverLevel.getServer()
                    .reloadableRegistries()
                    .lookup()
                    .lookupOrThrow(Registries.PREDICATE)
                    .getOrThrow(this.entityPredicate.orElseThrow())
                    .value()
                    .test(context);
        }
    }

    final class Server extends Shared {
        public Server(Optional<ResourceKey<LootItemCondition>> entityPredicate, Shape shape, Loot loot, Optional<String> customName, boolean mobDisguise, Optional<Holder<SoundEvent>> noteBlockSound, List<Model> models) {
            super(entityPredicate, shape, loot, customName, mobDisguise, noteBlockSound, models);
        }
    }

    final class Data extends Shared {
        private final Holder<EntityType<?>> entityType;

        public Data(Optional<ResourceKey<LootItemCondition>> entityPredicate, Shape shape, Loot loot, Optional<String> customName, boolean mobDisguise, Optional<Holder<SoundEvent>> noteBlockSound, List<Model> models, Holder<EntityType<?>> entityType) {
            super(entityPredicate, shape, loot, customName, mobDisguise, noteBlockSound, models);
            this.entityType = entityType;
        }

        @Override
        public Optional<Holder<EntityType<?>>> entityType() {
            return Optional.of(this.entityType);
        }
    }
}
