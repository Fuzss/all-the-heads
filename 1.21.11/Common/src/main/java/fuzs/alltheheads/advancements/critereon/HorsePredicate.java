package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import fuzs.puzzleslib.api.util.v1.CodecExtras;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.animal.equine.Markings;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public record HorsePredicate(Optional<Markings> markings) implements EntitySubPredicate {
    public static final MapCodec<HorsePredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    CodecExtras.fromEnum(Markings.class).optionalFieldOf("markings").forGetter(HorsePredicate::markings))
            .apply(instance, HorsePredicate::new));

    @Override
    public MapCodec<HorsePredicate> codec() {
        return ModRegistry.HORSE_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, Vec3 position) {
        if (!(entity instanceof Horse horse)) {
            return false;
        } else if (this.markings.isPresent() && horse.getMarkings() != this.markings.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static HorsePredicate forMarkings(Markings markings) {
        return new HorsePredicate(Optional.of(markings));
    }
}
