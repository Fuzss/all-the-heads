package fuzs.alltheheads.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.variant.ModelAndTexture;

import java.util.Optional;

public record Model(ModelAndTexture<ModelType> model, Optional<Color> color, Optional<Integer> blockLight) {
    public static final Codec<Model> CODEC = RecordCodecBuilder.create(instance -> instance.group(ModelAndTexture.codec(
                            ModelType.CODEC,
                            ModelType.MOB).forGetter(Model::model),
                    Color.CODEC.optionalFieldOf("color").forGetter(Model::color),
                    Codec.intRange(0, 15).optionalFieldOf("block_light").forGetter(Model::blockLight))
            .apply(instance, Model::new));

    public Model(ModelType modelType, Identifier assetId) {
        this(new ModelAndTexture<>(modelType, assetId), Optional.empty(), Optional.empty());
    }
}
