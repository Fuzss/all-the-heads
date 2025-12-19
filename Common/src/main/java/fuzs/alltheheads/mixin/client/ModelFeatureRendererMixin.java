package fuzs.alltheheads.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import fuzs.alltheheads.client.handler.CustomHeadLayerHandler;
import fuzs.puzzleslib.api.client.renderer.v1.RenderStateExtraData;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelFeatureRenderer.class)
abstract class ModelFeatureRendererMixin {

    @Inject(method = "renderModel", at = @At("HEAD"))
    private <S> void renderModel$0(CallbackInfo callback, @Local(argsOnly = true) SubmitNodeStorage.ModelSubmit<S> submit, @Share(
            "is_head_visible") LocalBooleanRef isHeadVisibleRef) {
        // Disable model head rendering, some mob heads are smaller than the player head and will not cover all of it.
        // The idea is taken from here: https://github.com/Mrbysco/Heads
        if (submit.state() instanceof EntityRenderState entityRenderState
                && submit.model() instanceof HeadedModel headedModel && RenderStateExtraData.has(entityRenderState,
                CustomHeadLayerHandler.HEAD_TYPE_RENDER_PROPERTY)) {
            isHeadVisibleRef.set(headedModel.getHead().visible);
            headedModel.getHead().visible = false;
        }
    }

    @Inject(method = "renderModel", at = @At("TAIL"))
    private <S> void renderModel$1(CallbackInfo callback, @Local(argsOnly = true) SubmitNodeStorage.ModelSubmit<S> submit, @Share(
            "is_head_visible") LocalBooleanRef isHeadVisibleRef) {
        if (submit.state() instanceof EntityRenderState entityRenderState
                && submit.model() instanceof HeadedModel headedModel && RenderStateExtraData.has(entityRenderState,
                CustomHeadLayerHandler.HEAD_TYPE_RENDER_PROPERTY)) {
            headedModel.getHead().visible = isHeadVisibleRef.get();
        }
    }
}
