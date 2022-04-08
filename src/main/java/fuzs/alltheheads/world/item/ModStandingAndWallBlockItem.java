package fuzs.alltheheads.world.item;

import fuzs.alltheheads.registry.ModSkullType;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;

public class ModStandingAndWallBlockItem extends StandingAndWallBlockItem {
    public ModStandingAndWallBlockItem(Block p_43248_, Block p_43249_, Properties p_43250_) {
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
    public Component getName(ItemStack p_41458_) {
        return ((ModSkullType) ((AbstractSkullBlock) this.getBlock()).getType()).getName();
    }
}
