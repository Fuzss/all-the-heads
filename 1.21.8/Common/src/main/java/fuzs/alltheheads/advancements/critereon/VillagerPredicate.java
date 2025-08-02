package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record VillagerPredicate(Optional<Holder<VillagerType>> type,
                                Optional<Holder<VillagerProfession>> profession,
                                Optional<Integer> level) implements EntitySubPredicate {
    public static final MapCodec<VillagerPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.VILLAGER_TYPE.holderByNameCodec()
                    .optionalFieldOf("villager_type")
                    .forGetter(VillagerPredicate::type),
            BuiltInRegistries.VILLAGER_PROFESSION.holderByNameCodec()
                    .optionalFieldOf("profession")
                    .forGetter(VillagerPredicate::profession),
            Codec.intRange(VillagerData.MIN_VILLAGER_LEVEL, VillagerData.MAX_VILLAGER_LEVEL)
                    .optionalFieldOf("level")
                    .forGetter(VillagerPredicate::level)).apply(instance, VillagerPredicate::new));

    @Override
    public MapCodec<VillagerPredicate> codec() {
        return ModRegistry.VILLAGER_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof Villager villager)) {
            return false;
        } else if (this.type.isPresent() && !villager.getVillagerData().type().is(this.type.get())) {
            return false;
        } else if (this.profession.isPresent() && !villager.getVillagerData().profession().is(this.profession.get())) {
            return false;
        } else if (this.level.isPresent() && villager.getVillagerData().level() != this.level.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static VillagerPredicate hasData(Holder<VillagerType> type, Holder<VillagerProfession> profession) {
        return new VillagerPredicate(Optional.of(type), Optional.of(profession), Optional.empty());
    }
}
