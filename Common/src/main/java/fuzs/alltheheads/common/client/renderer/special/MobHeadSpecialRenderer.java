package fuzs.alltheheads.common.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.common.client.renderer.blockentity.MobHeadRenderer;
import fuzs.alltheheads.common.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;
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
    public void submit(@Nullable Holder<HeadType> headType, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        poseStack.pushPose();
        poseStack.mulPose(MobHeadRenderState.createGroundTransformation(headType, true));
        MobHeadRenderState state = new MobHeadRenderState(headType, this.animation, 0.0F, lightCoords, outlineColor);
        MobHeadRenderer.submitSkull(state, poseStack, submitNodeCollector, this.skullModelGetter);
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        // There seems to be no good way to get the proper model for the model type.
        SkullModelBase model = this.skullModelGetter.apply(ModelType.DEFAULT);
        PoseStack poseStack = new PoseStack();
        SkullModelBase.State state = new SkullModelBase.State();
        state.animationPos = this.animation;
        model.setupAnim(state);
        model.root().getExtentsForGui(poseStack, output);
    }

    @Override
    public @Nullable Holder<HeadType> extractArgument(ItemStack itemStack) {
        return itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }

    public record Unbaked(float animation) implements SpecialModelRenderer.Unbaked<@Nullable Holder<HeadType>> {
        public static final MapCodec<MobHeadSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec((RecordCodecBuilder.Instance<Unbaked> instance) -> {
            return instance.group(Codec.FLOAT.optionalFieldOf("animation", 0.0F).forGetter(Unbaked::animation))
                    .apply(instance, Unbaked::new);
        });

        public Unbaked() {
            this(0.0F);
        }

        @Override
        public MapCodec<MobHeadSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public MobHeadSpecialRenderer bake(BakingContext context) {
            return new MobHeadSpecialRenderer(MobHeadRenderer.createSkullModels(context.entityModelSet()),
                    this.animation);
        }
    }
}
