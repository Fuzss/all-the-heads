package fuzs.alltheheads.world.item;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;

public class ModSkullBlockItem extends StandingAndWallBlockItem {

    public ModSkullBlockItem(Block block, Block wallBlock, Properties properties) {
        super(block, wallBlock, Direction.DOWN, properties);
    }

    public static ItemStack createHead(Holder<HeadType> headType) {
        ItemStack itemStack = new ItemStack(ModRegistry.MOB_HEAD_ITEM);
        itemStack.set(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value(), headType);
        return itemStack;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Holder<HeadType> headType = itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
        return headType != null ? headType.value().getName(this.descriptionId) : super.getName(itemStack);
    }
}
