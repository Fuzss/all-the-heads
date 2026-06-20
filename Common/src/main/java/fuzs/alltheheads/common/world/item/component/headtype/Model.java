package fuzs.alltheheads.common.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.variant.ModelAndTexture;

import java.util.Optional;

/**
 * @see ModelAndTexture
 */
public record Model(ModelType model,
                    Optional<ClientAsset.ResourceTexture> asset,
                    Optional<Color> color,
                    Optional<Integer> blockLight) {
    public static final Codec<Model> CODEC = RecordCodecBuilder.create(instance -> instance.group(ModelType.CODEC.optionalFieldOf(
                            "model",
                            ModelType.MOB).forGetter(Model::model),
                    ClientAsset.ResourceTexture.CODEC.optionalFieldOf("asset_id").forGetter(Model::asset),
                    Color.CODEC.optionalFieldOf("color").forGetter(Model::color),
                    Codec.intRange(0, 15).optionalFieldOf("block_light").forGetter(Model::blockLight))
            .apply(instance, Model::new));

    public Model(ModelType modelType, Identifier assetId, Optional<Color> color, Optional<Integer> blockLight) {
        this(modelType, Optional.of(new ClientAsset.ResourceTexture(assetId)), color, blockLight);
    }

    public Model(ModelType modelType, Identifier assetId) {
        this(modelType, assetId, Optional.empty(), Optional.empty());
    }
}
