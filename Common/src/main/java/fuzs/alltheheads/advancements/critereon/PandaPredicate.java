package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record PandaPredicate(Optional<Panda.Gene> variant) implements EntitySubPredicate {
    public static final MapCodec<PandaPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Panda.Gene.CODEC.optionalFieldOf(
            "variant").forGetter(PandaPredicate::variant)).apply(instance, PandaPredicate::new));

    @Override
    public MapCodec<PandaPredicate> codec() {
        return ModRegistry.PANDA_ENTITY_SUB_PREDICATE_TYPE.value();
    }

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
