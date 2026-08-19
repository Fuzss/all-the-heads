package fuzs.alltheheads.common.advancements.predicates.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record GhastPredicate(Optional<Boolean> charging) implements EntitySubPredicate {
    public static final Codec<GhastPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.BOOL.optionalFieldOf(
            "charging").forGetter(GhastPredicate::charging)).apply(instance, GhastPredicate::new));

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Ghast ghast)) {
            return false;
        } else if (this.charging.isPresent() && ghast.isCharging() != this.charging.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static GhastPredicate isCharging(boolean charging) {
        return new GhastPredicate(Optional.of(charging));
    }
}
