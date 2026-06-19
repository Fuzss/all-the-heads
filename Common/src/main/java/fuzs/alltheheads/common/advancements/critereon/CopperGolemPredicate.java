package fuzs.alltheheads.common.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record CopperGolemPredicate(Optional<WeatheringCopper.WeatherState> state) implements EntitySubPredicate {
    public static final Codec<CopperGolemPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    WeatheringCopper.WeatherState.CODEC.optionalFieldOf("state").forGetter(CopperGolemPredicate::state))
            .apply(instance, CopperGolemPredicate::new));

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof CopperGolem copperGolem)) {
            return false;
        } else if (this.state.isPresent() && copperGolem.getWeatherState() != this.state.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static CopperGolemPredicate hasState(WeatheringCopper.WeatherState state) {
        return new CopperGolemPredicate(Optional.of(state));
    }
}
