package fuzs.alltheheads.common.world.level.block.entity;

import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.block.v1.entity.TickingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class MobHeadBlockEntity extends SkullBlockEntity implements TickingBlockEntity {
    public static final String TAG_HEAD_TYPE = "head_type";

    @Nullable
    private Holder<HeadType> headType;
    public int tickCount;

    public MobHeadBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    @Override
    public void clientTick(Level level, BlockPos blockPos, BlockState blockState) {
        this.tickCount++;
        animation(level, blockPos, blockState, this);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.value();
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.storeNullable(TAG_HEAD_TYPE, HeadType.CODEC, this.headType);
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.headType = valueInput.read(TAG_HEAD_TYPE, HeadType.CODEC).orElse(null);
    }

    @Nullable
    public Holder<HeadType> getHeadType() {
        return this.headType;
    }

    @Override
    public @Nullable Identifier getNoteBlockSound() {
        Identifier noteBlockSound = super.getNoteBlockSound();
        if (noteBlockSound != null) {
            return noteBlockSound;
        } else if (this.headType != null) {
            return this.headType.value().noteBlockSound().map(Holder::value).map(SoundEvent::location).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        super.applyImplicitComponents(componentGetter);
        this.headType = componentGetter.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(), this.headType);
    }

    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        super.removeComponentsFromTag(output);
        output.discard(TAG_HEAD_TYPE);
    }
}
