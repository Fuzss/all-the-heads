package fuzs.alltheheads.world.level.block;

import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModWallSkullBlock extends WallSkullBlock {
    private final VoxelShape shapeFacingNorth;
    private final VoxelShape shapeFacingEast;
    private final VoxelShape shapeFacingSouth;
    private final VoxelShape shapeFacingWest;

    public ModWallSkullBlock(SkullBlock.Type p_58101_, Properties p_58102_) {
        super(p_58101_, p_58102_);
    }

    private static VoxelShape createShape(float width, float height, float depth, Direction direction) {
        return switch (direction) {
            case NORTH ->
        }


        if (axis == Direction.Axis.X) {
            return Block.box(8.0F - width / 2.0F, 0.0D, 8.0F - depth / 2.0F, 8.0F + width / 2.0F, height, 8.0F + depth / 2.0F);
        } else if (axis == Direction.Axis.Z) {
            return Block.box(8.0F - depth / 2.0F, 0.0D, 8.0F - width / 2.0F, 8.0F + depth / 2.0F, height, 8.0F + width / 2.0F);
        }
        return Block.box(8.0F - Math.max(width, depth) / 2.0F, 0.0D, 8.0F - Math.max(width, depth) / 2.0F, 8.0F + Math.max(width, depth) / 2.0F, height, 8.0F + Math.max(width, depth) / 2.0F);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_151996_, BlockState p_151997_) {
        return new ModSkullBlockEntity(p_151996_, p_151997_);
    }

    @Override
    public MutableComponent getName() {
        return ((ModSkullType) this.getType()).getWallName();
    }

    @Override
    public String getDescriptionId() {
        // overriding #getName is mostly enough, but just in case something uses this id provide a localized default
        return "block.alltheheads.mob_wall_head";
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        LootContext lootcontext = p_60538_.withParameter(LootContextParams.BLOCK_STATE, p_60537_).create(LootContextParamSets.BLOCK);
        LootTable loottable = ((ModSkullType) this.getType()).lootTable.get();
        return loottable.getRandomItems(lootcontext);
    }
}
