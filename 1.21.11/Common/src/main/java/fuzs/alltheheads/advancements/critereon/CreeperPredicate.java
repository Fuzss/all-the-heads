package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record CreeperPredicate(Optional<Boolean> powered) implements EntitySubPredicate {
    public static final MapCodec<CreeperPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codec.BOOL.optionalFieldOf(
            "powered").forGetter(CreeperPredicate::powered)).apply(instance, CreeperPredicate::new));

    @Override
    public MapCodec<CreeperPredicate> codec() {
        return ModRegistry.CREEPER_ENTITY_SUB_PREDICATE_TYPE.value();
    }

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
