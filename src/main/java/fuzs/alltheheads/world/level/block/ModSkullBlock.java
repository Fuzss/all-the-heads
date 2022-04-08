package fuzs.alltheheads.world.level.block;

import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModSkullBlock extends SkullBlock {
    public ModSkullBlock(Type p_56318_, Properties p_56319_) {
        super(p_56318_, p_56319_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_151996_, BlockState p_151997_) {
        return new ModSkullBlockEntity(p_151996_, p_151997_);
    }

    @Override
    public MutableComponent getName() {
        return ((ModSkullType) this.getType()).getName();
    }

    @Override
    public String getDescriptionId() {
        // overriding #getName is mostly enough, but just in case something uses this id provide a localized default
        return "block.alltheheads.mob_head";
    }
}
