package fuzs.alltheheads.mixin.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fuzs.alltheheads.client.handler.ModelBakeHandler;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Inject(method = "getModel", at = @At("HEAD"))
    public void getModel(ResourceLocation p_119342_, CallbackInfoReturnable<UnbakedModel> callbackInfoReturnable) {
        if (p_119342_.toString().contains("piglin_head")) {
            ModelBakeHandler.extracted(p_119342_, "GET");
        }
    }

    @Inject(method = "loadModel", at = @At("HEAD"))
    private void loadModel(ResourceLocation p_119363_, CallbackInfo callbackInfo) throws Exception {
        if (p_119363_.toString().contains("piglin_head")) {
            ModelBakeHandler.extracted(p_119363_, "LOAD");
        }
    }

    @Inject(method = "loadBlockModel", at = @At("HEAD"), cancellable = true)
    protected void loadBlockModel(ResourceLocation p_119365_, CallbackInfoReturnable<BlockModel> callbackInfo) throws IOException {
//        BlockModel itemModel = new BlockModel(new ResourceLocation("item/template_skull"), Lists.newArrayList(), Maps.newHashMap(), true, null, ItemTransforms.NO_TRANSFORMS, Lists.newArrayList());
//        itemModel.name = p_119365_.toString();
    }
}
