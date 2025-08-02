package fuzs.alltheheads.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.Model;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.alltheheads.world.item.component.headtype.Shape;
import fuzs.alltheheads.world.level.block.entity.MobHeadBlockEntity;
import net.minecraft.Util;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

/**
 * @see SkullBlockRenderer
 */
public class MobHeadBlockRenderer implements BlockEntityRenderer<MobHeadBlockEntity> {
    /**
     * Directly supplying both client asset resource locations is only possible as long as it is never serialised, which
     * is fine here.
     */
    private static final List<Model> DEFAULT_MODELS = List.of(new Model(new ModelAndTexture<>(ModelType.DEFAULT,
            new ClientAsset(DefaultPlayerSkin.getDefaultTexture(), DefaultPlayerSkin.getDefaultTexture())),
            Optional.empty(),
            Optional.empty()));
    private static final Shape DEFAULT_SHAPE = new Shape(8.0, 8.0, 8.0);
    private static final Map<ModelType, Function<EntityModelSet, SkullModelBase>> SKULL_MODELS = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(), (Map<ModelType, Function<EntityModelSet, SkullModelBase>> map) -> {
                // NO-OP
            }));
    private static final Map<ModelType, Function<ResourceLocation, RenderType>> RENDER_TYPES = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(), (Map<ModelType, Function<ResourceLocation, RenderType>> map) -> {
                map.put(ModelType.ENDERMAN_EYES, RenderType::eyes);
                map.put(ModelType.SPIDER_EYES, RenderType::eyes);
            }));

    private final Function<ModelType, SkullModelBase> skullModelGetter;

    public MobHeadBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.skullModelGetter = createSkullModels(context.getModelSet());
    }

    public static Function<ModelType, SkullModelBase> createSkullModels(EntityModelSet entityModelSet) {
        return Util.memoize((ModelType modelType) -> {
            Function<EntityModelSet, SkullModelBase> skullModelGetter = SKULL_MODELS.getOrDefault(modelType,
                    (EntityModelSet modelSet) -> {
                        ModelLayerLocation modelLayerLocation = createModelLayer(modelType);
                        return new SkullModel(modelSet.bakeLayer(modelLayerLocation));
                    });
            return skullModelGetter.apply(entityModelSet);
        });
    }

    public static ModelLayerLocation createModelLayer(ModelType modelType) {
        return new ModelLayerLocation(modelType.model(), modelType.layer());
    }

    @Override
    public void render(MobHeadBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
        float animation = blockEntity.getAnimation(partialTick);
        BlockState blockState = blockEntity.getBlockState();
        boolean isWallSkullBlock = blockState.getBlock() instanceof WallSkullBlock;
        Direction direction = isWallSkullBlock ? blockState.getValue(WallSkullBlock.FACING) : null;
        int rotationSegment = isWallSkullBlock ? RotationSegment.convertToSegment(direction.getOpposite()) :
                blockState.getValue(SkullBlock.ROTATION);
        float rotationSegmentDegrees = RotationSegment.convertToDegrees(rotationSegment);
        renderSkull(direction,
                rotationSegmentDegrees,
                animation,
                poseStack,
                bufferSource,
                packedLight,
                this.skullModelGetter,
                blockEntity.getHeadType(),
                false);
    }

    public static void renderSkull(@Nullable Direction direction, float rotationSegmentDegrees, float animation, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Function<ModelType, SkullModelBase> skullModelGetter, @Nullable Holder<HeadType> headType, boolean guiOffset) {
        Shape shape = getShape(headType);
        for (Model model : getModels(headType)) {
            SkullModelBase skullModelBase = skullModelGetter.apply(model.model().model());
            RenderType renderType = getRenderType(model.model());
            renderSkull(shape,
                    guiOffset,
                    direction,
                    rotationSegmentDegrees,
                    animation,
                    poseStack,
                    bufferSource,
                    model.blockLight()
                            .map((Integer blockLight) -> LightTexture.pack(blockLight, LightTexture.sky(packedLight)))
                            .orElse(packedLight),
                    skullModelBase,
                    renderType,
                    model.color().orElse(-1));
        }
    }

    /**
     * @see SkullBlockRenderer#renderSkull(Direction, float, float, PoseStack, MultiBufferSource, int,
     *         SkullModelBase, RenderType)
     */
    private static void renderSkull(Shape shape, boolean guiOffset, @Nullable Direction direction, float yRot, float mouthAnimation, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, SkullModelBase model, RenderType renderType, int color) {
        poseStack.pushPose();
        double offsetY = (16.0 - shape.scaledHeight()) / 2.0;

        if (direction != null) {
            double offsetX = 8.0 - direction.getStepX() * (16.0 - shape.scaledWidth()) / 2.0;
            double offsetZ = 8.0 - direction.getStepZ() * (16.0 - shape.scaledDepth()) / 2.0;
            poseStack.translate(offsetX / 16.0, offsetY / 16.0, offsetZ / 16.0);
        } else {
            poseStack.translate(0.5F, guiOffset ? ((float) offsetY - 10.0F / 3.0F) / 16.0F : 0.0F, 0.5F);
        }

        poseStack.scale((float) -shape.scale(), (float) -shape.scale(), (float) shape.scale());
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);
        model.setupAnim(mouthAnimation, yRot, 0.0F);
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
        poseStack.popPose();
    }

    private static List<Model> getModels(Holder<HeadType> headType) {
        return headType != null ? headType.value().models() : DEFAULT_MODELS;
    }

    private static Shape getShape(Holder<HeadType> headType) {
        return headType != null ? headType.value().shape() : DEFAULT_SHAPE;
    }

    private static RenderType getRenderType(ModelAndTexture<ModelType> modelAndTexture) {
        Function<ResourceLocation, RenderType> renderTypeGetter = RENDER_TYPES.getOrDefault(modelAndTexture.model(),
                RenderType::entityCutoutNoCull);
        return renderTypeGetter.apply(modelAndTexture.asset().texturePath());
    }
}
