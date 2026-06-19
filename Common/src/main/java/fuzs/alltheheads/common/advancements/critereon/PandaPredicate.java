package fuzs.alltheheads.common.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.panda.Panda;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record PandaPredicate(Optional<Panda.Gene> variant) implements EntitySubPredicate {
    public static final Codec<PandaPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(Panda.Gene.CODEC.optionalFieldOf(
            "variant").forGetter(PandaPredicate::variant)).apply(instance, PandaPredicate::new));

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Panda panda)) {
            return false;
        } else if (this.variant.isPresent() && panda.getVariant() != this.variant.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static PandaPredicate hasVariant(Panda.Gene variant) {
        return new PandaPredicate(Optional.of(variant));
    }
}
