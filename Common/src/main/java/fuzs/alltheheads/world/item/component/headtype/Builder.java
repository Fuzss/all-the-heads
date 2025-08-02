package fuzs.alltheheads.world.item.component.headtype;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
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

public final class Builder {
    private final EntityType<?> entityType;
    private final List<Consumer<EntityPredicate.Builder>> entityPredicate = new ArrayList<>();
    private Shape shape = new Shape(8.0, 8.0, 8.0);
    private double scale = 1.0;
    private final List<Model> models = new ArrayList<>();
    private Optional<Holder<SoundEvent>> noteBlockSound = Optional.empty();
    private Optional<ResourceKey<LootTable>> lootTable = Optional.empty();
    private boolean chargedCreeperDrop = true;
    private boolean mobDisguise = true;
    private Optional<String> customName = Optional.empty();

    Builder(EntityType<?> entityType) {
        this.entityType = entityType;
        this.entityPredicate((EntityPredicate.Builder builder) -> {
            builder.entityType(EntityTypePredicate.of(BuiltInRegistries.ENTITY_TYPE, entityType))
                    .flags(EntityFlagsPredicate.Builder.flags().setIsBaby(false));
        });
    }

    public Builder entityPredicate(Consumer<EntityPredicate.Builder> entityPredicate) {
        this.entityPredicate.add(entityPredicate);
        return this;
    }

    public Builder shape(double width, double height, double depth) {
        return this.shape(new Shape(width, height, depth));
    }

    public Builder shape(Shape shape) {
        this.shape = shape;
        return this;
    }

    public Builder scale(double scale) {
        this.scale = scale;
        return this;
    }

    public Builder model(ModelType modelType, ResourceLocation assetId) {
        this.models.add(new Model(modelType, assetId));
        return this;
    }

    public Builder dyedModel(ModelType modelType, ResourceLocation assetId, int color) {
        this.models.add(new Model(new ModelAndTexture<>(modelType, assetId), Optional.of(color), Optional.empty()));
        return this;
    }

    public Builder litModel(ModelType modelType, ResourceLocation assetId, int blockLight) {
        this.models.add(new Model(new ModelAndTexture<>(modelType, assetId),
                Optional.empty(),
                Optional.of(blockLight)));
        return this;
    }

    public Builder noteBlockSound(SoundEvent noteBlockSound) {
        this.noteBlockSound = Optional.of(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(noteBlockSound));
        return this;
    }

    public Builder noteBlockSound(Holder<SoundEvent> noteBlockSound) {
        this.noteBlockSound = Optional.of(noteBlockSound);
        return this;
    }

    public Builder lootTable(ResourceKey<HeadType> resourceKey) {
        ResourceLocation resourceLocation = this.getNamespacedLocation(this.entityType, resourceKey);
        this.lootTable = Optional.of(ResourceKey.create(Registries.LOOT_TABLE,
                resourceLocation.withPrefix("entities/heads/")));
        return this;
    }

    public Builder chargedCreeperDrop(boolean chargedCreeperDrop) {
        this.chargedCreeperDrop = chargedCreeperDrop;
        return this;
    }

    public Builder mobDisguise(boolean mobDisguise) {
        this.mobDisguise = mobDisguise;
        return this;
    }

    public Builder customName(ResourceKey<HeadType> resourceKey) {
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

    private Loot buildLoot() {
        return new Loot(this.lootTable, this.chargedCreeperDrop);
    }

    static ResourceLocation getNamespacedLocation(EntityType<?> entityType, ResourceKey<HeadType> resourceKey) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType).withPath(resourceKey.location().getPath());
    }
}
