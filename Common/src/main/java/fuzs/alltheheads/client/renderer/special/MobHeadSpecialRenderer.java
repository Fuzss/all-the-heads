package fuzs.alltheheads.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadRenderer;
import fuzs.alltheheads.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemDisplayContext;
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
    public void submit(@Nullable Holder<HeadType> headType, ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
        MobHeadRenderState mobHeadRenderState = MobHeadRenderState.create(packedLight,
                180.0F,
                this.animation,
                headType,
                0.0F,
                outlineColor,
                true);
        MobHeadRenderer.submitSkull(mobHeadRenderState, poseStack, nodeCollector, this.skullModelGetter);
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        poseStack.translate(0.5F, 0.0F, 0.5F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        // there seems to be no good way to get the proper model for the model type
        SkullModelBase skullModelBase = this.skullModelGetter.apply(ModelType.DEFAULT);
        SkullModelBase.State state = new SkullModelBase.State();
        state.animationPos = this.animation;
        state.yRot = 180.0F;
        skullModelBase.setupAnim(state);
        skullModelBase.root().getExtentsForGui(poseStack, output);
    }

    @Override
    public @Nullable Holder<HeadType> extractArgument(ItemStack itemStack) {
        return itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }

    public record Unbaked(float animation) implements SpecialModelRenderer.Unbaked {
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
        public SpecialModelRenderer<?> bake(BakingContext context) {
            return new MobHeadSpecialRenderer(MobHeadRenderer.createSkullModels(context.entityModelSet()),
                    this.animation);
        }
    }
}
