package fuzs.alltheheads.common.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.common.init.ModRegistry;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record VexPredicate(Optional<Boolean> charging) implements EntitySubPredicate {
    public static final MapCodec<VexPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codec.BOOL.optionalFieldOf(
            "charging").forGetter(VexPredicate::charging)).apply(instance, VexPredicate::new));

    @Override
    public MapCodec<VexPredicate> codec() {
        return ModRegistry.VEX_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Vex vex)) {
            return false;
        } else {
            return this.charging.isPresent() && vex.isCharging() == this.charging.get();
        }
    }

    public static VexPredicate isCharging(boolean charging) {
        return new VexPredicate(Optional.of(charging));
    }
}
