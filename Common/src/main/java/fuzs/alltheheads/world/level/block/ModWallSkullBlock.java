package fuzs.alltheheads.world.level.block;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModWallSkullBlock extends WallSkullBlock {

    public ModWallSkullBlock(Properties properties) {
        super(ModRegistry.MOB_SKULL_BLOCK_TYPE, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) instanceof ModSkullBlockEntity blockEntity && blockEntity.getHeadType() != null) {
            return blockEntity.getHeadType().value().shape().shapes().get(state.getValue(FACING));
        } else {
            return super.getShape(state, level, pos, context);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ModSkullBlockEntity(blockPos, blockState);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        ItemStack itemStack = super.getCloneItemStack(level, pos, state, includeData);
        if (level.getBlockEntity(pos) instanceof ModSkullBlockEntity blockEntity) {
            itemStack.applyComponents(blockEntity.collectComponents());
        }

        return itemStack;
    }
}
