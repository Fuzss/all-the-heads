package fuzs.alltheheads.common.advancements.predicates.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record CreeperPredicate(Optional<Boolean> powered) implements EntitySubPredicate {
    public static final Codec<CreeperPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.BOOL.optionalFieldOf(
            "powered").forGetter(CreeperPredicate::powered)).apply(instance, CreeperPredicate::new));

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Creeper creeper)) {
            return false;
        } else if (this.powered.isPresent() && creeper.isPowered() != this.powered.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static CreeperPredicate isPowered(boolean powered) {
        return new CreeperPredicate(Optional.of(powered));
    }
}
