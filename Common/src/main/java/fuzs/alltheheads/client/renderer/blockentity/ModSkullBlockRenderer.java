package fuzs.alltheheads.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.Vec3;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class ModSkullBlockRenderer implements BlockEntityRenderer<ModSkullBlockEntity> {
    public static final List<ModelAndTexture<HeadType.ModelType>> DEFAULT_MODEL_AND_TEXTURES = List.of(new ModelAndTexture<>(
            HeadType.ModelType.DEFAULT,
            DefaultPlayerSkin.getDefaultTexture()));

    private final Map<HeadType.ModelType, SkullModelBase> modelByType = new IdentityHashMap<>();
    private final Map<HeadType.ModelType, Function<ResourceLocation, RenderType>> renderTypeGetters = new IdentityHashMap<>();

    public ModSkullBlockRenderer(BlockEntityRendererProvider.Context context) {
        for (HeadType.ModelType modelType : HeadType.ModelType.ID_MAPPER.idToValue.values()) {
            this.modelByType.put(modelType, new SkullModel(context.bakeLayer(createModelLayer(modelType))));
        }
        this.renderTypeGetters.put(HeadType.ModelType.ENDERMAN_EYES, RenderType::eyes);
    }

    public static ModelLayerLocation createModelLayer(HeadType.ModelType modelType) {
        return new ModelLayerLocation(modelType.model(), "main");
    }

    /**
     * @see SkullBlockRenderer#render(SkullBlockEntity, float, PoseStack, MultiBufferSource, int, int, Vec3)
     */
    @Override
    public void render(ModSkullBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
        float animation = blockEntity.getAnimation(partialTick);
        BlockState blockState = blockEntity.getBlockState();
        boolean isWallSkullBlock = blockState.getBlock() instanceof WallSkullBlock;
        Direction direction = isWallSkullBlock ? blockState.getValue(WallSkullBlock.FACING) : null;
        int rotationSegment = isWallSkullBlock ? RotationSegment.convertToSegment(direction.getOpposite()) :
                blockState.getValue(SkullBlock.ROTATION);
        float rotationSegmentDegrees = RotationSegment.convertToDegrees(rotationSegment);
        List<ModelAndTexture<HeadType.ModelType>> modelAndTextures = this.getModelAndTextures(blockEntity);
        for (ModelAndTexture<HeadType.ModelType> modelAndTexture : modelAndTextures) {
            SkullModelBase skullModelBase = this.modelByType.get(modelAndTexture.model());
            Objects.requireNonNull(skullModelBase, "skull model is null");
            RenderType renderType = this.getRenderType(modelAndTexture);
            SkullBlockRenderer.renderSkull(direction,
                    rotationSegmentDegrees,
                    animation,
                    poseStack,
                    bufferSource,
                    packedLight,
                    skullModelBase,
                    renderType);
        }
    }

    private List<ModelAndTexture<HeadType.ModelType>> getModelAndTextures(ModSkullBlockEntity blockEntity) {
        if (blockEntity.getHeadType() != null) {
            return blockEntity.getHeadType().value().modelAndTextures();
        } else {
            return DEFAULT_MODEL_AND_TEXTURES;
        }
    }

    private RenderType getRenderType(ModelAndTexture<HeadType.ModelType> modelAndTexture) {
        Function<ResourceLocation, RenderType> renderTypeGetter = this.renderTypeGetters.computeIfAbsent(modelAndTexture.model(),
                (HeadType.ModelType modelType) -> RenderType::entityCutoutNoCull);
        return renderTypeGetter.apply(modelAndTexture.asset().texturePath());
    }
}
