package fuzs.alltheheads.common.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.common.AllTheHeads;
import fuzs.alltheheads.common.init.ModRegistry;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public record HeadType(Optional<ResourceKey<LootItemCondition>> entityPredicate,
                       Shape shape,
                       Loot loot,
                       Optional<String> customName,
                       boolean mobDisguise,
                       Optional<Holder<SoundEvent>> noteBlockSound,
                       List<Model> models) {
    public static final Codec<HeadType> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(ResourceKey.codec(
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
    private static final ResourceKey<LootItemCondition> EMPTY_PREDICATE = ResourceKey.create(Registries.PREDICATE,
            AllTheHeads.id("empty"));

    private HeadType(Shape shape, Optional<String> customName, Optional<Holder<SoundEvent>> noteBlockSound, List<Model> models) {
        this(Optional.empty(), shape, new Loot(Optional.empty(), true), customName, false, noteBlockSound, models);
    }

    public static Builder builder() {
        return new Builder();
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
    public Stream<Holder<EntityType<?>>> getEntityTypes(HolderLookup.Provider context) {
        return this.entityPredicate()
                .flatMap(context::get)
                .map(Holder.Reference::value)
                .flatMap((LootItemCondition condition) -> {
                    if (condition instanceof LootItemEntityPropertyCondition entityCondition) {
                        return entityCondition.predicate();
                    } else {
                        return Optional.empty();
                    }
                })
                .stream()
                .flatMap((EntityPredicate entityPredicate) -> entityPredicate.parts.values()
                        .stream()
                        .mapMulti((EntitySubPredicate predicate, Consumer<EntityTypePredicate> consumer) -> {
                            if (predicate instanceof EntityTypePredicate typePredicate) {
                                consumer.accept(typePredicate);
                            }
                        })
                        .map(EntityTypePredicate::types)
                        .flatMap(HolderSet::stream));
    }

    /**
     * This is only used during data-generation; hence it's ok to filter this out on-demand.
     */
    public Holder<EntityType<?>> getEntityType(HolderLookup.Provider context) {
        return this.getEntityTypes(context).findFirst().orElseThrow();
    }

    public Component getName(String descriptionId) {
        return Component.translatable(this.customName.map((String name) -> descriptionId + "." + name)
                .orElse(descriptionId));
    }

    public boolean matches(ServerLevel serverLevel, Entity entity) {
        LootParams lootParams = new LootParams.Builder(serverLevel).withParameter(LootContextParams.THIS_ENTITY, entity)
                .create(ModRegistry.HEAD_CONTEXT_KEY_SET);
        LootContext context = new LootContext.Builder(lootParams).create(Optional.empty());
        return serverLevel.registryAccess()
                .lookupOrThrow(Registries.PREDICATE)
                .getOrThrow(this.entityPredicate().orElseThrow())
                .value()
                .test(context);
    }
}
