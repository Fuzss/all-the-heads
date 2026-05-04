package fuzs.alltheheads.common.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.common.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.common.world.item.component.headtype.Color;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.Model;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import fuzs.alltheheads.common.world.level.block.entity.MobHeadBlockEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.skull.PiglinHeadModel;
import net.minecraft.client.model.object.skull.SkullModel;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.blockentity.state.SkullBlockRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @see SkullBlockRenderer
 */
public class MobHeadRenderer extends SkullBlockRenderer {
    private static final BiFunction<Identifier, Float, RenderType> DEFAULT_RENDER_TYPE_GETTER = (Identifier identifier, Float tickCount) -> {
        return RenderTypes.entityCutoutZOffset(identifier);
    };
    private static final Map<ModelType, Function<ModelPart, SkullModelBase>> SKULL_MODELS = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(), (Map<ModelType, Function<ModelPart, SkullModelBase>> map) -> {
                map.put(ModelType.PIGLIN, PiglinHeadModel::new);
            }));
    private static final Map<ModelType, BiFunction<Identifier, Float, RenderType>> RENDER_TYPES = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(), (Map<ModelType, BiFunction<Identifier, Float, RenderType>> map) -> {
                putRenderType(ModelType.ALLAY, RenderTypes::entityTranslucent, map::put);
                putRenderType(ModelType.BAT, RenderTypes::entityCutout, map::put);
                putEyesRenderType(ModelType.BREEZE, ModelType.BREEZE_EYES, map::put);
                putEyesRenderType(ModelType.COPPER_GOLEM, ModelType.COPPER_GOLEM_EYES, map::put);
                putEyesRenderType(ModelType.CREAKING, ModelType.CREAKING_EYES, map::put);
                map.put(ModelType.CREEPER_CHARGE, (Identifier identifier, Float tickCount) -> {
                    return RenderTypes.energySwirl(identifier, tickCount * 0.01F % 1.0F, tickCount * 0.01F % 1.0F);
                });
                putEyesRenderType(ModelType.ENDERMAN, ModelType.ENDERMAN_EYES, map::put);
                putRenderType(ModelType.HORSE, RenderTypes::entityCutout, map::put);
                putRenderType(ModelType.HORSE_MARKINGS, RenderTypes::entityTranslucent, map::put);
                putEyesRenderType(ModelType.PHANTOM, ModelType.PHANTOM_EYES, map::put);
                putRenderType(ModelType.SHEEP, RenderTypes::entityCutout, map::put);
                putRenderType(ModelType.SLIME_GEL, RenderTypes::entityTranslucent, map::put);
                putEyesRenderType(ModelType.SPIDER, ModelType.SPIDER_EYES, map::put);
                putRenderType(ModelType.TROPICAL_FISH_LARGE, RenderTypes::entityCutout, map::put);
                putRenderType(ModelType.TROPICAL_FISH_SMALL, RenderTypes::entityCutout, map::put);
                putRenderType(ModelType.VEX, RenderTypes::entityTranslucent, map::put);
                map.put(ModelType.WITHER_SHIELD, (Identifier identifier, Float tickCount) -> {
                    return RenderTypes.energySwirl(identifier,
                            Mth.cos(tickCount * 0.02F) * 3.0F % 1.0F,
                            tickCount * 0.01F % 1.0F);
                });
            }));

    private final Function<ModelType, SkullModelBase> skullModelGetter;

    public MobHeadRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.skullModelGetter = createSkullModels(context.entityModelSet());
    }

    private static void putEyesRenderType(ModelType modelType, ModelType eyesModelType, BiConsumer<ModelType, BiFunction<Identifier, Float, RenderType>> consumer) {
        putRenderType(modelType, RenderTypes::entityCutout, consumer);
        putRenderType(eyesModelType, RenderTypes::eyes, consumer);
    }

    private static void putRenderType(ModelType modelType, Function<Identifier, RenderType> renderTypeGetter, BiConsumer<ModelType, BiFunction<Identifier, Float, RenderType>> consumer) {
        consumer.accept(modelType, (Identifier identifier, Float tickCount) -> {
            return renderTypeGetter.apply(identifier);
        });
    }

    public static Function<ModelType, SkullModelBase> createSkullModels(EntityModelSet entityModelSet) {
        return Util.memoize((ModelType modelType) -> {
            Function<ModelPart, SkullModelBase> skullModelGetter = SKULL_MODELS.getOrDefault(modelType,
                    SkullModel::new);
            ModelLayerLocation modelLayerLocation = createModelLayer(modelType);
            ModelPart modelPart = entityModelSet.bakeLayer(modelLayerLocation);
            return skullModelGetter.apply(modelPart);
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
        submitSkull((MobHeadRenderState) state, poseStack, submitNodeCollector, this.skullModelGetter);
        poseStack.popPose();
    }

    public static void submitSkull(MobHeadRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, Function<ModelType, SkullModelBase> skullModelGetter) {
        for (Model model : state.models) {
            SkullModelBase skullModel = skullModelGetter.apply(model.model().model());
            RenderType renderType = getRenderType(model.model(), state.time);
            int lightCoords = model.blockLight()
                    .map((Integer blockLight) -> LightCoordsUtil.pack(blockLight,
                            LightCoordsUtil.sky(state.lightCoords)))
                    .orElse(state.lightCoords);
            int tintColor = model.color().map((Color color) -> color.getColor(state.time)).orElse(-1);
            submitSkull(state, poseStack, submitNodeCollector, skullModel, renderType, lightCoords, tintColor);
        }
    }

    /**
     * @see SkullBlockRenderer#submitSkull(float, PoseStack, SubmitNodeCollector, int, SkullModelBase, RenderType,
     *         int, ModelFeatureRenderer.CrumblingOverlay)
     */
    private static void submitSkull(MobHeadRenderState state, PoseStack poseStack, SubmitNodeCollector nodeCollector, SkullModelBase model, RenderType renderType, int lightCoords, int tintColor) {
        SkullModelBase.State modelState = new SkullModelBase.State();
        modelState.animationPos = state.animationProgress;
        nodeCollector.submitModel(model,
                modelState,
                poseStack,
                renderType,
                lightCoords,
                OverlayTexture.NO_OVERLAY,
                tintColor,
                null,
                state.outlineColor,
                state.breakProgress);
    }

    private static RenderType getRenderType(ModelAndTexture<ModelType> modelAndTexture, float tickCount) {
        BiFunction<Identifier, Float, RenderType> renderTypeGetter = RENDER_TYPES.getOrDefault(modelAndTexture.model(),
                DEFAULT_RENDER_TYPE_GETTER);
        return renderTypeGetter.apply(modelAndTexture.asset().texturePath(), tickCount);
    }
}
