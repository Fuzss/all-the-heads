package fuzs.alltheheads.client.handler;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.client.renderer.entity.layers.MobHeadLayer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomHeadLayerHandler {
    private static boolean isHeadVisible;

    public static <T extends LivingEntity, M extends EntityModel<T> & HeadedModel> void addLivingEntityRenderLayers(EntityType<?> entityType, LivingEntityRenderer<?, ?> entityRenderer, EntityRendererProvider.Context context) {
        CustomHeadLayer<T, M> customHeadLayer = getCustomHeadLayer(((LivingEntityRenderer<T, M>) entityRenderer).layers);
        if (customHeadLayer != null) {
            ((LivingEntityRenderer<T, M>) entityRenderer).addLayer(new MobHeadLayer<>(((LivingEntityRenderer<T, M>) entityRenderer),
                    context,
                    customHeadLayer.scaleX,
                    customHeadLayer.scaleY,
                    customHeadLayer.scaleZ));
        }
    }

    @Nullable
    private static <T extends LivingEntity, M extends EntityModel<T> & HeadedModel> CustomHeadLayer<T, M> getCustomHeadLayer(List<? extends RenderLayer<T, M>> layers) {
        for (RenderLayer<T, M> renderLayer : layers) {
            if (renderLayer instanceof CustomHeadLayer<T, M> customHeadLayer) {
                return customHeadLayer;
            }
        }

        return null;
    }

    public static <T extends LivingEntity, M extends EntityModel<T>> EventResult onBeforeRenderEntity(T livingEntity, LivingEntityRenderer<T, M> entityRenderer, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        // disable model head rendering, some mob heads are smaller than the player head and will not cover all of it
        // the idea is taken from here: https://github.com/Mrbysco/Heads
        if (entityRenderer.getModel() instanceof HeadedModel headedModel) {
            ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
            if (itemStack.has(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value())) {
                isHeadVisible = headedModel.getHead().visible;
                headedModel.getHead().visible = false;
            }
        }

        return EventResult.PASS;
    }

    public static <T extends LivingEntity, M extends EntityModel<T>> void onAfterRenderEntity(T livingEntity, LivingEntityRenderer<T, M> entityRenderer, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entityRenderer.getModel() instanceof HeadedModel headedModel) {
            ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
            if (itemStack.has(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value())) {
                headedModel.getHead().visible = isHeadVisible;
            }
        }
    }
}
