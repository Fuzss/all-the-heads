package fuzs.alltheheads.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

/**
 * Used for disabling head rendering for our custom skull type, so our own head layer can take over.
 *
 * @see MobHeadLayer
 */
public class ForwardingCustomHeadLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends CustomHeadLayer<T, M> {
    private final CustomHeadLayer<T, M> headLayer;

    /**
     * This copies the scale properties from the original head layer, just in case another mod tries to retrieve them
     * from this.
     */
    public ForwardingCustomHeadLayer(RenderLayerParent<T, M> renderer, CustomHeadLayer<T, M> headLayer, EntityModelSet modelSet, ItemInHandRenderer itemInHandRenderer) {
        super(renderer, modelSet, headLayer.scaleX, headLayer.scaleY, headLayer.scaleZ, itemInHandRenderer);
        this.headLayer = headLayer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.getItemBySlot(EquipmentSlot.HEAD).has(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value())) {
            this.headLayer.render(poseStack,
                    buffer,
                    packedLight,
                    livingEntity,
                    limbSwing,
                    limbSwingAmount,
                    partialTicks,
                    ageInTicks,
                    netHeadYaw,
                    headPitch);
        }
    }
}
