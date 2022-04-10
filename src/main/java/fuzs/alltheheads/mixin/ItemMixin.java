package fuzs.alltheheads.mixin;

import fuzs.alltheheads.registry.ModRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AbstractSkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "allowdedIn", at = @At("HEAD"), cancellable = true)
    protected void allowdedIn(CreativeModeTab tab, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (tab == ModRegistry.ALL_THE_HEADS_CREATIVE_TAB) {
            if ((Object) this instanceof BlockItem item && item.getBlock() instanceof AbstractSkullBlock) {
                callbackInfo.setReturnValue(true);
            }
        }
    }
}
