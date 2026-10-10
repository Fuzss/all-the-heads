package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record AllOfEntityPredicate(List<EntitySubPredicate> predicates) implements EntitySubPredicate {
    public static final MapCodec<AllOfEntityPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntitySubPredicate.CODEC.listOf().fieldOf("predicates").forGetter(AllOfEntityPredicate::predicates))
            .apply(instance, AllOfEntityPredicate::new));

    @Override
    public MapCodec<? extends EntitySubPredicate> codec() {
        return ModRegistry.ALL_OF_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        for (EntitySubPredicate predicate : this.predicates) {
            if (!predicate.matches(entity, level, position)) {
                return false;
            }
        }

        return true;
    }

    public static AllOfEntityPredicate of(EntitySubPredicate... predicates) {
        return new AllOfEntityPredicate(List.of(predicates));
    }
}
