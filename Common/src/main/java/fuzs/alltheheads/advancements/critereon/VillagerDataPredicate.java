package fuzs.alltheheads.advancements.critereon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.init.ModRegistry;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerDataHolder;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public record VillagerDataPredicate(Optional<Holder<VillagerType>> type,
                                    Optional<Holder<VillagerProfession>> profession,
                                    Optional<Integer> level) implements EntitySubPredicate {
    public static final MapCodec<VillagerDataPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.VILLAGER_TYPE.holderByNameCodec()
                    .optionalFieldOf("villager_type")
                    .forGetter(VillagerDataPredicate::type),
            BuiltInRegistries.VILLAGER_PROFESSION.holderByNameCodec()
                    .optionalFieldOf("villager_profession")
                    .forGetter(VillagerDataPredicate::profession),
            Codec.intRange(VillagerData.MIN_VILLAGER_LEVEL, VillagerData.MAX_VILLAGER_LEVEL)
                    .optionalFieldOf("level")
                    .forGetter(VillagerDataPredicate::level)).apply(instance, VillagerDataPredicate::new));

    @Override
    public MapCodec<VillagerDataPredicate> codec() {
        return ModRegistry.VILLAGER_DATA_ENTITY_SUB_PREDICATE_TYPE.value();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position) {
        if (!(entity instanceof VillagerDataHolder dataHolder)) {
            return false;
        } else if (this.type.isPresent() && !dataHolder.getVillagerData().type().is(this.type.get())) {
            return false;
        } else if (this.profession.isPresent() && !dataHolder.getVillagerData()
                .profession()
                .is(this.profession.get())) {
            return false;
        } else if (this.level.isPresent() && dataHolder.getVillagerData().level() != this.level.get()) {
            return false;
        } else {
            return true;
        }
    }

    public static VillagerDataPredicate hasData(Holder<VillagerType> type, Holder<VillagerProfession> profession) {
        return new VillagerDataPredicate(Optional.of(type), Optional.of(profession), Optional.empty());
    }
}
