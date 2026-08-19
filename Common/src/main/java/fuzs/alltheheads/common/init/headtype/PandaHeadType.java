package fuzs.alltheheads.common.init.headtype;

import fuzs.alltheheads.common.advancements.predicates.entity.PandaPredicate;
import fuzs.alltheheads.common.world.item.component.headtype.HeadType;
import fuzs.alltheheads.common.world.item.component.headtype.ModelType;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.panda.Panda;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

import static fuzs.alltheheads.common.init.HeadTypes.bootstrap;
import static fuzs.alltheheads.common.init.HeadTypes.register;

public class PandaHeadType {
    public static final ResourceKey<HeadType> AGGRESSIVE_PANDA = register("panda/aggressive");
    public static final ResourceKey<HeadType> BROWN_PANDA = register("panda/brown");
    public static final ResourceKey<HeadType> LAZY_PANDA = register("panda/lazy");
    public static final ResourceKey<HeadType> PANDA = register("panda");
    public static final ResourceKey<HeadType> PLAYFUL_PANDA = register("panda/playful");
    public static final ResourceKey<HeadType> WEAK_PANDA = register("panda/weak");
    public static final ResourceKey<HeadType> WORRIED_PANDA = register("panda/worried");

    public static void bootstrapHeadTypes(BootstrapContext<HeadType> context) {
        bootstrapPanda(context,
                AGGRESSIVE_PANDA,
                "entity/panda/panda_aggressive",
                SoundEvents.PANDA_AGGRESSIVE_AMBIENT);
        bootstrapPanda(context, BROWN_PANDA, "entity/panda/panda_brown", SoundEvents.PANDA_AMBIENT);
        bootstrapPanda(context, LAZY_PANDA, "entity/panda/panda_lazy", SoundEvents.PANDA_AMBIENT);
        bootstrapPanda(context, PANDA, "entity/panda/panda", SoundEvents.PANDA_AMBIENT);
        bootstrapPanda(context, PLAYFUL_PANDA, "entity/panda/panda_playful", SoundEvents.PANDA_AMBIENT);
        bootstrapPanda(context, WEAK_PANDA, "entity/panda/panda_weak", SoundEvents.PANDA_SNEEZE);
        bootstrapPanda(context, WORRIED_PANDA, "entity/panda/panda_worried", SoundEvents.PANDA_WORRIED_AMBIENT);
    }

    private static void bootstrapPanda(BootstrapContext<HeadType> context, ResourceKey<HeadType> resourceKey, String textureLocation, SoundEvent noteBlockSound) {
        HeadType.builder()
                .shape(13.0, 10.0, 9.0)
                .scale(10.0 / 13.0)
                .model(ModelType.PANDA, Identifier.withDefaultNamespace(textureLocation))
                .noteBlockSound(noteBlockSound)
                .build(context, resourceKey);
    }

    public static void bootstrapLootItemConditions(BootstrapContext<LootItemCondition> context) {
        bootstrapPanda(context, Panda.Gene.AGGRESSIVE, AGGRESSIVE_PANDA);
        bootstrapPanda(context, Panda.Gene.BROWN, BROWN_PANDA);
        bootstrapPanda(context, Panda.Gene.LAZY, LAZY_PANDA);
        bootstrapPanda(context, Panda.Gene.NORMAL, PANDA);
        bootstrapPanda(context, Panda.Gene.PLAYFUL, PLAYFUL_PANDA);
        bootstrapPanda(context, Panda.Gene.WEAK, WEAK_PANDA);
        bootstrapPanda(context, Panda.Gene.WORRIED, WORRIED_PANDA);
    }

    private static void bootstrapPanda(BootstrapContext<LootItemCondition> context, Panda.Gene variant, ResourceKey<HeadType> resourceKey) {
        bootstrap(context, resourceKey, EntityTypes.PANDA, (EntityPredicate.Builder builder) -> {
            builder.put(PandaPredicate.CODEC, PandaPredicate.hasVariant(variant));
        });
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
