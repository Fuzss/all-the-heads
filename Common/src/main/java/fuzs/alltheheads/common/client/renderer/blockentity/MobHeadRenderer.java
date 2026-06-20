package fuzs.alltheheads.common.client.renderer.blockentity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.common.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.Model;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import fuzs.alltheheads.common.world.level.block.entity.MobHeadBlockEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.skull.PiglinHeadModel;
import net.minecraft.client.model.object.skull.SkullModel;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.blockentity.state.SkullBlockRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

/**
 * @see SkullBlockRenderer
 */
public class MobHeadRenderer extends SkullBlockRenderer {
    private static final SkullBlockLayer.Unbaked DEFAULT_SKULL_MODEL = new SkullBlockLayer.UnbakedModel();
    private static final Map<ModelType, SkullBlockLayer.Unbaked> SKULL_MODELS;

    static {
        ImmutableMap.Builder<ModelType, SkullBlockLayer.Unbaked> builder = ImmutableMap.builder();
        builder.put(ModelType.ALLAY, new SkullBlockLayer.UnbakedModel(RenderTypes::entityTranslucent));
        builder.put(ModelType.BAT, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.BREEZE, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.BREEZE_EYES, new SkullBlockLayer.UnbakedModel(RenderTypes::eyes));
        builder.put(ModelType.COPPER_GOLEM, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.COPPER_GOLEM_EYES, new SkullBlockLayer.UnbakedModel(RenderTypes::eyes));
        builder.put(ModelType.CREAKING, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.CREAKING_EYES, new SkullBlockLayer.UnbakedModel(RenderTypes::eyes));
        builder.put(ModelType.CREEPER_CHARGE,
                new SkullBlockLayer.UnbakedModel(SkullModel::new, (Identifier texture, Float tickCount) -> {
                    return RenderTypes.energySwirl(texture, tickCount * 0.01F % 1.0F, tickCount * 0.01F % 1.0F);
                }));
        builder.put(ModelType.ENDERMAN, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.ENDERMAN_EYES, new SkullBlockLayer.UnbakedModel(RenderTypes::eyes));
        builder.put(ModelType.HORSE, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.HORSE_MARKINGS, new SkullBlockLayer.UnbakedModel(RenderTypes::entityTranslucent));
        builder.put(ModelType.PHANTOM, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.PHANTOM_EYES, new SkullBlockLayer.UnbakedModel(RenderTypes::eyes));
        builder.put(ModelType.PIGLIN,
                new SkullBlockLayer.UnbakedModel(PiglinHeadModel::new,
                        (Function<Identifier, RenderType>) RenderTypes::entityCutoutZOffset));
        builder.put(ModelType.SHEEP, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.SLIME_GEL, new SkullBlockLayer.UnbakedModel(RenderTypes::entityTranslucent));
        builder.put(ModelType.SPIDER, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.SPIDER_EYES, new SkullBlockLayer.UnbakedModel(RenderTypes::eyes));
        builder.put(ModelType.SULFUR_CUBE_BOUNCY, new SkullBlockLayer.UnbakedBlock(Blocks.OAK_LOG));
        builder.put(ModelType.SULFUR_CUBE_EXPLOSIVE, new SkullBlockLayer.UnbakedBlock(Blocks.TNT));
        builder.put(ModelType.SULFUR_CUBE_FAST_FLAT, new SkullBlockLayer.UnbakedBlock(Blocks.MOSS_BLOCK));
        builder.put(ModelType.SULFUR_CUBE_FAST_SLIDING, new SkullBlockLayer.UnbakedBlock(Blocks.BLUE_ICE));
        builder.put(ModelType.SULFUR_CUBE_GEL, new SkullBlockLayer.UnbakedModel(RenderTypes::entityTranslucent));
        builder.put(ModelType.SULFUR_CUBE_HIGH_RESISTANCE, new SkullBlockLayer.UnbakedBlock(Blocks.SOUL_SAND));
        builder.put(ModelType.SULFUR_CUBE_HOT, new SkullBlockLayer.UnbakedBlock(Blocks.MAGMA_BLOCK));
        builder.put(ModelType.SULFUR_CUBE_LIGHT, new SkullBlockLayer.UnbakedBlock(Blocks.WOOL.white()));
        builder.put(ModelType.SULFUR_CUBE_REGULAR, new SkullBlockLayer.UnbakedBlock(Blocks.GRASS_BLOCK));
        builder.put(ModelType.SULFUR_CUBE_SLOW_BOUNCY, new SkullBlockLayer.UnbakedBlock(Blocks.STONE));
        builder.put(ModelType.SULFUR_CUBE_SLOW_FLAT, new SkullBlockLayer.UnbakedBlock(Blocks.IRON_BLOCK));
        builder.put(ModelType.SULFUR_CUBE_SLOW_SLIDING, new SkullBlockLayer.UnbakedBlock(Blocks.RED_MUSHROOM_BLOCK));
        builder.put(ModelType.SULFUR_CUBE_STICKY, new SkullBlockLayer.UnbakedBlock(Blocks.HONEYCOMB_BLOCK));
        builder.put(ModelType.TROPICAL_FISH_LARGE, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.TROPICAL_FISH_SMALL, new SkullBlockLayer.UnbakedModel(RenderTypes::entityCutout));
        builder.put(ModelType.VEX, new SkullBlockLayer.UnbakedModel(RenderTypes::entityTranslucent));
        builder.put(ModelType.WITHER_SHIELD,
                new SkullBlockLayer.UnbakedModel(SkullModel::new, (Identifier texture, Float tickCount) -> {
                    return RenderTypes.energySwirl(texture,
                            Mth.cos(tickCount * 0.02F) * 3.0F % 1.0F,
                            tickCount * 0.01F % 1.0F);
                }));
        SKULL_MODELS = builder.build();
    }

