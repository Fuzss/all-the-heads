package fuzs.alltheheads.common.client.renderer.blockentity.state;

import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.Model;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import fuzs.alltheheads.common.world.item.component.headtype.Shape;
import net.minecraft.client.renderer.blockentity.state.SkullBlockRenderState;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MobHeadRenderState extends SkullBlockRenderState {
    /**
     * Directly supplying both client asset resource locations is only possible as long as it is never serialized, which
     * never happens here.
     */
    public static final List<Model> DEFAULT_MODELS = List.of(new Model(new ModelAndTexture<>(ModelType.DEFAULT,
            new ClientAsset.ResourceTexture(DefaultPlayerSkin.getDefaultTexture(),
                    DefaultPlayerSkin.getDefaultTexture())), Optional.empty(), Optional.empty()));
    public static final Shape DEFAULT_SHAPE = new Shape(8.0, 8.0, 8.0);

    public List<Model> models = DEFAULT_MODELS;
    public float time;
    public int outlineColor;

    public MobHeadRenderState() {
        // NO-OP
    }

    public MobHeadRenderState(@Nullable Holder<HeadType> headType, float animationProgress, float time, int lightCoords, int outlineColor) {
        this.models = getModels(headType);
        this.animationProgress = animationProgress;
        this.time = time;
        this.lightCoords = lightCoords;
        this.outlineColor = outlineColor;
    }

    /**
     * @see net.minecraft.client.renderer.blockentity.SkullBlockRenderer#createWallTransformation(Direction)
     */
    public static Transformation createWallTransformation(@Nullable Holder<HeadType> headType, Direction direction) {
        Shape shape = getShape(headType);
        double offsetX = 8.0 - direction.getStepX() * (16.0 - shape.sizeX(direction)) / 2.0;
        double offsetY = (16.0 - shape.sizeY()) / 2.0;
        double offsetZ = 8.0 - direction.getStepZ() * (16.0 - shape.sizeZ(direction)) / 2.0;
        return new Transformation(new Vector3f((float) offsetX / 16.0F,
                (float) offsetY / 16.0F,
                (float) offsetZ / 16.0F),
                Axis.YP.rotationDegrees(-direction.getOpposite().toYRot()),
                new Vector3f((float) -shape.scale(), (float) -shape.scale(), (float) shape.scale()),
                null);
    }

    public static Transformation createGroundTransformation(@Nullable Holder<HeadType> headType, boolean guiOffset) {
        return createGroundTransformation(headType, 8, guiOffset);
    }

    /**
     * @see net.minecraft.client.renderer.blockentity.SkullBlockRenderer#createGroundTransformation(int)
     */
    public static Transformation createGroundTransformation(@Nullable Holder<HeadType> headType, int segment, boolean guiOffset) {
        Shape shape = getShape(headType);
        double offsetY = (16.0 - shape.sizeY()) / 2.0;
        return new Transformation(new Matrix4f().translation(0.5F,
                        guiOffset ? ((float) offsetY - 10.0F / 3.0F) / 16.0F : 0.0F,
                        0.5F)
                .rotate(Axis.YP.rotationDegrees(-RotationSegment.convertToDegrees(segment)))
                .scale((float) -shape.scale(), (float) -shape.scale(), (float) shape.scale()));
    }

    public static List<Model> getModels(@Nullable Holder<HeadType> headType) {
        return headType != null ? headType.value().models() : DEFAULT_MODELS;
    }

    private static Shape getShape(@Nullable Holder<HeadType> headType) {
        return headType != null ? headType.value().shape() : DEFAULT_SHAPE;
    }
}
