package fuzs.alltheheads.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import fuzs.alltheheads.client.renderer.blockentity.MobHeadBlockRenderer;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class MobHeadSpecialRenderer implements SpecialModelRenderer<List<ModelAndTexture<HeadType.ModelType>>> {
    private final Function<HeadType.ModelType, SkullModelBase> skullModelGetter;

    public MobHeadSpecialRenderer(Function<HeadType.ModelType, SkullModelBase> skullModelGetter) {
        this.skullModelGetter = skullModelGetter;
    }

    @Override
    public void render(List<ModelAndTexture<HeadType.ModelType>> modelAndTextures, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean hasFoilType) {
        MobHeadBlockRenderer.renderSkull(null,
                null,
                180.0F,
                0.0F,
                poseStack,
                bufferSource,
                packedLight,
                this.skullModelGetter,
                modelAndTextures);
    }

    @Override
    public void getExtents(Set<Vector3f> output) {
        PoseStack poseStack = new PoseStack();
        poseStack.translate(0.5F, 0.0F, 0.5F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        // there seems to be no good way to get the proper model for the model type
        SkullModelBase skullModelBase = this.skullModelGetter.apply(HeadType.ModelType.DEFAULT);
        skullModelBase.setupAnim(0.0F, 180.0F, 0.0F);
        skullModelBase.root().getExtentsForGui(poseStack, output);
    }

    @Override
    public List<ModelAndTexture<HeadType.ModelType>> extractArgument(ItemStack itemStack) {
        return MobHeadBlockRenderer.getModelAndTextures(itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value()));
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
