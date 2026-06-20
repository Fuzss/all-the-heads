package fuzs.alltheheads.common.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fuzs.alltheheads.common.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.common.world.item.component.headtype.Color;
import fuzs.alltheheads.common.world.item.component.headtype.Model;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.skull.SkullModel;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.state.SulfurCubeRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.ClientAsset;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface SkullBlockLayer {
    void submit(Model model, MobHeadRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector);

    @FunctionalInterface
    interface Unbaked {
        SkullBlockLayer bake(ModelType modelType, EntityModelSet entityModelSet, BlockModelResolver blockModelResolver);

        default int lightCoords(Model model, MobHeadRenderState state) {
            return model.blockLight()
                    .map((Integer blockLight) -> LightCoordsUtil.pack(blockLight,
                            LightCoordsUtil.sky(state.lightCoords)))
                    .orElse(state.lightCoords);
        }
    }

    record UnbakedModel(Function<ModelPart, SkullModelBase> model,
                        BiFunction<Identifier, Float, RenderType> renderType) implements SkullBlockLayer.Unbaked {

        public UnbakedModel() {
            this(SkullModel::new, (Function<Identifier, RenderType>) RenderTypes::entityCutoutZOffset);
        }

        public UnbakedModel(Function<Identifier, RenderType> renderType) {
            this(SkullModel::new, renderType);
        }

        public UnbakedModel(Function<ModelPart, SkullModelBase> model, Function<Identifier, RenderType> renderType) {
            this(model, (Identifier texture, Float tickCount) -> {
                return renderType.apply(texture);
            });
        }

        /**
         * @see SkullBlockRenderer#submitSkull(float, PoseStack, SubmitNodeCollector, int, SkullModelBase,
         *         RenderType, int, ModelFeatureRenderer.CrumblingOverlay)
         */
        @Override
        public SkullBlockLayer bake(ModelType modelType, EntityModelSet entityModelSet, BlockModelResolver blockModelResolver) {
            ModelLayerLocation modelLayerLocation = MobHeadRenderer.createModelLayer(modelType);
            ModelPart modelPart = entityModelSet.bakeLayer(modelLayerLocation);
            SkullModelBase skullModel = this.model().apply(modelPart);
            return (Model model, MobHeadRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) -> {
                submitNodeCollector.submitModel(skullModel,
                        UnbakedModel.this.state(state),
                        poseStack,
                        UnbakedModel.this.renderType(model, state),
                        UnbakedModel.this.lightCoords(model, state),
                        OverlayTexture.NO_OVERLAY,
                        UnbakedModel.this.tintColor(model, state),
                        null,
                        state.outlineColor,
                        state.breakProgress);
            };
        }

        public SkullModelBase.State state(MobHeadRenderState state) {
            SkullModelBase.State modelState = new SkullModelBase.State();
            modelState.animationPos = state.animationProgress;
            return modelState;
        }

        public RenderType renderType(Model model, MobHeadRenderState state) {
            Identifier textureLocation = model.asset()
                    .map(ClientAsset.ResourceTexture::texturePath)
                    .orElse(MissingTextureAtlasSprite.getLocation());
            return this.renderType().apply(textureLocation, state.time);
        }

        public int tintColor(Model model, MobHeadRenderState state) {
            return model.color().map((Color color) -> color.getColor(state.time)).orElse(-1);
        }
    }

    record UnbakedBlock(BlockState blockState) implements SkullBlockLayer.Unbaked {
        public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

        public UnbakedBlock(Block block) {
            this(block.defaultBlockState());
        }

        /**
         * @see net.minecraft.client.renderer.entity.layers.SulfurCubeInnerLayer#submit(PoseStack,
         *         SubmitNodeCollector, int, SulfurCubeRenderState, float, float)
         */
        @Override
        public SkullBlockLayer bake(ModelType modelType, EntityModelSet entityModelSet, BlockModelResolver blockModelResolver) {
            BlockModelRenderState containedBlock = new BlockModelRenderState();
            blockModelResolver.update(containedBlock, this.blockState(), BLOCK_DISPLAY_CONTEXT);
            return (Model model, MobHeadRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) -> {
                poseStack.pushPose();
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                poseStack.translate(-0.5F, 0.0625F, -0.5F);
                containedBlock.submit(poseStack,
                        submitNodeCollector,
                        this.lightCoords(model, state),
                        OverlayTexture.NO_OVERLAY,
                        state.outlineColor);
                poseStack.popPose();
            };
        }
    }
}
