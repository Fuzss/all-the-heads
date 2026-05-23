package fuzs.alltheheads.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.WalkAnimationState;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

/**
 * @see CustomHeadLayer
 */
public class MobHeadLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
    private final Function<ModelType, SkullModelBase> skullModelGetter;
    private final float scaleX;
    private final float scaleY;
    private final float scaleZ;

    public MobHeadLayer(RenderLayerParent<T, M> renderer, EntityRendererProvider.Context context, float scaleX, float scaleY, float scaleZ) {
        super(renderer);
        this.skullModelGetter = MobHeadBlockRenderer.createSkullModels(context.getModelSet());
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        Holder<HeadType> holder = itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
        if (holder != null) {
            poseStack.pushPose();
            poseStack.scale(this.scaleX, this.scaleY, this.scaleZ);
            boolean isVillagerLike = livingEntity instanceof Villager || livingEntity instanceof ZombieVillager;
            if (livingEntity.isBaby() && !(livingEntity instanceof Villager)) {
                poseStack.translate(0.0F, 0.03125F, 0.0F);
                poseStack.scale(0.7F, 0.7F, 0.7F);
                poseStack.translate(0.0F, 1.0F, 0.0F);
            }

            this.getParentModel().getHead().translateAndRotate(poseStack);
            poseStack.scale(1.1875F, -1.1875F, -1.1875F);
            if (isVillagerLike) {
                poseStack.translate(0.0F, 0.0625F, 0.0F);
            }

            poseStack.translate(-0.5, 0.0, -0.5);
            WalkAnimationState walkAnimationState;
            if (livingEntity.getVehicle() instanceof LivingEntity livingEntity2) {
                walkAnimationState = livingEntity2.walkAnimation;
            } else {
                walkAnimationState = livingEntity.walkAnimation;
            }

            float wornHeadAnimationPos = walkAnimationState.position(partialTick);
            MobHeadBlockRenderer.renderSkull(null,
                    180.0F,
                    wornHeadAnimationPos,
                    poseStack,
                    bufferSource,
                    packedLight,
                    this.skullModelGetter,
                    holder,
                    false,
                    ageInTicks);
            poseStack.popPose();
        }
    }
}
