package fuzs.alltheheads.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import fuzs.alltheheads.client.model.ModSkullModel;
import fuzs.alltheheads.resources.SkullType;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(SkullBlockRenderer.class)
public abstract class SkullBlockRendererMixin {

    @Inject(method = "renderSkull", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"))
    private static void renderSkull$invokeScale(@Nullable Direction direction, float p_173665_, float p_173666_, PoseStack p_173667_, MultiBufferSource p_173668_, int p_173669_, SkullModelBase model, RenderType p_173671_, CallbackInfo callbackInfo) {
        if (direction != null && model instanceof ModSkullModel modSkullModel) {
            SkullType skullType = modSkullModel.getSkullType().getBaseSkullType();
            Vector3f skullSize = skullType.getSkullSize();
            // undo vanilla
            p_173667_.translate(-0.5F + direction.getStepX() * 0.25F, -0.25D, -0.5F + direction.getStepZ() * 0.25F);
            double sizeX = (direction.getAxis() == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X).choose(skullSize.x(), 0.0, skullSize.z()) / (16.0F * 2.0F);
            double sizeZ = direction.getAxis().choose(skullSize.x(), 0.0, skullSize.z()) / (16.0F * 2.0F);
            p_173667_.translate(0.5F - direction.getStepX() * (0.5F - sizeX), 0.5F - skullSize.y() / (16.0F * 2.0F), 0.5F - direction.getStepZ() * (0.5F - sizeZ));
        }
    }
}
