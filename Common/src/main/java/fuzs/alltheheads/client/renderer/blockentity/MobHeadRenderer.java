package fuzs.alltheheads.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.alltheheads.client.renderer.blockentity.state.MobHeadRenderState;
import fuzs.alltheheads.world.item.component.headtype.*;
import fuzs.alltheheads.world.level.block.entity.MobHeadBlockEntity;
import net.minecraft.Util;
import net.minecraft.client.model.PiglinHeadModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.blockentity.state.SkullBlockRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @see SkullBlockRenderer
 */
public class MobHeadRenderer extends SkullBlockRenderer {
    /**
     * Directly supplying both client asset resource locations is only possible as long as it is never serialised, which
     * is fine here.
     */
    private static final List<Model> DEFAULT_MODELS = List.of(new Model(new ModelAndTexture<>(ModelType.DEFAULT,
            new ClientAsset.ResourceTexture(DefaultPlayerSkin.getDefaultTexture(),
                    DefaultPlayerSkin.getDefaultTexture())), Optional.empty(), Optional.empty()));
    private static final Shape DEFAULT_SHAPE = new Shape(8.0, 8.0, 8.0);
    private static final BiFunction<ResourceLocation, Float, RenderType> DEFAULT_RENDER_TYPE_GETTER = (ResourceLocation resourceLocation, Float tickCount) -> {
        return RenderType.entityCutoutNoCullZOffset(resourceLocation);
    };
    private static final Map<ModelType, Function<ModelPart, SkullModelBase>> SKULL_MODELS = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(), (Map<ModelType, Function<ModelPart, SkullModelBase>> map) -> {
                map.put(ModelType.PIGLIN, PiglinHeadModel::new);
            }));
    private static final Map<ModelType, BiFunction<ResourceLocation, Float, RenderType>> RENDER_TYPES = Collections.unmodifiableMap(
            Util.make(new IdentityHashMap<>(),
                    (Map<ModelType, BiFunction<ResourceLocation, Float, RenderType>> map) -> {
                        putRenderType(ModelType.ALLAY, RenderType::entityTranslucent, map::put);
                        putRenderType(ModelType.BAT, RenderType::entityCutout, map::put);
                        putEyesRenderType(ModelType.BREEZE, ModelType.BREEZE_EYES, map::put);
                        putEyesRenderType(ModelType.COPPER_GOLEM, ModelType.COPPER_GOLEM_EYES, map::put);
                        putEyesRenderType(ModelType.CREAKING, ModelType.CREAKING_EYES, map::put);
                        map.put(ModelType.CREEPER_CHARGE, (ResourceLocation resourceLocation, Float tickCount) -> {
                            return RenderType.energySwirl(resourceLocation,
                                    tickCount * 0.01F % 1.0F,
                                    tickCount * 0.01F % 1.0F);
                        });
                        putEyesRenderType(ModelType.ENDERMAN, ModelType.ENDERMAN_EYES, map::put);
                        putRenderType(ModelType.HORSE, RenderType::entityCutoutNoCull, map::put);
                        putRenderType(ModelType.HORSE_MARKINGS, RenderType::entityTranslucent, map::put);
                        putEyesRenderType(ModelType.PHANTOM, ModelType.PHANTOM_EYES, map::put);
                        putRenderType(ModelType.SHEEP, RenderType::entityCutoutNoCull, map::put);
                        putRenderType(ModelType.SLIME_GEL, RenderType::entityTranslucent, map::put);
                        putEyesRenderType(ModelType.SPIDER, ModelType.SPIDER_EYES, map::put);
                        putRenderType(ModelType.TROPICAL_FISH_LARGE, RenderType::entityCutoutNoCull, map::put);
                        putRenderType(ModelType.TROPICAL_FISH_SMALL, RenderType::entityCutoutNoCull, map::put);
                        putRenderType(ModelType.VEX, RenderType::entityTranslucent, map::put);
                        map.put(ModelType.WITHER_SHIELD, (ResourceLocation resourceLocation, Float tickCount) -> {
                            return RenderType.energySwirl(resourceLocation,
                                    Mth.cos(tickCount * 0.02F) * 3.0F % 1.0F,
                                    tickCount * 0.01F % 1.0F);
                        });
                    }));

    private final Function<ModelType, SkullModelBase> skullModelGetter;

    public MobHeadRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.skullModelGetter = createSkullModels(context.entityModelSet());
    }

    private static void putEyesRenderType(ModelType modelType, ModelType eyesModelType, BiConsumer<ModelType, BiFunction<ResourceLocation, Float, RenderType>> consumer) {
        putRenderType(modelType, RenderType::entityCutoutNoCull, consumer);
        putRenderType(eyesModelType, RenderType::eyes, consumer);
    }

    private static void putRenderType(ModelType modelType, Function<ResourceLocation, RenderType> renderTypeGetter, BiConsumer<ModelType, BiFunction<ResourceLocation, Float, RenderType>> consumer) {
        consumer.accept(modelType, (ResourceLocation resourceLocation, Float tickCount) -> {
            return renderTypeGetter.apply(resourceLocation);
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
    public void extractRenderState(SkullBlockEntity blockEntity, SkullBlockRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        ((MobHeadRenderState) renderState).headType = ((MobHeadBlockEntity) blockEntity).getHeadType();
        ((MobHeadRenderState) renderState).time = ((MobHeadBlockEntity) blockEntity).tickCount + partialTick;
    }

    @Override
    public void submit(SkullBlockRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        submitSkull((MobHeadRenderState) renderState, poseStack, nodeCollector, this.skullModelGetter);
    }

    public static void submitSkull(MobHeadRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, Function<ModelType, SkullModelBase> skullModelGetter) {
        Shape shape = getShape(renderState.headType);
        for (Model model : getModels(renderState.headType)) {
            SkullModelBase skullModelBase = skullModelGetter.apply(model.model().model());
            RenderType renderType = getRenderType(model.model(), renderState.time);
            submitSkull(renderState,
                    poseStack,
                    nodeCollector,
                    skullModelBase,
                    renderType,
                    model.blockLight()
                            .map((Integer blockLight) -> LightTexture.pack(blockLight,
                                    LightTexture.sky(renderState.lightCoords)))
                            .orElse(renderState.lightCoords),
                    model.color().map((Color tintColor) -> tintColor.getColor(renderState.time)).orElse(-1),
                    shape);
        }
    }

    /**
     * @see SkullBlockRenderer#submitSkull(Direction, float, float, PoseStack, SubmitNodeCollector, int,
     *         SkullModelBase, RenderType, int, ModelFeatureRenderer.CrumblingOverlay)
     */
    private static void submitSkull(MobHeadRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, SkullModelBase model, RenderType renderType, int packedLight, int tintColor, Shape shape) {
        poseStack.pushPose();
        double offsetY = (16.0 - shape.sizeY()) / 2.0;
        if (renderState.direction != null) {
            double offsetX = 8.0 - renderState.direction.getStepX() * (16.0 - shape.sizeX(renderState.direction)) / 2.0;
            double offsetZ = 8.0 - renderState.direction.getStepZ() * (16.0 - shape.sizeZ(renderState.direction)) / 2.0;
            poseStack.translate(offsetX / 16.0, offsetY / 16.0, offsetZ / 16.0);
        } else {
            poseStack.translate(0.5F, renderState.guiOffset ? ((float) offsetY - 10.0F / 3.0F) / 16.0F : 0.0F, 0.5F);
        }

        poseStack.scale((float) -shape.scale(), (float) -shape.scale(), (float) shape.scale());
        SkullModelBase.State state = new SkullModelBase.State();
        state.animationPos = renderState.animationProgress;
        state.yRot = renderState.rotationDegrees;
        nodeCollector.submitModel(model,
                state,
                poseStack,
                renderType,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                tintColor,
                null,
                renderState.outlineColor,
                renderState.breakProgress);
        poseStack.popPose();
    }

    private static List<Model> getModels(Holder<HeadType> headType) {
        return headType != null ? headType.value().models() : DEFAULT_MODELS;
    }

    private static Shape getShape(Holder<HeadType> headType) {
        return headType != null ? headType.value().shape() : DEFAULT_SHAPE;
    }

    private static RenderType getRenderType(ModelAndTexture<ModelType> modelAndTexture, float tickCount) {
        BiFunction<ResourceLocation, Float, RenderType> renderTypeGetter = RENDER_TYPES.getOrDefault(modelAndTexture.model(),
                DEFAULT_RENDER_TYPE_GETTER);
        return renderTypeGetter.apply(modelAndTexture.asset().texturePath(), tickCount);
    }
}
