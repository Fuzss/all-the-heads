package fuzs.alltheheads.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadRenderer;
import fuzs.alltheheads.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.common.api.client.renderer.v1.RenderStateExtraData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.object.skull.SkullModelBase;
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
    private final Function<ModelType, SkullModelBase> skullModelGetter;
    private final CustomHeadLayer.Transforms transforms;

    public MobHeadLayer(RenderLayerParent<S, M> renderer, EntityRendererProvider.Context context, CustomHeadLayer.Transforms transforms) {
        super(renderer);
        this.skullModelGetter = MobHeadRenderer.createSkullModels(context.getModelSet());
        this.transforms = transforms;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, S renderState, float yRot, float xRot) {
        if (RenderStateExtraData.has(renderState, CustomHeadLayerHandler.HEAD_TYPE_RENDER_PROPERTY)) {
            poseStack.pushPose();
            poseStack.scale(this.transforms.horizontalScale(), 1.0F, this.transforms.horizontalScale());
            M entityModel = this.getParentModel();
            entityModel.root().translateAndRotate(poseStack);
            entityModel.getHead().translateAndRotate(poseStack);
            poseStack.translate(0.0F, this.transforms.skullYOffset(), 0.0F);
            poseStack.scale(1.1875F, -1.1875F, -1.1875F);
            poseStack.translate(-0.5, 0.0, -0.5);
            Optional<Holder<HeadType>> headType = RenderStateExtraData.getOrDefault(renderState,
                    CustomHeadLayerHandler.HEAD_TYPE_RENDER_PROPERTY,
                    Optional.empty());
            MobHeadRenderState mobHeadRenderState = MobHeadRenderState.create(packedLight,
                    180.0F,
                    renderState.wornHeadAnimationPos,
                    headType.orElse(null),
                    renderState.ageInTicks,
                    renderState.outlineColor,
                    false);
            MobHeadRenderer.submitSkull(mobHeadRenderState, poseStack, nodeCollector, this.skullModelGetter);
            poseStack.popPose();
        }
    }
}
