package fuzs.alltheheads.common.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.fish.TropicalFish;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record TropicalFishPredicate(Optional<TropicalFish.Pattern> pattern,
                                    Optional<DyeColor> baseColor,
                                    Optional<DyeColor> patternColor) implements EntitySubPredicate {
    public static final Codec<TropicalFishPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    TropicalFish.Pattern.CODEC.optionalFieldOf("pattern").forGetter(TropicalFishPredicate::pattern),
                    DyeColor.CODEC.optionalFieldOf("base_color").forGetter(TropicalFishPredicate::baseColor),
                    DyeColor.CODEC.optionalFieldOf("pattern_color").forGetter(TropicalFishPredicate::patternColor))
            .apply(instance, TropicalFishPredicate::new));

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof TropicalFish tropicalFish)) {
            return false;
        } else if (this.pattern.isPresent() && tropicalFish.getPattern() != this.pattern.get()) {
            return false;
        } else if (this.baseColor.isPresent() && tropicalFish.getBaseColor() != this.baseColor.get()) {
            return false;
        } else if (this.patternColor.isPresent() && tropicalFish.getPatternColor() != this.patternColor.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static TropicalFishPredicate hasVariant(TropicalFish.Variant variant) {
        return hasVariant(variant.pattern(), variant.baseColor(), variant.patternColor());
    }

    public static TropicalFishPredicate hasVariant(TropicalFish.Pattern pattern, DyeColor baseColor, DyeColor patternColor) {
        return new TropicalFishPredicate(Optional.of(pattern), Optional.of(baseColor), Optional.of(patternColor));
    }
}
