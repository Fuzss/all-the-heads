package fuzs.alltheheads.common.handler;

import fuzs.alltheheads.common.init.ModRegistry;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.puzzleslib.common.api.event.v1.data.MutableDouble;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class HeadBehaviorHandler {

    public static void onCalculateLivingVisibility(LivingEntity livingEntity, @Nullable Entity lookingEntity, MutableDouble visibilityPercentage) {
        if (lookingEntity != null) {
            ItemStack itemStack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
            Holder<HeadType> headType = itemStack.get(ModRegistry.HEAD_TYPE_DATA_COMPONENT_TYPE.value());
            if (headType != null && headType.value().mobDisguise() && headType.value().matches(lookingEntity)) {
                visibilityPercentage.mapAsDouble((double value) -> value * 0.5);
            }
        }
    }
}
