package fuzs.alltheheads.world.item;

import fuzs.alltheheads.registry.ModRegistry;
import fuzs.alltheheads.resources.ModSkullType;
import fuzs.alltheheads.resources.SkullManager;
import fuzs.alltheheads.world.level.block.entity.ModSkullBlockEntity;
import net.minecraft.core.BlockSource;
import net.minecraft.core.NonNullList;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.Nullable;

public class ModSkullBlockItem extends StandingAndWallBlockItem {
    public ModSkullBlockItem(Block p_43248_, Block p_43249_, Properties p_43250_) {
        super(p_43248_, p_43249_, p_43250_);
        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected ItemStack execute(BlockSource p_123429_, ItemStack p_123430_) {
                this.setSuccess(ArmorItem.dispenseArmor(p_123429_, p_123430_));
                return p_123430_;
            }
        };
        DispenserBlock.registerBehavior(this, dispenseitembehavior);
    }

    @Override
    public Component getName(ItemStack stack) {
        ModSkullType skullType = readStackSkullType(stack);
        if (skullType != null) {
            return skullType.getName();
        }
        return super.getName(stack);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        if (tab == ModRegistry.DEFAULT_CREATIVE_TAB || tab == ModRegistry.VILLAGERS_CREATIVE_TAB) {
            for (ModSkullType skullType : SkullManager.INSTANCE.getAllSkullTypes()) {
                if (skullType.getCreativeModeTab() == tab) {
                    items.add(setSkullType(new ItemStack(this), skullType));
                }
            }
        }
    }

    @Nullable
    public static ModSkullType readStackSkullType(ItemStack stack) {
        if (stack.is(ModRegistry.MOB_HEAD_ITEM.get()) && stack.hasTag()) {
            CompoundTag tag = BlockItem.getBlockEntityData(stack);
            if (tag != null && tag.contains(ModSkullBlockEntity.SKULL_TYPE_KEY, Tag.TAG_STRING)) {
                return SkullManager.INSTANCE.getSkullType(tag.getString(ModSkullBlockEntity.SKULL_TYPE_KEY));
            }
        }
        return null;
    }

    public static ItemStack createSkullTypeStack(@Nullable ModSkullType skullType) {
        return setSkullType(new ItemStack(ModRegistry.MOB_HEAD_ITEM.get()), skullType);
    }

    public static ItemStack setSkullType(ItemStack stack, @Nullable ModSkullType skullType) {
        if (skullType == null) {
            BlockItem.setBlockEntityData(stack, ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.get(), new CompoundTag());
        } else {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putString(ModSkullBlockEntity.SKULL_TYPE_KEY, skullType.getMappingKey());
            BlockItem.setBlockEntityData(stack, ModRegistry.MOB_HEAD_BLOCK_ENTITY_TYPE.get(), compoundTag);
        }
        return stack;
    }
}
