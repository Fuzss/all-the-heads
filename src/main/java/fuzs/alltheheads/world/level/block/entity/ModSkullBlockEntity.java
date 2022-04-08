package fuzs.alltheheads.world.level.block.entity;

import fuzs.alltheheads.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * a custom block entity is necessary as we cannot add new blocks to the vanilla entity
 */
public class ModSkullBlockEntity extends SkullBlockEntity {
    public ModSkullBlockEntity(BlockPos p_155731_, BlockState p_155732_) {
        super(p_155731_, p_155732_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.get();
    }
}
