package fuzs.alltheheads.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.math.Vector3f;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.world.item.ModSkullBlockItem;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ModSkullBlock extends SkullBlock {

    public ModSkullBlock(Type type, Properties p_56319_) {
        super(type, p_56319_);
    }

    public static Map<Integer, VoxelShape> makeShapes(Vector3f skullSize) {
        VoxelShape shapeRotationX = createShape(skullSize.x(), skullSize.y(), skullSize.z(), Direction.Axis.X);
        VoxelShape shapeRotationZ = createShape(skullSize.x(), skullSize.y(), skullSize.z(), Direction.Axis.Z);
        VoxelShape shapeRotationAny = createShape(skullSize.x(), skullSize.y(), skullSize.z(), null);
         return IntStream.range(0, 16).boxed()
                .collect(ImmutableMap.toImmutableMap(Function.identity(), index -> selectShape(index, shapeRotationX, shapeRotationZ, shapeRotationAny)));
    }

    private static VoxelShape createShape(float width, float height, float depth, @Nullable Direction.Axis axis) {
        if (axis == Direction.Axis.X) {
            return Block.box(8.0F - width / 2.0F, 0.0D, 8.0F - depth / 2.0F, 8.0F + width / 2.0F, height, 8.0F + depth / 2.0F);
        } else if (axis == Direction.Axis.Z) {
            return Block.box(8.0F - depth / 2.0F, 0.0D, 8.0F - width / 2.0F, 8.0F + depth / 2.0F, height, 8.0F + width / 2.0F);
        }
        return Block.box(8.0F - Math.max(width, depth) / 2.0F, 0.0D, 8.0F - Math.max(width, depth) / 2.0F, 8.0F + Math.max(width, depth) / 2.0F, height, 8.0F + Math.max(width, depth) / 2.0F);
    }

    private static VoxelShape selectShape(int rotationValue, VoxelShape rotationX, VoxelShape rotationZ, VoxelShape rotationAny) {
        if (rotationValue % 8 == 0) {
            return rotationX;
        } else if ((rotationValue + 4) % 8 == 0) {
            return rotationZ;
        }
        return rotationAny;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_56332_, BlockPos p_56333_, CollisionContext p_56334_) {
        BlockEntity blockentity = p_56332_.getBlockEntity(p_56333_);
        if (blockentity instanceof ModSkullBlockEntity blockEntity) {
            ModSkullType skullType = blockEntity.getSkullType();
            if (skullType != null) {
                return skullType.shapes.get(state.getValue(ROTATION));
            }
        }
        return super.getShape(state, p_56332_, p_56333_, p_56334_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_151996_, BlockState p_151997_) {
        return new ModSkullBlockEntity(p_151996_, p_151997_);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack stack) {
        super.setPlacedBy(level, pos, p_49849_, p_49850_, stack);
        // make sure block entity data is set on client from beginning to avoid having to wait server to sync correct data
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ModSkullBlockEntity skullBlockEntity) {
            ModSkullType skullType = ModSkullBlockItem.readStackSkullType(stack);
            skullBlockEntity.setSkullType(skullType);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
        BlockEntity blockEntity = p_49823_.getBlockEntity(p_49824_);
        if (blockEntity instanceof ModSkullBlockEntity skullBlockEntity) {
            return ModSkullBlockItem.createSkullTypeStack(skullBlockEntity.getSkullType());
        }
        return super.getCloneItemStack(p_49823_, p_49824_, p_49825_);
    }
}
