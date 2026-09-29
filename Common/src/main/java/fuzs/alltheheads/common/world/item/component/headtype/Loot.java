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
                                    Registries.LOOT_TABLE)
                            .xmap(Optional::of, Optional::orElseThrow)
                            .fieldOf("loot_table")
                            .forGetter(Loot::lootTable),
                    Codec.BOOL.optionalFieldOf("charged_creeper_drop", true).forGetter(Loot::chargedCreeperDrop))
            .apply(instance, Loot::new));
    public static final StreamCodec<ByteBuf, Loot> STREAM_CODEC = StreamCodec.composite(ResourceKey.streamCodec(
                    Registries.LOOT_TABLE).apply(ByteBufCodecs::optional),
            Loot::lootTable,
            ByteBufCodecs.BOOL,
            Loot::chargedCreeperDrop,
            Loot::new);
    public static final Loot EMPTY = new Loot(Optional.empty());

    public Loot(ResourceKey<LootTable> lootTable) {
        this(Optional.of(lootTable));
    }

    private Loot(Optional<ResourceKey<LootTable>> lootTable) {
        this(lootTable, true);
    }
}
