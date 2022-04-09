package fuzs.alltheheads.world.level.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.math.Vector3f;
import fuzs.alltheheads.resources.SkullType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModWallSkullBlock extends WallSkullBlock {
    private final Map<Direction, VoxelShape> shapes;

    public ModWallSkullBlock(SkullBlock.Type type, Properties p_58102_) {
        super(type, p_58102_);
        Vector3f skullSize = ((SkullType) type).getSkullSize();
        this.shapes = Maps.newEnumMap(Direction.Plane.HORIZONTAL.stream()
                .collect(ImmutableMap.toImmutableMap(Function.identity(), direction -> createShape(skullSize.x(), skullSize.y(), skullSize.z(), direction))));
    }

    private static VoxelShape createShape(float width, float height, float depth, Direction direction) {
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
        return this.shapes.get(state.getValue(FACING));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_151996_, BlockState p_151997_) {
        return new ModSkullBlockEntity(p_151996_, p_151997_);
    }

    @Override
    public MutableComponent getName() {
        return ((SkullType) this.getType()).getWallName();
    }

    @Override
    public String getDescriptionId() {
        // overriding #getName is mostly enough, but just in case something uses this id provide a localized default
        return "block.alltheheads.mob_wall_head";
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        LootContext lootcontext = p_60538_.withParameter(LootContextParams.BLOCK_STATE, p_60537_).create(LootContextParamSets.BLOCK);
        LootTable loottable = ((SkullType) this.getType()).lootTable.get();
        return loottable.getRandomItems(lootcontext);
    }
}
