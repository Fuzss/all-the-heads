package fuzs.alltheheads.world.level.block.entity;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.api.block.v1.entity.TickingBlockEntity;
import fuzs.puzzleslib.api.util.v1.CompoundTagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MobHeadBlockEntity extends SkullBlockEntity implements TickingBlockEntity {
    public static final String TAG_HEAD_TYPE = "head_type";

    @Nullable
    private Holder<HeadType> headType;
    public int tickCount;

    public MobHeadBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    @Override
    public void clientTick() {
        this.tickCount++;
        animation(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.value();
    }

    @Override
    protected void saveAdditional(CompoundTag valueOutput, HolderLookup.Provider registries) {
        super.saveAdditional(valueOutput, registries);
        RegistryOps<Tag> registryOps = registries.createSerializationContext(NbtOps.INSTANCE);
        CompoundTagHelper.storeNullable(valueOutput, TAG_HEAD_TYPE, HeadType.CODEC, registryOps, this.headType);
    }

    @Override
    protected void loadAdditional(CompoundTag valueInput, HolderLookup.Provider registries) {
        super.loadAdditional(valueInput, registries);
        RegistryOps<Tag> registryOps = registries.createSerializationContext(NbtOps.INSTANCE);
        this.headType = CompoundTagHelper.read(valueInput, TAG_HEAD_TYPE, HeadType.CODEC, registryOps).orElse(null);
    }

    @Nullable
    public Holder<HeadType> getHeadType() {
        return this.headType;
    }

    @Override
    public @Nullable ResourceLocation getNoteBlockSound() {
        ResourceLocation noteBlockSound = super.getNoteBlockSound();
        if (noteBlockSound != null) {
            return noteBlockSound;
        } else if (this.headType != null) {
            return this.headType.value().noteBlockSound().map(Holder::value).map(SoundEvent::getLocation).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentGetter) {
        super.applyImplicitComponents(componentGetter);
        this.headType = componentGetter.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(), this.headType);
    }

    @Override
    public void removeComponentsFromTag(CompoundTag output) {
        super.removeComponentsFromTag(output);
        output.remove(TAG_HEAD_TYPE);
    }
}
