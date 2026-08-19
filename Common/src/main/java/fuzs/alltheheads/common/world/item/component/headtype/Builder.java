package fuzs.alltheheads.common.world.item.component.headtype;

import com.google.common.collect.ImmutableList;
import fuzs.alltheheads.common.init.HeadTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Builder {
    private Shape shape = new Shape(8.0, 8.0, 8.0);
    private double scale = 1.0;
    private final List<Model> models = new ArrayList<>();
    private Optional<Holder<SoundEvent>> noteBlockSound = Optional.empty();
    private Optional<ResourceKey<LootTable>> lootTable = Optional.empty();
    private boolean chargedCreeperDrop = true;
    private boolean mobDisguise = true;
    private Optional<String> customName = Optional.empty();

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

    public Builder model(ModelType modelType, Identifier assetId) {
        this.models.add(new Model(modelType, assetId));
        return this;
    }

    public Builder dyedModel(ModelType modelType, Identifier assetId, Color color) {
        this.models.add(new Model(modelType, assetId, Optional.of(color), Optional.empty()));
        return this;
    }

    public Builder litModel(ModelType modelType, Identifier assetId) {
        this.models.add(new Model(modelType, assetId, Optional.empty(), Optional.of(15)));
        return this;
    }

    public Builder specialModel(ModelType modelType) {
        this.models.add(new Model(modelType, Optional.empty(), Optional.empty(), Optional.empty()));
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
        this.lootTable = Optional.of(ResourceKey.create(Registries.LOOT_TABLE,
                resourceKey.identifier().withPrefix("entities/heads/")));
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
        this.customName = Optional.of(HeadType.customName(resourceKey).toLanguageKey());
        return this;
    }

    public void build(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey) {
        if (this.lootTable.isEmpty()) {
            this.lootTable(resourceKey);
        }

        if (this.customName.isEmpty()) {
            this.customName(resourceKey);
        }

        HeadType value = new HeadType(Optional.of(HeadTypes.conditionKey(resourceKey)),
                this.shape.scale(this.scale),
                this.buildLoot(),
                this.customName,
                this.mobDisguise,
                this.noteBlockSound,
                ImmutableList.copyOf(this.models));
        context.register(resourceKey, value);
    }

    private Loot buildLoot() {
        return new Loot(this.lootTable, this.chargedCreeperDrop);
    }
}
