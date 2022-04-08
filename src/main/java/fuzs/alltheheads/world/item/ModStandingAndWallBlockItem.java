package fuzs.alltheheads.world.item;

import fuzs.alltheheads.registry.ModSkullType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Block;

public class ModStandingAndWallBlockItem extends StandingAndWallBlockItem {
    public ModStandingAndWallBlockItem(Block p_43248_, Block p_43249_, Properties p_43250_) {
        super(p_43248_, p_43249_, p_43250_);
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return ((ModSkullType) ((AbstractSkullBlock) this.getBlock()).getType()).getName();
    }
}