    private final Function<ModelType, SkullBlockLayer> skullLayerGetter;

    public MobHeadRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.skullLayerGetter = createSkullModels(context.entityModelSet(), context.blockModelResolver());
    }

    public static Function<ModelType, SkullBlockLayer> createSkullModels(EntityModelSet entityModelSet, BlockModelResolver blockModelResolver) {
        return Util.memoize((ModelType modelType) -> {
            SkullBlockLayer.Unbaked unbaked = SKULL_MODELS.getOrDefault(modelType, DEFAULT_SKULL_MODEL);
            return unbaked.bake(modelType, entityModelSet, blockModelResolver);
        });
    }

    public static ModelLayerLocation createModelLayer(ModelType modelType) {
        return new ModelLayerLocation(modelType.model(), modelType.layer());
    }

    @Override
    public MobHeadRenderState createRenderState() {
        return new MobHeadRenderState();
    }

    @Override
    public void extractRenderState(SkullBlockEntity blockEntity, SkullBlockRenderState state, float partialTick, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        super.extractRenderState(blockEntity, state, partialTick, cameraPosition, breakProgress);
        Holder<HeadType> headType = ((MobHeadBlockEntity) blockEntity).getHeadType();
        BlockState blockState = blockEntity.getBlockState();
        if (blockState.getBlock() instanceof WallSkullBlock) {
            Direction direction = blockState.getValue(WallSkullBlock.FACING);
            state.transformation = MobHeadRenderState.createWallTransformation(headType, direction);
        } else {
            int rotation = blockState.getValue(SkullBlock.ROTATION);
            state.transformation = MobHeadRenderState.createGroundTransformation(headType, rotation, false);
        }

        ((MobHeadRenderState) state).models = MobHeadRenderState.getModels(headType);
        ((MobHeadRenderState) state).time = ((MobHeadBlockEntity) blockEntity).tickCount + partialTick;
    }

    @Override
    public void submit(SkullBlockRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(state.transformation);
        submitSkull((MobHeadRenderState) state, poseStack, submitNodeCollector, this.skullLayerGetter);
        poseStack.popPose();
    }

    /**
     * @see SkullBlockRenderer#submitSkull(float, PoseStack, SubmitNodeCollector, int, SkullModelBase, RenderType,
     *         int, ModelFeatureRenderer.CrumblingOverlay)
     */
    public static void submitSkull(MobHeadRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, Function<ModelType, SkullBlockLayer> skullLayerGetter) {
        for (Model model : state.models) {
            SkullBlockLayer skullLayer = skullLayerGetter.apply(model.model());
            skullLayer.submit(model, state, poseStack, submitNodeCollector);
        }
    }
}
