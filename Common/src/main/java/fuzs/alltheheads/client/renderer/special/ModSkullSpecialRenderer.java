package fuzs.alltheheads.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Set;

public class ModSkullSpecialRenderer implements SpecialModelRenderer<Holder<HeadType>> {

    @Override
    public void render(@Nullable Holder<HeadType> headType, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean hasFoilType) {

    }

    @Override
    public void getExtents(Set<Vector3f> output) {

    }

    @Override
    public @Nullable Holder<HeadType> extractArgument(ItemStack itemStack) {
        return itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }
}
