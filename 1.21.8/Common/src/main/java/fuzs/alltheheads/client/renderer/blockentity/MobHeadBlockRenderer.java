package fuzs.alltheheads.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.alltheheads.world.level.block.entity.MobHeadBlockEntity;
import net.minecraft.Util;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
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

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @see SkullBlockRenderer
 */
public class MobHeadBlockRenderer implements BlockEntityRenderer<MobHeadBlockEntity> {
    /**
     * Directly supplying both client asset resource locations is only possible as long as it is never serialised, which
     * is fine here.
     */
    private static final List<HeadType.Model> DEFAULT_MODEL_AND_TEXTURES = List.of(new HeadType.Model(new ModelAndTexture<>(
            HeadType.ModelType.DEFAULT,
            new ClientAsset(DefaultPlayerSkin.getDefaultTexture(), DefaultPlayerSkin.getDefaultTexture())), -1));
    private static final Map<HeadType.ModelType, Function<EntityModelSet, SkullModelBase>> SKULL_MODELS = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(),
                    (Map<HeadType.ModelType, Function<EntityModelSet, SkullModelBase>> map) -> {
                        // NO-OP
                    }));
    private static final Map<HeadType.ModelType, Function<ResourceLocation, RenderType>> RENDER_TYPES = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(),
                    (Map<HeadType.ModelType, Function<ResourceLocation, RenderType>> map) -> {
                        map.put(HeadType.ModelType.ENDERMAN_EYES, RenderType::eyes);
                    }));

    private final Function<HeadType.ModelType, SkullModelBase> skullModelGetter;

    public MobHeadBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.skullModelGetter = createSkullModels(context.getModelSet());
    }

    public static Function<HeadType.ModelType, SkullModelBase> createSkullModels(EntityModelSet entityModelSet) {
        return Util.memoize((HeadType.ModelType modelType) -> {
            Function<EntityModelSet, SkullModelBase> skullModelGetter = SKULL_MODELS.getOrDefault(modelType,
                    (EntityModelSet modelSet) -> {
                        ModelLayerLocation modelLayerLocation = createModelLayer(modelType);
                        return new SkullModel(modelSet.bakeLayer(modelLayerLocation));
                    });
            return skullModelGetter.apply(entityModelSet);
        });
    }

    public static ModelLayerLocation createModelLayer(HeadType.ModelType modelType) {
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
        HeadType.Shape shape = getShape(blockEntity.getHeadType());
        List<HeadType.Model> modelAndTextures = getModels(blockEntity.getHeadType());
        renderSkull(shape,
                direction,
                rotationSegmentDegrees,
                animation,
                poseStack,
                bufferSource,
                packedLight,
                this.skullModelGetter,
                modelAndTextures);
    }

    public static List<HeadType.Model> getModels(Holder<HeadType> headType) {
        return headType != null ? headType.value().models() : DEFAULT_MODEL_AND_TEXTURES;
    }

    private static @Nullable HeadType.Shape getShape(Holder<HeadType> headType) {
        return headType != null ? headType.value().shape() : null;
    }

    public static void renderSkull(@Nullable HeadType.Shape shape, @Nullable Direction direction, float rotationSegmentDegrees, float animation, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Function<HeadType.ModelType, SkullModelBase> skullModelGetter, List<HeadType.Model> modelAndTextures) {
        for (HeadType.Model model : modelAndTextures) {
            SkullModelBase skullModelBase = skullModelGetter.apply(model.model().model());
            RenderType renderType = getRenderType(model.model());
            poseStack.pushPose();
            if (shape != null && direction != null) {
                double offsetX = -direction.getStepX() * (8.0 - shape.scaledWidth()) / 32.0;
                double offsetY = (8.0 - shape.scaledHeight()) / 32.0;
                double offsetZ = -direction.getStepZ() * (8.0 - shape.scaledDepth()) / 32.0;
                poseStack.translate(offsetX, offsetY, offsetZ);
            }
            SkullBlockRenderer.renderSkull(direction,
                    rotationSegmentDegrees,
                    animation,
                    poseStack,
                    bufferSource,
                    packedLight,
                    skullModelBase,
                    renderType);
            poseStack.popPose();
        }
    }

    private static RenderType getRenderType(ModelAndTexture<HeadType.ModelType> modelAndTexture) {
        Function<ResourceLocation, RenderType> renderTypeGetter = RENDER_TYPES.getOrDefault(modelAndTexture.model(),
                RenderType::entityCutoutNoCull);
        return renderTypeGetter.apply(modelAndTexture.asset().texturePath());
    }
}
