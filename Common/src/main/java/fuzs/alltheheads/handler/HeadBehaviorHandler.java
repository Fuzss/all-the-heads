package fuzs.alltheheads.handler;

import fuzs.alltheheads.init.ModRegistry;
import fuzs.alltheheads.world.item.component.HeadType;
import fuzs.puzzleslib.api.event.v1.data.MutableDouble;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class HeadBehaviorHandler {

    public static void onLivingVisibility(LivingEntity livingEntity, @Nullable Entity lookingEntity, MutableDouble visibilityPercentage) {
        if (lookingEntity != null) {
            ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
            Holder<HeadType> headType = itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
            if (headType != null && headType.value().mobDisguise() && headType.value().matches(lookingEntity)) {
                visibilityPercentage.mapDouble((double value) -> value * 0.5);
            }
        }
    }
}
