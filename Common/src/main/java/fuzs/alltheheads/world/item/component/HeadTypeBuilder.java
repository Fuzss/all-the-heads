package fuzs.alltheheads.world.item.component;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public final class HeadTypeBuilder {
    private final EntityType<?> entityType;
    private final List<Consumer<EntityPredicate.Builder>> entityPredicate = new ArrayList<>();
    private HeadType.Shape shape = new HeadType.Shape(8.0, 8.0, 8.0);
    private double scale = 1.0;
    private final List<HeadType.Model> models = new ArrayList<>();
    private Optional<Holder<SoundEvent>> noteBlockSound = Optional.empty();
    private Optional<ResourceKey<LootTable>> lootTable = Optional.empty();
    private boolean chargedCreeperDrop = true;
    private boolean mobDisguise = true;
    private Optional<String> customName = Optional.empty();

    HeadTypeBuilder(EntityType<?> entityType) {
        this.entityType = entityType;
        this.entityPredicate((EntityPredicate.Builder builder) -> {
            builder.entityType(EntityTypePredicate.of(BuiltInRegistries.ENTITY_TYPE, entityType));
        });
    }

    public HeadTypeBuilder entityPredicate(Consumer<EntityPredicate.Builder> entityPredicate) {
        this.entityPredicate.add(entityPredicate);
        return this;
    }

    public HeadTypeBuilder shape(double size) {
        return this.shape(size, size);
    }

    public HeadTypeBuilder shape(double width, double height) {
        return this.shape(width, height, width);
    }

    public HeadTypeBuilder shape(double width, double height, double depth) {
        return this.shape(new HeadType.Shape(width, height, depth));
    }

    public HeadTypeBuilder shape(HeadType.Shape shape) {
        this.shape = shape;
        return this;
    }

    public HeadTypeBuilder scale(double scale) {
        this.scale = scale;
        return this;
    }

    public HeadTypeBuilder model(HeadType.ModelType modelType, ResourceLocation assetId) {
        this.models.add(new HeadType.Model(modelType, assetId));
        return this;
    }

    public HeadTypeBuilder model(HeadType.ModelType modelType, ResourceLocation assetId, int color) {
        this.models.add(new HeadType.Model(new ModelAndTexture<>(modelType, assetId), color));
        return this;
    }

    public HeadTypeBuilder noteBlockSound(SoundEvent noteBlockSound) {
        this.noteBlockSound = Optional.of(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(noteBlockSound));
        return this;
    }

    public HeadTypeBuilder noteBlockSound(Holder<SoundEvent> noteBlockSound) {
        this.noteBlockSound = Optional.of(noteBlockSound);
        return this;
    }

    public HeadTypeBuilder lootTable(ResourceKey<HeadType> resourceKey) {
        ResourceLocation resourceLocation = this.getNamespacedLocation(this.entityType, resourceKey);
        this.lootTable = Optional.of(ResourceKey.create(Registries.LOOT_TABLE,
                resourceLocation.withPrefix("entities/heads/")));
        return this;
    }

    public HeadTypeBuilder chargedCreeperDrop(boolean chargedCreeperDrop) {
        this.chargedCreeperDrop = chargedCreeperDrop;
        return this;
    }

    public HeadTypeBuilder mobDisguise(boolean mobDisguise) {
        this.mobDisguise = mobDisguise;
        return this;
    }

    public HeadTypeBuilder customName(ResourceKey<HeadType> resourceKey) {
        this.customName = Optional.of(HeadType.customName(this.entityType, resourceKey, null));
        return this;
    }

    public void build(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey) {
        this.lootTable(resourceKey).customName(resourceKey);
        context.register(resourceKey,
                new HeadType(this.buildEntityPredicate(),
                        this.shape.scale(this.scale),
                        this.buildLoot(),
                        this.customName,
                        this.mobDisguise,
                        this.noteBlockSound,
                        ImmutableList.copyOf(this.models)));
    }

    private EntityPredicate buildEntityPredicate() {
        EntityPredicate.Builder builder = EntityPredicate.Builder.entity();
        this.entityPredicate.forEach((Consumer<EntityPredicate.Builder> builderConsumer) -> {
            builderConsumer.accept(builder);
        });
        return builder.build();
    }

    private HeadType.Loot buildLoot() {
        return new HeadType.Loot(this.lootTable, this.chargedCreeperDrop);
    }

    static ResourceLocation getNamespacedLocation(EntityType<?> entityType, ResourceKey<HeadType> resourceKey) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType).withPath(resourceKey.location().getPath());
    }
}
