package fuzs.alltheheads.mixin.client;

import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.world.item.ModSkullBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public abstract class BlockEntityWithoutLevelRendererMixin {

    @ModifyVariable(method = "renderByItem", at = @At(value = "STORE", ordinal = 0))
    public SkullBlock.Type renderByItem$modifyVariableStore(SkullBlock.Type skullType, ItemStack stack) {
        if (skullType == ModRegistry.MOB_SKULL_BLOCK_TYPE) {
            ModSkullType modSkullType = ModSkullBlockItem.readStackSkullType(stack);
            if (modSkullType != null) {
                return modSkullType;
            }
        }
        return skullType;
    }
}
