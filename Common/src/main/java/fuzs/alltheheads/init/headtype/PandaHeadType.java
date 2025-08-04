package fuzs.alltheheads.init.headtype;

import fuzs.alltheheads.advancements.critereon.PandaPredicate;
import fuzs.alltheheads.world.item.component.headtype.HeadType;
import fuzs.alltheheads.world.item.component.headtype.ModelType;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Panda;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.init.HeadTypes.register;

public class PandaHeadType {
    public static final ResourceKey<HeadType> AGGRESSIVE_PANDA = register("panda/aggressive");
    public static final ResourceKey<HeadType> BROWN_PANDA = register("panda/brown");
    public static final ResourceKey<HeadType> LAZY_PANDA = register("panda/lazy");
    public static final ResourceKey<HeadType> PANDA = register("panda");
    public static final ResourceKey<HeadType> PLAYFUL_PANDA = register("panda/playful");
    public static final ResourceKey<HeadType> WEAK_PANDA = register("panda/weak");
    public static final ResourceKey<HeadType> WORRIED_PANDA = register("panda/worried");

    public static void bootstrap(BootstrapContext<HeadType> context) {
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.AGGRESSIVE,
                PandaHeadType.AGGRESSIVE_PANDA,
                "entity/panda/aggressive_panda",
                SoundEvents.PANDA_AGGRESSIVE_AMBIENT);
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.BROWN,
                PandaHeadType.BROWN_PANDA,
                "entity/panda/brown_panda",
                SoundEvents.PANDA_AMBIENT);
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.LAZY,
                PandaHeadType.LAZY_PANDA,
                "entity/panda/lazy_panda",
                SoundEvents.PANDA_AMBIENT);
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.NORMAL,
                PandaHeadType.PANDA,
                "entity/panda/panda",
                SoundEvents.PANDA_AMBIENT);
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.PLAYFUL,
                PandaHeadType.PLAYFUL_PANDA,
                "entity/panda/playful_panda",
                SoundEvents.PANDA_AMBIENT);
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.WEAK,
                PandaHeadType.WEAK_PANDA,
                "entity/panda/weak_panda",
                SoundEvents.PANDA_AMBIENT);
        PandaHeadType.bootstrapPanda(context,
                Panda.Gene.WORRIED,
                PandaHeadType.WORRIED_PANDA,
                "entity/panda/worried_panda",
                SoundEvents.PANDA_WORRIED_AMBIENT);
    }

    private static void bootstrapPanda(BootstrapContext<HeadType> context, Panda.Gene variant, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder(EntityType.PANDA)
                .entityPredicate((EntityPredicate.Builder builder) -> {
                    builder.subPredicate(PandaPredicate.hasVariant(variant));
                })
                .shape(13.0, 10.0, 9.0)
                .scale(10.0 / 13.0)
                .model(ModelType.PANDA, ResourceLocationHelper.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void registerTranslations(BiConsumer<ResourceKey<HeadType>, String> translationConsumer) {
        translationConsumer.accept(AGGRESSIVE_PANDA, "Aggressive Panda Head");
        translationConsumer.accept(BROWN_PANDA, "Brown Panda Head");
        translationConsumer.accept(LAZY_PANDA, "Lazy Panda Head");
        translationConsumer.accept(PANDA, "Panda Head");
        translationConsumer.accept(PLAYFUL_PANDA, "Playful Panda Head");
        translationConsumer.accept(WEAK_PANDA, "Weak Panda Head");
        translationConsumer.accept(WORRIED_PANDA, "Worried Panda Head");
    }
}
