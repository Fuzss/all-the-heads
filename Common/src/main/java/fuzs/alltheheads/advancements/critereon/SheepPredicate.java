package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record SheepPredicate(Optional<DyeColor> color, Optional<Boolean> sheared) implements EntitySubPredicate {
    public static final MapCodec<SheepPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(DyeColor.CODEC.optionalFieldOf(
                            "color").forGetter(SheepPredicate::color),
                    Codec.BOOL.optionalFieldOf("sheared").forGetter(SheepPredicate::sheared))
            .apply(instance, SheepPredicate::new));

    @Override
    public MapCodec<SheepPredicate> codec() {
        return ModRegistry.SHEEP_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Sheep sheep)) {
            return false;
        } else if (this.color.isPresent() && sheep.getColor() != this.color.get()) {
            return false;
        } else if (this.sheared.isPresent() && sheep.isSheared() != this.sheared.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static SheepPredicate forColor(DyeColor dyeColor) {
        return new SheepPredicate(Optional.of(dyeColor), Optional.empty());
    }

    public static SheepPredicate hasWool(boolean hasWool) {
        return new SheepPredicate(Optional.empty(), Optional.of(!hasWool));
    }

    public static SheepPredicate forColor(DyeColor dyeColor, boolean hasWool) {
        return new SheepPredicate(Optional.of(dyeColor), Optional.of(!hasWool));
    }
}
