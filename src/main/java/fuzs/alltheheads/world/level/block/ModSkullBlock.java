package fuzs.alltheheads.world.level.block;

import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

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

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        LootContext lootcontext = p_60538_.withParameter(LootContextParams.BLOCK_STATE, p_60537_).create(LootContextParamSets.BLOCK);
        LootTable loottable = ((ModSkullType) this.getType()).lootTable.get();
        return loottable.getRandomItems(lootcontext);
    }
}
