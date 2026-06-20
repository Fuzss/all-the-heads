package fuzs.alltheheads.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.common.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.common.client.renderer.blockentity.MobHeadRenderer;
import fuzs.alltheheads.common.client.renderer.blockentity.SkullBlockLayer;
import fuzs.alltheheads.common.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.common.api.client.renderer.v1.RenderStateExtraData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.Holder;

import java.util.Optional;
import java.util.function.Function;

/**
 * @see CustomHeadLayer
 */
public class MobHeadLayer<S extends LivingEntityRenderState, M extends EntityModel<S> & HeadedModel> extends RenderLayer<S, M> {
    private final Function<ModelType, SkullBlockLayer> skullLayerGetter;
    private final CustomHeadLayer.Transforms transforms;

    public MobHeadLayer(RenderLayerParent<S, M> renderer, EntityRendererProvider.Context context, CustomHeadLayer.Transforms transforms) {
        super(renderer);
        this.skullLayerGetter = MobHeadRenderer.createSkullModels(context.getModelSet(),
                context.getBlockModelResolver());
        this.transforms = transforms;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        if (RenderStateExtraData.has(state, CustomHeadLayerHandler.HEAD_TYPE_RENDER_PROPERTY)) {
            poseStack.pushPose();
            poseStack.scale(this.transforms.horizontalScale(), 1.0F, this.transforms.horizontalScale());
            M parentModel = this.getParentModel();
            parentModel.root().translateAndRotate(poseStack);
            parentModel.translateToHead(poseStack);
            poseStack.translate(0.0F, this.transforms.skullYOffset(), 0.0F);
            poseStack.scale(1.1875F, -1.1875F, -1.1875F);
            poseStack.translate(-0.5, 0.0, -0.5);
            Holder<HeadType> headType = RenderStateExtraData.getOrDefault(state,
                    CustomHeadLayerHandler.HEAD_TYPE_RENDER_PROPERTY,
                    Optional.empty()).orElse(null);
            poseStack.mulPose(MobHeadRenderState.createGroundTransformation(headType, false));
            MobHeadRenderState headState = new MobHeadRenderState(headType,
                    state.wornHeadAnimationPos,
                    state.ageInTicks,
                    lightCoords,
                    state.outlineColor);
            MobHeadRenderer.submitSkull(headState, poseStack, submitNodeCollector, this.skullLayerGetter);
            poseStack.popPose();
        }
    }
}
