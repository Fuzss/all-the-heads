package fuzs.alltheheads.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.client.renderer.v1.special.SpecialModelRenderer;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * @see net.minecraft.client.renderer.special.SkullSpecialRenderer
 */
public class MobHeadSpecialRenderer implements SpecialModelRenderer<@Nullable Holder<HeadType>> {
    private final Function<ModelType, SkullModelBase> skullModelGetter;
    private final float animation;

    public MobHeadSpecialRenderer(Function<ModelType, SkullModelBase> skullModelGetter, float animation) {
        this.skullModelGetter = skullModelGetter;
        this.animation = animation;
    }

    @Override
    public void render(@Nullable Holder<HeadType> headType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean hasFoilType) {
        MobHeadBlockRenderer.renderSkull(null,
                180.0F,
                this.animation,
                poseStack,
                bufferSource,
                packedLight,
                this.skullModelGetter,
                headType,
                true,
                0.0F);
    }

    @Override
    public @Nullable Holder<HeadType> extractArgument(ItemStack itemStack) {
        return itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }

    public record Unbaked(float animation) implements SpecialModelRenderer.Unbaked<@Nullable Holder<HeadType>> {
        public static final MapCodec<MobHeadSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Codec.FLOAT.optionalFieldOf("animation", 0.0F).forGetter(MobHeadSpecialRenderer.Unbaked::animation))
                .apply(instance, MobHeadSpecialRenderer.Unbaked::new));

        public Unbaked() {
            this(0.0F);
        }

        @Override
        public MapCodec<MobHeadSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public MobHeadSpecialRenderer bake(EntityModelSet modelSet) {
            return new MobHeadSpecialRenderer(MobHeadBlockRenderer.createSkullModels(modelSet), this.animation);
        }
    }
}
