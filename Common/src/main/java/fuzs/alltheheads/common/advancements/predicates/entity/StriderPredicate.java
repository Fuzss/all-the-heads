package fuzs.alltheheads.common.advancements.predicates.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record StriderPredicate(Optional<Boolean> cold) implements EntitySubPredicate {
    public static final Codec<StriderPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.BOOL.optionalFieldOf(
            "cold").forGetter(StriderPredicate::cold)).apply(instance, StriderPredicate::new));

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Strider strider)) {
            return false;
        } else if (this.cold.isPresent() && strider.isSuffocating() != this.cold.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static StriderPredicate isCold(boolean cold) {
        return new StriderPredicate(Optional.of(cold));
    }
}
