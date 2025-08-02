package fuzs.alltheheads.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.variant.ModelAndTexture;

import java.util.Optional;

public record Model(ModelAndTexture<ModelType> model, Optional<Integer> color, Optional<Integer> blockLight) {
    public static final Codec<Model> CODEC = RecordCodecBuilder.create(instance -> instance.group(ModelAndTexture.codec(
                            ModelType.CODEC,
                            ModelType.MOB).forGetter(Model::model),
                    ExtraCodecs.RGB_COLOR_CODEC.optionalFieldOf("color").forGetter(Model::color),
                    Codec.intRange(0, 15).optionalFieldOf("block_light").forGetter(Model::blockLight))
            .apply(instance, Model::new));

    public Model(ModelType modelType, ResourceLocation assetId) {
        this(new ModelAndTexture<>(modelType, assetId), Optional.empty(), Optional.empty());
    }
}
