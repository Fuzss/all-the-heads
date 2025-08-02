package fuzs.alltheheads.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Set;
import java.util.function.Function;

public class MobHeadSpecialRenderer implements SpecialModelRenderer<@Nullable Holder<HeadType>> {
    private final Function<ModelType, SkullModelBase> skullModelGetter;

    public MobHeadSpecialRenderer(Function<ModelType, SkullModelBase> skullModelGetter) {
        this.skullModelGetter = skullModelGetter;
    }

    @Override
    public void render(@Nullable Holder<HeadType> headType, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean hasFoilType) {
        MobHeadBlockRenderer.renderSkull(null,
                180.0F,
                0.0F,
                poseStack,
                bufferSource,
                packedLight,
                this.skullModelGetter,
                headType,
                true);
    }

    @Override
    public void getExtents(Set<Vector3f> output) {
        PoseStack poseStack = new PoseStack();
        poseStack.translate(0.5F, 0.0F, 0.5F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        // there seems to be no good way to get the proper model for the model type
        SkullModelBase skullModelBase = this.skullModelGetter.apply(ModelType.DEFAULT);
        skullModelBase.setupAnim(0.0F, 180.0F, 0.0F);
        skullModelBase.root().getExtentsForGui(poseStack, output);
    }

    @Override
    public @Nullable Holder<HeadType> extractArgument(ItemStack itemStack) {
        return itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<MobHeadSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public MapCodec<MobHeadSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet modelSet) {
            return new MobHeadSpecialRenderer(MobHeadBlockRenderer.createSkullModels(modelSet));
        }
    }
}
