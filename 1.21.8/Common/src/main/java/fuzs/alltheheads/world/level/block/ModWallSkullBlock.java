package fuzs.alltheheads.world.level.block;

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
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ModWallSkullBlock extends WallSkullBlock {

    public ModWallSkullBlock(SkullBlock.Type type, Properties p_58102_) {
        super(type, p_58102_);
    }

    public static VoxelShape createShape(float width, float height, float depth, Direction direction) {
        return switch (direction) {
            case NORTH -> Block.box(8.0F - width / 2.0F, 8.0F - height / 2.0F, 16.0F - depth, 8.0F + width / 2.0F, 8.0F + height / 2.0F, 16.0F);
            case EAST -> Block.box(0.0F, 8.0F - height / 2.0F, 8.0F - width / 2.0F, depth, 8.0F + height / 2.0F, 8.0F + width / 2.0F);
            case SOUTH -> Block.box(8.0F - width / 2.0F, 8.0F - height / 2.0F, 0.0F, 8.0F + width / 2.0F, 8.0F + height / 2.0F, depth);
            case WEST -> Block.box(16.0F - depth, 8.0F - height / 2.0F, 8.0F - width / 2.0F, 16.0F, 8.0F + height / 2.0F, 8.0F + width / 2.0F);
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_56332_, BlockPos p_56333_, CollisionContext p_56334_) {
        BlockEntity blockentity = p_56332_.getBlockEntity(p_56333_);
        if (blockentity instanceof ModSkullBlockEntity blockEntity) {
            ModSkullType skullType = blockEntity.getSkullType();
            if (skullType != null) {
                return skullType.wallShapes.get(state.getValue(FACING));
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
