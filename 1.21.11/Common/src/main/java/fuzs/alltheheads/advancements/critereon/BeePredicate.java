package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record BeePredicate(Optional<Boolean> angry, Optional<Boolean> nectar) implements EntitySubPredicate {
    public static final MapCodec<BeePredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codec.BOOL.optionalFieldOf(
                            "angry").forGetter(BeePredicate::angry),
                    Codec.BOOL.optionalFieldOf("has_nectar").forGetter(BeePredicate::nectar))
            .apply(instance, BeePredicate::new));

    @Override
    public MapCodec<BeePredicate> codec() {
        return ModRegistry.BEE_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Bee bee)) {
            return false;
        } else if (this.angry.isPresent() && bee.isAngry() != this.angry.get()) {
            return false;
        } else if (this.nectar.isPresent() && bee.hasNectar() != this.nectar.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static BeePredicate isAngry() {
        return new BeePredicate(Optional.of(true), Optional.empty());
    }

    public static BeePredicate hasNectar() {
        return new BeePredicate(Optional.empty(), Optional.of(true));
    }
}
