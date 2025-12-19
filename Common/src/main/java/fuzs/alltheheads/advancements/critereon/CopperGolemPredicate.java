package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record CopperGolemPredicate(Optional<WeatheringCopper.WeatherState> state) implements EntitySubPredicate {
    public static final MapCodec<CopperGolemPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    WeatheringCopper.WeatherState.CODEC.optionalFieldOf("state").forGetter(CopperGolemPredicate::state))
            .apply(instance, CopperGolemPredicate::new));

    @Override
    public MapCodec<CopperGolemPredicate> codec() {
        return ModRegistry.COPPER_GOLEM_ENTITY_SUB_PREDICATE_TYPE.value();
    }

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
