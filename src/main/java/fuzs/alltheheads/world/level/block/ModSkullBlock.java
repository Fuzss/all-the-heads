package fuzs.alltheheads.world.level.block;

import com.mojang.math.Vector3f;
import fuzs.alltheheads.registry.ModSkullType;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModSkullBlock extends SkullBlock {
    private final VoxelShape shapeRotationX;
    private final VoxelShape shapeRotationZ;
    private final VoxelShape shapeRotationAny;

    public ModSkullBlock(Type type, Properties p_56319_) {
        super(type, p_56319_);
        Vector3f skullSize = ((ModSkullType) type).getSkullSize();
        this.shapeRotationX = createShape(skullSize.x(), skullSize.y(), skullSize.z(), Direction.Axis.X);
        this.shapeRotationZ = createShape(skullSize.x(), skullSize.y(), skullSize.z(), Direction.Axis.Z);
        this.shapeRotationAny = createShape(skullSize.x(), skullSize.y(), skullSize.z(), null);
    }

    private static VoxelShape createShape(float width, float height, float depth, @Nullable Direction.Axis axis) {
        if (axis == Direction.Axis.X) {
            return Block.box(8.0F - width / 2.0F, 0.0D, 8.0F - depth / 2.0F, 8.0F + width / 2.0F, height, 8.0F + depth / 2.0F);
        } else if (axis == Direction.Axis.Z) {
            return Block.box(8.0F - depth / 2.0F, 0.0D, 8.0F - width / 2.0F, 8.0F + depth / 2.0F, height, 8.0F + width / 2.0F);
        }
        return Block.box(8.0F - Math.max(width, depth) / 2.0F, 0.0D, 8.0F - Math.max(width, depth) / 2.0F, 8.0F + Math.max(width, depth) / 2.0F, height, 8.0F + Math.max(width, depth) / 2.0F);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_56332_, BlockPos p_56333_, CollisionContext p_56334_) {
        int rotation = state.getValue(ROTATION);
        if (rotation % 8 == 0) {
            return this.shapeRotationX;
        } else if ((rotation + 4) % 8 == 0) {
            return this.shapeRotationZ;
        }
        return this.shapeRotationAny;
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
