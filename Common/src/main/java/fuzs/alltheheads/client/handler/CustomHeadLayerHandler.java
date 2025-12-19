package fuzs.alltheheads.client.handler;

import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.client.renderer.entity.layers.MobHeadLayer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.client.renderer.v1.RenderStateExtraData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.Holder;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CustomHeadLayerHandler {
    public static final ContextKey<Optional<Holder<HeadType>>> HEAD_TYPE_RENDER_PROPERTY = new ContextKey<>(AllTheHeads.id(
            "head_type"));

    public static void onExtractRenderState(Entity entity, EntityRenderState renderState, float partialTick) {
        if (entity instanceof LivingEntity livingEntity
                && renderState instanceof LivingEntityRenderState livingEntityRenderState
                && livingEntityRenderState.wornHeadType == ModRegistry.MOB_SKULL_BLOCK_TYPE) {
            livingEntityRenderState.wornHeadType = null;
            ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
            Holder<HeadType> headType = itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
            RenderStateExtraData.set(renderState, HEAD_TYPE_RENDER_PROPERTY, Optional.ofNullable(headType));
        }
    }

    public static <S extends LivingEntityRenderState, M extends EntityModel<S> & HeadedModel> void addLivingEntityRenderLayers(EntityType<?> entityType, LivingEntityRenderer<?, ?, ?> entityRenderer, EntityRendererProvider.Context context) {
        CustomHeadLayer<S, M> customHeadLayer = getCustomHeadLayer(((LivingEntityRenderer<?, S, M>) entityRenderer).layers);
        if (customHeadLayer != null) {
            ((LivingEntityRenderer<?, S, M>) entityRenderer).addLayer(new MobHeadLayer<>(((LivingEntityRenderer<?, S, M>) entityRenderer),
                    context,
                    customHeadLayer.transforms));
        }
    }

    @Nullable
    private static <S extends LivingEntityRenderState, M extends EntityModel<S> & HeadedModel> CustomHeadLayer<S, M> getCustomHeadLayer(List<? extends RenderLayer<S, M>> layers) {
        for (RenderLayer<S, M> renderLayer : layers) {
            if (renderLayer instanceof CustomHeadLayer<S, M> customHeadLayer) {
                return customHeadLayer;
            }
        }

        return null;
    }
}
