package fuzs.alltheheads.world.level.block;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.level.block.entity.MobHeadBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MobHeadBlock extends SkullBlock {

    public MobHeadBlock(Properties properties) {
        super(ModRegistry.MOB_SKULL_BLOCK_TYPE, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) instanceof MobHeadBlockEntity blockEntity && blockEntity.getHeadType() != null) {
            return blockEntity.getHeadType().value().shape().shapes().get(Direction.UP);
        } else {
            return super.getShape(state, level, pos, context);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MobHeadBlockEntity(blockPos, blockState);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        ItemStack itemStack = super.getCloneItemStack(level, pos, state, includeData);
        if (level.getBlockEntity(pos) instanceof MobHeadBlockEntity blockEntity) {
            itemStack.applyComponents(blockEntity.collectComponents());
        }

        return itemStack;
    }
}
