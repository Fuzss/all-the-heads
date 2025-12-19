package fuzs.alltheheads.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.Util;
import net.minecraft.core.Direction;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public record Shape(double width,
                    double height,
                    double depth,
                    double scale,
                    Int2ObjectMap<VoxelShape> verticalShapes,
                    Map<Direction, VoxelShape> horizontalShapes) {
    public static final Codec<Shape> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.doubleRange(1.0,
                            24.0).fieldOf("width").forGetter(Shape::width),
                    Codec.doubleRange(1.0, 24.0).fieldOf("height").forGetter(Shape::height),
                    Codec.doubleRange(1.0, 24.0).fieldOf("depth").forGetter(Shape::depth),
                    Codec.doubleRange(0.0, Double.MAX_VALUE).optionalFieldOf("scale", 1.0).forGetter(Shape::scale))
            .apply(instance, Shape::new));
    public static final StreamCodec<ByteBuf, Shape> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.DOUBLE,
            Shape::scaledWidth,
            ByteBufCodecs.DOUBLE,
            Shape::scaledHeight,
            ByteBufCodecs.DOUBLE,
            Shape::scaledDepth,
            Shape::new);

    public Shape(double width, double height, double depth) {
        this(width, height, depth, 1.0);
    }

    private Shape(double width, double height, double depth, double scale) {
        this(width,
                height,
                depth,
                scale,
                Util.make(new Int2ObjectArrayMap<>(), (Int2ObjectArrayMap<VoxelShape> map) -> {
                    Map<Direction, VoxelShape> rotatedShapes = Shapes.rotateHorizontal(Block.column(width * scale,
                            depth * scale,
                            0.0,
                            height * scale));
                    for (int i = 0; i <= RotationSegment.getMaxSegmentIndex(); i++) {
                        map.put(i, RotationSegment.convertToDirection(i).map(rotatedShapes::get).orElseGet(() -> {
                            return Block.column(Math.max(width, depth) * scale, 0.0, height * scale);
                        }));
                    }

                }),
                Shapes.rotateHorizontal(Block.boxZ(width * scale,
                        8.0 - height * scale / 2.0,
                        8.0 + height * scale / 2.0,
                        16.0 - depth * scale,
                        16.0)));
    }

    public Shape scale(double scale) {
        return new Shape(this.width, this.height, this.depth, this.scale * scale);
    }

    private double scaledWidth() {
        return this.width * this.scale;
    }

    private double scaledHeight() {
        return this.height * this.scale;
    }

    private double scaledDepth() {
        return this.depth * this.scale;
    }

    public double sizeX(Direction direction) {
        return direction.getAxis() != Direction.Axis.Z ? this.scaledDepth() : this.scaledWidth();
    }

    public double sizeY() {
        return this.scaledHeight();
    }

    public double sizeZ(Direction direction) {
        return direction.getAxis() == Direction.Axis.Z ? this.scaledDepth() : this.scaledWidth();
    }
}
