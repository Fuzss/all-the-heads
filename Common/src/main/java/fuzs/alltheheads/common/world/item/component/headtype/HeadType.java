package fuzs.alltheheads.common.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.common.init.ModRegistry;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

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

    public static Builder builder(EntityType<?> type) {
        return new Builder(HolderSet.direct(type.builtInRegistryHolder()));
    }

    public static Builder builder(HolderGetter<EntityType<?>> lookup, TagKey<EntityType<?>> tag) {
        return new Builder(lookup.getOrThrow(tag));
    }

    public static Identifier customName(ResourceKey<HeadType> resourceKey) {
        String joinedPath = String.join(":", resourceKey.identifier().getPath().split("/", 2));
        return Optional.ofNullable(Identifier.tryParse(joinedPath))
                .orElse(resourceKey.identifier())
                .withPath((String path) -> {
                    return path.replace('/', '_');
                });
    }

    /**
     * This is only used during data-generation; hence it's ok to filter this out on-demand.
     */
    public Stream<Holder<EntityType<?>>> getEntityTypes() {
        return this.entityPredicate().parts.values()
                .stream()
                .mapMulti((EntitySubPredicate predicate, Consumer<EntityTypePredicate> consumer) -> {
                    if (predicate instanceof EntityTypePredicate typePredicate) {
                        consumer.accept(typePredicate);
                    }
                })
                .map(EntityTypePredicate::types)
                .flatMap(HolderSet::stream);
    }

    /**
     * This is only used during data-generation; hence it's ok to filter this out on-demand.
     */
    public Holder<EntityType<?>> getEntityType() {
        return this.getEntityTypes().findFirst().orElseThrow();
    }

    public Component getName(String descriptionId) {
        return Component.translatable(this.customName.map((String name) -> descriptionId + "." + name)
                .orElse(descriptionId));
    }

    public boolean matches(ServerLevel serverLevel, Entity entity) {
        return this.entityPredicate().matches(serverLevel, entity.position(), entity);
    }
}
