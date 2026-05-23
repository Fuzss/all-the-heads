package fuzs.alltheheads.core;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import fuzs.puzzleslib.api.client.init.v1.BuiltinItemRenderer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface SpecialModelRenderer<T> extends BuiltinItemRenderer {
    @Override
    default void renderByItem(ItemStack itemStack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int lightCoords, int overlayCoords) {
        this.render(this.extractArgument(itemStack),
                context,
                poseStack,
                bufferSource,
                lightCoords,
                overlayCoords,
                itemStack.hasFoil());
    }

    void render(@Nullable T argument, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int lightCoords, int overlayCoords, boolean hasFoil);

    @Nullable T extractArgument(ItemStack itemStack);

    interface Unbaked<T> {
        @Nullable SpecialModelRenderer<T> bake(EntityModelSet modelSet);

        MapCodec<? extends SpecialModelRenderer.Unbaked<T>> type();
    }
}
