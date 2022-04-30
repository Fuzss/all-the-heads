package fuzs.alltheheads.world.level.block.entity;

import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.resources.SkullManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ModSkullBlockEntity extends SkullBlockEntity {
    public static final String SKULL_TYPE_KEY = "SkullType";

    @Nullable
    private ModSkullType skullType;

    public ModSkullBlockEntity(BlockPos p_155731_, BlockState p_155732_) {
        super(p_155731_, p_155732_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.get();
    }

    @Override
    protected void saveAdditional(CompoundTag p_187518_) {
        super.saveAdditional(p_187518_);
        if (this.skullType != null) {
            p_187518_.putString(SKULL_TYPE_KEY, this.skullType.getMappingKey());
        }
    }

    @Override
    public void load(CompoundTag p_155745_) {
        super.load(p_155745_);
        if (p_155745_.contains(SKULL_TYPE_KEY, Tag.TAG_STRING)) {
            this.skullType = SkullManager.INSTANCE.getSkullType(p_155745_.getString(SKULL_TYPE_KEY));
        }
    }

    @Nullable
    public ModSkullType getSkullType() {
        return this.skullType;
    }

    public void setSkullType(@Nullable ModSkullType skullType) {
        // vanilla does it like that for player heads, not sure if necessary
        synchronized (this) {
            this.skullType = skullType;
        }
    }
}
