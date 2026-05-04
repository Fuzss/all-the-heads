package fuzs.alltheheads.common.world.item.component.headtype;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

public record Loot(Optional<ResourceKey<LootTable>> lootTable, boolean chargedCreeperDrop) {
    public static final MapCodec<Loot> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(ResourceKey.codec(
                            Registries.LOOT_TABLE).optionalFieldOf("loot_table").forGetter(Loot::lootTable),
                    Codec.BOOL.optionalFieldOf("charged_creeper_drop", true).forGetter(Loot::chargedCreeperDrop))
            .apply(instance, Loot::new));
    public static final StreamCodec<ByteBuf, Loot> STREAM_CODEC = StreamCodec.composite(ResourceKey.streamCodec(
                    Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
            Loot::lootTable,
            ByteBufCodecs.BOOL,
            Loot::chargedCreeperDrop,
            Loot::new);

    public Loot(ResourceKey<LootTable> resourceKey) {
        this(Optional.of(resourceKey), true);
    }
}
