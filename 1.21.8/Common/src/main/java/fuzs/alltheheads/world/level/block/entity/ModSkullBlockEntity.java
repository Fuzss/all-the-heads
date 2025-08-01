package fuzs.alltheheads.world.level.block.entity;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class ModSkullBlockEntity extends SkullBlockEntity {
    public static final String TAG_HEAD_TYPE = "head_type";

    @Nullable
    private Holder<HeadType> headType;

    public ModSkullBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
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
    public @Nullable ResourceLocation getNoteBlockSound() {
        return this.headType != null ?
                this.headType.value().noteBlockSound().map(Holder::value).map(SoundEvent::location).orElse(null) : null;
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
