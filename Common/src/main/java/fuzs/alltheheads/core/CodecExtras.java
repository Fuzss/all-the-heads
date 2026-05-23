package fuzs.alltheheads.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import fuzs.puzzleslib.api.util.v1.ARGB;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class CodecExtras {
    public static final Codec<Integer> RGB_COLOR_CODEC = Codec.withAlternative(Codec.INT,
            ExtraCodecs.VECTOR3F,
            v -> ARGB.colorFromFloat(1.0F, v.x(), v.y(), v.z()));
    public static final Codec<Integer> ARGB_COLOR_CODEC = Codec.withAlternative(Codec.INT,
            ExtraCodecs.VECTOR4F,
            v -> ARGB.colorFromFloat(v.w(), v.x(), v.y(), v.z()));

    public static <I, E> Codec<E> idResolverCodec(Codec<I> value, Function<I, @Nullable E> fromId, Function<E, @Nullable I> toId) {
        return value.flatXmap(id -> {
            E element = fromId.apply((I) id);
            return element == null ? DataResult.error(() -> "Unknown element id: " + id) : DataResult.success(element);
        }, e -> {
            I id = toId.apply((E) e);
            return id == null ? DataResult.error(() -> "Element with unknown id: " + e) : DataResult.success(id);
        });
    }
}
