package fuzs.alltheheads.world.item.component.headtype;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.alltheheads.AllTheHeads;
import fuzs.alltheheads.core.CodecExtras;
import fuzs.alltheheads.core.LateBoundIdMapper;
import fuzs.puzzleslib.api.util.v1.ARGB;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public interface Color {
    LateBoundIdMapper<ResourceLocation, MapCodec<? extends Color>> ID_MAPPER = Util.make(new LateBoundIdMapper<>(),
            (LateBoundIdMapper<ResourceLocation, MapCodec<? extends Color>> idMapper) -> {
                idMapper.put(AllTheHeads.id("constant"), Constant.CODEC);
                idMapper.put(AllTheHeads.id("dye"), Dye.CODEC);
                idMapper.put(AllTheHeads.id("sheep"), Sheep.CODEC);
                idMapper.put(AllTheHeads.id("rainbow"), Rainbow.CODEC);
            });
    Codec<Color> CODEC = ID_MAPPER.codec(ResourceLocation.CODEC).dispatch(Color::type, Function.identity());

    MapCodec<? extends Color> type();

    int getColor(float tickCount);

    record Constant(int value) implements Color {
        public static final MapCodec<Constant> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        CodecExtras.RGB_COLOR_CODEC.fieldOf("value").forGetter(Constant::value))
                .apply(instance, Constant::new));

        public Constant(int value) {
            this.value = ARGB.opaque(value);
        }

        @Override
        public MapCodec<? extends Color> type() {
            return CODEC;
        }

        @Override
        public int getColor(float tickCount) {
            return this.value;
        }
    }

    record Dye(DyeColor dye) implements Color {
        public static final MapCodec<Dye> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(DyeColor.CODEC.fieldOf(
                "dye").forGetter(Dye::dye)).apply(instance, Dye::new));

        @Override
        public MapCodec<? extends Color> type() {
            return CODEC;
        }

        @Override
        public int getColor(float tickCount) {
            return this.dye.getTextureDiffuseColor();
        }
    }

    /**
     * Copied from client-only {@code ColorLerper} class.
     */
    record Sheep(DyeColor dye) implements Color {
        public static final MapCodec<Sheep> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(DyeColor.CODEC.fieldOf(
                "dye").forGetter(Sheep::dye)).apply(instance, Sheep::new));
        static final Map<DyeColor, Integer> COLOR_BY_DYE = Arrays.stream(DyeColor.values())
                .collect(Maps.<DyeColor, DyeColor, Integer>toImmutableEnumMap(Function.identity(),
                        Sheep::getModifiedColor));

        @Override
        public MapCodec<? extends Color> type() {
            return CODEC;
        }

        @Override
        public int getColor(float tickCount) {
            return COLOR_BY_DYE.get(this.dye);
        }

        static int getModifiedColor(DyeColor color) {
            return getModifiedColor(color, 0.75F);
        }

        static int getModifiedColor(DyeColor color, float brightness) {
            if (color == DyeColor.WHITE) {
                return -1644826;
            } else {
                int i = color.getTextureDiffuseColor();
                return ARGB.color(255,
                        Mth.floor(ARGB.red(i) * brightness),
                        Mth.floor(ARGB.green(i) * brightness),
                        Mth.floor(ARGB.blue(i) * brightness));
            }
        }
    }

    /**
     * Copied from client-only {@code ColorLerper} class.
     */
    record Rainbow() implements Color {
        public static final MapCodec<Rainbow> CODEC = MapCodec.unit(new Rainbow());
        private static final DyeColor[] COLORS = DyeColor.values();
        private static final int COLOR_DURATION = 25;

        @Override
        public MapCodec<? extends Color> type() {
            return CODEC;
        }

        @Override
        public int getColor(float tickCount) {
            return getLerpedColor(tickCount);
        }

        static int getLerpedColor(float time) {
            int i = Mth.floor(time);
            int j = i / COLOR_DURATION;
            int k = COLORS.length;
            int l = j % k;
            int m = (j + 1) % k;
            float f = (i % COLOR_DURATION + Mth.frac(time)) / COLOR_DURATION;
            int n = Sheep.COLOR_BY_DYE.get(COLORS[l]);
            int o = Sheep.COLOR_BY_DYE.get(COLORS[m]);
            return ARGB.lerp(f, n, o);
        }
    }
}
