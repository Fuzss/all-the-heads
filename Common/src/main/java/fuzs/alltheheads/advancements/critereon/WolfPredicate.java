package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record WolfPredicate(Optional<Boolean> angry, Optional<Boolean> tame) implements EntitySubPredicate {
    public static final MapCodec<WolfPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codec.BOOL.optionalFieldOf(
                    "angry").forGetter(WolfPredicate::angry), Codec.BOOL.optionalFieldOf("tame").forGetter(WolfPredicate::tame))
            .apply(instance, WolfPredicate::new));

    @Override
    public MapCodec<WolfPredicate> codec() {
        return ModRegistry.WOLF_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Wolf wolf)) {
            return false;
        } else if (this.angry.isPresent() && wolf.isAngry() != this.angry.get()) {
            return false;
        } else if (this.tame.isPresent() && wolf.isTame() != this.tame.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static WolfPredicate isAngry() {
        return new WolfPredicate(Optional.of(true), Optional.empty());
    }

    public static WolfPredicate isTame() {
        return new WolfPredicate(Optional.empty(), Optional.of(true));
    }
}
