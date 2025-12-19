package fuzs.alltheheads.world.level.block;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.level.block.entity.MobHeadBlockEntity;
import fuzs.puzzleslib.api.block.v1.entity.TickingEntityBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class MobHeadSkullBlock extends WallSkullBlock implements TickingEntityBlock<MobHeadBlockEntity> {

    public MobHeadSkullBlock(Properties properties) {
        super(ModRegistry.MOB_SKULL_BLOCK_TYPE, properties);
    }

    @Override
    public BlockEntityType<? extends MobHeadBlockEntity> getBlockEntityType() {
        return ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.value();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) instanceof MobHeadBlockEntity blockEntity && blockEntity.getHeadType() != null) {
            return blockEntity.getHeadType().value().shape().horizontalShapes().get(state.getValue(FACING));
        } else {
            return super.getShape(state, level, pos, context);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return TickingEntityBlock.super.newBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return TickingEntityBlock.super.getTicker(level, state, blockEntityType);
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
