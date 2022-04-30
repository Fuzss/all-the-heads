package fuzs.alltheheads.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.world.item.ModSkullBlockItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CustomHeadLayer.class)
public abstract class CustomHeadLayerMixin<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {

    public CustomHeadLayerMixin(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
    }

    @ModifyVariable(method = "render", at = @At(value = "STORE", ordinal = 0))
    public SkullBlock.Type render$modifyVariableStore(SkullBlock.Type skullType, PoseStack p_116731_, MultiBufferSource p_116732_, int p_116733_, T p_116734_) {
        if (skullType == ModRegistry.MOB_SKULL_BLOCK_TYPE) {
            ItemStack stack = p_116734_.getItemBySlot(EquipmentSlot.HEAD);
            ModSkullType modSkullType = ModSkullBlockItem.readStackSkullType(stack);
            if (modSkullType != null) {
                return modSkullType;
            }
        }
        return skullType;
    }
}
