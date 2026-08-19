package fuzs.alltheheads.common.world.item;

import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;

public class MobHeadItem extends StandingAndWallBlockItem {

    public MobHeadItem(Block block, Block wallBlock, Properties properties) {
        super(block, wallBlock, Direction.DOWN, properties);
    }

    public static ItemStack createItem(HolderLookup.Provider context, ResourceKey<HeadType> key) {
        return createItem(context.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY).getOrThrow(key));
    }

    public static ItemStack createItem(Holder<HeadType> holder) {
        ItemStack item = new ItemStack(ModRegistry.MOB_HEAD_ITEM);
        item.set(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(), holder);
        return item;
    }

    public static ItemStackTemplate createTemplate(HolderLookup.Provider context, ResourceKey<HeadType> key) {
        return createTemplate(context.lookupOrThrow(ModRegistry.HEAD_REGISTRY_KEY).getOrThrow(key));
    }

    public static ItemStackTemplate createTemplate(Holder<HeadType> holder) {
        return new ItemStackTemplate(ModRegistry.MOB_HEAD_ITEM.value(),
                DataComponentPatch.builder().set(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(), holder).build());
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Holder<HeadType> holder = itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
        return holder != null ? holder.value().getName(this.descriptionId) : super.getName(itemStack);
    }
}
