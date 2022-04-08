package fuzs.alltheheads.registry;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Optional;

public class ModSkullType implements SkullBlock.Type {
    private static final String HEAD_SUFFIX = "_head";
    private static final String WALL_HEAD_SUFFIX = "_wall_head";
    private static final String WALL_TRANSLATION_KEY = "block.alltheheads.wall";
    private static final String HEAD_TRANSLATION_KEY = "block.alltheheads.head";
    private static final String SKULL_TRANSLATION_KEY = "block.alltheheads.skull";

    private final ResourceLocation mobType;
    private final ResourceLocation textureLocation;
    private final ResourceLocation modelLocation;
    private final String layerLocation;
    private final boolean skull;

    private EntityType<?> entityType;
    private String id;
    private String wallId;

    private ModSkullType(ResourceLocation mobType, ResourceLocation textureLocation, ResourceLocation modelLocation, String layerLocation, boolean skull) {
        this.mobType = mobType;
        this.textureLocation = textureLocation;
        this.modelLocation = modelLocation;
        this.layerLocation = layerLocation;
        this.skull = skull;
    }

    public String getId() {
        return this.mobType.getPath().concat(HEAD_SUFFIX);
    }

    public String getWallId() {
        return this.mobType.getPath().concat(WALL_HEAD_SUFFIX);
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public Pair<ResourceLocation, String> getModelLayerLocation() {
        return Pair.of(this.modelLocation, this.layerLocation);
    }

    public MutableComponent getName() {
        this.resolveEntityType();
        return this.getName(this.entityType.getDescription());
    }

    public MutableComponent getWallName() {
        this.resolveEntityType();
        return this.getName(new TranslatableComponent(WALL_TRANSLATION_KEY, this.entityType.getDescription()));
    }

    private void resolveEntityType() {
        if (this.entityType == null) {
            this.entityType = Optional.ofNullable(ForgeRegistries.ENTITIES.getValue(this.mobType))
                    .orElseThrow(() -> new RuntimeException("Entity type was resolved too early!"));
        }
    }

    private MutableComponent getName(Component description) {
        return new TranslatableComponent(this.skull ? SKULL_TRANSLATION_KEY : HEAD_TRANSLATION_KEY, description);
    }

    public static class Builder {
        private ResourceLocation mobType;
        private ResourceLocation textureLocation;
        private ResourceLocation modelLocation;
        private String layerLocation;
        private boolean skull;

        public Builder mobType(String path) {
            return this.mobType(new ResourceLocation(path));
        }

        public Builder mobType(ResourceLocation mobType) {
            this.mobType = mobType;
            return this;
        }

        public Builder textureLocation(String path) {
            return this.textureLocation(new ResourceLocation(path));
        }

        public Builder textureLocation(ResourceLocation textureLocation) {
            this.textureLocation = textureLocation;
            return this;
        }

        public Builder modelLayerLocation(String path) {
            return this.modelLayerLocation(path, "main");
        }

        public Builder modelLayerLocation(ResourceLocation model) {
            return this.modelLayerLocation(model, "main");
        }

        public Builder modelLayerLocation(String path, String layer) {
            return this.modelLayerLocation(new ResourceLocation(path), layer);
        }

        public Builder modelLayerLocation(ResourceLocation model, String layer) {
            this.modelLocation = model;
            this.layerLocation = layer;
            return this;
        }

        public Builder skull() {
            this.skull = true;
            return this;
        }

        public ModSkullType build() {
            Objects.requireNonNull(this.mobType);
            Objects.requireNonNull(this.textureLocation);
            Objects.requireNonNull(this.modelLocation);
            return new ModSkullType(this.mobType, this.textureLocation, this.modelLocation, this.layerLocation, this.skull);
        }
    }
}
